package com.netbull.shop.manage.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.common.util.HttpXmlUtil;
import com.netbull.shop.manage.weixin.service.DoctorService;
import com.netbull.shop.manage.weixin.service.FreeTimeService;
import com.netbull.shop.manage.weixin.service.JPushService;
import com.netbull.shop.manage.weixin.service.MultiMediaService;
import com.netbull.shop.manage.weixin.service.PatientService;
import com.netbull.shop.manage.weixin.service.ReturnVisitService;
import com.netbull.shop.manage.weixin.service.SubscribeService;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;

@Controller
public class SubscribeController extends AbstractController{

	private static final Logger logger = Logger.getLogger("controlerLog");
	private final Integer restPeriod=5;//休息时间，以分钟计
	
	@Autowired
	private SubscribeService subscribeService;
	@Autowired
	private FreeTimeService freeTimeService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private PatientService patientService;
	@Resource
	private JPushService jPushService;
	@Resource
	private MultiMediaService multiMediaService;
	@Autowired
	private ReturnVisitService returnVisitService;
	
	/**
	 * 取消预约
	 * @param request 参数：orderID-预约ID
	 * 						alert-消息内容
	 * 						type-操作人类型：0-医生；1-患者
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/anon/cancelSubscribe", produces="application/json;charset=utf-8")
	@ResponseBody
	public String cancelSubscribe(HttpServletRequest request){
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class cancelSubscribe method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("取消预约，参数：" + requestMap.toString());
			}
			String type=StringUtil.getString(requestMap.get("type"));
			Map subscribe=subscribeService.querySubscribeByParams(requestMap);
			
			if (logger.isDebugEnabled()) {
				logger.debug("subscribe：" + subscribe.toString());
			}
			
			if(NullUtil.isNull(subscribe)){
				resultMap.put("code", Constants.FAIL_2);
				resultMap.put("msg", Constants.FAIL_MSG_8);
				return JsonUtils.obj2Json(resultMap);
			}
			requestMap.put("state", "-1");
			subscribeService.cancelOrder(requestMap);
			
			String aliases=null;
			if(StringTools.equals("0", type)){
				aliases = StringUtil.getString(subscribe.get("patientID"));
				requestMap.put("sender", StringUtil.getString(subscribe.get("doctorID")));
			}else if(StringTools.equals("1", type)){
				aliases = StringUtil.getString(subscribe.get("doctorID"));
				requestMap.put("sender", StringUtil.getString(subscribe.get("patientID")));
			}
			requestMap.put("senderType", type);
			requestMap.put("aliases", aliases);
			String alert = !NullUtil.isNull(requestMap.get("alert"))?requestMap.get("alert"):null;
			
			if(HttpXmlUtil.sendNoticeMsg(aliases, alert)){
				requestMap.put("status", "0");
			}else{
				requestMap.put("status", "1");
			}
			jPushService.savePushLogInfo(requestMap);
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 获取预约列表
	 * @param request 参数	oprType:操作人类型(0-医生；1-患者)
	 * 						pageId:分页的页码
	 * 						showCount:每页显示数目
	 * 						
	 * 						1.oprType=0时，带上以下参数
	 * 									doctorID:医生ID
	 * 									type:0-最近预约；1-历史预约
	 * 						2.oprType=1时，带上以下参数
	 * 									patientID:患者ID
	 * 						
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/anon/querySubscribeList", produces="application/json;charset=utf-8")
	@ResponseBody
	public String querySubscribeList(HttpServletRequest request){
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String,Object> operatorMap=new HashMap<String, Object>();
		List<Map> subscribeList=new ArrayList<Map>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class querySubscribeList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("获取患者预约信息，参数：" + requestMap.toString());
			}
			
			String showCount = !NullUtil.isNull(requestMap.get("showCount"))?requestMap.get("showCount"):"10";
			
			Map paramter = new HashMap();
			paramter.put("start", Integer.parseInt(requestMap.get("pageId"))*Integer.parseInt(showCount));
			paramter.put("limit", Integer.parseInt(showCount));
			
			String operator=requestMap.get("oprType");
			if(StringUtil.isEmpty(operator)){
				
				if(StringTools.equals("0", operator)){
					String type=StringUtil.getString(requestMap.get("type"));
					if(StringTools.equals("1", type)){
						paramter.put("state", "1");
					}else{
						paramter.put("state", "0");
					}
					String doctorID=StringUtil.getString(requestMap.get("doctorID"));
					paramter.put("doctorID", doctorID);
					paramter.put("nowDate", getNowDate());
					
					operatorMap=doctorService.queryDoctorDetail(paramter);
					List<Map> _subscribeList=subscribeService.querySubscribeList(paramter);
					for(Map map:_subscribeList){
						Map visitTimeMap=returnVisitService.queryLastVisitTime(map);
						map.put("returnVisitTime", !NullUtil.isNull(visitTimeMap)?visitTimeMap.get("returnVisitTime"):null);
						subscribeList.add(map);
					}
				
				}else if(StringTools.equals("1", operator)){
					String patientID=StringUtil.getString(requestMap.get("patientID"));
					paramter.put("patientID", patientID);
					
					String type=StringUtil.getString(requestMap.get("type"));
					if(StringTools.equals("0", type)){
						paramter.put("state", "0");
					}else{
						paramter.put("state", "1");
					}
					
					operatorMap=patientService.queryPatientDetail(paramter);
					subscribeList=subscribeService.querySubscribeList(paramter);
				}
			}
			
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("operatorDetail", operatorMap);
			resultMap.put("subscribeList", subscribeList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 预约
	 * @param request 
	 * 		请求参数中包含:	doctorID-医生ID
	 * 						patientID-患者ID
	 * 						freeID-医生值班记录ID
	 * 					 	timeCount-预约时长(单位:分钟)
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/anon/subscribe",produces="application/json;charset=utf-8")
	@ResponseBody
	public String subscribe(HttpServletRequest request){
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryDoctorList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("获取专家预约信息，参数：" + requestMap.toString());
			}
			Map freeTimeMap=freeTimeService.queryFreeTime(requestMap);
			
			Integer timeCount=Integer.parseInt(requestMap.get("timeCount"));
			
			Map parameter=new HashMap();
			parameter.put("doctorID", Integer.parseInt(requestMap.get("doctorID")));
			parameter.put("patientID", Integer.parseInt(requestMap.get("patientID")));
			parameter.put("isAddPrescription", requestMap.get("isAddPrescription"));
			parameter.put("paymethod", requestMap.get("paymethod"));
			parameter.put("purpose", requestMap.get("purpose"));
			parameter.put("type", requestMap.get("type"));
			parameter.put("price", requestMap.get("price"));
			
			Integer weekFlag=Integer.parseInt(StringUtil.getString(freeTimeMap.get("weekFlag")));
			Integer weekNum=Integer.parseInt(StringUtil.getString(freeTimeMap.get("weekNum")));
			parameter.put("orderDate", createOrderDate(weekFlag,weekNum));
			
			requestMap.put("orderDate", createOrderDate(weekFlag,weekNum));
			Map subscribeMap=subscribeService.querySubscribe(requestMap);
			
			String subTime1=null;
			String subTime2=null;
			boolean fullFlag=false;//预约是否已满,true为满，false未满
			if(NullUtil.isNull(subscribeMap)){
				subTime1=convertTime(StringUtil.getString(freeTimeMap.get("startTime")),0);
				subTime2=convertTime(subTime1, timeCount);
			}else{
				//最后预约结束时间是否超过值班结束时间
				if(compare(StringUtil.getString(subscribeMap.get("endTime")),
						StringUtil.getString(freeTimeMap.get("endTime")))){
					fullFlag=true;
				
				//最后预约结束时间距值班结束时间差是否还可以满足客户预约
				//不满足则需判断是否延时
				}else if(getTimeLag(StringUtil.getString(freeTimeMap.get("endTime")),
							StringUtil.getString(subscribeMap.get("endTime")))<(restPeriod+timeCount)){
					//是否延时
					if(StringTools.equals(StringUtil.getString(freeTimeMap.get("isLater")), "1")){
						subTime1=convertTime(StringUtil.getString(subscribeMap.get("endTime")),restPeriod);
						subTime2=convertTime(subTime1, timeCount);
					}else{
						fullFlag=true;
					}
					
				}else{
					subTime1=convertTime(StringUtil.getString(subscribeMap.get("endTime")),restPeriod);
					subTime2=convertTime(subTime1, timeCount);
				}
			}
			parameter.put("startTime", subTime1);
			parameter.put("endTime", subTime2);
			
			if(fullFlag){
				resultMap.put("code", Constants.SUCCESS);
				resultMap.put("msg", Constants.FULL_MSG);
			}else{
				subscribeService.saveSubscribe(parameter);
				resultMap.put("code", Constants.SUCCESS);
				resultMap.put("msg", Constants.SUCCESS_MSG);
				resultMap.put("orderDetail", parameter);
			}
			
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/anon/callAuthForClient", produces="application/json;charset=utf-8")
	@ResponseBody
	public String callAuthForClient(HttpServletRequest request){
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class callAuthForClient method.");
			}
			
			Long count = subscribeService.callAuth(requestMap);
			if(/*!NullUtil.isNull(count) && count > 0*/true){
				if(!NullUtil.isNull(requestMap.get("fromUserId")) && !NullUtil.isNull(requestMap.get("fromUserType"))){
					requestMap.put("userId", StringUtil.getString(requestMap.get("fromUserId")));
					requestMap.put("userType",StringUtil.getString(requestMap.get("fromUserType")));
					resultMap.put("selfAccount", createSubAccount(requestMap));
				}
				if(!NullUtil.isNull(requestMap.get("toUserId")) && !NullUtil.isNull(requestMap.get("toUserType"))){
					requestMap.put("userId", StringUtil.getString(requestMap.get("toUserId")));
					requestMap.put("userType",StringUtil.getString(requestMap.get("toUserType")));
					resultMap.put("callAccount", createSubAccount(requestMap));
				}
				resultMap.put("duration", /*count*/180);
				resultMap.put("code", Constants.SUCCESS);
				resultMap.put("msg", Constants.SUCCESS_MSG);
			}else{
				resultMap.put("duration", 0);
				resultMap.put("code", Constants.FAIL_SUBSCRIBE_0);
				resultMap.put("msg", Constants.FAIL_SUBSCRIBE_MSG_0);
			}
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	
	/**
	 * 修改订单；
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/anon/updateSubscribe", produces="application/json;charset=utf-8")
	@ResponseBody
	public String updateSubscribe(HttpServletRequest request){
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class updateSubscribe method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("参数：" + requestMap.toString());
			}
			
			subscribeService.modifyOrder(requestMap);
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/anon/querySubscribeDetail", produces="application/json;charset=utf-8")
	@ResponseBody
	public String querySubscribeDetail(HttpServletRequest request){
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class updateSubscribe method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("参数：" + requestMap.toString());
			}
			
			Map subscribeDetail = subscribeService.querySubscribeByParams(requestMap);
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("patientDetail", patientService.queryPatientDetail(subscribeDetail));
			resultMap.put("subscribeDetail", subscribeDetail);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	@SuppressWarnings("rawtypes")
	private Map createSubAccount(Map<String,String> requestMap){
		Map multMedia = multiMediaService.queryMultMedia(requestMap);
		if(NullUtil.isNull(multMedia)){
			CCPRestSDK restAPI = new CCPRestSDK();
			restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
			restAPI.setAccount(SID, TOKEN);// 初始化主帐号名称和主帐号令牌
			restAPI.setAppId(APPID);// 初始化应用ID
			Map<String,Object> result = restAPI.createSubAccount(StringTools.getBillno());
			Map responseMap = this.constructCreateSubAccountParamter(requestMap, result);
			Integer count = multiMediaService.modifyMultMedia(responseMap);
			if(count <= 0){
				multiMediaService.saveMultMediaInfo(responseMap);
			}
			return responseMap;
		}else{
			return multMedia;
		}
	}
	
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	private Map constructCreateSubAccountParamter(Map<String,String> requestMap,Map<String,Object> resultMap){
		if(NullUtil.isNull(resultMap) || NullUtil.isNull(requestMap)){
			return null;
		}
		
		Map data = (Map)resultMap.get("data");
		Map SubAccount = (Map)data.get("SubAccount");
		SubAccount.put("userId", StringUtil.getString(requestMap.get("userId")));
		SubAccount.put("userType", StringUtil.getString(requestMap.get("userType")));
		SubAccount.put("status", "0");
		return SubAccount;
	}
	
	//转换时间
	@SuppressWarnings("static-access")
	private static String convertTime(String time,Integer period){
		String[] timeArr=time.split(":");
		Integer hour=Integer.parseInt(timeArr[0]);
		Integer minute=Integer.parseInt(timeArr[1]);
		
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
		
		cal.set(cal.YEAR, cal.MONTH, cal.DAY_OF_MONTH, hour, minute+period);
		Date date=cal.getTime();
		return sdf.format(date);
	}
	
	//比较两个时间大小
	private static boolean compare(String time1,String time2){
		return getCalTimeMill(getIntArray(time1)[0],getIntArray(time1)[1])
				>= getCalTimeMill(getIntArray(time2)[0],getIntArray(time2)[1]);
	}
	
	@SuppressWarnings("static-access")
	private static long getCalTimeMill(Integer hour,Integer minute){
		Calendar cal = Calendar.getInstance();
		cal.set(cal.YEAR, cal.MONTH, cal.DAY_OF_MONTH, hour, minute);
		return cal.getTimeInMillis();
	}
	
	private static Integer[] getIntArray(String timestring){
		String[] timeArray=timestring.split(":");
		return new Integer[]{Integer.parseInt(timeArray[0]),Integer.parseInt(timeArray[1])};
	}
	
	//获取时间差,以分钟计
	private static Integer getTimeLag(String time1,String time2){
		long millis1=getCalTimeMill(getIntArray(time1)[0],getIntArray(time1)[1]);
		long millis2=getCalTimeMill(getIntArray(time2)[0],getIntArray(time2)[1]);
		return (int)(millis1-millis2)/(60*1000)+1;
	}
	
	//获取预约日期
	private static String createOrderDate(int weekFlag,int weekNum){
		Calendar cal = Calendar.getInstance();
		
		if(cal.get(Calendar.DAY_OF_WEEK)==1){//当日是否是周日
			if(weekFlag==0){//本周
//				if(weekNum==7){//是否获取本周第七天
//					cal.set(Calendar.DAY_OF_WEEK, 1);
//				}else{
//					cal.add(Calendar.WEEK_OF_YEAR, -1);
//					cal.set(Calendar.DAY_OF_WEEK, weekNum+1);
//				}
				if(weekNum==7){//是否获取下周第七天
					cal.add(Calendar.WEEK_OF_YEAR, 1);
					cal.set(Calendar.DAY_OF_WEEK, 1);
				}else{
					cal.set(Calendar.DAY_OF_WEEK, weekNum+1);
				}
			}else if(weekFlag==1){//下周
//				if(weekNum==7){//是否获取下周第七天
//					cal.add(Calendar.WEEK_OF_YEAR, 1);
//					cal.set(Calendar.DAY_OF_WEEK, 1);
//				}else{
//					cal.set(Calendar.DAY_OF_WEEK, weekNum+1);
//				}
				if(weekNum==7){//是否获取下周第七天
					cal.add(Calendar.WEEK_OF_YEAR, 2);
					cal.set(Calendar.DAY_OF_WEEK, 1);
				}else{
					cal.set(Calendar.DAY_OF_WEEK, weekNum+1);
				}
			}
		}else{
			if(weekFlag==0){
//				if(weekNum==7){
//					cal.add(Calendar.WEEK_OF_YEAR, 1);
//					cal.set(Calendar.DAY_OF_WEEK, 1);
//				}else{
//					cal.set(Calendar.DAY_OF_WEEK, weekNum+1);
//				}
				if(weekNum==7){
					cal.add(Calendar.WEEK_OF_YEAR, 2);
					cal.set(Calendar.DAY_OF_WEEK, 1);
				}else{
					cal.add(Calendar.WEEK_OF_YEAR, 1);
					cal.set(Calendar.DAY_OF_WEEK, weekNum+1);
				}
			}else if(weekFlag==1){
//				if(weekNum==7){
//					cal.add(Calendar.WEEK_OF_YEAR, 2);
//					cal.set(Calendar.DAY_OF_WEEK, 1);
//				}else{
//					cal.add(Calendar.WEEK_OF_YEAR, 1);
//					cal.set(Calendar.DAY_OF_WEEK, weekNum+1);
//				}
				if(weekNum==7){
					cal.add(Calendar.WEEK_OF_YEAR, 3);
					cal.set(Calendar.DAY_OF_WEEK, 1);
				}else{
					cal.add(Calendar.WEEK_OF_YEAR, 2);
					cal.set(Calendar.DAY_OF_WEEK, weekNum+1);
				}
			}
		}
		Date date=cal.getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	//获取当前日期
	private static String getNowDate(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
}

package com.netbull.shop.manage.controller;

import java.io.IOException;
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

import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.weixin.service.DoctorService;
import com.netbull.shop.manage.weixin.service.FreeTimeService;
import com.netbull.shop.manage.weixin.service.JPushService;
import com.netbull.shop.manage.weixin.service.PlusService;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;

@Controller
public class PlusController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	@Autowired
	private PlusService plusService;
	@Autowired
	private JPushService jPushService;
	@Autowired
	private FreeTimeService freeTimeService;
	@Resource
	private DoctorService doctorService;
	
	/**
	 * 
	 * @param request 参数doctorID-医生ID
	 * 						patientID-患者ID
	 * 						freeTimeID-医生门诊值班记录ID
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/applyPlus", produces="application/json;charset=utf-8")
	@ResponseBody
	public String applyPlus(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class applyPlus method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("申请加号，参数：" + requestMap.toString());
			}
			Map jpushMap=new HashMap();
			
			Map parameter=new HashMap();
			if(StringUtil.isEmpty(requestMap.get("patientID"))&&StringUtil.isEmpty(requestMap.get("doctorID"))
					&&StringUtil.isEmpty(requestMap.get("freeTimeID"))){
				
				Map param=new HashMap();
				param.put("freeID", requestMap.get("freeTimeID"));
				Map freeTimeMap=freeTimeService.queryFreeTime(param);
				if(!NullUtil.isNull(freeTimeMap)){
					String plusTime=createPlusDate(Integer.parseInt(StringUtil.getString(freeTimeMap.get("weekFlag"))),
							Integer.parseInt(StringUtil.getString(freeTimeMap.get("weekNum"))));
					
					parameter.put("doctorID", requestMap.get("doctorID"));
					parameter.put("patientID", requestMap.get("patientID"));
					parameter.put("plusTime", plusTime);
					parameter.put("isPay", "0");
					plusService.savePlus(parameter);
					
					jpushMap.put("aliases", requestMap.get("doctorID"));
					jpushMap.put("aliasesType", 0);
					jpushMap.put("sender", requestMap.get("patientID"));
					jpushMap.put("senderType", 1);
					jpushMap.put("alert", "有患者向您申请加号。");
					jPushService.savePushLogInfo(jpushMap);
					
				}else{
					resultMap.put("code", Constants.FAIL);
					resultMap.put("msg", Constants.FAIL_MSG);
					return JsonUtils.obj2Json(resultMap);
				}
			}else{
				resultMap.put("code", Constants.FAIL);
				resultMap.put("msg", Constants.FAIL_MSG);
				return JsonUtils.obj2Json(resultMap);
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 
	 * @param request参数plusID-加号ID
	 * 					patientID-患者ID
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/queryPlusDetail", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryPlusDetail(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryPlusDetail method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查看医生是否确认，加号是否成功，参数：" + requestMap.toString());
			}
			Map plusDetail=new HashMap();
			if(StringUtil.isEmpty(requestMap.get("plusID"))
					&&StringUtil.isEmpty(requestMap.get("patientID"))){
				Map parameter=new HashMap();
				parameter.put("plusID", requestMap.get("plusID"));
				parameter.put("patientID", requestMap.get("patientID"));
				plusDetail=plusService.queryPlusDetail(parameter);
			}
			Map doctorDetail = doctorService.queryDoctorDetail(plusDetail);
			plusDetail.put("doctorDetail", doctorDetail);
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("plusDetail", plusDetail);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 
	 * @param request 参数doctorID:医生ID
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/queryPlusList", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryPlusList(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryPlusList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查看加号列表，参数：" + requestMap.toString());
			}
			List<Map> plusList=new ArrayList<Map>();
			String doctorID=StringUtil.getString(requestMap.get("doctorID"));
			if(StringUtil.isEmpty(doctorID)){
				Map parameter=new HashMap();
				parameter.put("doctorID", Integer.parseInt(doctorID));
				plusList=plusService.queryPlusList(parameter);
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("plusList", plusList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 
	 * @param request参数doctorID-医生ID
	 * 					patientID-患者ID
	 * 					plusID-加号ID
	 * 					status-状态（1-同意；-1-不同意）
	 * 					alert-医生附加消息
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/confirmPlus", produces="application/json;charset=utf-8")
	@ResponseBody
	public String updatePlusStatus(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class updatePlusStatus method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("提供医生是否确认患者加号，反馈患者,参数：" + requestMap.toString());
			}
			Map parameter=new HashMap();
			Map jpushMap=new HashMap();
			if(StringUtil.isEmpty(requestMap.get("plusID"))&&StringUtil.isEmpty(requestMap.get("doctorID"))
					&&StringUtil.isEmpty(requestMap.get("patientID"))&&StringUtil.isEmpty(requestMap.get("status"))){
				
				parameter.put("plusID", requestMap.get("plusID"));
				parameter.put("doctorID", requestMap.get("doctorID"));
				
				jpushMap.put("aliases", requestMap.get("patientID"));
				jpushMap.put("aliasesType", 1);
				jpushMap.put("sender", requestMap.get("doctorID"));
				jpushMap.put("senderType", 0);
				
				if(StringTools.equals(requestMap.get("status"), "1")){
					parameter.put("status", 1);
					parameter.put("isPay", "1");
					plusService.updatePlus(parameter);
					
					jpushMap.put("alert", StringUtil.isEmpty(requestMap.get("alert"))?requestMap.get("alert"):"医生同意了您的加号申请。");
					jPushService.savePushLogInfo(jpushMap);
				}else if(StringTools.equals(requestMap.get("status"), "-1")){
					parameter.put("status", -1);
					plusService.updatePlus(parameter);
					
					jpushMap.put("alert", StringUtil.isEmpty(requestMap.get("alert"))?requestMap.get("alert"):"医生拒绝了您的加号申请。");
					jPushService.savePushLogInfo(jpushMap);
				}else{
					resultMap.put("code", Constants.FAIL);
					resultMap.put("msg", Constants.FAIL_MSG);
					return JsonUtils.obj2Json(resultMap);
				}
				
			}else{
				resultMap.put("code", Constants.FAIL);
				resultMap.put("msg", Constants.FAIL_MSG);
				return JsonUtils.obj2Json(resultMap);
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	//获取加号日期
	private static String createPlusDate(int weekFlag,int weekNum){
		Calendar cal = Calendar.getInstance();
		
		if(cal.get(Calendar.DAY_OF_WEEK)==1){
			if(weekFlag==0){
				if(weekNum==7){
					cal.set(Calendar.DAY_OF_WEEK, 1);
				}else{
					cal.add(Calendar.WEEK_OF_YEAR, -1);
					cal.set(Calendar.DAY_OF_WEEK, weekNum+1);
				}
			}else if(weekFlag==1){
				if(weekNum==7){
					cal.add(Calendar.WEEK_OF_YEAR, 1);
					cal.set(Calendar.DAY_OF_WEEK, 1);
				}else{
					cal.set(Calendar.DAY_OF_WEEK, weekNum+1);
				}
			}
		}else{
			if(weekFlag==0){
				if(weekNum==7){
					cal.add(Calendar.WEEK_OF_YEAR, 1);
					cal.set(Calendar.DAY_OF_WEEK, 1);
				}else{
					cal.set(Calendar.DAY_OF_WEEK, weekNum+1);
				}
			}else if(weekFlag==1){
				if(weekNum==7){
					cal.add(Calendar.WEEK_OF_YEAR, 2);
					cal.set(Calendar.DAY_OF_WEEK, 1);
				}else{
					cal.add(Calendar.WEEK_OF_YEAR, 1);
					cal.set(Calendar.DAY_OF_WEEK, weekNum+1);
				}
			}
		}
		Date date=cal.getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
}

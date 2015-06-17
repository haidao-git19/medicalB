package com.netbull.shop.manage.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.netbull.shop.manage.weixin.service.FreeTimeService;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;

@Controller
public class FreeTimeController implements Comparator<Map>{

	private static final Logger logger = Logger.getLogger("controlerLog");
	
	@Autowired
	private FreeTimeService freeTimeService;
	
	/**
	 * 
	 * @param request
	 * @return 参数：doctorID---医生ID
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/anon/queryFreeTimeList", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryFreeTimeList(HttpServletRequest request){
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String,Object> freeTimeMap=new HashMap<String, Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryFreeTimeList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查询医生值班表信息，参数：" + requestMap.toString());
			}
			String doctorID=requestMap.get("doctorID");
			List<Map> thisFreeTimeList=null;
			List<Map> nextFreeTimeList=null;
			if(StringUtil.isEmpty(doctorID)){
				requestMap.put("weekFlag", "0");
				thisFreeTimeList= freeTimeService.queryFreeTimeList(requestMap);
				
				requestMap.put("weekFlag", "1");
				nextFreeTimeList= freeTimeService.queryFreeTimeList(requestMap);
			}
			
			Map<Integer, Calendar> dates = new HashMap<Integer, Calendar>();
			for(int i=0; i<7; i++) {
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DAY_OF_YEAR, i);
				dates.put(DutyHelper.getDayOfWeek(c), c);
			}
			
			for(Integer weekNum : dates.keySet()) {
				boolean isMat = false;
				for(Map map : thisFreeTimeList) {
					Integer wn = (Integer)map.get("weekNum");
					if(weekNum == wn) {
						isMat = true;
						map.put("dutyDate", DutyHelper.format(dates.get(weekNum).getTime()));
						map.put("sort", weekNum);
					}
				}
				
				if(!isMat) {
					Map m = new HashMap();
					m.put("dutyDate", DutyHelper.format(dates.get(weekNum).getTime()));
					m.put("sort", weekNum);
					thisFreeTimeList.add(m);
				}
			}
			
			ValueComparator c =  new ValueComparator();  
			Collections.sort(thisFreeTimeList, c);
			
			freeTimeMap.put("this", thisFreeTimeList);
			freeTimeMap.put("next", nextFreeTimeList);
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("freeTimeList", freeTimeMap);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 查询未来七天医生值班表
	 * @param request 参数：doctorID-医生ID type-值班表类型(0:门诊值班表;1:咨询值班表)
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/anon/queryDutyTimeList")
	@ResponseBody
	public String queryDutyTimeList(HttpServletRequest request){
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<Map> dutyList = new ArrayList<Map>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryFreeTimeList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查询医生值班表信息，参数：" + requestMap.toString());
			}
			if(!NullUtil.isNull(requestMap.get("doctorID"))&&!NullUtil.isNull(requestMap.get("type"))){
				Map parameter=new HashMap();
				parameter.put("weekNum", currentTime().get("currentDayOfWeek"));
				parameter.put("doctorID", requestMap.get("doctorID"));
				parameter.put("type", requestMap.get("type"));
				
				List<Map> freetimeList=freeTimeService.queryDutyTimeList(parameter);
				for(Map ft:freetimeList){
					if(getCovertTime(ft).compareTo(StringUtil.getString(currentTime().get("currentDateTime")))>0
							&&getCovertDateStr(ft).compareTo(get7thDayDateStr())<0){
						dutyList.add(ft);
					}
				}
				Collections.sort(dutyList, this);
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("dutyList", dutyList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	protected static Map<String,Object> currentTime() {
		Calendar cal=Calendar.getInstance();
		Map<String,Object> map=new HashMap<String, Object>();
		
		if(cal.get(Calendar.DAY_OF_WEEK)==1){
			map.put("currentDayOfWeek",7);
		}else{
			map.put("currentDayOfWeek",cal.get(Calendar.DAY_OF_WEEK)-1);
		}
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String currentDateTime=sdf.format(cal.getTime());
		map.put("currentDateTime", currentDateTime);
		return map;
	}
	
	protected static String get7thDayDateStr(){
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 6);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dateStr=sdf.format(cal.getTime());
		return dateStr;
	}
	
	protected static String getCovertDateStr(Map ft) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=sdf.parse(getCovertTime(ft));
		return sdf.format(date);
	}
	
	protected static String getCovertTime(Map ft){
		Integer weekFlag=Integer.parseInt(StringUtil.getString(ft.get("weekFlag")));
		Integer weekNum=Integer.parseInt(StringUtil.getString(ft.get("weekNum")));
		String startTime=StringUtil.getString(ft.get("startTime"));
			
		Calendar cal = Calendar.getInstance();
			
			if(cal.get(Calendar.DAY_OF_WEEK)==1){//当日是否是周日
				if(weekFlag==0){//本周
					if(weekNum==7){//是否获取本周第七天
						cal.set(Calendar.DAY_OF_WEEK, 1);
					}else{
						cal.add(Calendar.WEEK_OF_YEAR, -1);
						cal.set(Calendar.DAY_OF_WEEK, weekNum+1);
					}
				}else if(weekFlag==1){//下周
					if(weekNum==7){//是否获取下周第七天
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
		return sdf.format(date)+" "+startTime;
	}

	@Override
	public int compare(Map o1, Map o2) {
		if(StringUtil.getString(o1.get("weekFlag")).compareTo(StringUtil.getString(o2.get("weekFlag")))>0){
			return 1;
		}else if(StringTools.equals(StringUtil.getString(o1.get("weekFlag")), 
				StringUtil.getString(o2.get("weekFlag")))){
			
			if(StringUtil.getString(o1.get("weekNum")).compareTo(StringUtil.getString(o2.get("weekNum")))>0){
				return 1;
			}else if(StringTools.equals(StringUtil.getString(o1.get("weekNum")), 
					StringUtil.getString(o2.get("weekNum")))){
				
				if(StringUtil.getString(o1.get("startTime")).compareTo(StringUtil.getString(o2.get("startTime")))>0){
					return 1;
				}else{
					return 0;
				}
			}else{
				return 0;
			}
			
		}else{
			return 0;
		}
	}
	
	/*	
	public static void main(String[] args) throws Exception {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Date date = f.parse("2015-12-31");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		System.out.println(c.getTime());
	}
	*/
	
}

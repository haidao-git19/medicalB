package com.netbull.shop.statistics.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.DateUtil;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.hospital.service.HospitalService;
import com.netbull.shop.statistics.service.StatisticsService;
import com.netbull.shop.util.DataTableUtils;
import com.netbull.shop.util.RequestUtils;

@Controller
public class StatisticsController {

	@Autowired
	private StatisticsService statisticsService;
	@Autowired
	private HospitalService hospitalService;
	
	@RequestMapping(value="/statistics/registerUserList")
	public String registerUserList(HttpServletRequest request){
		return "statistics/registerUserList";
	}
	
	@RequestMapping(value="/statistics/queryRegisterUserPage")
	@ResponseBody
	public  Map<String,Object> queryRegisterUserPage(Integer sEcho, Integer iColumns, Integer iDisplayStart, 
			Integer iDisplayLength,HttpServletRequest request) {
		
		Map<String, String> requestMap =  RequestUtils.parameterToMap(request);
		
		Page page = statisticsService.queryRegisterUserPage(iDisplayStart, iDisplayLength, requestMap);
		 List<List<?>> aaData = new ArrayList<List<?>>();
		 Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho , page.getTotal());
		  for (Object o : page.getResult()) {
				Map map = (Map)o;
				List<Object> list = new ArrayList<Object>();
				list.add(map.get("patientID"));
				list.add(map.get("patientName"));
				list.add(map.get("nickName"));
				list.add(map.get("patientAge"));
				list.add(map.get("patientSex"));
				list.add(map.get("contactPhone"));
				
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String createTime=null;
				if(!NullUtil.isNull(map.get("createTime"))){
					createTime=sdf.format(map.get("createTime"));
				}
				list.add(createTime);
				aaData.add(list);
			}
		  
			resultMap.put("aaData", aaData);
		   return resultMap;
	}
	
	@RequestMapping(value="/statistics/shopOrderList")
	public String shopOrderList(HttpServletRequest request){
		return "statistics/shopOrderList";
	}
	
	@RequestMapping(value="/statistics/queryShopOrderPage")
	@ResponseBody
	public  Map<String,Object> queryShopOrderPage(Integer sEcho, Integer iColumns, Integer iDisplayStart, 
			Integer iDisplayLength,HttpServletRequest request) {
		
		Map<String, String> requestMap =  RequestUtils.parameterToMap(request);
		
		Page page = statisticsService.queryShopOrderPage(iDisplayStart, iDisplayLength, requestMap);
		 List<List<?>> aaData = new ArrayList<List<?>>();
		 Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho , page.getTotal());
		  for (Object o : page.getResult()) {
				Map map = (Map)o;
				List<Object> list = new ArrayList<Object>();
				list.add(map.get("id"));
				list.add(map.get("shopName"));
				list.add(map.get("orderNumber"));
				list.add(map.get("name"));
				list.add(map.get("price"));
				list.add(map.get("amount"));
				Float price=Float.parseFloat(StringUtil.getString(map.get("price")));
				Integer amount=Integer.parseInt(StringUtil.getString(map.get("amount")));
				list.add(price*amount);
				
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String createTime=null;
				if(!NullUtil.isNull(map.get("createTime"))){
					createTime=sdf.format(map.get("createTime"));
				}
				list.add(createTime);
				aaData.add(list);
			}
		  
			resultMap.put("aaData", aaData);
		   return resultMap;
	}
	
	@RequestMapping(value="/statistics/doctorBusinessList")
	public String businessList(Integer doctorID,HttpServletRequest request){
		request.setAttribute("doctorId", doctorID);
		return "statistics/doctorBusinessList";
	}
	
	@RequestMapping(value="/statistics/queryDoctorBusinessPage")
	@ResponseBody
	public  Map<String,Object> queryDoctorBusinessPage(Integer sEcho, Integer iColumns, Integer iDisplayStart, 
			Integer iDisplayLength,HttpServletRequest request) {
		
		Map<String, String> requestMap =  RequestUtils.parameterToMap(request);
		
		Page page = statisticsService.queryDoctorBusinessPage(iDisplayStart, iDisplayLength, requestMap);
		 List<List<?>> aaData = new ArrayList<List<?>>();
		 Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho , page.getTotal());
		  for (Object o : page.getResult()) {
				Map map = (Map)o;
				List<Object> list = new ArrayList<Object>();
				list.add(map.get("id"));
				list.add(map.get("orderNumber"));
				list.add(map.get("doctorName"));
				list.add(map.get("serviceType"));
				list.add(map.get("payPrice"));
				list.add(map.get("payStatus"));
				
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String createTime=null;
				if(!NullUtil.isNull(map.get("createTime"))){
					createTime=sdf.format(map.get("createTime"));
				}
				list.add(createTime);
				
				aaData.add(list);
			}
		  
			resultMap.put("aaData", aaData);
		   return resultMap;
	}
	
	@RequestMapping(value="/statistics/hospitalBusinessList")
	public String hospitalBusinessList(HttpServletRequest request){
		Map parameter=new HashMap();
		List<Map> hospitalList=hospitalService.queryAllHospitalList(parameter);
		request.setAttribute("hospitalList", hospitalList);
		return "statistics/hospitalBusinessList";
	}
	
	@RequestMapping(value="/statistics/queryHospitalBusinessPage")
	@ResponseBody
	public  Map<String,Object> queryHospitalBusinessPage(Integer sEcho, Integer iColumns, Integer iDisplayStart, 
			Integer iDisplayLength,HttpServletRequest request) {
		Map<String, String> requestMap =  RequestUtils.parameterToMap(request);
		
		Page page = statisticsService.queryHospitalBusinessPage(iDisplayStart, iDisplayLength, requestMap);
		 List<List<?>> aaData = new ArrayList<List<?>>();
		 Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho , page.getTotal());
		  for (Object o : page.getResult()) {
				Map map = (Map)o;
				List<Object> list = new ArrayList<Object>();
				list.add(map.get("hospitalName"));
				list.add(map.get("doctorName"));
				list.add(map.get("serviceSum"));
				list.add(map.get("servicePrice"));
				list.add(map.get("doctorId"));
				aaData.add(list);
			}
		  
			resultMap.put("aaData", aaData);
		   return resultMap;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/anon/queryDayRV")
	@ResponseBody
	public List<Map> queryDayRV(HttpServletRequest request){
		List<Map> resultList=new ArrayList<Map>();
		String formatStr="yyyy-MM-dd";
		Map parameter=new HashMap();
		
		for(int i=0;i>-3;i--){
			parameter.put("dateParam", getRequiredDate(formatStr, 0, i));
			Map resultMap=new HashMap();
			resultMap.put("count", statisticsService.queryDRV(parameter)==null?0:statisticsService.queryDRV(parameter));
			resultList.add(resultMap);
		}
		Object[] mapArray=resultList.toArray();
		CollectionUtils.reverseArray(mapArray);
		return new ArrayList(Arrays.asList(mapArray));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/anon/queryDayRVBefore")
	@ResponseBody
	public List<Map> queryDayRVBefore(HttpServletRequest request){
		List<Map> resultList=new ArrayList<Map>();
		String formatStr="yyyy-MM-dd";
		Map parameter=new HashMap();
		
		for(int i=0;i>-3;i--){
			parameter.put("dateParam", getRequiredDate(formatStr, -1, i));
			Map resultMap=new HashMap();
			resultMap.put("count", statisticsService.queryDRV(parameter)==null?0:statisticsService.queryDRV(parameter));
			resultList.add(resultMap);
		}
		Object[] mapArray=resultList.toArray();
		CollectionUtils.reverseArray(mapArray);
		return new ArrayList(Arrays.asList(mapArray));
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/anon/queryMonthRV")
	@ResponseBody
	public List<Map> queryMonthRV(HttpServletRequest request){
		List<Map> resultList=new ArrayList<Map>();
		String formatStr="yyyy-MM";
		Map parameter=new HashMap();
		
		for(int i=0;i>-2;i--){
			parameter.put("dateParam", getRequiredDate(formatStr, i, null));
			Map resultMap=new HashMap();
			resultMap.put("count", statisticsService.queryMRV(parameter)==null?0:statisticsService.queryMRV(parameter));
			resultList.add(resultMap);
		}
		Object[] mapArray=resultList.toArray();
		CollectionUtils.reverseArray(mapArray);
		return new ArrayList(Arrays.asList(mapArray));
	}
	
	private static String getRequiredDate(String formatStr,Integer m,Integer d){
		Date date=new Date();
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, m);
		if(d!=null){
			c.add(Calendar.DAY_OF_MONTH, d);
		}
		
		SimpleDateFormat sdf=new SimpleDateFormat(formatStr);
		return sdf.format(c.getTime());
	}
	
}

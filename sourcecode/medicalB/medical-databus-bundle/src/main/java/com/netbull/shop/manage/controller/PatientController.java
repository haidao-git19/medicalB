package com.netbull.shop.manage.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.common.util.DoctorDC;
import com.netbull.shop.manage.common.util.HospitalDC;
import com.netbull.shop.manage.common.util.SectionFilterDC;
import com.netbull.shop.manage.weixin.service.PatientService;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;

@Controller
public class PatientController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	
	@Autowired
	private PatientService patientService;
	
	/**
	 * 查看患者列表
	 * @param request 参数doctorID:医生ID
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/anon/queryPatientList", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryPatientList(HttpServletRequest request){
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryPatientList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查询患者列表信息，参数：" + requestMap.toString());
			}
			List<Map> patientList=patientService.queryPatientList(requestMap);
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("patientList", patientList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 查看患者列表
	 * @param request 参数doctorID:医生ID
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/anon/myServiceList", produces="application/json;charset=utf-8")
	@ResponseBody
	public String myServiceList(HttpServletRequest request){
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class myServiceList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查询我的服务列表信息，参数：" + requestMap.toString());
			}
			List<Map> serviceList=patientService.myServiceList(requestMap);
			
			for (Iterator iterator = serviceList.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				if(!NullUtil.isNull(map.get("avatar"))){
					map.put("avatar", ConfigLoadUtil.loadConfig().getPropertie("accessAddress")+map.get("avatar"));
				}
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("serviceList", serviceList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	@RequestMapping(value = "/anon/myPatientList", produces="application/json;charset=utf-8")
	@ResponseBody
	public String myPatientList(HttpServletRequest request){
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class myPatientList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查询患者列表信息，参数：" + requestMap.toString());
			}
			List<Map> patientList=patientService.myPatientList(requestMap);
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("patientList", patientList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 查看患者详细信息
	 * @param request 参数patientID:患者ID
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/anon/queryPatientDetail", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryPatientDetail(HttpServletRequest request){
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryPatientDetail method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查询患者详细信息，参数：" + requestMap.toString());
			}
			Map patientDetail=patientService.queryPatientDetail(requestMap);
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("patientDetail", patientDetail);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 获取最近要到预约时间的患者数据
	 * @param request 参数doctorID:医生ID
	 * 					showCount:记录条数,默认为5条	
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/queryNearPatientList", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryNearPatientList(HttpServletRequest request){
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryNearPatientList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查询最近要到预约时间的患者列表信息，参数：" + requestMap.toString());
			}
			String showCount = !NullUtil.isNull(requestMap.get("showCount"))?requestMap.get("showCount"):"5";
			Map paramter = new HashMap();
			paramter.put("doctorID", requestMap.get("doctorID"));
			paramter.put("start", 0);
			paramter.put("limit", Integer.parseInt(showCount));
			paramter.put("nowDate", getNowDate());
			paramter.put("nowTime", getNowTime());
			List<Map> patientList=patientService.queryPatientList(paramter);
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("patientList", patientList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 将医生加入我的医生列表信息
	 * @param request 参数patientID-患者ID
	 * 						doctorID-医生ID
	 * 						doctorType-医生类型(家庭医生、专家医生)
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/anon/saveMyDoctor", produces="application/json;charset=utf-8")
	@ResponseBody
	public String saveMyDoctor(HttpServletRequest request){
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class saveMyDoctor method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("将医生加入我的医生列表信息，参数：" + requestMap.toString());
			}
			String patientID=StringUtil.getString("patientID");
			String doctorID=StringUtil.getString("doctorID");
			String doctorType=StringUtil.getString("doctorType");
			
			if(StringUtil.isEmpty(patientID)
					&&StringUtil.isEmpty(doctorID)&&StringUtil.isEmpty(doctorType)){
				Map myDoctorMap=patientService.queryMyDoctor(requestMap);
				if(NullUtil.isNull(myDoctorMap)){
					patientService.saveMyDoctor(requestMap);
				}
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
	 * 查询我的医生列表信息
	 * @param request 参数patientID-患者ID
	 * 						doctorType-医生类型(选填)
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	@RequestMapping(value = "/anon/queryMyDoctorList", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryMyDoctorList(HttpServletRequest request){
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryMyDoctorList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查询我的医生列表信息，参数：" + requestMap.toString());
			}
			String patientID=StringUtil.getString("patientID");
			List<Map> myDoctorList=new ArrayList<Map>();
			
			if(StringUtil.isEmpty(patientID)){
				myDoctorList=patientService.queryMyDoctorList(requestMap);
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("myDoctorList", myDoctorList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	
	private static String getNowDate(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
	
	private static String getNowTime(){
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		return sdf.format(new Date());
	}
	
}

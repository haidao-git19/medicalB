package com.netbull.shop.manage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.manage.weixin.service.PatientService;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.weixin.service.ConsultationService;
import com.netbull.shop.manage.weixin.service.DoctorService;
import com.netbull.shop.manage.weixin.vo.ConsultationVo;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;


@Controller
public class ConsultationCotroller {

	private static final Logger logger = Logger.getLogger("controlerLog");

	@Resource
	private ConsultationService consultationService;
	
	@Resource
	private PatientService patientService;
	@Resource
	private DoctorService doctorService;
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/anon/queryConsultationList", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryConsultationList(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryConsultationList method.");
			}
			String showCount = !NullUtil.isNull(requestMap.get("showCount"))?requestMap.get("showCount"):"10";
			Map paramter = new HashMap();
			paramter.put("start", Integer.parseInt(requestMap.get("pageId"))*Integer.parseInt(showCount));
			paramter.put("limit", Integer.parseInt(showCount));
			paramter.put("patientID", requestMap.get("patientID"));
			paramter.put("doctorID", requestMap.get("doctorID"));
			paramter.put("type", requestMap.get("type"));
			paramter.put("isFree", requestMap.get("isFree"));
			List<Map> consultationList = consultationService.queryConsultationList(paramter);
			
			if(NullUtil.isNull(consultationList)){
				resultMap.put("code", Constants.FAIL_NULL_RESULT);
				resultMap.put("msg", Constants.FAIL_NULL_RESULT_MSG);
				return JsonUtils.obj2Json(resultMap);
			}
			
			for (Iterator iterator = consultationList.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				map.put("repeatList", consultationService.queryConsultationRepeatList(map));
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("consultationList", consultationList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/anon/queryConsultationDetail", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryConsultationDetail(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMapCheck(request,"consultationID");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryConsultationDetail method.");
			}
			if(NullUtil.isNull(requestMap)){
				resultMap.put("code", Constants.FAIL_NULL_1);
				resultMap.put("msg", Constants.FAIL_NULL_MSG);
				return JsonUtils.obj2Json(resultMap);
			}
			
			Map consultationMap = consultationService.queryConsultationDetail(requestMap);
			
			if(NullUtil.isNull(consultationMap)){
				resultMap.put("code", Constants.FAIL_NULL_RESULT);
				resultMap.put("msg", Constants.FAIL_NULL_RESULT_MSG);
				return JsonUtils.obj2Json(resultMap);
			}
			//放置repeat记录
			consultationMap.put("repeatList", consultationService.queryConsultationRepeatList(consultationMap));
			//放置咨询分诊记录
			Integer dispatcherType= (Integer) consultationMap.get("dispatcherType");
			//已经分诊 查询分诊信息
			if(dispatcherType!=null){
				resultMap.put("consultDispatcherDetail",consultationService.queryConsultDispatcherDetail(consultationMap));
			}
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("consultationtDetail", consultationMap);
			resultMap.put("patientDetail", patientService.queryPatientDetail(consultationMap));
			resultMap.put("doctorDetail", doctorService.queryDoctorDetail(consultationMap));
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	@RequestMapping(value = "/anon/saveConsultationInfo" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String saveConsultationInfo(ConsultationVo consultationVo,HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("保存信息，参数：" + requestMap.toString());
			}
			consultationService.saveConsultationInfo(consultationVo);
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("sourceID", consultationVo.getConsultationID());
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	@RequestMapping(value = "/anon/modifyConsultation" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String modifyConsultation(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("修改信息，参数：" + requestMap.toString());
			}
			consultationService.modifyConsultation(requestMap);
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
}

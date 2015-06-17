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
import com.netbull.shop.databus.questionnaire.service.QuestionnaireService;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.common.util.HttpXmlUtil;
import com.netbull.shop.manage.weixin.service.ConsultationService;
import com.netbull.shop.manage.weixin.service.DoctorService;
import com.netbull.shop.manage.weixin.service.FreeTimeService;
import com.netbull.shop.manage.weixin.service.JPushService;
import com.netbull.shop.manage.weixin.service.MultiMediaService;
import com.netbull.shop.manage.weixin.service.PatientService;
import com.netbull.shop.manage.weixin.service.PlusService;
import com.netbull.shop.manage.weixin.service.PrescribeService;
import com.netbull.shop.manage.weixin.service.ReturnVisitService;
import com.netbull.shop.manage.weixin.service.SubscribeService;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;

@Controller
public class BusinessController extends AbstractController{

	private static final Logger logger = Logger.getLogger("controlerLog");
	
	@Resource
	private ConsultationService consultationService;
	@Autowired
	private SubscribeService subscribeService;
	@Autowired
	private PlusService plusService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/anon/modifyBizStatus", produces="application/json;charset=utf-8")
	@ResponseBody
	public String modifyBizStatus(HttpServletRequest request) throws Exception{
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class modifyBizStatus method.");
			}
			String bizType=StringUtil.getString(requestMap.get("bizType"));
			String bizCode=StringUtil.getString(requestMap.get("bizCode"));
			String bizState=StringUtil.getString(requestMap.get("bizState"));
			
			if (logger.isDebugEnabled()) {
				logger.debug("bizType：" + bizType);
				logger.debug("bizCode：" + bizCode);
			}
			
			if(NullUtil.isNull(bizType) || NullUtil.isNull(bizCode)){
				resultMap.put("code", Constants.FAIL_2);
				resultMap.put("msg", Constants.FAIL_MSG_8);
				return JsonUtils.obj2Json(resultMap);
			}
			
			if(Constants.CONSULATOIN_PAY_TYPE.equals(bizType)){
				this.modifyConsultation(bizCode, bizState);
			}else if(Constants.SUBSCRIBE_PAY_TYPE.equals(bizType)){
				this.modifySubscribe(bizCode, bizState);
			}else if(Constants.PLUS_PAY_TYPE.equals(bizType)){
				this.modifyPlus(bizCode, bizState);
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
	
	private void modifyConsultation(String bizCode,String bizState){
		
		Map<String,Object> requestMap = new HashMap<String,Object>();
		requestMap.put("consultationID", bizCode);
		requestMap.put("state", !NullUtil.isNull(bizState)&&Constants.SUCCESS.equals(bizState)?Constants.CONSULATOIN_PAY_STATE_FINISH:"0");
		
		this.consultationService.modifyConsultation(requestMap);
	}
	
	private void modifySubscribe(String bizCode,String bizState){
		
		Map<String,Object> requestMap = new HashMap<String,Object>();
		requestMap.put("orderID", bizCode);
		requestMap.put("state", !NullUtil.isNull(bizState)&&Constants.SUCCESS.equals(bizState)?Constants.SUBSCRIBE_PAY_STATE_FINISH:Constants.SUBSCRIBE_PAY_STATE_FAIL);
		
		this.subscribeService.modifyOrder(requestMap);
	}
	
	private void modifyPlus(String bizCode,String bizState){
		
		Map<String,Object> requestMap = new HashMap<String,Object>();
		requestMap.put("plusID", bizCode);
		requestMap.put("status", !NullUtil.isNull(bizState)&&Constants.SUCCESS.equals(bizState)?Constants.SUBSCRIBE_PAY_STATE_FINISH:Constants.SUBSCRIBE_PAY_STATE_FAIL);
		
		this.plusService.updatePlus(requestMap);
	}
	
}

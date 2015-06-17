package com.netbull.shop.manage.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.weixin.service.DiseaseService;
import com.netbull.shop.manage.weixin.service.RecoveryNursingService;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;

@Controller
public class RecoveryNursingController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	@Autowired
	private RecoveryNursingService recoveryNursingService;
	@Autowired
	private DiseaseService diseaseService;
	
	/**
	 * 康复护理详情
	 * @param request参数recoveryID-康复方案ID
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/queryRecoveryDetail", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryRecoveryNursingDetail(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryRecoveryNursingDetail method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("根据病种查询康复护理详情，参数：" + requestMap.toString());
			}
			Map nursingDetail=new HashMap();
			String recoveryID=requestMap.get("recoveryID");
			if(StringUtil.isEmpty(recoveryID)){
				Map parameter=new HashMap();
				parameter.put("id", recoveryID);
				nursingDetail=recoveryNursingService.queryRecoveryNursingDetail(parameter);
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("recoveryDetail", nursingDetail);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 
	 * @param request 参数patientID-患者ID
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/queryRecoveryList", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryRecoveryList(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryRecoveryList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("根据患者病种查询康复护理列表，参数：" + requestMap.toString());
			}
			List<Map> recoveryList=new ArrayList<Map>();
			
			if(StringUtil.isEmpty(requestMap.get("patientID"))){
				Map parameter=new HashMap();
				parameter.put("patientID", requestMap.get("patientID"));
				List<Integer> diseaseIDList=diseaseService.queryDiseaseIDList(parameter);
				if(!NullUtil.isNull(diseaseIDList)){
					Map param=new HashMap();
					param.put("diseaseIDList", diseaseIDList);
					recoveryList=recoveryNursingService.queryRecoveryList(param);
				}
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("recoveryList", recoveryList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
}

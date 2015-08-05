package com.netbull.shop.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.netbull.shop.manage.common.http.Resp;
import com.netbull.shop.manage.weixin.service.BusinessPayService;
import com.netbull.shop.manage.weixin.service.MonthServiceService;
import com.netbull.shop.manage.weixin.vo.MonthService;
import com.netbull.shop.util.RequestUtils;

@Controller
public class MonthServiceController {
	private static final Logger logger = Logger.getLogger("controlerLog");
	@Autowired
	private MonthServiceService monthServiceService;
	@Resource
	private BusinessPayService businessPayService;
	private static final String SUC_BIZ_STATE = "0";

	@RequestMapping(value = "/anon/monservice/add", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String monthServiceAdd(HttpServletRequest request) throws Exception {
		Map<String, String> requestMap = RequestUtils.parameterToMap(request);
		logger.info("req=" + requestMap);
		Map<String, Object> resp = monthServiceService
				.saveMonthService(requestMap);
		return JSON.toJSONString(resp);
	}

	@RequestMapping(value = "/anon/monservice/pay", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String monservicePpay(HttpServletRequest request) throws Exception {
		// patientId：病人的ID，doctorId：医生的ID，serviceType：服务类型,serviceNumber：服务ID
		Map<String, String> requestMap = RequestUtils.parameterToMap(request);
		logger.info("req=" + requestMap);
		// 获取当前患者 对于当前医生是否有包月信息
		MonthService resp = monthServiceService.querymonthService(requestMap);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (resp != null) {
			resultMap = monthServiceService.modifyBizStatus(
					String.valueOf(requestMap.get("serviceType")),
					String.valueOf(requestMap.get("serviceNumber")),
					SUC_BIZ_STATE);
		} else {
			resultMap.put("code", 0);
			resultMap.put("msg", "success");
			resultMap.put("ismonthPay", 0);
		}
		return JSON.toJSONString(resp);
	}
}

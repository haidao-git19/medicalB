package com.netbull.shop.databus.prescription.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.netbull.shop.databus.prescription.service.FetchRecordService;
import com.netbull.shop.manage.common.http.Resp;
import com.netbull.shop.util.RequestUtils;

@Controller("fetchRecordController")
public class FetchRecordController {
	private static final Logger logger = Logger.getLogger("controlerLog");
	@Resource
	private FetchRecordService fetchRecordService;

	/**
	 * 0,msg 成功 200 已经购买 404 未找到购买记录 保存购要记录 orderNumber,sign=orderNumber123456
	 * 
	 * @throws Exception
	 *             String
	 */
	@RequestMapping(value = "/anon/prescription/save/record", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String saveRecord(HttpServletRequest request) throws Exception {
		Map<String, String> requestMap = RequestUtils.parameterToMap(request);
		logger.info("requestMap=" + requestMap);
		Resp resp = fetchRecordService.saveRecord(requestMap);
		return JSON.toJSONString(resp);
	}
}

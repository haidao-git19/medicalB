package com.netbull.shop.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.netbull.shop.common.util.RequestUtil;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.common.http.Resp;
import com.netbull.shop.manage.weixin.service.AccountmanageService;
import com.netbull.shop.manage.weixin.vo.Account;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;

@Controller
public class AccountManageController extends AbstractController {
	private static final Logger logger = Logger.getLogger("controlerLog");
	@Autowired
	private AccountmanageService accService;

	// 1. 查询余额 /anon/acc/query/balance
	// 2. 查询消费记录 /anon/acc/query/acclog
	// 3. 充值接口 /anon/acc/recharge/fee
	// 4. 提现保存接口 /anon/acc/recharge/fee
	@RequestMapping(value = "/anon/acc/query/balance", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String queryAccBalance(HttpServletRequest request) {
		Map<String, String> requestMap = RequestUtils.parameterToMap(request);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName()
						+ " class queryAccBalance method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("参数：" + requestMap.toString());
			}
			String patientId = requestMap.get("patientId");
			Account account = accService.queryPatientAccount(Long
					.valueOf(patientId));
			if (account.validAcc()) {
				resultMap.put("code", Constants.SUCCESS);
				resultMap.put("msg", Constants.SUCCESS_MSG);
				resultMap.put("account", account);
			} else {
				resultMap.put("code", "403");
				resultMap.put("msg", "invaild account");
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("操作失败，原因：" + e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}

	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/anon/acc/query/acclog", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String patientOrderList(HttpServletRequest request) throws Exception {
		Map<String, String> requestMap = RequestUtils.parameterToMap(request);
		Map resp = accService.queryAccLogList(requestMap);
		return JSON.toJSONString(resp);
	}

	@RequestMapping(value = "/anon/acc/applyCash", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String applyCash(HttpServletRequest request) throws Exception {
		Map<String, String> requestMap = RequestUtils.parameterToMap(request);
		logger.info("req=" + requestMap);
		Resp resp = accService.saveApplyCash(requestMap);
		return JSON.toJSONString(resp);
	}
}

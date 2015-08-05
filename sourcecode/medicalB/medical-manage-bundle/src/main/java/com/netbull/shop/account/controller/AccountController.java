package com.netbull.shop.account.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.account.service.AccountService;
import com.netbull.shop.area.entity.Area;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.util.DataTableUtils;
import com.netbull.shop.util.RequestUtils;

@Controller("accountController")
public class AccountController {

	private static final Logger logger = Logger.getLogger("controlerLog");

	@Resource
	private AccountService accountService;

	@RequestMapping(value = "/getcashList.json")
	@ResponseBody
	public Map<String, Object> query(Integer sEcho, Integer iColumns,
			Integer iDisplayStart, Integer iDisplayLength,
			HttpServletRequest request) {

		Map<String, String> requestMap = RequestUtils.parameterToMap(request);

		Page page = accountService.page(iDisplayStart, iDisplayLength,
				requestMap);
		List<List<?>> aaData = new ArrayList<List<?>>();
		Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho,
				page.getTotal());
		for (Object o : page.getResult()) {
			Map map = (Map) o;
			List<Object> list = new ArrayList<Object>();
			list.add(map.get("acc_log_id"));
			list.add(map.get("patientName"));
			list.add(map.get("patientCard"));
			list.add(map.get("contactPhone"));
			list.add(map.get("createDate"));
			list.add(map.get("fee"));
			list.add(map.get("tradebank"));
			list.add(map.get("bankUserName"));
			list.add(map.get("branchBank"));
			list.add(map.get("logstate"));
			aaData.add(list);
		}

		resultMap.put("aaData", aaData);
		return resultMap;
	}

	@RequestMapping(value = "/applyCash.do")
	public String applyCash(HttpServletRequest request,
			HttpServletResponse response) {
		return "account/applyCashlist";
	}

}

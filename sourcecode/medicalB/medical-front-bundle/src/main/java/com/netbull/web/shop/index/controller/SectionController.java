package com.netbull.web.shop.index.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.netbull.web.shop.common.SectionFilterDC;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;

@Controller("web/shop/index/controller/SectionController")
public class SectionController {
	
	private static final Logger logger = Logger.getLogger("controlerLog");
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/anon/querySectionFilter" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String querySectionFilter(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			Map _map = SectionFilterDC.getInstance().queryFilter();
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("filter", _map.get(0));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
}

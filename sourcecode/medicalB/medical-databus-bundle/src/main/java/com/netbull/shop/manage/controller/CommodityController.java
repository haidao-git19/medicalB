package com.netbull.shop.manage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.weixin.service.CommodityService;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;

@Controller
public class CommodityController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	
	@Autowired
	private CommodityService commodityService;
	
	/**
	 * 获取某个药店的药品列表
	 * @param request 参数shopID-药店ID
	 * 					wordsParam-拼音首字母参数
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/anon/queryCommodityList", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryCommodityList(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryCommodityList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("新增处方，参数：" + requestMap.toString());
			}
			List<Map> commodityList=commodityService.queryCommodityList(requestMap);
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("commodityList", commodityList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
}

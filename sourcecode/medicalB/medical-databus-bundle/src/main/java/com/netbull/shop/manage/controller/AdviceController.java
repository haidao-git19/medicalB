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

import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.weixin.service.AdviceService;
import com.netbull.shop.manage.weixin.vo.AdviceVo;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;

@Controller
public class AdviceController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	
	@Autowired
	private AdviceService adviceService;
	
	/**
	 * 提交医生建议信息
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/anon/saveAdvice", produces="application/json;charset=utf-8")
	@ResponseBody
	public String saveAdvice(AdviceVo adviceVo,HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class saveAdvice method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("提交医生建议信息，参数：" + requestMap.toString());
			}

			adviceService.saveAdvice(adviceVo);
			
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

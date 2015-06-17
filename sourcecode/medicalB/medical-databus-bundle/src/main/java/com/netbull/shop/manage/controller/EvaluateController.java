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
import com.netbull.shop.manage.weixin.service.EvaluateService;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;

@Controller
public class EvaluateController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	
	@Autowired
	private EvaluateService evaluateService;
	
	/**
	 * 查看评价结果
	 * @param request 参数objectID-被评价对象ID
	 * 						objectType-被评价对象类型
	 * 					
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/anon/queryEvaluateList", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryEvaluateList(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryEvaluateList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查询评价结果信息，参数：" + requestMap.toString());
			}
			String objectID=StringUtil.getString(requestMap.get("objectID"));
			String objectType=StringUtil.getString(requestMap.get("objectType"));
			List<Map> evaluateList=new ArrayList<Map>();
			if(StringUtil.isEmpty(objectID)&&StringUtil.isEmpty(objectType)){
				evaluateList=evaluateService.queryEvaluateList(requestMap);
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("evaluateList", evaluateList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 保存评价
	 * @param request 参数objectID-被评价对象ID
	 * 						objectType-被评价对象类型
	 * 						evaluatorID-评价人ID
	 * 						score-评分
	 * 						content-评价内容
	 * 						
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/saveEvaluate", produces="application/json;charset=utf-8")
	@ResponseBody
	public String saveEvaluate(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class saveEvaluate method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("保存评价信息，参数：" + requestMap.toString());
			}
			Integer objectID=Integer.parseInt(requestMap.get("objectID"));
			Integer evaluatorID=Integer.parseInt(requestMap.get("evaluatorID"));
			Integer objectType=Integer.parseInt(requestMap.get("objectType"));
			Integer score=requestMap.get("score")!=null?Integer.parseInt(StringUtil.getString(requestMap.get("score"))):0;
			if(!NullUtil.isNull(objectID)
					&&!NullUtil.isNull(evaluatorID)&&!NullUtil.isNull(objectType)){
				Map parameter=new HashMap();
				parameter.put("objectID", objectID);
				parameter.put("evaluatorID", evaluatorID);
				parameter.put("objectType", objectType);
				parameter.put("score", score);
				parameter.put("content", requestMap.get("content"));
				evaluateService.saveEvaluate(parameter);
				evaluateService.updateBetterRate(parameter);//更新好评率
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
}

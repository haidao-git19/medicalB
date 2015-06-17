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
import com.netbull.shop.manage.weixin.service.PrescribeService;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;

@Controller
public class PrescribeController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	
	@Autowired
	private PrescribeService prescribeService;
	
	/**
	 * 查询处方列表 
	 * @param request 参数doctorID-医生ID
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/anon/queryPrescribeList", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryPrescribeList(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryPrescribeList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查询处方列表，参数：" + requestMap.toString());
			}
			String showCount = !NullUtil.isNull(requestMap.get("showCount"))?requestMap.get("showCount"):"10";
			Map paramter = new HashMap();
			paramter.put("start", Integer.parseInt(requestMap.get("pageId"))*Integer.parseInt(showCount));
			paramter.put("limit", Integer.parseInt(showCount));
			paramter.put("doctorID", requestMap.get("doctorID"));
			List<Map> prescribeList=prescribeService.queryPrescribeList(paramter);
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("prescribeList", prescribeList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 查询处方详细信息列表
	 * @param request 参数prescribeID-处方ID
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/anon/queryPrescribeDetailList", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryPrescribeDetailList(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<Map> prescribeDetailList=new ArrayList<Map>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryPrescribeDetailList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查询处方信息列表，参数：" + requestMap.toString());
			}
			if(StringUtil.isEmpty(StringUtil.getString(requestMap.get("prescribeID")))){
				prescribeDetailList=prescribeService.queryPrescribeDetailList(requestMap);
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("prescribeDetailList", prescribeDetailList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 新增处方名称
	 * @param request 参数doctorID-医生ID
	 * 						prescribeName-处方名称
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/anon/addPrescribe", produces="application/json;charset=utf-8")
	@ResponseBody
	public String addPrescribe(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class addPrescribe method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("新增处方，参数：" + requestMap.toString());
			}
			prescribeService.savePrescribe(requestMap);
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 保存处方
	 * @param request 参数prescribeID-处方ID
	 * 						commodityID-药品ID
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/anon/addCommodityToPrescribe", produces="application/json;charset=utf-8")
	@ResponseBody
	public String addCommodityToPrescribe(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class addCommodityToPrescribe method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("保存处方，参数：" + requestMap.toString());
			}
			prescribeService.addCommodityToPrescribe(requestMap);
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 绑定一个处方给患者
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/anon/bindPrescribeToPatient", produces="application/json;charset=utf-8")
	@ResponseBody
	public String bindPrescribeToPatient(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class bindPrescribeToPatient method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("保存处方，参数：" + requestMap.toString());
			}
			
			prescribeService.bindPrescribeToPatient(requestMap);
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

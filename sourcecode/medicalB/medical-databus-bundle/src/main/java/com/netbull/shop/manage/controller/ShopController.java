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

import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.weixin.service.ShopService;
import com.netbull.shop.util.RequestUtils;

@Controller
public class ShopController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	
	@Autowired
	private ShopService shopService;
	
	/**
	 * 查找附近2公里范围内药店列表
	 * @param request 参数locationX-患者位置经度
	 * 						locationY-患者位置纬度
	 * 						scope-查询范围,默认为2公里
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/queryNearShopList", produces="application/json;charset=utf-8")
	@ResponseBody
	public Map queryNearShopList(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryNearShopList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查询附近药店列表信息，参数：" + requestMap.toString());
			}
			Double scope=requestMap.get("scope")!=null?Double.parseDouble(StringUtil.getString(requestMap.get("scope"))):2.0;
			String locationX=StringUtil.getString(requestMap.get("locationX"));
			String locationY=StringUtil.getString(requestMap.get("locationY"));
			
			if(!StringUtil.isEmpty(locationX)||!StringUtil.isEmpty(locationY)){
				resultMap.put("code", Constants.FAIL);
				resultMap.put("msg", Constants.FAIL_LOCATION);
				return resultMap;
			}
			Map parameter=new HashMap();
			parameter.put("scope", scope);
			parameter.put("locationX", requestMap.get("locationX"));
			parameter.put("locationY", requestMap.get("locationY"));
			List<Map> nearShopList=shopService.queryNearShopList(parameter);
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("nearShopList", nearShopList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return resultMap;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/queryShopList", produces="application/json;charset=utf-8")
	@ResponseBody
	public Map queryShopList(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryShopList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查询附近药店列表信息，参数：" + requestMap.toString());
			}
			
			List<Map> shopList=shopService.queryShopList(requestMap);
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("shopList", shopList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return resultMap;
	}
	
	/**
	 * 查询药店详情介绍信息
	 * @param request 参数shopID-药店ID
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/anon/queryShopDetail", produces="application/json;charset=utf-8")
	@ResponseBody
	public Map queryShopDetail(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryShopDetail method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查询药店详情介绍信息，参数：" + requestMap.toString());
			}
			Map shopDetail=new HashMap();
			
			String shopID=StringUtil.getString(requestMap.get("shopID"));
			if(StringUtil.isEmpty(shopID)){
				shopDetail=shopService.queryShopDetail(requestMap);
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("shopDetail", shopDetail);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return resultMap;
	}
}

package com.netbull.shop.manage.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.task.Const;
import com.netbull.shop.common.util.CookiesUtils;
import com.netbull.shop.common.util.DesEncrypt;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.common.util.TokenDC;
import com.netbull.shop.manage.common.util.HttpXmlUtil;
import com.netbull.shop.manage.common.util.RequestUtils;
import com.netbull.shop.util.JsonUtils;

@Controller
public class AddressController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	
	@RequestMapping("/anon/queryAcceptInfoList")
	@ResponseBody
	public String queryAcceptInfoList(ModelMap model,HttpServletRequest request) throws Exception{
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryAcceptInfoList method.");
		}
		Cookie  cookie = CookiesUtils.getCookie(request, "userId");
		String userId = !NullUtil.isNull(cookie)?cookie.getValue():null;
		if(NullUtil.isNull(userId)){
			return JsonUtils.obj2Json("noLogin");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		requestMap.put("userId", DesEncrypt.getDecryptString(userId));
		String url = ConfigLoadUtil.loadConfig().getPropertie("queryAcceptInfoList"); 
		String resp = HttpXmlUtil.doPost(url, requestMap);
		if(NullUtil.isNull(resp)){
			return null;
		}
		return resp;
	}
	
	@RequestMapping("/anon/queryAcceptInfo")
	@ResponseBody
	public String queryAcceptInfo(ModelMap model,HttpServletRequest request) throws Exception{
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryAcceptInfo method.");
		}
		Cookie  cookie = CookiesUtils.getCookie(request, "userId");
		String userId = !NullUtil.isNull(cookie)?cookie.getValue():null;
		if(NullUtil.isNull(userId)){
			return JsonUtils.obj2Json("noLogin");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String url = ConfigLoadUtil.loadConfig().getPropertie("queryAcceptInfo"); 
		String resp = HttpXmlUtil.doPost(url, requestMap);
		if(NullUtil.isNull(resp)){
			return null;
		}
		return resp;
	}
	/**
	 * 新增收货地址
	 * @return
	 */
	@RequestMapping("/anon/toAddAdress")
	public String toAddAdress(ModelMap model,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class toAddAdress method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		model.addAttribute("_param", requestMap);
		
		Map paramterMap = TokenDC.getInstance().getWechatVo(Const.NETBULL_LOGIN_CODE);
		String appid = StringUtil.getString(paramterMap.get("appId"));
		String jsapi_ticket =  StringUtil.getString(paramterMap.get("jsapi_ticket"));
		String queryStr = request.getQueryString();
		String currentUrl = null;
		if(!NullUtil.isNull(queryStr)){
			currentUrl = request.getRequestURL()+"?"+request.getQueryString();
		}else{
			currentUrl = request.getRequestURL().toString();
		}
		
		Map<String, String> ret = StringTools.sign(jsapi_ticket, currentUrl);
		model.addAttribute("appId",appid);
		model.addAttribute("timestamp",ret.get("timestamp"));
		model.addAttribute("nonceStr",ret.get("nonceStr"));
		model.addAttribute("signature",ret.get("signature"));
		
		return "address/addAdress";
	}
	
	/**
	 * 新增收货地址
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/anon/toModify")
	public String toModify(ModelMap model,HttpServletRequest request) throws Exception{
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class toModify method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		String id = requestMap.get("id");
		Cookie  cookie = CookiesUtils.getCookie(request, "userId");
		String userId = !NullUtil.isNull(cookie)?cookie.getValue():null;
		if(NullUtil.isNull(userId)){
			return JsonUtils.obj2Json("noLogin");
		}
		requestMap.put("userId", DesEncrypt.getDecryptString(userId));
		String url = ConfigLoadUtil.loadConfig().getPropertie("queryAcceptInfoList"); 
		String resp = HttpXmlUtil.doPost(url, requestMap);
		if(NullUtil.isNull(resp)){
			return null;
		}
		Map respMap = JsonUtils.json2Map(resp);
		if(NullUtil.isNull(respMap)){
			return null;
		}
		List<Map> acceptList = (List<Map>) respMap.get("acceptList");
		for (Iterator iterator = acceptList.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			if(id.equals(map.get("id").toString())){
				String area = StringUtil.getString(map.get("province"))+","+StringUtil.getString(map.get("city"))+","+StringUtil.getString(map.get("area"));
				map.put("area", area);
				map.put("isEidt", StringUtil.getString(requestMap.get("isEidt")));
				model.addAttribute("_param", map);
			}
		}
		return "address/addAdress";
	}
	
	/**
	 * 新增收货地址
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/anon/submitAddress")
	@ResponseBody
	public Map submitAddress(ModelMap model,HttpServletRequest request) throws Exception{
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class submitAddress method.");
		}
		Cookie  cookie = CookiesUtils.getCookie(request, "userId");
		String userId = !NullUtil.isNull(cookie)?cookie.getValue():null;
		if(NullUtil.isNull(userId)){
			return null;
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String area = requestMap.get("area");
		String[] _areas = area.split(",");
		if(_areas!=null && _areas.length == 3){
			requestMap.put("province", _areas[0]);
			requestMap.put("city", _areas[1]);
			requestMap.put("area", _areas[2]);
		}
		requestMap.put("userId", DesEncrypt.getDecryptString(userId));
		String url = ConfigLoadUtil.loadConfig().getPropertie("modifyAcceptInfo"); 
		String resp = HttpXmlUtil.doPost(url, requestMap);
		if(NullUtil.isNull(resp)){
			return null;
		}
		return JsonUtils.json2Map(resp);
	}
	
	/**
	 * 新增收货地址
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/anon/deleteAddress")
	@ResponseBody
	public String deleteAddress(ModelMap model,HttpServletRequest request) throws Exception{
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class deleteAddress method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String url = ConfigLoadUtil.loadConfig().getPropertie("deleteAcceptInfo"); 
		String resp = HttpXmlUtil.doPost(url, requestMap);
		if(NullUtil.isNull(resp)){
			return null;
		}
		return resp;
	}
	
	/**
	 * 收货地址列表；
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/anon/toMoreAddress")
	public String toMoreAddress(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class toMoreAddress method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		model.addAttribute("_param", requestMap);
		
		Cookie  cookie = CookiesUtils.getCookie(request, "userId");
		String userId = !NullUtil.isNull(cookie)?cookie.getValue():null;
		if(NullUtil.isNull(userId)){
			return JsonUtils.obj2Json("noLogin");
		}
		requestMap.put("userId", DesEncrypt.getDecryptString(userId));
		String url = ConfigLoadUtil.loadConfig().getPropertie("queryAcceptInfoList"); 
		String resp = HttpXmlUtil.doPost(url, requestMap);
		
		Map result = JsonUtils.json2Map(resp);
		List acceptList = !NullUtil.isNull(result.get("acceptList"))?(List)result.get("acceptList"):null;
		if(NullUtil.isNull(acceptList)){
			model.addAttribute("isEmpty", true);
			return "address/addAdress";
		}
		return "address/addressList";
	}
	
	/**
	 * 区域选择；
	 * @return
	 */
	@RequestMapping("/anon/toChooseArea")
	public String toChooseArea(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class toChooseArea method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		model.addAttribute("param", requestMap);
		return "address/chooseArea";
	}
	
	/**
	 * 区域选择；
	 * @return
	 */
	@RequestMapping("/anon/queryAreaInfoList")
	@ResponseBody
	public String queryAreaInfoList(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryAreaInfoList method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String url = ConfigLoadUtil.loadConfig().getPropertie("queryAreaInfoList"); 
		String resp = HttpXmlUtil.doPost(url, requestMap);
		if(NullUtil.isNull(resp)){
			return null;
		}
		return resp;
	}
	
}

package com.netbull.shop.manage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.manage.weixin.service.PharmacyService;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.Md5;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.common.util.DoctorDC;
import com.netbull.shop.manage.common.util.HospitalDC;
import com.netbull.shop.manage.weixin.vo.Doctor;
import com.netbull.shop.manage.weixin.vo.DoctorGroup;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;


@Controller
public class PharmacyCotroller extends AbstractController{

	private static final Logger logger = Logger.getLogger("controlerLog");

	@Resource
	private PharmacyService pharmacyService;
	
	/**
	 * 登录获取用户基本信息；
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/anon/pharmacyLogin" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String login(HttpServletRequest request) throws Exception {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("登录获取用户基本信息，参数：" + requestMap.toString());
			}
			
			String loginName = requestMap.get("pharmacyName");
			String password = requestMap.get("pharmacyPassword");
			if(NullUtil.isNull(loginName)||NullUtil.isNull(password)){
				resultMap.put("code", Constants.FAIL);
				resultMap.put("msg", Constants.FAIL_MSG);
				return JsonUtils.obj2Json(resultMap);
			}
			if(!NullUtil.isNull(password)&&password.length()<32){
				requestMap.put("pharmacyPassword", Md5.md5Digest(password));
			}
			Map user = this.pharmacyService.queryPharmacyLogin(requestMap);
			
			if(!NullUtil.isNull(user)){
				resultMap.put("code", Constants.SUCCESS);
				resultMap.put("msg", Constants.SUCCESS_MSG);
				resultMap.put("pharmacyInfo", user);
			}else{
				resultMap.put("code", Constants.FAIL);
				resultMap.put("msg", Constants.FAIL_MSG);
			}
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
}

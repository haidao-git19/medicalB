package com.netbull.shop.auth.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.auth.entity.AreaInfo;
import com.netbull.shop.auth.entity.MenuInfo;
import com.netbull.shop.auth.entity.PlatformInfo;
import com.netbull.shop.auth.entity.ProductInfo;
import com.netbull.shop.auth.service.AuthRemoteService;
import com.netbull.shop.util.HttpUtils;
import com.netbull.shop.util.JsonUtils;

@Controller("authRemoteController")
public class AuthRemoteController {

	@Resource
	private AuthRemoteService authRemoteService;

	@RequestMapping("/anon/rest")
	@ResponseBody
	public String rest(HttpServletRequest request, String url) {
		try {
			Map<String, String> params = HttpUtils.getParams(request);
			return new String(HttpUtils.post(url, params), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping("/anon/getAuthUrl")
	@ResponseBody
	public String getAuthUrl() {
		return authRemoteService.getAuthUrl();
	}
	
	@RequestMapping("/anon/getServiceUrl")
	@ResponseBody
	public String getServiceUrl() {
		return authRemoteService.getServiceUrl();
	}

	@RequestMapping("/rest/getPermissionMenus")
	@ResponseBody
	public List<MenuInfo> permitSubMenus(HttpServletRequest request) {
		List<MenuInfo> menus = new ArrayList<MenuInfo>();
		String text = rest(request, getAuthUrl() + "/anon/getPermissionMenus");
		MenuInfo[] array = JsonUtils.json2Obj(text, MenuInfo[].class);
		for (int i = 0; array != null && i < array.length; i++) {
			MenuInfo menu = array[i];
			menus.add(menu);
		}
		return menus;
	}

	@RequestMapping("/rest/getPermissionMenusOnceAll")
	@ResponseBody
	public List<MenuInfo> getPermissionMenusOnceAll(HttpServletRequest request) {
		List<MenuInfo> menus = new ArrayList<MenuInfo>();
		String text = rest(request, getAuthUrl() + "/anon/getPermissionMenusOnceAll");
		MenuInfo[] array = JsonUtils.json2Obj(text, MenuInfo[].class);
		for (int i = 0; array != null && i < array.length; i++) {
			MenuInfo menu = array[i];
			menus.add(menu);
		}
		return menus;
	}

	@RequestMapping("/rest/getPermitProducts")
	@ResponseBody
	public List<ProductInfo> getPermitProducts(HttpServletRequest request) {
		List<ProductInfo> list = new ArrayList<ProductInfo>();
		String text = rest(request, getAuthUrl() + "/anon/getPermitProducts");
		ProductInfo[] array = JsonUtils.json2Obj(text, ProductInfo[].class);
		for (int i = 0; array != null && i < array.length; i++) {
			ProductInfo product = array[i];
			list.add(product);
		}
		return list;
	}
	
	@RequestMapping("/rest/permitCitys")
	@ResponseBody
	public List<AreaInfo> permitCitys(HttpServletRequest request) {
		List<AreaInfo> list = new ArrayList<AreaInfo>();
		String text = rest(request, getAuthUrl() + "/area/permitCitys");
		AreaInfo[] array = JsonUtils.json2Obj(text, AreaInfo[].class);
		for (int i = 0; array != null && i < array.length; i++) {
			AreaInfo areaInfo = array[i];
			list.add(areaInfo);
		}
		return list;
	}

	@RequestMapping("/rest/permitProvinces")
	@ResponseBody
	public List<AreaInfo> permitProvinces(HttpServletRequest request) {
		List<AreaInfo> list = new ArrayList<AreaInfo>();
		String text = rest(request, getAuthUrl() + "/area/permitProvinces");
		AreaInfo[] array = JsonUtils.json2Obj(text, AreaInfo[].class);
		for (int i = 0; array != null && i < array.length; i++) {
			AreaInfo areaInfo = array[i];
			list.add(areaInfo);
		}
		return list;
	}
	
	@RequestMapping("/rest/getPermitPlatforms")
	@ResponseBody
	public List<PlatformInfo> getPermitPlatforms(HttpServletRequest request) {
		List<PlatformInfo> list = new ArrayList<PlatformInfo>();
		String text = rest(request, getAuthUrl() + "/anon/getPermitPlatforms");
		PlatformInfo[] array = JsonUtils.json2Obj(text, PlatformInfo[].class);
		for (int i = 0; array != null && i < array.length; i++) {
			PlatformInfo platform = array[i];
			list.add(platform);
		}
		return list;
	}

	@RequestMapping("/anon/checkTicket")
	public String checkTicket(HttpServletRequest request, HttpServletResponse response, String ticket) {
		try {
			if (StringUtils.isEmpty(ticket)) {
				response.sendRedirect(getServiceUrl());
				return null;
			}
			UsernamePasswordToken token = new UsernamePasswordToken(ticket, ticket, false);
			SecurityUtils.getSubject().login(token);
			WebUtils.redirectToSavedRequest(request, response, getServiceUrl());
		} catch (IOException e) {
		}
		return null;
	}

	@RequestMapping(value = "/logout")
	public String logout(String service) {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			subject.logout();
		}
		String redirect = "/";
		if (StringUtils.hasText(service)) {
			redirect = service;
		}
		if (StringUtils.hasText(getAuthUrl())) {
			redirect = getAuthUrl() + "/logout";
			if (StringUtils.hasText(service)) {
				redirect += "?service=" + service;
			}
		}
		return "redirect:" + redirect;
	}

}

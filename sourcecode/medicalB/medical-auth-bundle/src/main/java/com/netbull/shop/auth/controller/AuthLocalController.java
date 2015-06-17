package com.netbull.shop.auth.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.auth.entity.MenuInfo;
import com.netbull.shop.auth.entity.PlatformInfo;
import com.netbull.shop.auth.entity.ProductInfo;
import com.netbull.shop.auth.entity.UserInfo;
import com.netbull.shop.shiro.ShiroUser;
import com.netbull.shop.util.HttpUtils;
import com.netbull.shop.auth.service.AuthService;
import com.netbull.shop.auth.service.MenuService;
import com.netbull.shop.auth.service.ProductPlatformService;
import com.netbull.shop.auth.service.UserService;

@Controller("authLocalController")
public class AuthLocalController {

	
	@Resource
	private UserService userService;

	@Resource
	private MenuService menuService;

	@Resource
	private AuthService authService;

	@Resource
	private ProductPlatformService productPlatformService;

	@RequestMapping("/anon/getRoleNames")
	@ResponseBody
	public Collection<String> getRoleNames(String loginName) {
		return authService.getRoleNames(loginName);
	}

	@RequestMapping("/anon/getPermissionNames")
	@ResponseBody
	public Collection<String> getPermissionNames(String loginName) {
		return authService.getPermissionNames(loginName);
	}

	@RequestMapping("/anon/getPermissionMenus")
	@ResponseBody
	public List<MenuInfo> getPermissionMenus(String loginName, Long parentId) {
		List<MenuInfo> menus = new ArrayList<MenuInfo>();
		UserInfo userInfo = userService.getUserByLoginName(loginName);
		if (userInfo == null) {
			return menus;
		}
		List<MenuInfo> menuInfos = menuService.getVisibleMenusByUserAndParent(
				userInfo.getId(), parentId);
		menus.addAll(menuInfos);
		return menus;
	}

	@RequestMapping("/anon/getPermissionMenusOnceAll")
	@ResponseBody
	public List<MenuInfo> getPermissionMenusOnceAll(String loginName) {
		List<MenuInfo> menus = new ArrayList<MenuInfo>();
		UserInfo userInfo = userService.getUserByLoginName(loginName);
		if (userInfo == null) {
			return menus;
		}
		List<MenuInfo> menuInfos = menuService
				.getPermissionMenusOnceAll(userInfo.getId());
		menus.addAll(menuInfos);
		return menus;
	}

	@RequestMapping("/anon/getUserDatas")
	@ResponseBody
	public List<String> getUserDatas(String loginName, String key) {
		List<String> values = new ArrayList<String>();
		UserInfo userInfo = userService.getUserByLoginName(loginName);
		if (userInfo == null) {
			return values;
		}
		List<String> _values = userService.getUserData(userInfo.getId(), key);
		values.addAll(_values);
		return values;
	}

	@RequestMapping("/anon/getPermitPlatforms")
	@ResponseBody
	public List<PlatformInfo> getPermitPlatforms(HttpServletRequest request,String loginName) {
		List<PlatformInfo> list = new ArrayList<PlatformInfo>();
		if (StringUtils.isEmpty(loginName)) {
			return list;
		}
		Map<String,String> params = HttpUtils.getParams(request);
		String productId = params.get("productId");
		String productIds = params.get("productIds");
		List<String> pIds = new ArrayList<String>();
		if(StringUtils.hasText(productId)){
			pIds.add(productId);
		}
		if(StringUtils.hasText(productIds)){
			pIds.addAll(Arrays.asList(productIds.split(",")));
		}
		return productPlatformService.getPlatformsByUser(loginName,pIds.toArray(new String[]{}));
	}

	@RequestMapping("/anon/getPermitProducts")
	@ResponseBody
	public List<ProductInfo> getPermitProducts(String loginName) {
		List<ProductInfo> list = new ArrayList<ProductInfo>();
		if (StringUtils.isEmpty(loginName)) {
			return list;
		}
		return productPlatformService.getProductsByUser(loginName);

	}

	@RequestMapping("/anon/getUserByTicket")
	@ResponseBody
	public ShiroUser getUserByTicket(String ticket) {
		return authService.getTicketCache().getIfPresent(ticket);
	}
	
	@RequestMapping("/anon/getShiroUser")
	@ResponseBody
	public ShiroUser getShiroUser(String loginName) {
		return authService.getShiroUser(loginName);
	}
}

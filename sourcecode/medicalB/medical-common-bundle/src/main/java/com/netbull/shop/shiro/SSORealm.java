package com.netbull.shop.shiro;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import com.netbull.shop.auth.service.AuthRemoteService;
import com.netbull.shop.util.HttpUtils;
import com.netbull.shop.util.JsonUtils;

public class SSORealm extends AuthorizingRealm {
	
	@Resource
	private AuthRemoteService authRemoteService;
	
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		String loginName = shiroUser.getLoginName();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Collection<String> roleNames = getRoleNames(loginName);
		if (roleNames == null) {
			roleNames = new HashSet<String>();
		}
		Collection<String> permissionNames = getPermissionNames(loginName);
		if (permissionNames == null) {
			permissionNames = new HashSet<String>();
		}
		for (String roleName : roleNames) {
			info.addRole(roleName);
		}
		info.addStringPermissions(permissionNames);
		return info;
	}

	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		if (token instanceof UsernamePasswordToken) {
			UsernamePasswordToken upt = (UsernamePasswordToken) token;
			char[] pwd = upt.getPassword();
			String uname = upt.getUsername();
			Map<String, String> params = new HashMap<String, String>();
			params.put("ticket", uname);
			String responseText = rest(authRemoteService.getAuthUrl()
					+ "/anon/getUserByTicket", params);
			if (StringUtils.hasText(responseText)) {
				ShiroUser su = JsonUtils
						.json2Obj(responseText, ShiroUser.class);
				if (su != null) {
					return new SimpleAuthenticationInfo(su, pwd, getName());
				}
			}
		}
		return null;
	}

	protected Collection<String> getRoleNames(String loginName) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("loginName", loginName);
		Collection<String> permissions = new HashSet<String>();
		String data = rest(authRemoteService.getAuthUrl() + "/anon/getRoleNames", params);
		String[] array = JsonUtils.json2Obj(data, String[].class);
		for (int i = 0; array != null && i < array.length; i++) {
			String role = array[i];
			if(StringUtils.hasText(role)){
				permissions.add(role);
			}
		}
		return permissions;
	}

	protected Collection<String> getPermissionNames(String loginName) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("loginName", loginName);
		Collection<String> permissions = new HashSet<String>();
		String data = rest(authRemoteService.getAuthUrl() + "/anon/getPermissionNames", params);
		String[] array = JsonUtils.json2Obj(data, String[].class);
		for (int i = 0; array != null && i < array.length; i++) {
			String p = array[i];
			if(StringUtils.hasText(p)){
				permissions.add(p);
			}
		}
		return permissions;
	}

	private String rest(String url, Map<String, String> params) {
		try {
			return new String(HttpUtils.post(url, params), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

}

package com.netbull.shop.auth.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.netbull.shop.auth.entity.MenuInfo;
import com.netbull.shop.auth.entity.RoleInfo;
import com.netbull.shop.auth.entity.UserInfo;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.shiro.ShiroUser;

@Service("authService")
public class AuthService {
	@Resource
	private UserService userService;

	@Resource
	private RoleService roleService;

	@Resource
	private MenuService menuService;

	private Cache<String, ShiroUser> ticketCache = CacheBuilder.newBuilder()
			.expireAfterWrite(30, TimeUnit.SECONDS).build();
    /**
     * 查询用户被赋予的角色和用户常见的角色.
     * @param loginName
     * @return
     */
	public Collection<String> getRoleNames(String loginName) {
		Collection<String> roles = new HashSet<String>();
		List<RoleInfo> roleInfos = roleService.getRolesByUser(loginName);
		for (RoleInfo roleInfo : roleInfos) {
			roles.add(roleInfo.getName());
		}
		return roles;
	}
    
	/**
	 * 查询用户可进行的操作
	 * @param loginName
	 * @return
	 */
	public Collection<String> getPermissionNames(String loginName) {
		Collection<String> permissions = new HashSet<String>();
		List<MenuInfo> pers = menuService.getVisibleMenuActionsByUser(loginName);
		for (MenuInfo menuInfo : pers) {
			String permit = menuInfo.getName();
			MenuInfo page = menuInfo.getParent();
			if (page != null) {
				permit = page.getName() + ":" + permit;
				MenuInfo folder = page.getParent();
				if (folder != null) {
					permit = folder.getName() + ":" + permit;
				}
			}
			permissions.add(permit);
		}
		return permissions;
	}

	public String getPassword(String loginName) {
		return userService.getUserByLoginName(loginName).getPassword();
	}

	public ShiroUser getShiroUser(String loginName) {
		UserInfo userInfo = userService.getUserByLoginName(loginName);
		
		ShiroUser su = new ShiroUser();
		su.setId(userInfo.getId());
		su.setAreaId(Long.valueOf(userInfo.getArea().getAiid()));
		su.setLoginName(userInfo.getLoginName());
		su.setName(userInfo.getTrueName());
		
		if(!NullUtil.isNull(userInfo)){
			List<String> childNodes = userService.getUserAllChildNode(userInfo.getId());
			StringBuffer sb = new StringBuffer();
			sb.append(userInfo.getId());
			sb.append(",");
			for (Iterator iterator = childNodes.iterator(); iterator.hasNext();) {
				String childNode = (String) iterator.next();
				sb.append(childNode);
				sb.append(",");
			}
			String temp = sb.toString();
			temp = temp.substring(0,temp.length()-1);
			su.setChildNodes(temp);
		}
		
		return su;
	}
	
	public Cache<String, ShiroUser> getTicketCache() {
		return ticketCache;
	}

	public void setTicketCache(Cache<String, ShiroUser> ticketCache) {
		this.ticketCache = ticketCache;
	}

}

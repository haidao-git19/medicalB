package com.netbull.shop.auth;

import java.util.Collection;

import javax.annotation.Resource;

import com.netbull.shop.shiro.AbstractDbRealm;
import com.netbull.shop.shiro.ShiroUser;
import com.netbull.shop.auth.service.AuthService;

/**
 *  Realm充当了Shiro与应用安全数据间的“桥梁”或者“连接器”。
 *  也就是说，当对用户执行认证（登录）和授权（访问控制）验证时，
 *  Shiro会从应用配置的Realm中查找用户及其权限信息
 * @author hkj
 *
 */
public class AuthDbRealm extends AbstractDbRealm {

	@Resource
	private AuthService authService;
    
	/**
	 * 查询用户拥有的角色
	 */
	protected Collection<String> getRoleNames(String loginName) {
		return authService.getRoleNames(loginName);
	}
    /**
     * 查询用户可进行的操作
     */
	protected Collection<String> getPermissionNames(String loginName) {
		return authService.getPermissionNames(loginName);
	}
	 /**
     * 查询用户密码
     */
	protected String getPassword(String loginName) {
		return authService.getPassword(loginName);
	}
	/**
     * 获得当前用户的ShiroUser信息
     */
	protected ShiroUser getShiroUser(String loginName) {
		return authService.getShiroUser(loginName);
	}

}

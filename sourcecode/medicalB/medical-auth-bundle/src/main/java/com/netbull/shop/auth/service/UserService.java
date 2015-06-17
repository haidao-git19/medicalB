package com.netbull.shop.auth.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.subject.WebSubject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.netbull.shop.auth.entity.RoleInfo;
import com.netbull.shop.auth.entity.RoleUserInfo;
import com.netbull.shop.auth.entity.UserDataInfo;
import com.netbull.shop.auth.entity.UserInfo;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.dao.HDao;
import com.netbull.shop.shiro.ShiroUser;
import com.netbull.shop.util.MsgSender;

@Service("userService")
public class UserService {

	public static final String DEFAULT_PASSWORD = "000000";

	@Resource
	private HDao authDao;
	
	public UserInfo getUserById(Long id) {
		return authDao.get(UserInfo.class, id);
	}

	public UserInfo getUserByLoginName(String loginName) {
		if (StringUtils.isEmpty(loginName)) {
			return null;
		}
		return authDao.findSingle("from UserInfo where loginName=?", loginName);
	}

	public void saveUser(UserInfo userInfo) {
		Date now = new Date();
		boolean isNew = true;
		if (userInfo.getId() != null) {
			isNew = false;
			UserInfo old = authDao.get(UserInfo.class, userInfo.getId());
			userInfo.setCreateTime(old.getCreateTime());
			userInfo.setCreator(old.getCreator());
			userInfo.setPassword(old.getPassword());
			userInfo.setUpdateTime(now);
			userInfo.setUpdator(UserService.getCurrentLoginName());
		}
		if (userInfo.getCreator() == null) {
			userInfo.setCreator(UserService.getCurrentLoginName());
		}
		if (userInfo.getCreateTime() == null) {
			userInfo.setCreateTime(now);
		}
		if (StringUtils.isEmpty(userInfo.getPassword())) {
			String encodePwd = DigestUtils.md5DigestAsHex(UserService.DEFAULT_PASSWORD.getBytes());
			userInfo.setPassword(encodePwd);
		}
		userInfo.setParentid(getCurrentUserId());
		authDao.save(userInfo);
		if (isNew && StringUtils.hasText(userInfo.getPhone())) {
			MsgSender ms = new MsgSender();
			ms.sendMsg(userInfo.getPhone(), String.format("您好, 您的运营平台账户已创建, 登录名：%s, 初始密码: %s, 请尽快登录并修改密码",
					userInfo.getLoginName(), UserService.DEFAULT_PASSWORD), "GBK", 3000);
		}
	}

	public void changePassword(String oldPassword, String password) {
		try {
			UserInfo userInfo = getCurrentUser();
			if (userInfo == null) {
				throw new RuntimeException("未登录");
			}
			String oldEncodePwd = DigestUtils.md5DigestAsHex(oldPassword.getBytes("utf-8"));
			if (!userInfo.getPassword().equals(oldEncodePwd)) {
				throw new RuntimeException("原密码不正确");
			}
			String encodePwd = DigestUtils.md5DigestAsHex(password.getBytes("utf-8"));
			userInfo.setPassword(encodePwd);
			saveUser(userInfo);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void resetPassword(Long userId) {
		try {
			UserInfo userInfo = getUserById(userId);
			String encodePwd = DigestUtils.md5DigestAsHex(UserService.DEFAULT_PASSWORD.getBytes("utf-8"));
			userInfo.setPassword(encodePwd);
			saveUser(userInfo);
			if (StringUtils.hasText(userInfo.getPhone())) {
				MsgSender ms = new MsgSender();
				ms.sendMsg(userInfo.getPhone(),
						String.format("您好, 您的运营平台登录密码已重置, 初始密码：%s, 请尽快登录并修改密码", UserService.DEFAULT_PASSWORD), "GBK",
						3000);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Transactional
	public void deleteUser(Long id) {
		deleteUserData(id);
		authDao.bulkUpdate("delete from RoleUserInfo where user.id=?", id);
		authDao.bulkUpdate("delete from UserInfo where id=?", id);
	}

	public void enableUser(Long id) {
		UserInfo userInfo = getUserById(id);
		userInfo.setStatus(UserInfo.STATUS_ENABLE);
		saveUser(userInfo);
	}

	public void disableUser(Long id) {
		UserInfo userInfo = getUserById(id);
		userInfo.setStatus(UserInfo.STATUS_DISABLE);
		saveUser(userInfo);
	}

	@Transactional
	public void saveUserRole(Long userId, Long[] roleIds) {
		if (roleIds == null) {
			return;
		}
		UserInfo userInfo = getUserById(userId);
		authDao.bulkUpdate("delete from RoleUserInfo where user.id=?", userId);
		for (Long roleId : roleIds) {
			RoleInfo roleInfo = authDao.get(RoleInfo.class, roleId);
			if (roleInfo == null) {
				continue;
			}
			RoleUserInfo roleUserInfo = new RoleUserInfo();
			roleUserInfo.setUser(userInfo);
			roleUserInfo.setRole(roleInfo);
			authDao.save(roleUserInfo);
		}
	}

	public List<UserDataInfo> getUserData(Long userId) {
		if (userId == null) {
			return new ArrayList<UserDataInfo>();
		}
		return authDao.find("from UserDataInfo where user.id=? order by id", userId);

	}

	public void deleteUserData(Long userId) {
		if (userId != null) {
			authDao.bulkUpdate("delete from UserDataInfo where user.id=?", userId);
		}
	}

	public void deleteUserData(Long userId, String key) {
		if (userId != null && StringUtils.hasText(key)) {
			authDao.bulkUpdate("delete from UserDataInfo where user.id=? and dataType=?", userId, key);
		}
	}

	public List<String> getUserData(Long userId, String key) {
		if (userId == null || StringUtils.isEmpty(key)) {
			return new ArrayList<String>();
		}
		return authDao.find("select dataValue from UserDataInfo where user.id=? and dataType=? order by id", userId,
				key);
	}

	@Transactional
	public void saveUserData(Long userId, String key, String[] values) {
		deleteUserData(userId, key);
		if (userId != null && StringUtils.hasText(key) && ArrayUtils.isNotEmpty(values)) {
			for (String value : values) {
				UserDataInfo data = new UserDataInfo();
				data.setUser(getUserById(userId));
				data.setDataType(key);
				data.setDataValue(value);
				authDao.save(data);
			}
		}
	}

	public static ShiroUser getCurrentShiroUser() {
		try {
			WebSubject ws = (WebSubject) SecurityUtils.getSubject();
			return (ShiroUser) ws.getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

	public static Long getCurrentUserId() {
		ShiroUser su = getCurrentShiroUser();
		if (su == null) {
			return null;
		} else {
			return su.getId();
		}
	}

	public static String getCurrentLoginName() {
		ShiroUser su = getCurrentShiroUser();
		if (su == null) {
			return "";
		} else {
			return su.getLoginName();
		}
	}

	public UserInfo getCurrentUser() {
		ShiroUser su = UserService.getCurrentShiroUser();
		if (su == null) {
			return null;
		}
		return getUserById(su.getId());
	}

	public List<UserDataInfo> getCurrentUserData() {
		ShiroUser su = getCurrentShiroUser();
		if (su == null) {
			return new ArrayList<UserDataInfo>();
		}
		return getUserData(su.getId());
	}

	public List<String> getCurrentUserData(String key) {
		ShiroUser su = getCurrentShiroUser();
		if (su == null) {
			return null;
		}
		return getUserData(su.getId(), key);
	}

	public Boolean checkLoginName(Long id, String loginName) {
		String hql = "select count(*) from UserInfo where loginName = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(loginName);
		if (id != null) {
			hql += " and id!=?";
			params.add(id);
		}
		long count = authDao.getCount(authDao.findSingle(hql, params.toArray()));
		return count == 0;
	}

	public Boolean checkEmail(Long id, String email) {
		String hql = "select count(*) from UserInfo where email = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(email);
		if (id != null) {
			hql += " and id!=?";
			params.add(id);
		}
		long count = authDao.getCount(authDao.findSingle(hql, params.toArray()));
		return count == 0;
	}

	public Boolean checkPhone(Long id, String phone) {
		String hql = "select count(*) from UserInfo where phone = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(phone);
		if (id != null) {
			hql += " and id!=?";
			params.add(id);
		}
		long count = authDao.getCount(authDao.findSingle(hql, params.toArray()));
		return count == 0;
	}
	
	public List<String> getUserAllChildNode(Long userId) {
		if (userId == null) {
			return new ArrayList<String>();
		}
		List<UserInfo> userInfoList = authDao.find("from UserInfo order by parentid");
		if (NullUtil.isNull(userInfoList)) {
			return new ArrayList<String>();
		}
		List<String> childNode = new ArrayList<String>();
		callBackChildNode(userInfoList,childNode,userId);
		return childNode;
	}
	
	private void callBackChildNode(List<UserInfo> userInfoList,List<String> childList,Long id){
		for (Iterator iterator = userInfoList.iterator(); iterator.hasNext();) {
			UserInfo userInfo = (UserInfo) iterator.next();
			if(id.equals(userInfo.getParentid())){
				callBackChildNode(userInfoList,childList,userInfo.getId());
				childList.add(userInfo.getId()+"");
			}
		}
	}

}

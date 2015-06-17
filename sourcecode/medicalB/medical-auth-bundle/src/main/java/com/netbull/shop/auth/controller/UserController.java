package com.netbull.shop.auth.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.auth.entity.RoleInfo;
import com.netbull.shop.auth.entity.UserDataInfo;
import com.netbull.shop.auth.entity.UserInfo;
import com.netbull.shop.dao.HDao;
import com.netbull.shop.util.DataTablesQuery;
import com.netbull.shop.util.HttpUtils;
import com.netbull.shop.auth.service.AreaService;
import com.netbull.shop.auth.service.LogService;
import com.netbull.shop.auth.service.RoleService;
import com.netbull.shop.auth.service.UserService;

@Controller("userController")
public class UserController {

	@Resource
	private UserService userService;

	@Resource
	private AreaService areaService;

	@Resource
	private RoleService roleService;

	@Resource
	private HDao authDao;
	
	@Resource
	private LogService logService;

	private DataTablesQuery userQuery = new DataTablesQuery() {

		protected String buildQuery(Map<String, String> params, List<Object> paramValues) {
			String hql = "from UserInfo ui where 1=1";
			String name = params.get("name");
			if (StringUtils.hasText(name)) {
				hql += " and (ui.loginName like ? or ui.trueName like ?)";
				paramValues.add("%" + name + "%");
				paramValues.add("%" + name + "%");
			}
			String roleName = params.get("roleName");
			if(StringUtils.hasText(roleName)) {
				hql += " and ui.id in (select rui.user.id from RoleUserInfo rui where rui.role.name like ?)";
				paramValues.add("%" + roleName + "%");
			}
			String status = params.get("status");
			if (StringUtils.hasText(status)) {
				hql += " and ui.status = ?";
				paramValues.add(Integer.parseInt(status));
			}
			String areaId = "";
			String province = params.get("province");
			String city = params.get("city");
			if (StringUtils.hasText(province)) {
				areaId = province;
			}
			if (StringUtils.hasText(city)) {
				areaId = city;
			}
			if (StringUtils.hasText(areaId)) {
				hql += " and (ui.area.id=? or (ui.area.level=3 and ui.area.parentId=?))";
				paramValues.add(Integer.parseInt(areaId));
				paramValues.add(Integer.parseInt(areaId));
			}
			hql += " order by ui.createTime desc";
			return hql;
		}

		protected Object toRowDatas(List<Object> datas) {
			List<Object> rows = new ArrayList<Object>();
			for (Object data : datas) {
				UserInfo userInfo = (UserInfo) data;
				Long userId = userInfo.getId();
				List<Object> row = new ArrayList<Object>();
				row.add(userInfo.getLoginName());
				row.add(userInfo.getTrueName());
				row.add(userInfo.getArea());
				row.add(roleService.getRolesByUser(userId));
				row.add(userInfo.getStatus());
				row.add(userInfo.getCreateTime());
				row.add(userId);
				row.add(userId);
				row.add(userId);
				row.add(userId);
				rows.add(row);
			}
			return rows;
		}

	};

	@RequestMapping("/user")
	public String user() {
		return "auth/user/user";
	}

	@RequestMapping("/user/edit")
	public String editUser(HttpServletRequest req) {
		return "auth/user/editUser";
	}

	@RequestMapping("/user/changePwd")
	public String changePwd() {
		return "auth/user/changePwd";
	}

	@RequestMapping("/user/users")
	@ResponseBody
	public Object queryUsers(HttpServletRequest request) {
		return userQuery.queryPage(HttpUtils.getParams(request), authDao);
	}

	@RequestMapping("/user/save")
	@ResponseBody
	public UserInfo saveUser(Long id, String loginName, String trueName, Integer status, Integer province,
			Integer city, String sex, String phone, String email, Long[] roleIds, String[] platformIds,
			String[] productIds) {
		UserInfo userInfo = new UserInfo();
		if (id != null) {
			userInfo = userService.getUserById(id);
		}
		userInfo.setLoginName(loginName);
		userInfo.setTrueName(trueName);
		userInfo.setStatus(status);
		Integer areaId = city == null ? province : city;
		userInfo.setArea(areaService.getAreaById(areaId));
		userInfo.setSex(sex);
		userInfo.setPhone(phone);
		userInfo.setEmail(email);
		userService.saveUser(userInfo);
		userService.saveUserRole(userInfo.getId(), roleIds);
		userService.saveUserData(userInfo.getId(), UserDataInfo.TYPE_PLATFORM, platformIds);
		userService.saveUserData(userInfo.getId(), UserDataInfo.TYPE_PRODUCT, productIds);
		logService.log("保存用户: " + userInfo.getLoginName());
		return userInfo;
	}

	@RequestMapping("/user/pwd/change")
	@ResponseBody
	public void changePwd(String oldPassword, String newPassword) {
		userService.changePassword(oldPassword, newPassword);
		logService.log("修改密码");
	}

	@RequestMapping("/user/delete")
	@ResponseBody
	public void deleteUser(Long id) {
		UserInfo userInfo = userService.getUserById(id);
		userService.deleteUser(id);
		logService.log("删除用户: " + userInfo.getLoginName());
	}

	@RequestMapping("/user/resetPassword")
	@ResponseBody
	public void resetPassword(Long id) {
		UserInfo userInfo = userService.getUserById(id);
		userService.resetPassword(id);
		logService.log("重置密码: " + userInfo.getLoginName());
	}

	@RequestMapping("/user/findById")
	@ResponseBody
	public UserInfo findById(Long id) {
		return userService.getUserById(id);
	}

	@RequestMapping("/user/findRolesByUser")
	@ResponseBody
	public List<RoleInfo> findRolesByUser(Long userId) {
		return roleService.getRolesByUser(userId);
	}

	@RequestMapping("/user/findDatasByUser")
	@ResponseBody
	public List<String> findDatasByUser(Long userId, String key) {
		return userService.getUserData(userId, key);
	}

	@RequestMapping("/user/myRoles")
	@ResponseBody
	public List<RoleInfo> myRoles() {
		if (UserService.getCurrentShiroUser() == null) {
			return new ArrayList<RoleInfo>();
		}
		return roleService.getRolesByUser(UserService.getCurrentUserId());
	}

	@RequestMapping("/user/permitRoles")
	@ResponseBody
	public List<RoleInfo> permitRoles() {
		if (UserService.getCurrentShiroUser() == null) {
			return new ArrayList<RoleInfo>();
		}
		if ("admin".equals(UserService.getCurrentLoginName())) {
			return roleService.getAllRoles();
		}
		List<RoleInfo> list = roleService.getRolesByUser(UserService.getCurrentUserId());
		return list;

	}

	@RequestMapping("/user/checkLoginName")
	@ResponseBody
	public Object checkLoginName(Long id, String loginName) {
		if (StringUtils.isEmpty(loginName) || userService.checkLoginName(id, loginName)) {
			return true;
		} else {
			return "\"登录名已存在\"";
		}
	}

	@RequestMapping("/user/checkEmail")
	@ResponseBody
	public Object checkEmail(Long id, String email) {
		if (StringUtils.isEmpty(email) || userService.checkEmail(id, email)) {
			return true;
		} else {
			return "\"邮箱已被使用\"";
		}
	}

	@RequestMapping("/user/checkPhone")
	@ResponseBody
	public Object checkPhone(Long id, String phone) {
		if (StringUtils.isEmpty(phone) || userService.checkPhone(id, phone)) {
			return true;
		} else {
			return "\"手机号已被使用\"";
		}
	}

}

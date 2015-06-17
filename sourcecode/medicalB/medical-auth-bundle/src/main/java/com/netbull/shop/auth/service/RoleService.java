package com.netbull.shop.auth.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.shop.auth.entity.MenuInfo;
import com.netbull.shop.auth.entity.RoleInfo;
import com.netbull.shop.auth.entity.RoleMenuInfo;
import com.netbull.shop.auth.entity.UserInfo;
import com.netbull.shop.dao.HDao;

@Service("roleService")
public class RoleService {

	@Resource
	private HDao authDao;

	@Resource
	private UserService userService;

	public RoleInfo getRoleById(Long id) {
		if (id == null) {
			return null;
		}
		return authDao.get(RoleInfo.class, id);
	}

	public List<RoleInfo> getAllRoles() {
		return authDao.find("from RoleInfo order by createTime");
	}

	public List<RoleInfo> getRolesByCreator(Long userId) {
		return authDao.find("select distinct roleInfo from RoleInfo roleInfo, UserInfo userInfo where"
				+ " roleInfo.creator = userInfo.loginName and userInfo.id = ?", userId);
	}

	public List<RoleInfo> getRolesByUser(Long userId) {
		UserInfo userInfo = authDao.get(UserInfo.class, userId);
		if (userInfo == null) {
			return new ArrayList<RoleInfo>();
		}
		if ("admin".equals(userInfo.getLoginName())) {
			return getAllRoles();
		}
		String hql = "from RoleInfo ri where ri.id in (select distinct role.id from RoleUserInfo where user.id=?)"
				+ " or ri.creator=? order by ri.createTime";
		return authDao.find(hql, userId, userInfo.getLoginName());
	}

	public List<RoleInfo> getRolesByUser(String loginName) {
		if ("admin".equals(loginName)) {
			return getAllRoles();
		}
		String hql = "from RoleInfo ri where ri.id in (select distinct role.id from RoleUserInfo where user.loginName=?)"
				+ " or ri.creator=? order by ri.createTime";
		return authDao.find(hql, loginName, loginName);
	}

	public void saveRole(RoleInfo role) {
		Date now = new Date();
		if (role.getId() != null) {
			RoleInfo old = authDao.get(RoleInfo.class, role.getId());
			role.setCreateTime(old.getCreateTime());
			role.setCreator(old.getCreator());
			role.setUpdator(UserService.getCurrentLoginName());
			role.setUpdateTime(now);
		}
		if (role.getCreator() == null) {
			role.setCreator(UserService.getCurrentLoginName());
		}
		if (role.getCreateTime() == null) {
			role.setCreateTime(now);
		}
		authDao.save(role);
	}

	public UserInfo getCreator(Long id) {
		RoleInfo ri = getRoleById(id);
		return userService.getUserByLoginName(ri.getCreator());
	}

	public void saveRoleMenu(Long roleId, Long[] menuIds) {
		if (menuIds == null) {
			return;
		}
		RoleInfo roleInfo = getRoleById(roleId);
		if (roleInfo == null) {
			return;
		}
		authDao.bulkUpdate("delete from RoleMenuInfo where role.id=?", roleId);
		for (Long menuId : menuIds) {
			MenuInfo menuInfo = authDao.get(MenuInfo.class, menuId);
			if (menuInfo == null) {
				continue;
			}
			RoleMenuInfo rmi = new RoleMenuInfo();
			rmi.setRole(roleInfo);
			rmi.setMenu(menuInfo);
			authDao.save(rmi);
		}
	}

	public void deleteRole(Long id) {
		authDao.bulkUpdate("delete from RoleMenuInfo where role.id=?", id);
		authDao.bulkUpdate("delete from RoleUserInfo where role.id=?", id);
		authDao.bulkUpdate("delete from RoleInfo where id=?", id);
	}

	public List<UserInfo> getUsersByRole(Long roleId) {
		return authDao.find("select rui.user from RoleUserInfo rui where rui.role.id = ?", roleId);
	}

}

package com.netbull.shop.auth.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netbull.shop.auth.entity.MenuInfo;
import com.netbull.shop.auth.entity.UserInfo;
import com.netbull.shop.dao.HDao;

@Service("menuService")
public class MenuService {

	@Resource
	private HDao authDao;

	public MenuInfo getMenuById(Long id) {
		if (id == null) {
			return null;
		}
		return authDao.get(MenuInfo.class, id);
	}

	public List<MenuInfo> getMenusByParent(Long parentId) {
		if (parentId == null) {
			return authDao.find("from MenuInfo where parent.id is null order by menuOrder");
		}
		return authDao.find("from MenuInfo where parent.id=? order by menuOrder", parentId);
	}

	public List<MenuInfo> getVisibleMenusByParent(Long parentId) {
		if (parentId == null) {
			return authDao.find("from MenuInfo where parent.id is null and visible is true order by menuOrder");
		}
		return authDao.find("from MenuInfo where parent.id=? and visible is true order by menuOrder", parentId);
	}

	public List<MenuInfo> getVisibleMenusByRole(Long roleId) {
		String hql = "select distinct rmi.menu from RoleMenuInfo rmi where rmi.role.id = ?"
				+ " and rmi.menu.visible is true";
		return authDao.find(hql, roleId);
	}
    
	/**
	 * 查询出当前用户允许的操作
	 * @param loginName
	 * @return
	 */
	public List<MenuInfo> getVisibleMenuActionsByUser(String loginName) {
		// 如果是admin用户，则返回所用可用的操作
		if ("admin".equals(loginName)) {
			return authDao.find("from MenuInfo where menuType = ? and visible is true", MenuInfo.MENU_TYPE_ACTION);
		}
		// 否则返回用户被赋予的可用操作
		return authDao.find("select distinct rmi.menu from RoleUserInfo rui,RoleMenuInfo rmi"
				+ " where rui.role.id=rmi.role.id and rui.user.loginName=?"
				+ " and rmi.menu.menuType = ? and rmi.menu.visible is true", loginName, MenuInfo.MENU_TYPE_ACTION);
	}

	public List<MenuInfo> getAllVisibleMenus() {
		List<MenuInfo> list = authDao.find("from MenuInfo where visible is true order by menuOrder");
		Collections.sort(list);
		return list;
	}

	public List<MenuInfo> getVisibleMenusByUserAndParent(Long userId, Long parentId) {
		if (userId == null) {
			return new ArrayList<MenuInfo>();
		}
		UserInfo userInfo = authDao.get(UserInfo.class, userId);
		if (userInfo == null) {
			return new ArrayList<MenuInfo>();
		}
		if ("admin".equals(userInfo.getLoginName())) {
			return getVisibleMenusByParent(parentId);
		}
		String hql = "from MenuInfo mi where ("
				+ "mi.id in (select distinct rmi.menu.id from RoleUserInfo rui,RoleMenuInfo rmi where rui.role.id=rmi.role.id and rui.user.id=?)"
				+ " or (mi.id in (select id from MenuInfo where creator=?))" + ")";
		if (parentId == null) {
			hql += " and mi.parent is null";
			hql += " order by mi.menuOrder";
			return authDao.find(hql, userId, userInfo.getLoginName());
		} else {
			hql += " and mi.parent.id = ?";
			hql += " order by mi.menuOrder";
			return authDao.find(hql, userId, userInfo.getLoginName(), parentId);
		}
	}

	/**
	 * 一次性返回主页操作菜单
	 * 
	 * @param userId
	 * @return
	 */
	public List<MenuInfo> getPermissionMenusOnceAll(Long userId) {
		if (userId == null) {
			return new ArrayList<MenuInfo>();
		}
		UserInfo userInfo = authDao.get(UserInfo.class, userId);
		if (userInfo == null) {
			return new ArrayList<MenuInfo>();
		}
		if ("admin".equals(userInfo.getLoginName())) {
			return getAllVisibleMenus();
		}
		String hql = "select distinct rmi.menu from RoleUserInfo rui,RoleMenuInfo rmi"
				+ " where rui.role.id=rmi.role.id and rui.user.id=? and rmi.menu.visible is true"
				+ " order by rmi.menu.menuOrder";
		List<MenuInfo> list = authDao.find(hql, userId);
		Collections.sort(list);
		return list;
	}

	public void saveMenu(MenuInfo menu) {
		if (menu.getId() != null) {
			MenuInfo old = authDao.get(MenuInfo.class, menu.getId());
			menu.setCreator(old.getCreator());
		}
		if (menu.getCreator() == null) {
			menu.setCreator(UserService.getCurrentLoginName());
		}
		authDao.save(menu);
	}

	@Transactional
	public void deleteMenu(Long id) {
		MenuInfo menu = getMenuById(id);
		if (menu == null) {
			return;
		}
		clearSubMenus(id);
		if (menu.getParent() != null) {
			authDao.bulkUpdate("update MenuInfo set menuOrder = menuOrder -1 where parent.id=? and menuOrder>?", menu
					.getParent().getId(), menu.getMenuOrder());
		} else {
			authDao.bulkUpdate("update MenuInfo set menuOrder = menuOrder -1 where parent is null and menuOrder>?",
					menu.getMenuOrder());
		}
		authDao.bulkUpdate("delete from RoleMenuInfo where menu.id=?", id);
		authDao.bulkUpdate("delete from MenuInfo where id=?", id);

	}

	@Transactional
	public void clearSubMenus(Long id) {
		List<MenuInfo> subMenus = getMenusByParent(id);
		for (MenuInfo sub : subMenus) {
			clearSubMenus(sub.getId());
			authDao.bulkUpdate("delete from RoleMenuInfo where menu.id=?", sub.getId());
			authDao.bulkUpdate("delete from MenuInfo where id=?", sub.getId());
		}
	}

	@Transactional
	public void clearRoleByMenuId(Long id) {
		List<MenuInfo> subMenus = getMenusByParent(id);
		for (MenuInfo sub : subMenus) {
			clearRoleByMenuId(sub.getId());
		}
		authDao.bulkUpdate("delete from RoleMenuInfo where menu.id=?", id);
	}

	@Transactional
	public void moveMenu(Long id, Long toId, Integer order) {
		if (id == null || order == null) {
			return;
		}
		MenuInfo menu = getMenuById(id);
		MenuInfo to = getMenuById(toId);
		MenuInfo from = menu.getParent();

		if (to == null) {
			if (from == null) {
				smallMove(menu, order);
			} else {
				bigMove(from, to, menu, order);
			}
		} else {
			if (to.equals(from)) {
				smallMove(menu, order);
			} else {
				bigMove(from, to, menu, order);
			}
		}

	}

	@Transactional
	public void copyMenu(Long id, Long toId, Integer order) {
		if (id == null || order == null) {
			return;
		}
		MenuInfo menu = getMenuById(id);
		MenuInfo to = getMenuById(toId);

		MenuInfo _menu = new MenuInfo();
		BeanUtils.copyProperties(menu, _menu);
		_menu.setId(null);
		_menu.setParent(to);
		_menu.setMenuOrder(order);
		saveMenu(_menu);

		List<MenuInfo> children = getMenusByParent(id);
		for (MenuInfo child : children) {
			copyMenu(child.getId(), _menu.getId(), child.getMenuOrder());
		}
	}

	@Transactional
	public void smallMove(MenuInfo menu, Integer order) {
		if (menu == null) {
			return;
		}
		Long pid = menu.getParent() != null ? menu.getParent().getId() : null;
		List<MenuInfo> menus = getMenusByParent(pid);
		menus.remove(menu);
		menus.add(order - 1, menu);
		for (int i = 0; i < menus.size(); i++) {
			menus.get(i).setMenuOrder(i + 1);
			saveMenu(menus.get(i));
		}
	}

	@Transactional
	public void bigMove(MenuInfo from, MenuInfo to, MenuInfo menu, Integer order) {
		clearRoleByMenuId(menu.getId());
		Integer _order = menu.getMenuOrder();
		menu.setParent(to);
		menu.setMenuOrder(order);
		saveMenu(menu);

		if (from != null) {
			authDao.bulkUpdate("update MenuInfo set menuOrder = menuOrder - 1 where parent.id = ? and menuOrder > ?",
					from.getId(), _order);
		} else {
			authDao.bulkUpdate("update MenuInfo set menuOrder = menuOrder - 1 where parent is null and menuOrder > ?",
					_order);
		}

		if (to != null) {
			authDao.bulkUpdate(
					"update MenuInfo set menuOrder = menuOrder + 1 where parent.id = ? and menuOrder >= ? and id !=?",
					to.getId(), order, menu.getId());
		} else {
			authDao.bulkUpdate(
					"update MenuInfo set menuOrder = menuOrder + 1 where parent is null and menuOrder >= ? and id !=?",
					order, menu.getId());
		}
	}
}

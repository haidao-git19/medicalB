package com.netbull.shop.auth.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.auth.entity.MenuInfo;
import com.netbull.shop.auth.entity.RoleInfo;
import com.netbull.shop.auth.entity.UserInfo;
import com.netbull.shop.dao.HDao;
import com.netbull.shop.util.DataTablesQuery;
import com.netbull.shop.util.HttpUtils;
import com.netbull.shop.auth.service.LogService;
import com.netbull.shop.auth.service.MenuService;
import com.netbull.shop.auth.service.RoleService;

@Controller("roleController")
public class RoleController {

	@Resource
	private RoleService roleService;

	@Resource
	private MenuService menuService;

	@Resource
	private HDao authDao;
	
	@Resource
	private LogService logService;


	private DataTablesQuery roleQuery = new DataTablesQuery() {

		@Override
		protected String buildQuery(Map<String, String> params,
				List<Object> paramValues) {
			String hql = "from RoleInfo where 1=1";
			String name = params.get("name");
			if (StringUtils.hasText(name)) {
				hql += " and name like ?";
				paramValues.add("%" + name + "%");
			}
			hql += " order by createTime desc";
			return hql;
		}

		@Override
		protected Object toRowDatas(List<Object> datas) {
			List<Object> rows = new ArrayList<Object>();
			for (Object data : datas) {
				RoleInfo roleInfo = (RoleInfo) data;
				List<Object> row = new ArrayList<Object>();
				row.add(roleInfo.getName());
				row.add(roleInfo.getUpdator());
				row.add(roleInfo.getUpdateTime());
				row.add(roleInfo.getCreator());
				row.add(roleInfo.getCreateTime());
				row.add(roleInfo.getId());
				row.add(roleInfo.getId());
				row.add(roleInfo.getId());
				rows.add(row);
			}
			return rows;
		}
	};

	@RequestMapping("/role")
	public String role() {
		return "auth/role/role";
	}

	@RequestMapping("/role/edit")
	public String edit() {
		return "auth/role/editRole";
	}

	@RequestMapping("/role/query")
	@ResponseBody
	public Map<String, Object> query(HttpServletRequest request) {
		return roleQuery.queryPage(HttpUtils.getParams(request), authDao);
	}

	@RequestMapping("/role/findById")
	@ResponseBody
	public RoleInfo findById(Long id) {
		return roleService.getRoleById(id);
	}

	@RequestMapping("/role/roleMenus")
	@ResponseBody
	public List<MenuInfo> roleMenus(Long roleId) {
		return menuService.getVisibleMenusByRole(roleId);
	}

	@RequestMapping("/role/all")
	@ResponseBody
	public List<RoleInfo> getAll() {
		return roleService.getAllRoles();
	}

	@RequestMapping("/role/delete")
	@ResponseBody
	public Map<String,String> delete(Long id) {
		Map<String,String> ret = new HashMap<String,String>();
		List<UserInfo> users = roleService.getUsersByRole(id);
		if(users.size() > 0) {
			ret.put("error", "角色有关联用户");
			return ret;
		}
		RoleInfo roleInfo = roleService.getRoleById(id);
		roleService.deleteRole(id);
		logService.log("删除角色: " + roleInfo.getName());
		return ret;
	}

	@RequestMapping("/role/save")
	@ResponseBody
	public RoleInfo save(RoleInfo roleInfo, Long[] menuIds) {
		roleService.saveRole(roleInfo);
		roleService.saveRoleMenu(roleInfo.getId(), menuIds);
		logService.log("保存角色: " + roleInfo.getName());
		return roleInfo;
	}

}

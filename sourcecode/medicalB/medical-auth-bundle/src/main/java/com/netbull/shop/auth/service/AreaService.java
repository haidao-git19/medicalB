package com.netbull.shop.auth.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netbull.shop.auth.entity.AreaInfo;
import com.netbull.shop.dao.HDao;
import com.netbull.shop.shiro.ShiroUser;

@Service("areaService")
public class AreaService {

	@Resource
	private HDao authDao;

	public AreaInfo getAreaById(Integer id) {
		if (id == null) {
			return null;
		}
		return authDao.get(AreaInfo.class, id);
	}

	public List<AreaInfo> getAreasByParent(Integer parentId) {
		if(parentId == null) {
			String hql = "from AreaInfo where parentId is null order by id";
			return authDao.find(hql);
		}else {
			String hql = "from AreaInfo where parentId = ? order by id";
			return authDao.find(hql,parentId);
		}
	}

	public List<AreaInfo> getAllAreas() {
		return authDao.find("from AreaInfo order by id");
	}

	public List<AreaInfo> getAreasByLevel(Integer level) {
		return authDao.find("from AreaInfo where level=? order by id", level);
	}

	public List<AreaInfo> getPermitProvinces() {
		List<AreaInfo> list = new ArrayList<AreaInfo>();
		ShiroUser su = UserService.getCurrentShiroUser();
		// 当前没有登录用户返回空
		if (su == null || su.getAreaId() == null) {
			return list;
		}
		// 当前用户没有地域属性返回空
		AreaInfo userArea = getAreaById(su.getAreaId().intValue());
		if (userArea == null) {
			return list;
		}
		// 在当前用户地域上级查找省级
		AreaInfo province = getParent(userArea.getAiid(),
				AreaInfo.LEVEL_PROVINCE);
		if (province != null) {
			list.add(province);
			return list;
		}
		// 没有找到,则当前用户为省级以上权限
		list = authDao.find("from AreaInfo where level <= ? order by id", AreaInfo.LEVEL_PROVINCE);
		return list;
	}

	public List<AreaInfo> getPermitCitys(Integer provinceId) {
		List<AreaInfo> list = new ArrayList<AreaInfo>();
		ShiroUser su = UserService.getCurrentShiroUser();
		if (su == null || su.getAreaId() == null || provinceId == null) {
			return list;
		}
		AreaInfo userArea = getAreaById(su.getAreaId().intValue());
		if (userArea == null) {
			return list;
		}
		// 在当前上级中查找市级地域
		AreaInfo city = getParent(userArea.getAiid(), AreaInfo.LEVEL_CITY);
		if (city != null) {
			if (Integer.valueOf(city.getParentId()).equals(provinceId)) {
				list.add(city);
			}
			return list;
		}
		// 没有找到则是市级以上,这时跟据父级ID罗列,若父级是全国,不返回数据
		AreaInfo areaInfo = getAreaById(provinceId);
		Integer level = areaInfo.getLevel();
		if(level != null && level.equals(AreaInfo.LEVEL_COUNTRY)) {
			return list;
		}
		list = getAreasByParent(provinceId);
		return list;
	}

	@Transactional
	public void saveArea(AreaInfo area) {
		if(area.getAiid() == null) {
			authDao.save(area);
		}else {
			authDao.update(area);
		}
	}

	@Transactional
	public void deleteArea(Integer id) {
		List<AreaInfo> children = getAreasByParent(id);
		for (AreaInfo child : children) {
			deleteArea(child.getAiid());
		}
		authDao.bulkUpdate("delete from AreaInfo where id=?", id);
	}

	public int calcLevel(Integer id) {
		AreaInfo area = getAreaById(id);
		if (area == null) {
			return 0;
		}
		AreaInfo parent = getAreaById(area.getParentId());
		if (parent == null) {
			return AreaInfo.LEVEL_TOP;
		}
		return calcLevel(parent.getAiid()) + 1;
	}

	public void initCalcLevel() {
		for (AreaInfo area : getAllAreas()) {
			int level = calcLevel(area.getAiid());
			area.setLevel(level);
			saveArea(area);
		}
	}

	public boolean isSameAncestor(Integer id1, Integer id2) {
		return isAncestor(id1, id2) || isAncestor(id2, id1);
	}

	public AreaInfo getParent(Integer id, Integer level) {
		if (id == null || level == null) {
			return null;
		}
		AreaInfo area = getAreaById(id);
		if (area == null) {
			return null;
		}
		if (Integer.valueOf(area.getLevel()).equals(level)) {
			return area;
		}
		if (Integer.valueOf(area.getLevel()).compareTo(level) < 0) {
			return null;
		}
		AreaInfo parent = getAreaById(area.getParentId());
		if (parent == null) {
			return null;
		}
		return getParent(parent.getAiid(), level);
	}

	public boolean isAncestor(Integer parentId, Integer childId) {
		if (null == parentId || null == childId) {
			return false;
		}
		AreaInfo parent = getAreaById(parentId);
		if (parent == null) {
			return false;
		}
		AreaInfo _parent = getParent(childId, parent.getLevel());
		if (parent.equals(_parent)) {
			return true;
		}
		return false;
	}

}

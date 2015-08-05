package com.netbull.shop.company.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.company.entity.CompanyVo;
import com.netbull.shop.dao.BaseDao;
import com.netbull.shop.doctor.entity.UserInfo;

@Repository("companyDao")
public class CompanyDao extends BaseDao {
	private static final String MYBATIS_PREFIX = CompanyDao.class.getName();
	@Resource
	private SqlSession session;

	public Page page(Integer iDisplayStart, Integer iDisplayLength,
			Map requestMap) {
		// TODO 设置权限
		requestMap.put("users", handleQueryOrgan());
		return session.page(MYBATIS_PREFIX + ".pageList", MYBATIS_PREFIX
				+ ".count", requestMap, iDisplayStart, iDisplayLength);
	}

	public int save(CompanyVo company) {
		company.setUserID(this.queryCurrentShiroUser().getLoginName());
		return session.insert(MYBATIS_PREFIX + ".save", company);
	}

	public int update(CompanyVo company) {
		return session.insert(MYBATIS_PREFIX + ".update", company);
	}

	@SuppressWarnings("unchecked")
	public CompanyVo findByParam(Map param) {
		//param.put("users", handleQueryOrgan());
		List<CompanyVo> list = session.find(MYBATIS_PREFIX + ".findByParam",
				param);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public int del(int id) {
		return session.delete(MYBATIS_PREFIX + ".del", id);
	}

	/**
	 * @param pharmacy
	 * @return
	 */
	public List<CompanyVo> queryCompany(CompanyVo pharmacy) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("users", handleQueryOrgan());
		List<CompanyVo> list = session.find(MYBATIS_PREFIX + ".findByParam",
				param);
		return list;
	}

	public UserInfo saveUserInfo(UserInfo userInfo) {
		session.insert(MYBATIS_PREFIX + ".saveUserInfo", userInfo);
		return userInfo;
	}

	public int updateUserInfo(UserInfo userInfo) {
		return session.update(MYBATIS_PREFIX + ".updateUserInfo", userInfo);
	}

	public int saveRoleUserInfo(int roleId, long userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("roleId", roleId);
		param.put("userId", userId);
		return session.insert(MYBATIS_PREFIX + ".saveRoleUserInfo", param);
	}
}

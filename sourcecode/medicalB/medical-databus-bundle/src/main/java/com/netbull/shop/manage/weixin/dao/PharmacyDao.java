package com.netbull.shop.manage.weixin.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.dao.BaseDao;

@Repository("pharmacyDao")
public class PharmacyDao extends BaseDao{
	private static final String MYBATIS_PREFIX = PharmacyDao.class.getName();
	@Resource
	private SqlSession session;

	public Map queryPharmacyLogin(Map paramter) {		
		Map docotor = session.selectOne(MYBATIS_PREFIX + ".queryPharmacyLogin",paramter);
		return docotor;
	}
}

package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;

@Repository
public class RecoveryNursingDao {

	private static final String MYBATIS_PREFIX=RecoveryNursingDao.class.getName();
	@Autowired
	private SqlSession session;

	public List<Map> queryList(Map parameter){
		return session.selectList(MYBATIS_PREFIX+".queryList", parameter);
	}
	
	public Map queryDetail(Map parameter){
		return session.selectOne(MYBATIS_PREFIX+".queryDetail", parameter);
	}
	
	public List<Map> queryRecoveryList(Map parameter){
		return session.selectList(MYBATIS_PREFIX+".queryRecoveryList", parameter);
	}
}

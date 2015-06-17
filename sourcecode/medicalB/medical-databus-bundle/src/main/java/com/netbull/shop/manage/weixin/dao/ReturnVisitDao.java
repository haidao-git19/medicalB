package com.netbull.shop.manage.weixin.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;

@Repository
public class ReturnVisitDao {

	private static final String MYBATIS_PREFIX=ReturnVisitDao.class.getName();
	@Autowired
	private SqlSession sqlSession;
	
	public Map queryLastVisit(Map parameter){
		return sqlSession.selectOne(MYBATIS_PREFIX+".queryLastVisit", parameter);
	}
	
}

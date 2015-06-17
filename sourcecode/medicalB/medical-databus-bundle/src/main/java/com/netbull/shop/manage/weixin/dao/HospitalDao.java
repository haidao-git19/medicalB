package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;

@Repository
public class HospitalDao {

	private static final String MYBATIS_PREFIX=HospitalDao.class.getName();
	@Autowired
	private SqlSession sqlSession;
	
	public List<Map> queryRecomList(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryRecomList", parameter);
	}
	
	public List<Map> queryNearList(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryNearList", parameter);
	}
	
	public List<Map> queryVirtualHospital(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryVirtualHospital", parameter);
	}
}

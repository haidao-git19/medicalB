package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;

@Repository
public class DiseaseDao {

	private static final String MYBATIS_PREFIX=DiseaseDao.class.getName();
	@Autowired
	private SqlSession sqlSession;
	
	public List<Map> queryList(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryList", parameter);
	}
	
	public List<Integer> queryDiseaseIDList(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryDiseaseIDList", parameter);
	}
}

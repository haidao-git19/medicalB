package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;

@Repository
public class EvaluateDao {

	private static final String MYBATIS_PREFIX=EvaluateDao.class.getName();
	@Autowired
	private SqlSession sqlSession;
	
	public List<Map> queryList(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryList", parameter);
	}
	
	public Float queryBetterRate(Map parameter){
		return sqlSession.selectOne(MYBATIS_PREFIX+".queryBetterRate", parameter);
	}
	
	public void save(Map parameter){
		sqlSession.insert(MYBATIS_PREFIX+".save", parameter);
	}
	
	public void updateBetterRate(Map parameter){
		sqlSession.update(MYBATIS_PREFIX+".updateBetterRate", parameter);
	}
}

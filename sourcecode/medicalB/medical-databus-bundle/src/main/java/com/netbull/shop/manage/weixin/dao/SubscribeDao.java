package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;

@Repository
public class SubscribeDao {

	private static final String MYBATIS_PREFIX=SubscribeDao.class.getName();
	
	@Autowired
	private SqlSession sqlSession;
	
	public Map querySubscribe(Map parameter){
		return sqlSession.selectOne(MYBATIS_PREFIX+".querySubscribe", parameter);
	}
	
	public void saveSubscribe(Map parameter){
		sqlSession.insert(MYBATIS_PREFIX+".save", parameter);
	}
	
	public List<Map> querySubscribeList(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryList", parameter);
	}
	
	public void cancelOrder(Map parameter){
		sqlSession.update(MYBATIS_PREFIX+".cancelOrder", parameter);
	}
	
	public Map queryByParams(Map parameter){
		return sqlSession.selectOne(MYBATIS_PREFIX+".queryByParams", parameter);
	}
	
	public Long callAuth(Map parameter){
		return sqlSession.selectOne(MYBATIS_PREFIX+".callAuth", parameter);
	}
	
	public Long queryTodaySubscribe(Map parameter){
		return sqlSession.selectOne(MYBATIS_PREFIX+".queryTodaySubscribe", parameter);
	}
	
	public List<Map> queryRcentiSubscribe(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryRcentiSubscribe", parameter);
	}
	
	public void modifyOrder(Map parameter){
		sqlSession.update(MYBATIS_PREFIX+".modifyOrder", parameter);
	}
}

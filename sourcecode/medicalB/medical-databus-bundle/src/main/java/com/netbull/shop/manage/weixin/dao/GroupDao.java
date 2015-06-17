package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;

@Repository
public class GroupDao {

	private static final String MYBATIS_PREFIX=GroupDao.class.getName();
	@Autowired
	private SqlSession sqlSession;
	
	public void save(Map parameter){
		sqlSession.insert(MYBATIS_PREFIX+".save", parameter);
	}
	
	public void update(Map parameter){
		sqlSession.update(MYBATIS_PREFIX+".update", parameter);
	}
	
	public Map queryOne(Map parameter){
		return sqlSession.selectOne(MYBATIS_PREFIX+".queryOne", parameter);
	}
	
	public List<Map> queryFriends(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryFriends", parameter);
	}
}

package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;

@Repository
public class MessageDao {

	private static final String MYBATIS_PREFIX=MessageDao.class.getName();
	@Autowired
	private SqlSession sqlSession;
	
	public List<Map> queryReplyedList(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryList", parameter);
	}
	
	public Integer save(Map parameter){
		return sqlSession.insert(MYBATIS_PREFIX+".save", parameter);
	}
	
	public Integer update(Map parameter){
		return sqlSession.update(MYBATIS_PREFIX+".update", parameter);
	}
}

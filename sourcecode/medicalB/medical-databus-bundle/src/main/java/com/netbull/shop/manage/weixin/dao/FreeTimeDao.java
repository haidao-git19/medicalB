package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;

@Repository
public class FreeTimeDao {
	
	private static final String MYBATIS_PREFIX=FreeTimeDao.class.getName();

	@Autowired
	private SqlSession sqlSession;
	
	public List<Map> queryDutyTimeList(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryDutyTimeList", parameter);
	}
	
	public List<Map> queryFreeTimeList(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryFreeTimeList", parameter);
	}
	
	public Map queryFreeTime(Map parameter){
		return sqlSession.selectOne(MYBATIS_PREFIX+".queryFreeTime", parameter);
	}
}

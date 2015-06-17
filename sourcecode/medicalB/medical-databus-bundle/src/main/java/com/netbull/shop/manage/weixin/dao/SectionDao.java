package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;

@Repository
public class SectionDao {

	private static final String MYBATIS_PREFIX=SectionDao.class.getName();
	@Autowired
	private SqlSession sqlSession;
	
	public List<Map> queryParentList(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryParentList", parameter);
	}
	
	public List<Map> queryChildList(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryChildList", parameter);
	}
	
	public Map queryDetail(Map parameter){
		return sqlSession.selectOne(MYBATIS_PREFIX+".queryDetail", parameter);
	}
}

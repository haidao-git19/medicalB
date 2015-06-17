package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.manage.weixin.vo.EMRecord;

@Repository
public class RecordAttaDao {

	private static final String MYBATIS_PREFIX=RecordAttaDao.class.getName();
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<Map> queryList(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryList", parameter);
	}
	
	public void save(Map parameter){
		sqlSession.insert(MYBATIS_PREFIX+".save", parameter);
	}
}

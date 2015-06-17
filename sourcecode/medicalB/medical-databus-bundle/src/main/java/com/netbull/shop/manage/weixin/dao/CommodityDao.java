package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;

@Repository
public class CommodityDao {

	private static final String MYBATIS_PREFIX=CommodityDao.class.getName();
	@Autowired
	private SqlSession sqlSession;
	
	public List<Map> queryList(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryList", parameter);
	}
}

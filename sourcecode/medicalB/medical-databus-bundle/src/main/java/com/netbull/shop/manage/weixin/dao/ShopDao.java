package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;

@Repository
public class ShopDao {

	private static final String MYBATIS_PREFIX=ShopDao.class.getName();
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<Map> queryNearList(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryNearList", parameter);
	}
	
	public Map queryDetail(Map parameter){
		return sqlSession.selectOne(MYBATIS_PREFIX+".queryDetail", parameter);
	}
	
	public List<Map> queryShopList(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryShopList", parameter);
	}
}

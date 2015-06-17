package com.netbull.shop.navicate.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;

@Repository
public class NavicateDao {
	
	private static final String MYBATIS_PREFIX=NavicateDao.class.getName();

	@Autowired
	private SqlSession session;
	
	public List<Map> queryCatList(Map parameter){
		return session.selectList(MYBATIS_PREFIX+".queryCatList", parameter);
	}
	
	public Map queryById(Integer id){
		return session.selectOne(MYBATIS_PREFIX+".queryById", id);
	}
	
	public List<Map> querySearchConditionList(Map parameter){
		return session.selectList(MYBATIS_PREFIX+".querySearchConditionList", parameter);
	}
	
	public List<Map> queryConditionOptList(Map parameter){
		return session.selectList(MYBATIS_PREFIX+".queryConditionOptList",parameter);
	}
	
	public Map queryOptById(Integer id){
		return session.selectOne(MYBATIS_PREFIX+".queryOptById", id);
	}
	
	public List<Map> queryScMappingByParam(Map parameter){
		return session.selectList(MYBATIS_PREFIX+".queryScMappingByParam", parameter);
	}
	
	public void saveScMapping(Map parameter){
		session.insert(MYBATIS_PREFIX+".saveScMapping", parameter);
	}
	
	public void deleteScMapping(Map parameter){
		session.delete(MYBATIS_PREFIX+".deleteScMapping", parameter);
	}
}

package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;

@Repository
public class PrescribeDao {

	private static final String MYBATIS_PREFIX=PrescribeDao.class.getName();
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<Map> queryList(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryPrescribeList", parameter);
	}
	
	public List<Map> queryDetailList(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryPrescribeDetailList", parameter);
	}
	
	public void save(Map parameter){
		sqlSession.insert(MYBATIS_PREFIX+".save", parameter);
	}
	
	public void addCommodityToPrescribe(Map parameter){
		sqlSession.insert(MYBATIS_PREFIX+".addCommodityToPrescribe",parameter);
	}
	
	public void bindPrescribeToPatient(Map parameter){
		sqlSession.insert(MYBATIS_PREFIX+".bindPrescribeToPatient", parameter);
	}
	
	public Integer update(Map parameter){
		return sqlSession.update(MYBATIS_PREFIX+".update", parameter);
	}
	
	public List<Map> queryRcentiPrescribe(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryRcentiPrescribe", parameter);
	}
}

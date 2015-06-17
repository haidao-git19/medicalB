package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;

@Repository
public class PlusDao {

	private static final String MYBATIS_PREFIX=PlusDao.class.getName();
	@Autowired
	private SqlSession session;
	
	public List<Map> queryList(Map parameter){
		return session.selectList(MYBATIS_PREFIX+".queryDetail", parameter);
	}
	
	public Map queryDetail(Map parameter){
		return session.selectOne(MYBATIS_PREFIX+".queryDetail", parameter);
	}
	
	public Integer update(Map parameter){
		return session.update(MYBATIS_PREFIX+".update", parameter);
	}
	
	public Integer save(Map parameter){
		return session.insert(MYBATIS_PREFIX+".save", parameter);
	}
	
	public List<Map> queryRcentiPlus(Map parameter){
		return session.selectList(MYBATIS_PREFIX+".queryRcentiPlus", parameter);
	}
}

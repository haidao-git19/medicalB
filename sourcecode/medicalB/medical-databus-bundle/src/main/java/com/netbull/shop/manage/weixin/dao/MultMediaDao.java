package com.netbull.shop.manage.weixin.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;

@SuppressWarnings("rawtypes")
@Repository
public class MultMediaDao {
	private static final String MYBATIS_PREFIX = MultMediaDao.class.getName();
	
	@Resource
	private SqlSession session;
    
	public Map queryMultMedia(Map paramter) {		
		Map result = session.selectOne(MYBATIS_PREFIX + ".queryMultMedia",paramter);
		return result;
	}
	
	public Integer modifyMultMedia(Map paramter) {		
		return session.update(MYBATIS_PREFIX + ".modifyMultMedia",paramter);
	}
	
	public Integer saveMultMediaInfo(Map<String,Object> resultMap) {		
		return session.update(MYBATIS_PREFIX + ".saveMultMediaInfo",resultMap);
	}
	
	public Integer saveCallAuthInfo(Map<String,Object> resultMap) {		
		return session.update(MYBATIS_PREFIX + ".saveCallAuthInfo",resultMap);
	}
	
}

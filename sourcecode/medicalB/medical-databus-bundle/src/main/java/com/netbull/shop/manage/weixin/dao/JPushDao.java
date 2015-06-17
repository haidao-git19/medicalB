package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;

@SuppressWarnings("rawtypes")
@Repository
public class JPushDao {
	private static final String MYBATIS_PREFIX = JPushDao.class.getName();
	
	@Resource
	private SqlSession session;
    
	public Integer savePushLogInfo(Map<String,String> resultMap) {		
		return session.update(MYBATIS_PREFIX + ".savePushLogInfo",resultMap);
	}
	
	public List<Map> queryPushLogList(Map paramter) {		
		List<Map> list = session.selectList(MYBATIS_PREFIX + ".queryPushLogList",paramter);
		return list;
	}
	
}

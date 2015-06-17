package com.netbull.web.section.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;

@Repository
public class PlusDao {

	private static final String NAMESPACE=PlusDao.class.getName();
	
	@Autowired
	private SqlSession session;
	
	public long queryPlusSuccessCount(Map parameter){
		return session.selectOne(NAMESPACE+".query_plus_success_count", parameter);
	}
}

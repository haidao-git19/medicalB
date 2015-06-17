package com.netbull.web.index.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.dao.BaseDao;

@Repository("nurseDao")
public class NurseDao extends BaseDao{
	
	private static final String MYBATIS_PREFIX = NurseDao.class.getName();
	@Resource
	private SqlSession session;

	public long getcount() {

		Map<String,Object> param=new HashMap<String, Object>();
		param.put("state", 0l);
		return session.count(MYBATIS_PREFIX+".countNurse", param);
	}

}

package com.netbull.web.index.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.dao.BaseDao;

@Repository("shopDao")
public class ShopDao extends BaseDao{
	
	private static final String MYBATIS_PREFIX = ShopDao.class.getName();
	@Resource
	private SqlSession session;

	public long getcount() {

		Map<String,Object> param=new HashMap<String, Object>();
		param.put("state", 0l);
		return session.count(MYBATIS_PREFIX+".countShop", param);
	}

}

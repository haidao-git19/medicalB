package com.netbull.shop.nursing.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.dao.BaseDao;
import com.netbull.shop.nursing.entity.Recovery;

@Repository
public class RecoveryDao extends BaseDao{

	private static final String MYBATIS_PREFIX=RecoveryDao.class.getName();
	
	@Autowired
	private SqlSession session;
	
	@SuppressWarnings("unchecked")
	public Page page(Integer iDisplayStart, Integer iDisplayLength,Map requestMap) {
		//设置权限
		requestMap.put("users", handleQueryOrgan());
		return session.page(MYBATIS_PREFIX+".pageList", MYBATIS_PREFIX+".count",
				requestMap, iDisplayStart, iDisplayLength);
	}
	
	@SuppressWarnings("static-access")
	public Integer save(Recovery recovery){
		recovery.setUserID(this.queryCurrentShiroUser().getLoginName());
		return session.insert(MYBATIS_PREFIX+".save", recovery);
	}
	
	public Integer update(Recovery recovery){
		return session.update(MYBATIS_PREFIX+".update", recovery);
	}
}

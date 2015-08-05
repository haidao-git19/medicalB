package com.netbull.shop.account.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.dao.BaseDao;

@Repository("accountDao")
public class AccountDao extends BaseDao {
	private static final String MYBATIS_PREFIX = AccountDao.class.getName();
	@Resource
	private SqlSession session;

	public Page page(Integer iDisplayStart, Integer iDisplayLength,
			Map requestMap) {
		return session.page(MYBATIS_PREFIX + ".pageList", MYBATIS_PREFIX
				+ ".count", requestMap, iDisplayStart, iDisplayLength);
	}

}

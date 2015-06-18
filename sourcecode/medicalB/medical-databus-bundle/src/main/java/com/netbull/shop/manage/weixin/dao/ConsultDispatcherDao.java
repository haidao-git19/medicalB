package com.netbull.shop.manage.weixin.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.manage.weixin.vo.ConsultationDispatcher;

@Repository
public class ConsultDispatcherDao {
	private static final String MYBATIS_PREFIX = ConsultDispatcherDao.class
			.getName();
	@Resource
	private SqlSession session;

	public Integer saveConsultDispatcher(
			ConsultationDispatcher consultDispatcher) {
		return session.update(MYBATIS_PREFIX + ".saveConsultDispatcher",
				consultDispatcher);
	}
}

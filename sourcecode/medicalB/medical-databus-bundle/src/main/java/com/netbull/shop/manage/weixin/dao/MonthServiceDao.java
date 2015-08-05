package com.netbull.shop.manage.weixin.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.manage.weixin.vo.MonthService;

@Repository
public class MonthServiceDao {
	private static final String MYBATIS_PREFIX = MonthServiceDao.class
			.getName();

	@Resource
	private SqlSession session;

	public MonthService findAreadyMonth(Map<String, String> requestMap) {
		return (MonthService) session.get(MYBATIS_PREFIX + ".findAreadyMonth",
				requestMap);
	}

	public Integer saveMonthService(Map<String, String> requestMap) {
		return session.insert(MYBATIS_PREFIX + ".saveMonthService", requestMap);

	}

	public void updateMonthPayState(Map<String, Object> requestMap) {
		session.update(MYBATIS_PREFIX + ".updateMonthPayState", requestMap);
	}

	public MonthService queryMonthService(Map<String, String> requestMap) {
		return (MonthService) session.get(MYBATIS_PREFIX + ".queryMonthService",
				requestMap);
	}

}

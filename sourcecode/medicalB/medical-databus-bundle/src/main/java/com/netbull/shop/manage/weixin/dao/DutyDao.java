package com.netbull.shop.manage.weixin.dao;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.manage.weixin.vo.Duty;

@Repository("dutyDao")
public class DutyDao {
	
	private static final String NAMESPACE = DutyDao.class.getName();

	@Resource
	private SqlSession session;

	public int deleteById(long id) {
		return session.delete(NAMESPACE+".delete_by_id", id);
	}
	
	public int save(Duty duty) {
		return session.insert(NAMESPACE+".save", duty);
	}
	
	public int update(Duty duty) {
		return session.update(NAMESPACE+".update", duty);
	}
}

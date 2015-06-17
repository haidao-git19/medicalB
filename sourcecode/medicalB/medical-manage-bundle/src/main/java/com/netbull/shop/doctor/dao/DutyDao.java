package com.netbull.shop.doctor.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.doctor.entity.Duty;

@Repository("dutyDao")
public class DutyDao {
	
	private static final String NAMESPACE = Duty.class.getName();

	@Resource
	private SqlSession session;

	public List<Duty> findByDoctorIDWeekFlag(long doctorID, int weekFlag) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("doctorID", doctorID);
		params.put("weekFlag", weekFlag);
		return session.find(NAMESPACE+".query_by_doctorID_weekFlag", params);
	}
	
	public Duty findById(long id) {
		return session.selectOne(NAMESPACE+".query_by_id", id);
	}
	
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

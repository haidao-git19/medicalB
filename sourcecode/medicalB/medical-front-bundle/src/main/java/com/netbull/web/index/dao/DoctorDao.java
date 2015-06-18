package com.netbull.web.index.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.web.index.entity.Doctor;

@Repository("doctorDao")
public class DoctorDao {
	
	private static final String NAMESPACE = Doctor.class.getName();

	@Resource
	private SqlSession session;

	public List<Doctor> findDoclistBybigsec(Integer id) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("sectionid", id);
		return session.find(NAMESPACE+".query_for_bigSerction", param);
	}
	
	public Doctor findById(long id) {
		return session.selectOne(NAMESPACE+".query_by_id", id);
	}
	public List<Doctor> query_by_hosid(Map paramter) {
		return session.selectOne(NAMESPACE+".query_by_hosid", paramter);
	}
	
	public List<Map> queryRecomDoctorList(Map parameter){
		return session.selectList(NAMESPACE+".queryRecomDoctorList", parameter);
	}
	
	public List<Map> queryDoctorDutyList(Map parameter){
		return session.selectList(NAMESPACE+".queryDoctorDutyList", parameter);
	}
	
	public long querySubscribePlusCount(Map parameter){
		return session.selectOne(NAMESPACE+".query_subscribe_plus_count", parameter);
	}
	
	public long queryIsAudioCTCount(Map parameter){
		return session.selectOne(NAMESPACE+".query_isAudioCT_count", parameter);
	}
	
	public Map queryDoctorFee(Map parameter){
		return session.selectOne(NAMESPACE+".query_doctor_fee", parameter);
	}
	
	public List<Map> queryDoctorFeeList(Map parameter){
		return session.selectList(NAMESPACE+".query_doctor_fee", parameter);
	}
}

package com.netbull.web.index.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.web.index.dao.DoctorDao;
import com.netbull.web.index.entity.Doctor;

@Service("doctorService")
public class DoctorService {
	
	@Resource
	private DoctorDao doctorDao;

	
	public Doctor findById(long id) {
		return doctorDao.findById(id);
	}
	
	
	public List<Doctor> findDoclistBybigsec(Integer doctorID) {
		return doctorDao.findDoclistBybigsec(doctorID);
	}
	
	public List<Doctor> query_by_hosid(Map paramter) {
		return doctorDao.query_by_hosid(paramter);
	}
	
	public List<Map> queryRecomDoctorList(Map parameter){
		return doctorDao.queryRecomDoctorList(parameter);
	}
	
	public List<Map> queryDoctorDutyList(Map parameter){
		return doctorDao.queryDoctorDutyList(parameter);
	}
	
	public long querySubscribePlusCount(Map parameter){
		return doctorDao.querySubscribePlusCount(parameter);
	}
	
	public long queryIsAudioCTCount(Map parameter){
		return doctorDao.queryIsAudioCTCount(parameter);
	}
}

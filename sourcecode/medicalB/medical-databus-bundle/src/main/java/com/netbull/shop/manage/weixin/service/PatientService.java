
package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.PatientDao;

@Service
public class PatientService {

	@Autowired
	private PatientDao patientDao;
	
	public List<Map> queryPatientList(Map parameter){
		return patientDao.queryList(parameter);
	}
	
	public Map queryPatientDetail(Map parameter){
		return patientDao.queryOne(parameter);
	}
	
	public List<Map> myPatientList(Map parameter){
		return patientDao.myPatientList(parameter);
	}
	
	public void saveMyDoctor(Map parameter){
		patientDao.saveMyDoctor(parameter);
	}
	
	public Map queryMyDoctor(Map parameter){
		return patientDao.queryMyDoctor(parameter);
	}
	
	public List<Map> queryMyDoctorList(Map parameter){
		return patientDao.queryMyDoctorList(parameter);
	}
	
	public List<Map> myServiceList(Map parameter){
		return patientDao.myServiceList(parameter);
	}
}

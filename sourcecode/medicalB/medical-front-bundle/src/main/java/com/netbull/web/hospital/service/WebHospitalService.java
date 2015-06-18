package com.netbull.web.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.web.hospital.dao.WebHospitalDao;
import com.netbull.web.hospital.entity.Hospital;

@Service
public class WebHospitalService {

	@Autowired
	private WebHospitalDao hospitalDao;
	
	public Hospital queryByHospitalID(Integer parameter){
		return hospitalDao.queryByHospitalID(parameter);
	}
}

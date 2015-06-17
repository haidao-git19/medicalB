package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.HospitalDao;

@Service
public class HospitalService {

	@Autowired
	private HospitalDao hospitalDao;
	
	public List<Map> queryRecomHospitalList(Map parameter){
		return hospitalDao.queryRecomList(parameter);
	}
	
	public List<Map> queryNearHospitalList(Map parameter){
		return hospitalDao.queryNearList(parameter);
	}
	
	public List<Map> queryVirtualHospital(Map parameter){
		return hospitalDao.queryVirtualHospital(parameter);
	}
}

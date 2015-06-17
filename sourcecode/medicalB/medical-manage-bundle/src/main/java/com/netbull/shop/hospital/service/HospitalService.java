package com.netbull.shop.hospital.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.shop.common.page.Page;
import com.netbull.shop.hospital.dao.HospitalDao;
import com.netbull.shop.hospital.entity.Hospital;


@Service("hospitalService")
public class HospitalService {

	@Resource
	private HospitalDao hospitalDao;


	public Page page(Integer iDisplayStart, Integer iDisplayLength,
			Map requestMap) {
		Page page = hospitalDao.page(iDisplayStart,iDisplayLength,requestMap);
		return page;
	}


	public void save(Hospital hospital) {
		// TODO Auto-generated method stub
		hospitalDao.save(hospital);
	}


	public void update(Hospital hospital) {
		// TODO Auto-generated method stub
		hospitalDao.update(hospital);
	}


	public Hospital findById(Hospital hospital) {
		// TODO Auto-generated method stub
		return hospitalDao.findById(hospital);
	}

	public Hospital findByHospitalId(long hospitalID) {
		return hospitalDao.findByHospitalId(hospitalID);
	}

	public int del(Hospital hospital) {
		// TODO Auto-generated method stub
		return hospitalDao.del(hospital.getHospitalID());
	}
	
	public List<Hospital> findByAreaID(int areaId) {
		return hospitalDao.findByAreaID(areaId);
	}
	
	public List<Map> queryAllHospitalList(Map parameter){
		return hospitalDao.queryAllHospitalList(parameter);
	}
}

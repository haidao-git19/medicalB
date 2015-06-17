package com.netbull.shop.doctor.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.shop.common.page.Page;
import com.netbull.shop.doctor.dao.DoctorFeeDao;
import com.netbull.shop.doctor.entity.DoctorFee;

@Service("doctorFeeService")
public class DoctorFeeService {
	
	@Resource
	private DoctorFeeDao doctorFeeDao;

	public Page page(Integer iDisplayStart, Integer iDisplayLength, Map<String, String> requestMap) {
		return doctorFeeDao.page(iDisplayStart,iDisplayLength,requestMap);
	}
	
	public DoctorFee findById(long id) {
		return doctorFeeDao.findById(id);
	}
	
	public int deleteById(long id) {
		return doctorFeeDao.deleteById(id);
	}
	
	public int save(DoctorFee doctorFee) {
		return doctorFeeDao.save(doctorFee);
	}
	
	public int update(DoctorFee doctorFee) {
		return doctorFeeDao.update(doctorFee);
	}
	
}

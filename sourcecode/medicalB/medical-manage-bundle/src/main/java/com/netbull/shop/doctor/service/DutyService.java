package com.netbull.shop.doctor.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.shop.doctor.dao.DutyDao;
import com.netbull.shop.doctor.entity.Duty;

@Service("dutyService")
public class DutyService {
	
	@Resource
	private DutyDao dutyDao;
	
	public List<Duty> findByDoctorIDWeekFlag(long doctorID, int weekFlag) {
		return dutyDao.findByDoctorIDWeekFlag(doctorID, weekFlag);
	}
	
	public Duty findById(long id) {
		return dutyDao.findById(id);
	}
	
	public int deleteById(long id) {
		return dutyDao.deleteById(id);
	}
	
	public int save(Duty duty) {
		return dutyDao.save(duty);
	}
	
	public int update(Duty duty) {
		return dutyDao.update(duty);
	}
}

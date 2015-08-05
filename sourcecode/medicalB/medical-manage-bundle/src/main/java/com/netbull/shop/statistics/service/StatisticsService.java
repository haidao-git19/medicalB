package com.netbull.shop.statistics.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.common.page.Page;
import com.netbull.shop.statistics.dao.StatisticsDao;

@Service
public class StatisticsService {

	@Autowired
	private StatisticsDao statisticsDao;
	
	public Page queryRegisterUserPage(Integer iDisplayStart, Integer iDisplayLength,Map requestMap){
		return statisticsDao.queryRegisterUserPage(iDisplayStart, iDisplayLength, requestMap);
	}
	
	public Page queryShopOrderPage(Integer iDisplayStart, Integer iDisplayLength,Map requestMap){
		return statisticsDao.queryShopOrderPage(iDisplayStart, iDisplayLength, requestMap);
	}
	
	public Page queryDoctorBusinessPage(Integer iDisplayStart, Integer iDisplayLength,Map requestMap){
		return statisticsDao.queryDoctorBusinessPage(iDisplayStart, iDisplayLength, requestMap);
	}
	
	public Page queryHospitalBusinessPage(Integer iDisplayStart, Integer iDisplayLength,Map requestMap){
		return statisticsDao.queryHospitalBusinessPage(iDisplayStart, iDisplayLength, requestMap);
	}
	
	public Integer queryDRV(Map parameter){
		return statisticsDao.queryDRV(parameter);
	}
	
	public Integer queryMRV(Map parameter){
		return statisticsDao.queryMRV(parameter);
	}

	public Page companyPatientPage(Integer iDisplayStart,
			Integer iDisplayLength, Map<String, String> requestMap) {
		return statisticsDao.queryConpanyPatient(iDisplayStart, iDisplayLength, requestMap);
	}
}

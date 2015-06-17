package com.netbull.web.hospital.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.web.hospital.dao.HospitalDeseaseDao;
import com.netbull.web.hospital.entity.HospitalDesease;

@Service("hospitalDeseaseService")
public class HospitalDeseaseService {
	
	@Resource
	private HospitalDeseaseDao hospitalDeseaseDao;
	/**
	 * 查询某医院所有病种 
	 * @param paramter
	 * @return
	 */
	public List<HospitalDesease> queryDisDeseaList(Map paramter) {		
		return hospitalDeseaseDao.queryDisDeseaList(paramter);
	} 

	/**
	 * 查询某医院病种对应医生信息
	 * @param paramter
	 * @return
	 */
	public List<HospitalDesease> queryHosAllList(Map paramter) {		
		return hospitalDeseaseDao.queryHosAllList(paramter);
	} 

}

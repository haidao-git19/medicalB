package com.netbull.web.hospital.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.web.hospital.dao.HospitalSectionDao;
import com.netbull.web.hospital.entity.HospitalSection;


@Service("hospitalSectionService")
public class HospitalSectionService {

	@Resource
	private HospitalSectionDao  hospitalSectionDao;

	/**
	 * 查询某医院所有一级科室
	 * @param paramter
	 * @return
	 */
	public List<HospitalSection> queryFirstSectionList(Map paramter) {		
		return hospitalSectionDao.queryFirstSectionList(paramter);
	} 

	/**
	 * 查询某医院所有二级科室
	 * @param paramter
	 * @return
	 */
	public List<HospitalSection> queryAllList(Map paramter) {		
		return hospitalSectionDao.queryAllList(paramter);
	} 


}

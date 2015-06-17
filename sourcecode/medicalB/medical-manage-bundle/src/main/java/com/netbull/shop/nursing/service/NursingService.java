package com.netbull.shop.nursing.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.shop.common.page.Page;
import com.netbull.shop.newborn.dao.NewBornDao;
import com.netbull.shop.newborn.entity.NewBorn;
import com.netbull.shop.nursing.dao.NursingDao;
import com.netbull.shop.nursing.entity.Nursing;
import com.netbull.shop.patient.dao.PatientDao;
import com.netbull.shop.patient.entity.Patient;
import com.netbull.shop.patient.entity.Picture;


@Service("nursingService")
public class NursingService {

	@Resource
	private NursingDao  nursingDao;

	public Page page(Integer iDisplayStart, Integer iDisplayLength,
			Map<String, String> requestMap) {
		Page page = nursingDao.page(iDisplayStart,iDisplayLength,requestMap);
		return page;
	}


	public void save(Nursing nursing) {
		// TODO Auto-generated method stub
		nursingDao.save(nursing);
	}


	public void update(Nursing nursing) {
		// TODO Auto-generated method stub
		nursingDao.update(nursing);
	}


	public Nursing findById(Nursing nursing) {
		// TODO Auto-generated method stub
		return nursingDao.findById(nursing);
	}


	public int del(Nursing nursing) {
		// TODO Auto-generated method stub
		return nursingDao.del(nursing.getAdviceID());
	}


}

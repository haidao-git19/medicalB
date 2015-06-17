package com.netbull.shop.newborn.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.shop.common.page.Page;
import com.netbull.shop.newborn.dao.NewBornDao;
import com.netbull.shop.newborn.entity.NewBorn;
import com.netbull.shop.patient.dao.PatientDao;
import com.netbull.shop.patient.entity.Patient;
import com.netbull.shop.patient.entity.Picture;


@Service("newBornService")
public class NewBornService {

	@Resource
	private  NewBornDao  newBornDao;
	
	@Resource
	private  PatientDao  patientDao;

	public Page page(Integer iDisplayStart, Integer iDisplayLength,
			Map<String, String> requestMap) {
		Page page = newBornDao.page(iDisplayStart,iDisplayLength,requestMap);
		return page;
	}


	public void save(NewBorn newBorn) {
		// TODO Auto-generated method stub
		newBornDao.save(newBorn);
	}


	public void update(NewBorn newBorn) {
		// TODO Auto-generated method stub
		newBornDao.update(newBorn);
	}


	public NewBorn findById(NewBorn newBorn) {
		// TODO Auto-generated method stub
		return newBornDao.findById(newBorn);
	}


	public int del(NewBorn newBorn) {
		// TODO Auto-generated method stub
		return newBornDao.del(newBorn.getId());
	}


	public int updatePicture(Picture picture) {
		// TODO Auto-generated method stub
	return	patientDao.updatePicture(picture);
		
	}


	public void savePicture(Picture picture) {
		// TODO Auto-generated method stub
		patientDao.savePicture(picture);
	}


	public String findImg(Picture picture) {
		// TODO Auto-generated method stub
	return patientDao.findImg(picture);
	}
}

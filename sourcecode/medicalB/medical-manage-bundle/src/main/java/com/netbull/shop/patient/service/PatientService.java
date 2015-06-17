package com.netbull.shop.patient.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.shop.common.page.Page;
import com.netbull.shop.patient.dao.PatientDao;
import com.netbull.shop.patient.entity.Patient;
import com.netbull.shop.patient.entity.Picture;


@Service("patientService")
public class PatientService {

	@Resource
	private  PatientDao  patientDao;
	

	public Page page(Integer iDisplayStart, Integer iDisplayLength,
			Map<String, String> requestMap) {
		Page page = patientDao.page(iDisplayStart,iDisplayLength,requestMap);
		return page;
	}


	public void save(Patient patient) {
		// TODO Auto-generated method stub
		patientDao.save(patient);
	}


	public void update(Patient patient) {
		// TODO Auto-generated method stub
		patientDao.update(patient);
	}


	public Patient findById(Patient patient) {
		// TODO Auto-generated method stub
		return patientDao.findById(patient);
	}


	public int del(Patient patient) {
		// TODO Auto-generated method stub
		return patientDao.del(patient.getPatientID());
	}


	public int updatePicture(Picture picture) {
		// TODO Auto-generated method stub
	return	patientDao.updatePicture(picture);
		
	}

	public Patient findByPatientCard(String patientCard) {
		return patientDao.findByPatientCard(patientCard);
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

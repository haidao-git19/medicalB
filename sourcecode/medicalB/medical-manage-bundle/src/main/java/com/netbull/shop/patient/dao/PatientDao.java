package com.netbull.shop.patient.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.dao.BaseDao;
import com.netbull.shop.patient.entity.Patient;
import com.netbull.shop.patient.entity.Picture;

@Repository("patientDao")
public class PatientDao extends BaseDao{
	private static final String MYBATIS_PREFIX = PatientDao.class.getName();
	@Resource
	private SqlSession session;
	

	public Page page(Integer iDisplayStart, Integer iDisplayLength,
			Map requestMap) {
		//设置权限
		requestMap.put("users", handleQueryOrgan());
		return session.page(MYBATIS_PREFIX+".pagePatient", MYBATIS_PREFIX+".countPatient",
				requestMap, iDisplayStart, iDisplayLength);
	}

	public void save(Patient patient) {
		// TODO Auto-generated method stub
		patient.setUserID(this.queryCurrentShiroUser().getLoginName());
		session.insert(MYBATIS_PREFIX+".save", patient);
	}
	
	public void update(Patient patient) {
		// TODO Auto-generated method stub
		session.insert(MYBATIS_PREFIX+".update", patient);
	}

	public Patient findById(Patient patient) {
		// TODO Auto-generated method stub
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("patientID", patient.getPatientID());
		param.put("patientCard", patient.getPatientCard());
		param.put("users", handleQueryOrgan());
		List<Patient> list= session.find(MYBATIS_PREFIX+".findByID", param);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return new Patient();
	}
	
	public Patient findByPatientCard(String patientCard) {
		return session.selectOne(MYBATIS_PREFIX+".findByPatientCard", patientCard);
	}

	public int del(int patientID) {
		// TODO Auto-generated method stub
		return session.delete(MYBATIS_PREFIX+".del",patientID);
	}

	public int updatePicture(Picture picture) {
		// TODO Auto-generated method stub
		return session.update(MYBATIS_PREFIX+".updatePicture",picture);
		
	}

	public void savePicture(Picture picture) {
		// TODO Auto-generated method stub
		 session.insert(MYBATIS_PREFIX+".savePicture",picture);
	}

	public String findImg(Picture picture) {
		// TODO Auto-generated method stub
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("objectID", picture.getObjectID());
		param.put("objectType", picture.getObjectType());
		List list= session.find(MYBATIS_PREFIX+".findPicture",param);
		if(list!=null&&list.size()>0){
			return ((Picture)list.get(0)).getPicUrl();
		}
		return "";
	}
}

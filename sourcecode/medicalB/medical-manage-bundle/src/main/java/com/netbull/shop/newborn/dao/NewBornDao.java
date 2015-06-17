package com.netbull.shop.newborn.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.dao.BaseDao;
import com.netbull.shop.newborn.entity.NewBorn;
import com.netbull.shop.patient.entity.Patient;
import com.netbull.shop.patient.entity.Picture;

@Repository("newBornDao")
public class NewBornDao  extends BaseDao{
	private static final String MYBATIS_PREFIX = NewBornDao.class.getName();
	@Resource
	private SqlSession session;
	

	public Page page(Integer iDisplayStart, Integer iDisplayLength,
			Map requestMap) {
		//设置权限
		requestMap.put("users", handleQueryOrgan());
		return session.page(MYBATIS_PREFIX+".pageList", MYBATIS_PREFIX+".count",
				requestMap, iDisplayStart, iDisplayLength);
	}

	public void save(NewBorn newBorn) {
		// TODO Auto-generated method stub
		newBorn.setUserID(this.queryCurrentShiroUser().getLoginName());
		session.insert(MYBATIS_PREFIX+".save", newBorn);
	}
	
	public void update(NewBorn newBorn) {
		// TODO Auto-generated method stub
		session.insert(MYBATIS_PREFIX+".update", newBorn);
	}

	public NewBorn findById(NewBorn newBorn) {
		// TODO Auto-generated method stub
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("id", newBorn.getId());
		param.put("babiName", newBorn.getBabiName());
		param.put("mamuIDCard", newBorn.getMamuIDCard());
		//设置权限
		param.put("users", handleQueryOrgan());
		List<NewBorn> list= session.find(MYBATIS_PREFIX+".findByID", param);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return new NewBorn();
	}

	public int del(int id) {
		// TODO Auto-generated method stub
		return session.delete(MYBATIS_PREFIX+".del",id);
	}

	public int updatePicture(NewBorn newBorn) {
		// TODO Auto-generated method stub
		return session.update("com.netbull.shop.patient.dao.PatientDao.updatePicture",newBorn);
		
	}

	public void savePicture(NewBorn newBorn) {
		// TODO Auto-generated method stub
		 session.insert("com.netbull.shop.patient.dao.PatientDao.savePicture",newBorn);
	}

	public String findImg(Patient patient) {
		// TODO Auto-generated method stub
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("patientID", patient.getPatientID());
		param.put("patientCard", patient.getPatientCard());
		List list= session.find("com.netbull.shop.patient.dao.PatientDao.findPicture",param);
		if(list!=null&&list.size()>0){
			return ((Picture)list.get(0)).getPicUrl();
		}
		return "";
	}
}

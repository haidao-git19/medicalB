package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.PrescribeDao;

@Service
public class PrescribeService {

	@Autowired
	private PrescribeDao prescribeDao;
	
	public List<Map> queryPrescribeList(Map parameter){
		return prescribeDao.queryList(parameter);
	}
	
	public List<Map> queryPrescribeDetailList(Map parameter){
		return prescribeDao.queryDetailList(parameter);
	}
	
	public void savePrescribe(Map parameter){
		prescribeDao.save(parameter);
	}
	
	public void addCommodityToPrescribe(Map parameter){
		prescribeDao.addCommodityToPrescribe(parameter);
	}
	
	public void bindPrescribeToPatient(Map parameter){
		prescribeDao.bindPrescribeToPatient(parameter);
	}
	
	public Integer update(Map parameter){
		return prescribeDao.update(parameter);
	}
	
	public List<Map> queryRcentiPrescribe(Map parameter){
		return prescribeDao.queryRcentiPrescribe(parameter);
	}
}

package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.RecoveryNursingDao;

@Service
public class RecoveryNursingService {

	@Autowired
	private RecoveryNursingDao recoveryNursingDao;
	
	public List<Map> queryRecoveryNursingList(Map parameter){
		return recoveryNursingDao.queryList(parameter);
	}
	
	public Map queryRecoveryNursingDetail(Map parameter){
		return recoveryNursingDao.queryDetail(parameter);
	}
	
	public List<Map> queryRecoveryList(Map parameter){
		return recoveryNursingDao.queryRecoveryList(parameter);
	}
}

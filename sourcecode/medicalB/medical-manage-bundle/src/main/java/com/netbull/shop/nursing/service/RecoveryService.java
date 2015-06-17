package com.netbull.shop.nursing.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.common.page.Page;
import com.netbull.shop.nursing.dao.RecoveryDao;
import com.netbull.shop.nursing.entity.Recovery;
import com.netbull.shop.util.MyBatisDao;

@Service
public class RecoveryService extends MyBatisDao<Recovery, Integer>{

	@Autowired
	private RecoveryDao recoveryDao;
	
	public Page page(Integer iDisplayStart, Integer iDisplayLength,Map<String, String> requestMap) {
		Page page = recoveryDao.page(iDisplayStart,iDisplayLength,requestMap);
		return page;
	}
	
	public Integer saveRecovery(Recovery recovery){
		return recoveryDao.save(recovery);
	}
	
	public Integer updateRecovery(Recovery recovery){
		return recoveryDao.update(recovery);
	}
}

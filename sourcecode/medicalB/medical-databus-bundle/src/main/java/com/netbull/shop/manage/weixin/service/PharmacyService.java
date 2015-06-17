package com.netbull.shop.manage.weixin.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.PharmacyDao;


@Service("pharmacyService")
public class PharmacyService {

	@Resource
	private  PharmacyDao  pharmacyDao;
	
	public Map queryPharmacyLogin(Map paramter) {		
		return pharmacyDao.queryPharmacyLogin(paramter);
	}

}

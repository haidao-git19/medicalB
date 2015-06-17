package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.DiseaseDao;

@Service
public class DiseaseService {

	@Autowired
	private DiseaseDao diseaseDao;
	
	public List<Map> queryDiseaseList(Map parameter){
		return diseaseDao.queryList(parameter);
	}
	
	public List<Integer> queryDiseaseIDList(Map parameter){
		return diseaseDao.queryDiseaseIDList(parameter);
	}
}

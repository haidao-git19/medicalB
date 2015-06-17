package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.EvaluateDao;

@Service
public class EvaluateService {

	@Autowired
	private EvaluateDao evaluateDao;
	
	public List<Map> queryEvaluateList(Map parameter){
		return evaluateDao.queryList(parameter);
	}
	
	public Float queryBetterRate(Map parameter){
		return evaluateDao.queryBetterRate(parameter);
	}
	
	public void saveEvaluate(Map parameter){
		evaluateDao.save(parameter);
	}
	
	public void updateBetterRate(Map parameter){
		evaluateDao.updateBetterRate(parameter);
	}
}

package com.netbull.shop.manage.weixin.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.ReturnVisitDao;

@Service
public class ReturnVisitService {

	@Autowired
	private ReturnVisitDao returnVisitDao;
	
	public Map queryLastVisitTime(Map parameter){
		return returnVisitDao.queryLastVisit(parameter);
	}
}

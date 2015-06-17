package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.FreeTimeDao;

@Service
public class FreeTimeService {

	@Autowired
	private FreeTimeDao freeTimeDao;
	
	public List<Map> queryDutyTimeList(Map parameter){
		return freeTimeDao.queryDutyTimeList(parameter);
	}
	
	public List<Map> queryFreeTimeList(Map parameter){
		return freeTimeDao.queryFreeTimeList(parameter);
	}
	
	public Map queryFreeTime(Map parameter){
		return freeTimeDao.queryFreeTime(parameter);
	}
}

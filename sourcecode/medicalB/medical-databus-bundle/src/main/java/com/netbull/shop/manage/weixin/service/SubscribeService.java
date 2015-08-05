package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netbull.shop.manage.weixin.dao.SubscribeDao;
import com.netbull.shop.manage.weixin.vo.EMRecord;

@Service
public class SubscribeService {

	@Autowired
	private SubscribeDao subscribeDao;
	
	public Map querySubscribe(Map parameter){
		return subscribeDao.querySubscribe(parameter);
	}
	
	public void saveSubscribe(Map parameter){
		subscribeDao.saveSubscribe(parameter);
	}
	
	public List<Map> querySubscribeList(Map parameter){
		return subscribeDao.querySubscribeList(parameter); 
	}
	@Transactional
	public void cancelOrder(Map parameter){
		subscribeDao.cancelOrder(parameter);
		
	}
	
	public Map querySubscribeByParams(Map parameter){
		return subscribeDao.queryByParams(parameter);
	}
	
	public Long callAuth(Map parameter){
		return subscribeDao.callAuth(parameter);
	}
	
	public Long queryTodaySubscribe(Map parameter){
		return subscribeDao.queryTodaySubscribe(parameter);
	}
	
	public List<Map> queryRcentiSubscribe(Map parameter){
		return subscribeDao.queryRcentiSubscribe(parameter);
	}
	
	public void modifyOrder(Map parameter){
		subscribeDao.modifyOrder(parameter);
	}
}

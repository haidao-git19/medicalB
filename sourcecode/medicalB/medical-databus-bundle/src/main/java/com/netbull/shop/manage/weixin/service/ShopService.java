package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.ShopDao;

@Service
public class ShopService {

	@Autowired
	private ShopDao shopDao;
	
	public List<Map> queryNearShopList(Map parameter){
		return shopDao.queryNearList(parameter);
	}
	
	public Map queryShopDetail(Map parameter){
		return shopDao.queryDetail(parameter);
	}
	
	public List<Map> queryShopList(Map parameter){
		return shopDao.queryShopList(parameter);
	}
}

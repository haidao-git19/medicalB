package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.CommodityDao;

@Service
public class CommodityService {

	@Autowired
	private CommodityDao commodityDao;
	
	public List<Map> queryCommodityList(Map parameter){
		return commodityDao.queryList(parameter);
	}
}

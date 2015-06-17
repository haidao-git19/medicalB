package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.PlusDao;

@Service
public class PlusService {

	@Autowired
	private PlusDao plusDao;
	
	public List<Map> queryPlusList(Map parameter){
		return plusDao.queryList(parameter);
	}
	
	public Map queryPlusDetail(Map parameter){
		return plusDao.queryDetail(parameter);
	}
	
	public Integer updatePlus(Map parameter){
		return plusDao.update(parameter);
	}
	
	public Integer savePlus(Map parameter){
		return plusDao.save(parameter);
	}
	
	public List<Map> queryRcentiPlus(Map parameter){
		return plusDao.queryRcentiPlus(parameter);
	}
}

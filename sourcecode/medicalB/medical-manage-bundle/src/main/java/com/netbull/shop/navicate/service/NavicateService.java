package com.netbull.shop.navicate.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.navicate.dao.NavicateDao;

@Service
public class NavicateService {

	@Autowired
	private NavicateDao navicateDao;
	
	public List<Map> queryNavicateList(Map parameter){
		return navicateDao.queryCatList(parameter);
	}
	
	public Map queryNavicateById(Integer id){
		return navicateDao.queryById(id);
	}
	
	public List<Map> querySearchConditionList(Map parameter){
		return navicateDao.querySearchConditionList(parameter);
	}
	
	public List<Map> queryConditionOptList(Map parameter){
		return navicateDao.queryConditionOptList(parameter);
	}
	
	public Map queryOptById(Integer id){
		return navicateDao.queryOptById(id);
	}
	
	public List<Map> queryScMappingByParam(Map parameter){
		return navicateDao.queryScMappingByParam(parameter);
	}
	
	public void saveScMapping(Map parameter){
		navicateDao.saveScMapping(parameter);
	}
	
	public void deleteScMapping(Map parameter){
		navicateDao.deleteScMapping(parameter);
	}
}

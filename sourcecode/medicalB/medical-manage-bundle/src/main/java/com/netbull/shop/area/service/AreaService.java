package com.netbull.shop.area.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.shop.area.dao.AreaDao;
import com.netbull.shop.area.entity.Area;
import com.netbull.shop.util.MyBatisDao;

@Service("areaService")
public class AreaService extends MyBatisDao<Area, Integer>{

	@Resource
	private AreaDao areaDao;
	public List<Area> handleAllArea(){
		return areaDao.findAll();
	}
	
	public List<Area> queryAreasByParams(Map parameter){
		return areaDao.queryByParams(parameter);
	}
	
	public Map<Integer,String> handleAllAreaMap(){
		List<Area> areaList=areaDao.findAll();
		Map<Integer,String> latnMap=new HashMap<Integer, String>();
		
		for (int i = 0; i < areaList.size(); i++) {
			
			latnMap.put(areaList.get(i).getAreaID(), areaList.get(i).getAreaName());
		}
		return latnMap;
	}
}

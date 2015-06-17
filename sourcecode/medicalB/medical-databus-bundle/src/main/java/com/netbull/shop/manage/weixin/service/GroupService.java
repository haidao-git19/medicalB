package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.GroupDao;

@Service
public class GroupService {

	@Autowired
	private GroupDao groupDao;

	public void addRelation(Map parameter){
		groupDao.save(parameter);
	}
	
	public void modifyRelation(Map parameter){
		groupDao.update(parameter);
	}
	
	public Map queryRelationBetw(Map parameter){
		return groupDao.queryOne(parameter);
	}
	
	public List<Map> queryFriends(Map parameter){
		return groupDao.queryFriends(parameter);
	}
}

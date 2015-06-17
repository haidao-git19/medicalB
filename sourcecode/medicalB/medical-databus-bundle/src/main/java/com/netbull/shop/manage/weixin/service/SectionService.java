package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.SectionDao;

@Service
public class SectionService {

	@Autowired
	private SectionDao sectionDao;
	
	public List<Map> queryParentSectionList(Map parameter){
		return sectionDao.queryParentList(parameter);
	}
	
	public List<Map> queryChildSectionList(Map parameter){
		return sectionDao.queryChildList(parameter);
	}
	
	public Map querySectionDetail(Map parameter){
		return sectionDao.queryDetail(parameter);
	}
}

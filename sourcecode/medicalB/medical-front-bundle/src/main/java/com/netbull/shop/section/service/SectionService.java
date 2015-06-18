package com.netbull.shop.section.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.shop.section.dao.SectionDao;
import com.netbull.shop.section.entity.Section;

@Service("sectionService")
public class SectionService {
	
	@Resource
	private SectionDao sectionDao;

	public List<Section> findFirstLevelSections() {
		return sectionDao.findFirstLevelSections();
	}
	
	public List<Section> findSecondLevelSections(long sectionId) {
		return sectionDao.findBySectionId(sectionId);
	}
	
	public Section findById(long id) {
		return sectionDao.findById(id);
	}
	
	public List<Section> querySectionList(Map parameter){
		return sectionDao.queryList(parameter);
	}
	
	public Integer saveRelatedSection(Map parameter){
		return sectionDao.saveRelatedSection(parameter);
	}
	
	public List<Map> queryRelatedSectionList(Map parameter){
		return sectionDao.queryRelatedSectionList(parameter);
	}
	
	public Map queryRelatedSection(Map parameter){
		return sectionDao.queryRelatedSection(parameter);
	}
	
	public Integer deleteRelatedSection(Map parameter){
		return sectionDao.deleteRelatedSection(parameter);
	}
	
	public List<Section> handleAllSection(){
		return sectionDao.findAll();
	}
	
	public Map<Integer,String> handleAllAreaMap(){
		List<Section> areaList=sectionDao.findAll();
		Map<Integer,String> latnMap=new HashMap<Integer, String>();
		for (int i = 0; i < areaList.size(); i++) {
			latnMap.put(Integer.valueOf(areaList.get(i).getId()+""), areaList.get(i).getAttrname());
		}
		return latnMap;
	}
}

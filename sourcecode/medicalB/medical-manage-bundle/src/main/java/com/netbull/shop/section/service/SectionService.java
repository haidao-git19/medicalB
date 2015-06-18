package com.netbull.shop.section.service;

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
	
	public List<Section> queryAimLevelSections(Map parameter){
		return sectionDao.queryAimLevelSections(parameter);
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
	
	public Integer updateRelatedSection(Map parameter){
		return sectionDao.updateRelatedSection(parameter);
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
}

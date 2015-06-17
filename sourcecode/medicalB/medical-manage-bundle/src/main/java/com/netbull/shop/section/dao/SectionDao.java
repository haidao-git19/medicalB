package com.netbull.shop.section.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.section.entity.Section;

@Repository("sectionDao")
public class SectionDao {
	
	private static final String NAMESPACE = Section.class.getName();
	
	@Resource
	private SqlSession session;
	
	public List<Section> findFirstLevelSections() {
		return session.find(NAMESPACE+".findFirstLevelSections");
	}
	
	public List<Section> queryAimLevelSections(Map parameter){
		return session.selectList(NAMESPACE+".queryAimLevelSections", parameter);
	}
	
	public List<Section> findBySectionId(long sectionId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sectionId", sectionId);
		return session.find(NAMESPACE+".findBySectionId", param);
	}
	
	public Section findById(long id) {
		return session.selectOne(NAMESPACE+".findById", id);
	}
	
	public List<Section> queryList(Map parameter){
		return session.selectList(NAMESPACE+".queryList", parameter);
	}
	
	public Integer saveRelatedSection(Map parameter){
		return session.insert(NAMESPACE+".saveRelatedSection", parameter);
	}
	
	public List<Map> queryRelatedSectionList(Map parameter){
		return session.selectList(NAMESPACE+".queryRelatedSection", parameter);
	}
	
	public Integer deleteRelatedSection(Map parameter){
		return session.delete(NAMESPACE+".deleteRelatedSection", parameter);
	}
}

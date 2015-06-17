package com.netbull.shop.disease.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.disease.entity.Disease;

@Repository("diseaseDao")
public class DiseaseDao {
	
	private static final String NAMESPACE = Disease.class.getName();

	@Resource
	private SqlSession session;
	
	public List<Disease> findBySectionID(long sectionID) {
		return session.selectList(NAMESPACE + ".query_by_sectionID", sectionID);
	}
	
}

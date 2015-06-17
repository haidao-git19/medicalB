package com.netbull.shop.disease.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.shop.disease.dao.DiseaseDao;
import com.netbull.shop.disease.entity.Disease;

@Service("diseaseService")
public class DiseaseService {
	
	@Resource
	private DiseaseDao diseaseDao;

	public List<Disease> findBySectionID(long sectionID) {
		return diseaseDao.findBySectionID(sectionID);
	}
	
}

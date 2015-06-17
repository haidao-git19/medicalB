package com.netbull.shop.emr.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.shop.common.page.Page;
import com.netbull.shop.emr.dao.EMRecordAttaDao;
import com.netbull.shop.emr.entity.EMRecordAtta;

@Service("emrecordAttaService")
public class EMRecordAttaService {
	
	@Resource
	private EMRecordAttaDao emrecordAttaDao;
	
	public Page page(Integer iDisplayStart, Integer iDisplayLength, Map<String, String> requestMap) {
		return emrecordAttaDao.page(iDisplayStart, iDisplayLength, requestMap);
	}
	
	public EMRecordAtta findById(long id) {
		return emrecordAttaDao.findById(id);
	}
	
	public int deleteById(long id) {
		return emrecordAttaDao.deleteById(id);
	}
	
	public int save(EMRecordAtta emrecordAtta) {
		return emrecordAttaDao.save(emrecordAtta);
	}
	
	public int update(EMRecordAtta emrecordAtta) {
		return emrecordAttaDao.update(emrecordAtta);
	}
}

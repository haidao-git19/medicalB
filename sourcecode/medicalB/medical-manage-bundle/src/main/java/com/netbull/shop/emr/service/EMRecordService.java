package com.netbull.shop.emr.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.shop.common.page.Page;
import com.netbull.shop.emr.dao.EMRecordAttaDao;
import com.netbull.shop.emr.dao.EMRecordDao;
import com.netbull.shop.emr.entity.EMRecord;

@Service("emrecordService")
public class EMRecordService {
	
	@Resource
	private EMRecordDao emrecordDao;
	@Resource
	private EMRecordAttaDao emrecordAttaDao;
	
	public Page page(Integer iDisplayStart, Integer iDisplayLength, Map<String, String> requestMap) {
		return emrecordDao.page(iDisplayStart, iDisplayLength, requestMap);
	}

	public EMRecord findById(long id) {
		return emrecordDao.findById(id);
	}
	
	public int deleteById(long id) {
		emrecordAttaDao.deleteByRecordID(id);
		return emrecordDao.deleteById(id);
	}
	
	public int save(EMRecord emrecord) {
		return emrecordDao.save(emrecord);
	}
	
	public int update(EMRecord emrecord) {
		return emrecordDao.update(emrecord);
	}
}

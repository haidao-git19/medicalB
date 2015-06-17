package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.RecordAttaDao;
import com.netbull.shop.manage.weixin.vo.EMRecord;

@Service
public class RecordAttaService {

	@Autowired
	private RecordAttaDao recordAttaDao;
	
	public List<Map> queryRecordAttaList(Map parameter){
		return recordAttaDao.queryList(parameter);
	}
	
	public void saveAttaRecord(Map parameter){
		recordAttaDao.save(parameter);
	}
}

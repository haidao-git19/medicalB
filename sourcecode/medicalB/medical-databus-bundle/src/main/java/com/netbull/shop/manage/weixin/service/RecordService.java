package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.common.vo.Patient;
import com.netbull.shop.manage.weixin.dao.RecordDao;
import com.netbull.shop.manage.weixin.vo.EMRecord;

@Service
public class RecordService {

	@Autowired
	private RecordDao recordDao;
	
	public Map queryRecordDetail(Map parameter){
		return recordDao.queryDetail(parameter);
	}
	
	public List<Map> queryRecordList(Map parameter){
		return recordDao.queryList(parameter);
	}
	
	public void saveRecord(EMRecord parameter){
		recordDao.save(parameter);
	}
	
	public Integer updateRecord(Map parameter) {		
		return recordDao.updateRecord(parameter);
	}
	
	public void saveRecordRelation(Map parameter){
		recordDao.saveRecordRelation(parameter);
	}
	
	public Integer updateAllRecord(EMRecord eMRecord) {		
		return recordDao.updateAllRecord(eMRecord);
	}
	
	public Integer deleteRecord(EMRecord eMRecord) {		
		return recordDao.deleteRecord(eMRecord);
	}
	
	public Long queryRecordRelationCount(Map parameter) {		
		return recordDao.queryRecordRelationCount(parameter);
	}
}

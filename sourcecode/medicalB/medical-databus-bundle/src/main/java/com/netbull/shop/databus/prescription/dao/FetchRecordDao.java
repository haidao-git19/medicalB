package com.netbull.shop.databus.prescription.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;

@Repository("fetchRecordDao")
public class FetchRecordDao {
	private static final Logger logger = Logger.getLogger("daoLog");

	private static final String NAMESPACE = FetchRecordDao.class.getName();
	@Resource
	private SqlSession session;

	public Map findFetchRecord(Map<String, String> requestMap) {
		return (Map) session.get(NAMESPACE + ".findFecthRecord", requestMap);
	}

	public Integer saveRecord(Map<String, String> requestMap) {
		return session.insert(NAMESPACE + ".saveFetchRecord", requestMap);
	}

}

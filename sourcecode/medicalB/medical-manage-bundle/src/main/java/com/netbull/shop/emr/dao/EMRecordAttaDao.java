package com.netbull.shop.emr.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.emr.entity.EMRecordAtta;

@Repository("emrecordAttaDao")
public class EMRecordAttaDao {
	
	private static final String NAMESPACE = EMRecordAtta.class.getName();

	@Resource
	private SqlSession session;

	public Page page(Integer iDisplayStart, Integer iDisplayLength, Map<String, String> requestMap) {
		return session.page(NAMESPACE + ".query_for_pagination", NAMESPACE + ".totalCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	public EMRecordAtta findById(long id) {
		return session.selectOne(NAMESPACE+".query_by_id", id);
	}
	
	public int deleteById(long id) {
		return session.delete(NAMESPACE+".delete_by_id", id);
	}
	
	public int deleteByRecordID(long recordID) {
		return session.delete(NAMESPACE+".delete_by_recordID", recordID);
	}
	
	public int save(EMRecordAtta emrecordAtta) {
		return session.insert(NAMESPACE+".save", emrecordAtta);
	}
	
	public int update(EMRecordAtta emrecordAtta) {
		return session.update(NAMESPACE+".update", emrecordAtta);
	}
}

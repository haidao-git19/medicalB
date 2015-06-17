package com.netbull.shop.emr.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.emr.entity.EMRecord;

@Repository("emrecordDao")
public class EMRecordDao {
	
	private static final String NAMESPACE = EMRecord.class.getName();

	@Resource
	private SqlSession session;
	
	public Page page(Integer iDisplayStart, Integer iDisplayLength, Map<String, String> requestMap) {
		return session.page(NAMESPACE + ".query_for_pagination", NAMESPACE + ".totalCount", requestMap, iDisplayStart, iDisplayLength);
	}

	public EMRecord findById(long id) {
		return session.selectOne(NAMESPACE+".query_by_id", id);
	}
	
	public int deleteById(long id) {
		return session.delete(NAMESPACE+".delete_by_id", id);
	}
	
	public int save(EMRecord emrecord) {
		return session.insert(NAMESPACE+".save", emrecord);
	}
	
	public int update(EMRecord emrecord) {
		return session.update(NAMESPACE+".update", emrecord);
	}
}

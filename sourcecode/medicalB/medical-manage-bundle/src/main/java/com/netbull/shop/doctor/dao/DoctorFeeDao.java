package com.netbull.shop.doctor.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.doctor.entity.DoctorFee;

@Repository("doctorFeeDao")
public class DoctorFeeDao {
	
	private static final String NAMESPACE = DoctorFee.class.getName();

	@Resource
	private SqlSession session;

	public Page page(Integer iDisplayStart, Integer iDisplayLength, Map<String, String> requestMap) {
		return session.page(NAMESPACE + ".query_for_pagination", NAMESPACE + ".totalCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	public DoctorFee findById(long id) {
		return session.selectOne(NAMESPACE+".query_by_id", id);
	}
	
	public int deleteById(long id) {
		return session.delete(NAMESPACE+".delete_by_id", id);
	}
	
	public int save(DoctorFee doctorFee) {
		return session.insert(NAMESPACE+".save", doctorFee);
	}
	
	public int update(DoctorFee doctorFee) {
		return session.update(NAMESPACE+".update", doctorFee);
	}
}

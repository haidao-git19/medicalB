package com.netbull.web.hospital.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.web.hospital.entity.Hospital;

@Repository
public class WebHospitalDao {

	private static final String NAMESPACE=WebHospitalDao.class.getName();
	
	@Autowired
	private SqlSession session;
	
	public Hospital queryByHospitalID(Integer parameter){
		return session.selectOne(NAMESPACE+".findByHospitalID", parameter);
	}
}

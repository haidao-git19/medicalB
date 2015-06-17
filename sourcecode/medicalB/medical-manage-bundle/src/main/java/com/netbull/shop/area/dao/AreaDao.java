package com.netbull.shop.area.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.area.entity.Area;
import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.hospital.dao.HospitalDao;
import com.netbull.shop.hospital.entity.Hospital;

@Repository("areaDao")
public class AreaDao {

	private static final String MYBATIS_PREFIX = AreaDao.class.getName();
	@Resource
	private SqlSession session;
	
	public List<Area> findAll() {
		// TODO Auto-generated method stub
		return session.find(MYBATIS_PREFIX+".findAll");
	}
}

package com.netbull.web.hospital.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.dao.BaseDao;
import com.netbull.web.hospital.entity.HospitalSection;

@Repository("hospitalSectionDao")
public class HospitalSectionDao  extends BaseDao{
	private static final String MYBATIS_PREFIX = HospitalSectionDao.class.getName();
	@Resource
	private SqlSession session;
	
	/**
	 * 查询某医院所有一级科室
	 * @param paramter
	 * @return
	 */
	public List<HospitalSection> queryFirstSectionList(Map paramter) {		
		List<HospitalSection> list = session.selectList(MYBATIS_PREFIX + ".queryFirstSectionList",paramter);
		return list;
	} 

	/**
	 * 查询某医院所有二级科室
	 * @param paramter
	 * @return
	 */
	public List<HospitalSection> queryAllList(Map paramter) {		
		List<HospitalSection> list = session.selectList(MYBATIS_PREFIX + ".queryAllList",paramter);
		return list;
	} 
	
}

package com.netbull.web.hospital.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.dao.BaseDao;
import com.netbull.web.hospital.entity.HospitalDesease;

@Repository("hospitalDeseaseDao")
public class HospitalDeseaseDao extends BaseDao{
	private static final String MYBATIS_PREFIX = HospitalDeseaseDao.class.getName();
	@Resource
	private SqlSession session;
	
	/**
	 * 查询某医院所有病种 
	 * @param paramter
	 * @return
	 */
	public List<HospitalDesease> queryDisDeseaList(Map paramter) {		
		List<HospitalDesease> list = session.selectList(MYBATIS_PREFIX + ".queryDisDeseaList",paramter);
		return list;
	} 

	/**
	 * 查询某医院病种对应医生信息
	 * @param paramter
	 * @return
	 */
	public List<HospitalDesease> queryHosAllList(Map paramter) {		
		List<HospitalDesease> list = session.selectList(MYBATIS_PREFIX + ".queryHosAllList",paramter);
		return list;
	} 

}

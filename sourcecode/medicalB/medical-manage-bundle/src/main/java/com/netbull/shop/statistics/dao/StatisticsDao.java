package com.netbull.shop.statistics.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.dao.BaseDao;
import com.netbull.shop.doctor.dao.DoctorDao;
import com.netbull.shop.doctor.entity.Doctor;
import com.netbull.shop.doctor.service.DoctorService;
import com.netbull.shop.shiro.ShiroUser;

@Repository
public class StatisticsDao extends BaseDao{

	private static final String MYBATIS_PREFIX=StatisticsDao.class.getName();
	@Autowired
	private SqlSession session;
	@Autowired
	private DoctorDao doctorDao;
	
	public Page queryRegisterUserPage(Integer iDisplayStart, Integer iDisplayLength,Map requestMap){
		return session.page(MYBATIS_PREFIX+".queryRegisterUserList", MYBATIS_PREFIX+".queryRegisterUserCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	public Page queryShopOrderPage(Integer iDisplayStart, Integer iDisplayLength,Map requestMap){
		//requestMap.put("users", this.handleQueryOrgan());
		return session.page(MYBATIS_PREFIX+".queryShopOrderList", MYBATIS_PREFIX+".queryShopOrderCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page queryDoctorBusinessPage(Integer iDisplayStart, Integer iDisplayLength,Map requestMap){
		ShiroUser currentUser=queryCurrentShiroUser();
		
		Map parameter=new HashMap();
		parameter.put("loginID", currentUser.getId());
		Doctor doctor=doctorDao.queryByParam(parameter);
		if(!NullUtil.isNull(doctor)){
			requestMap.put("doctorId", doctor.getDoctorID());
			requestMap.put("payStatus", 1);
		}else{
			requestMap.put("creator", currentUser.getId());
		}
		return session.page(MYBATIS_PREFIX+".queryDoctorBusinessList", MYBATIS_PREFIX+".queryDoctorBusinessCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	public Page queryHospitalBusinessPage(Integer iDisplayStart, Integer iDisplayLength,Map requestMap){
		requestMap.put("users", this.handleQueryOrgan());
		return session.page(MYBATIS_PREFIX+".queryHospitalBusinessList", MYBATIS_PREFIX+".queryHospitalBusinessCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	public Page queryConpanyPatient(Integer iDisplayStart, Integer iDisplayLength,Map requestMap){
		return session.page(MYBATIS_PREFIX+".queryConpanyPatient", MYBATIS_PREFIX+".queryConpanyPatientCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	public Integer queryDRV(Map parameter){
		return session.selectOne(MYBATIS_PREFIX+".queryDRV", parameter);
	}
	
	public Integer queryMRV(Map parameter){
		return session.selectOne(MYBATIS_PREFIX+".queryMRV", parameter);
	}
	
}

package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.DoctorDao;
import com.netbull.shop.manage.weixin.vo.Doctor;
import com.netbull.shop.manage.weixin.vo.DoctorGroup;

@Service
public class DoctorService {
	private static final Log log = LogFactory.getLog(DoctorService.class);
    
	@Resource
	private DoctorDao doctorDao;

	/**
	 * 分页查询医生列表
	 * @param paramter
	 * @return
	 */
	public List<Map> queryDoctorList(Map paramter) throws RuntimeException{
		return doctorDao.queryDoctorList(paramter);
	}
	
	public List<Map> queryDoctorListByTime(Map paramter) throws RuntimeException{
		return doctorDao.queryDoctorListByTime(paramter);
	}
	
	public List<Map> queryDoctorAllList(Map paramter) throws RuntimeException{
		return doctorDao.queryDoctorAllList(paramter);
	}
	
	public List<Map> queryDoctorListByParams(Map parameter){
		return doctorDao.queryDoctorListByParams(parameter);
	}

	public List<Map> queryRecommendDoctorList(Map paramter) {		
		return doctorDao.queryRecommendDoctorList(paramter);
	}
	
	public List<Map> queryRecommendDoctorListByDisease(Map paramter) {		
		return doctorDao.queryRecommendDoctorListByDisease(paramter);
	}
	
	public Map queryDoctorDetail(Map paramter) {		
		return doctorDao.queryDoctorDetail(paramter);
	}
	
	/**
	 * 修改医生密码信息
	 * @param paramter
	 * @return
	 */
	public Integer modifyPassword(Map paramter) {		
		return doctorDao.modifyPassword(paramter);
	}
	
	/**
	 * 添加医生信息
	 * @param paramter
	 * @return
	 */
	public Integer saveDoctorBaseInfo(Doctor doctor) {		
		return doctorDao.saveDoctorBaseInfo(doctor);
	}
	
	/**
	 * 更新医生信息
	 * @param map
	 */
	public Integer modifyDoctorBaseInfo(Doctor doctor){
		return doctorDao.modifyDoctorBaseInfo(doctor);
	}
	
	public Map queryDoctorLogin(Map paramter) {		
		return doctorDao.queryDoctorLogin(paramter);
	}
	
	public List<Map> myDoctorList(Map paramter) {		
		return doctorDao.myDoctorList(paramter);
	}
	
	public List<Map> querySelfDoctorGroupConsulationList(Map paramter) {		
		return doctorDao.querySelfDoctorGroupConsulationList(paramter);
	}
	
	public List<Map> queryOtherDoctorGroupConsulationList(Map paramter) {		
		return doctorDao.queryOtherDoctorGroupConsulationList(paramter);
	}
	
	public Integer saveDoctorGroupConsulation(DoctorGroup doctor) {		
		return doctorDao.saveDoctorGroupConsulation(doctor);
	}
	
	public Integer saveDoctorGroupConsulationR(Map paramter) {		
		return doctorDao.saveDoctorGroupConsulationR(paramter);
	}
	
	public Integer modifyDoctorGroupConsulation(Map paramter){
		return doctorDao.modifyDoctorGroupConsulation(paramter);
	}
	
	public Integer modifyDoctorGroupConsulationR(Map paramter){
		return doctorDao.modifyDoctorGroupConsulationR(paramter);
	}
	public Map queryDoctorGroupConsulationRDetail(Map paramter) {		
		return doctorDao.queryDoctorGroupConsulationRDetail(paramter);
	}

	public List<Map> queryNearDoctorList(Map requestMap) {
		return doctorDao.queryNearDoctorList(requestMap);
	}
}

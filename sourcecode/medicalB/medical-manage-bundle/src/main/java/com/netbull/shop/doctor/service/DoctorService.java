package com.netbull.shop.doctor.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.doctor.dao.DoctorDao;
import com.netbull.shop.doctor.entity.Doctor;
import com.netbull.shop.doctor.entity.DoctorDisease;
import com.netbull.shop.doctor.entity.UserInfo;

@Service("doctorService")
public class DoctorService {
	
	@Resource
	private DoctorDao doctorDao;

	public Page page(Integer iDisplayStart, Integer iDisplayLength, Map<String, String> requestMap) {
		return doctorDao.page(iDisplayStart,iDisplayLength,requestMap);
	}
	
	public Doctor findById(long id) {
		return doctorDao.findById(id);
	}
	
	public int deleteById(long id) {
		return doctorDao.deleteById(id);
	}
	
	public int save(Doctor doctor, long currUserId) {
		UserInfo userInfo = new UserInfo();
		userInfo.setLoginName(doctor.getLoginAccount());
		userInfo.setPassword(DigestUtils.md5Hex(doctor.getLoginPwd()));
		userInfo.setParentid(currUserId);
		userInfo.setPhone(doctor.getPhone());
		userInfo.setTrueName(doctor.getRealName());
		
		userInfo = doctorDao.saveUserInfo(userInfo);
		int doctorRoleId = StringUtil.parseInt(ConfigLoadUtil.loadConfig().getPropertie("DOCTOR_ROLE_ID"), 0);
		doctorDao.saveRoleUserInfo(doctorRoleId, userInfo.getId());
		
		doctor.setDoctorName(userInfo.getTrueName());
		doctor.setDoctorPassword(DigestUtils.md5Hex(doctor.getLoginPwd()));
		doctor.setLoginID(userInfo.getId());
		return doctorDao.save(doctor);
	}
	
	public int update(Doctor doctor) {
		UserInfo userInfo = new UserInfo();
		userInfo.setTrueName(doctor.getRealName());
		userInfo.setPhone(doctor.getPhone());
		userInfo.setId(doctor.getLoginID());
		userInfo.setLoginName(doctor.getLoginAccount());
		doctorDao.updateUserInfo(userInfo);
		
		return doctorDao.update(doctor);
	}
	
	public List<DoctorDisease> findDoctorDiseases(long doctorID) {
		return doctorDao.findDoctorDiseases(doctorID);
	}
	
	public int saveDoctorDisease(long doctorID, long[] diseaseID) {
		doctorDao.deleteDiseaseByDoctorID(doctorID);
		
		StringBuilder diseaseIDS = new StringBuilder();
		for(long id :diseaseID) {
			diseaseIDS.append("(").append(doctorID).append(", ").append(id).append(")").append(", ");
		}
		
		String doctorDiseases = diseaseIDS.toString();
		doctorDiseases = doctorDiseases.substring(0, doctorDiseases.length() - 2);
		
		return doctorDao.saveDoctorDisease(doctorDiseases);
	}
	
	public List<Map> queryAllRecommDoctorList(Map<String, String> param) {
		return doctorDao.queryAllRecommDoctorList(param);
	}
	
	public Integer updateRemind(Map parameter){
		return doctorDao.updateRemind(parameter);
	}
}

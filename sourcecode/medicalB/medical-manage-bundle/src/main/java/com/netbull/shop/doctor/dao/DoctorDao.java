package com.netbull.shop.doctor.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.dao.BaseDao;
import com.netbull.shop.doctor.entity.Doctor;
import com.netbull.shop.doctor.entity.DoctorDisease;
import com.netbull.shop.doctor.entity.UserInfo;

@Repository("doctorDao")
public class DoctorDao extends BaseDao{
	
	private static final String NAMESPACE = Doctor.class.getName();

	@Resource
	private SqlSession session;

	public Page page(Integer iDisplayStart, Integer iDisplayLength, Map<String, String> requestMap) {
		return session.page(NAMESPACE + ".query_for_pagination", NAMESPACE + ".totalCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	public Doctor findById(long id) {
		return session.selectOne(NAMESPACE+".query_by_id", id);
	}
	
	public int deleteById(long id) {
		return session.update(NAMESPACE+".delete_by_id", id);
	}
	
	public int deleteDiseaseByDoctorID(long doctorID) {
		return session.delete(NAMESPACE+".delete_disease_by_doctorID", doctorID);
	}
	
	public int save(Doctor doctor) {
		return session.insert(NAMESPACE+".save", doctor);
	}
	
	public UserInfo saveUserInfo(UserInfo userInfo) {
		session.insert(NAMESPACE+".saveUserInfo", userInfo);
		return userInfo;
	}
	
	public int updateUserInfo(UserInfo userInfo) {
		return session.update(NAMESPACE+".updateUserInfo", userInfo);
	}
	
	public int saveRoleUserInfo(int roleId, long userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("roleId", roleId);
		param.put("userId", userId);
		return session.insert(NAMESPACE+".saveRoleUserInfo", param);
	}
	
	public int saveDoctorDisease(String doctorDiseases) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("doctorDiseases", doctorDiseases);
		return session.insert(NAMESPACE+".save_doctor_disease", param);
	}
	
	public List<DoctorDisease> findDoctorDiseases(long doctorID) {
		return session.selectList(NAMESPACE + ".find_doctorDiseases", doctorID);
	}
	
	public int update(Doctor doctor) {
		return session.update(NAMESPACE+".update", doctor);
	}
	
	public List<Map> queryAllRecommDoctorList(Map<String, String> param) {
		return session.selectList(NAMESPACE + ".queryAllRecommDoctorList", param);
	}
	
	public Doctor queryByParam(Map param){
		return session.selectOne(NAMESPACE+".queryByParam", param);
	}
}

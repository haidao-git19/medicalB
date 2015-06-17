package com.netbull.shop.pharmacy.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.dao.BaseDao;
import com.netbull.shop.doctor.entity.UserInfo;
import com.netbull.shop.patient.entity.Patient;
import com.netbull.shop.patient.entity.Picture;
import com.netbull.shop.pharmacy.entity.Pharmacy;

@Repository("pharmacyDao")
public class PharmacyDao extends BaseDao{
	private static final String MYBATIS_PREFIX = PharmacyDao.class.getName();
	@Resource
	private SqlSession session;
	

	public Page page(Integer iDisplayStart, Integer iDisplayLength,
			Map requestMap) {
		//设置权限
		requestMap.put("users", handleQueryOrgan());
		return session.page(MYBATIS_PREFIX+".pageList", MYBATIS_PREFIX+".count",
				requestMap, iDisplayStart, iDisplayLength);
	}

	public int save(Pharmacy pharmacy) {
		// TODO Auto-generated method stub
		pharmacy.setUserID(this.queryCurrentShiroUser().getLoginName());
		return session.insert(MYBATIS_PREFIX+".save", pharmacy);
	}
	
	public int update(Pharmacy pharmacy) {
		// TODO Auto-generated method stub
		return session.insert(MYBATIS_PREFIX+".update", pharmacy);
	}

	@SuppressWarnings("unchecked")
	public Pharmacy findByParam(Map param) {
		// TODO Auto-generated method stub
		param.put("users", handleQueryOrgan());
		List<Pharmacy> list= session.find(MYBATIS_PREFIX+".findByParam", param);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public int del(int id) {
		// TODO Auto-generated method stub
		return session.delete(MYBATIS_PREFIX+".del",id);
	}

	public int updatePicture(Picture picture) {
		// TODO Auto-generated method stub
		return session.update("com.netbull.shop.patient.dao.PatientDao.updatePicture",picture);
		
	}

	public void savePicture(Picture picture) {
		// TODO Auto-generated method stub
		 session.insert("com.netbull.shop.patient.dao.PatientDao.updatePicture",picture);
	}

	public String findImg(Picture picture) {
		// TODO Auto-generated method stub
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("objectID", picture.getObjectID());
		param.put("objectType", picture.getObjectType());
		List list= session.find("com.netbull.shop.patient.dao.PatientDao.updatePicture",param);
		if(list!=null&&list.size()>0){
			return ((Picture)list.get(0)).getPicUrl();
		}
		return "";
	}

	/** 
	 * @param pharmacy
	 * @return 
	 */
	public List<Pharmacy> queryPharmacy(Pharmacy pharmacy) {

		Map<String,Object> param=new HashMap<String, Object>();
		param.put("users", handleQueryOrgan());
		List<Pharmacy> list= session.find(MYBATIS_PREFIX+".findByParam", param);
		return list;
	}
	
	public UserInfo saveUserInfo(UserInfo userInfo) {
		session.insert(MYBATIS_PREFIX+".saveUserInfo", userInfo);
		return userInfo;
	}
	
	public int updateUserInfo(UserInfo userInfo) {
		return session.update(MYBATIS_PREFIX+".updateUserInfo", userInfo);
	}
	
	public int saveRoleUserInfo(int roleId, long userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("roleId", roleId);
		param.put("userId", userId);
		return session.insert(MYBATIS_PREFIX+".saveRoleUserInfo", param);
	}
}

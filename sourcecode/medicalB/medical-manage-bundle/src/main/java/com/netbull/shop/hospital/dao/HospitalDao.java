package com.netbull.shop.hospital.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.dao.BaseDao;
import com.netbull.shop.hospital.entity.Hospital;

@Repository("hospitalDao")
public class HospitalDao extends BaseDao{
	
	private static final String MYBATIS_PREFIX = HospitalDao.class.getName();
	@Resource
	private SqlSession session;

	public Page page(Integer iDisplayStart, Integer iDisplayLength,
			Map requestMap) {
		//设置权限
		requestMap.put("users", handleQueryOrgan());
		return session.page(MYBATIS_PREFIX+".pageHospital", MYBATIS_PREFIX+".countHospital",
				requestMap, iDisplayStart, iDisplayLength);
	}

	public void save(Hospital hospital) {
		// TODO Auto-generated method stub
		hospital.setUserID(this.queryCurrentShiroUser().getLoginName());
		session.insert(MYBATIS_PREFIX+".save", hospital);
	}
	
	public void update(Hospital hospital) {
		// TODO Auto-generated method stub
		session.update(MYBATIS_PREFIX+".update", hospital);
	}

	public Hospital findById(Hospital hospital) {
		// TODO Auto-generated method stub
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("hospitalID", hospital.getHospitalID());
		param.put("hospitalName", hospital.getHospitalName());
		param.put("users", handleQueryOrgan());
		List<Hospital> list= session.find(MYBATIS_PREFIX+".findByID", param);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return new Hospital();
	}
	
	public Hospital findByHospitalId(long hospitalID) {
		return session.selectOne(MYBATIS_PREFIX+".findByHospitalID", hospitalID);
	}
	
	public List<Hospital> findByAreaID(int id) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("areaID", id);
		return session.find(MYBATIS_PREFIX+".findByAreaID", param);
	}

	public int del(int hospitalID) {
		// TODO Auto-generated method stub
		return session.delete(MYBATIS_PREFIX+".del",hospitalID);
	}
	
	public List<Map> queryAllHospitalList(Map parameter){
		parameter.put("users", this.handleQueryOrgan());
		return session.selectList(MYBATIS_PREFIX+".queryAllHospitalList",parameter);
	}
}

package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;

@Repository
public class PatientDao {

	private static final String MYBATIS_PREFIX=PatientDao.class.getName();
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<Map> queryList(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryList", parameter);
	}
	
	public Map queryOne(Map parameter){
		return sqlSession.selectOne(MYBATIS_PREFIX+".queryDetail", parameter);
	}
	
	public List<Map> myPatientList(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".myPatientList", parameter);
	}
	public void saveMyDoctor(Map parameter){
		sqlSession.insert(MYBATIS_PREFIX+".saveMyDoctor", parameter);
	}
	
	public Map queryMyDoctor(Map parameter){
		return sqlSession.selectOne(MYBATIS_PREFIX+".queryMyDoctor", parameter);
	}
	
	public List<Map> queryMyDoctorList(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryMyDoctorList", parameter);
	}
	
	public List<Map> myServiceList(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".myServiceList", parameter);
	}
}

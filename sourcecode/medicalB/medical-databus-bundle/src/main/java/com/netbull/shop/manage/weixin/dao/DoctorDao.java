package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import com.netbull.shop.manage.weixin.vo.Doctor;
import com.netbull.shop.manage.weixin.vo.DoctorGroup;
import com.netbull.shop.common.dao.SqlSession;

@Repository
public class DoctorDao {
	private static final String MYBATIS_PREFIX = DoctorDao.class.getName();
	
	@Resource
	private SqlSession session;
    
	/**
	 * 分页查询医生列表
	 * @param paramter
	 * @return
	 */
	public List<Map> queryDoctorList(Map paramter) {		
		List<Map> list = session.selectList(MYBATIS_PREFIX + ".queryDoctorList",paramter);
		return list;
	}
	
	public List<Map> queryDoctorAllList(Map paramter) {		
		List<Map> list = session.selectList(MYBATIS_PREFIX + ".queryDoctorAllList",paramter);
		return list;
	}
	
	public List<Map> queryDoctorListByTime(Map paramter) {		
		List<Map> list = session.selectList(MYBATIS_PREFIX + ".queryDoctorListByTime",paramter);
		return list;
	}
	
	public List<Map> queryDoctorListByParams(Map parameter){
		return session.selectList(MYBATIS_PREFIX+".queryDoctorListByParams", parameter);
	}
	
	public List<Map> queryRecommendDoctorList(Map paramter) {		
		List<Map> list = session.selectList(MYBATIS_PREFIX + ".queryRecommendDoctorList",paramter);
		return list;
	}
	
	public List<Map> queryRecommendDoctorListByDisease(Map paramter) {		
		List<Map> list = session.selectList(MYBATIS_PREFIX + ".queryRecommendDoctorListByDisease",paramter);
		return list;
	}
	
	public Map queryDoctorDetail(Map paramter) {		
		Map docotor = session.selectOne(MYBATIS_PREFIX + ".queryDoctorDetail",paramter);
		return docotor;
	}
	
	/**
	 * 修改医生密码信息
	 * @param paramter
	 * @return
	 */
	public Integer modifyPassword(Map paramter) {		
		return session.update(MYBATIS_PREFIX + ".modifyPassword",paramter);
	}
	
	/**
	 * 添加医生信息
	 * @param paramter
	 * @return
	 */
	public Integer saveDoctorBaseInfo(Doctor doctor) {		
		return session.update(MYBATIS_PREFIX + ".saveDoctorBaseInfo",doctor);
	}
	
	/**
	 * 更新医生信息
	 * @param map
	 */
	public Integer modifyDoctorBaseInfo(Doctor doctor){
		return session.update(MYBATIS_PREFIX+".modifyDoctorBaseInfo", doctor);
	}
	
	public Map queryDoctorLogin(Map paramter) {		
		Map docotor = session.selectOne(MYBATIS_PREFIX + ".queryDoctorLogin",paramter);
		return docotor;
	}
	
	public List<Map> myDoctorList(Map paramter) {		
		List<Map> list = session.selectList(MYBATIS_PREFIX + ".myDoctorList",paramter);
		return list;
	}
	
	public List<Map> querySelfDoctorGroupConsulationList(Map paramter) {		
		List<Map> list = session.selectList(MYBATIS_PREFIX + ".querySelfDoctorGroupConsulationList",paramter);
		return list;
	}
	
	public List<Map> queryOtherDoctorGroupConsulationList(Map paramter) {		
		List<Map> list = session.selectList(MYBATIS_PREFIX + ".queryOtherDoctorGroupConsulationList",paramter);
		return list;
	}
	
	public Integer saveDoctorGroupConsulation(DoctorGroup doctor) {		
		return session.update(MYBATIS_PREFIX + ".saveDoctorGroupConsulation",doctor);
	}
	
	public Integer saveDoctorGroupConsulationR(Map paramter) {		
		return session.update(MYBATIS_PREFIX + ".saveDoctorGroupConsulationR",paramter);
	}
	
	public Integer modifyDoctorGroupConsulation(Map paramter){
		return session.update(MYBATIS_PREFIX+".modifyDoctorGroupConsulation", paramter);
	}
	
	public Integer modifyDoctorGroupConsulationR(Map paramter){
		return session.update(MYBATIS_PREFIX+".modifyDoctorGroupConsulationR", paramter);
	}
	
	public Map queryDoctorGroupConsulationRDetail(Map paramter) {		
		return session.selectOne(MYBATIS_PREFIX + ".queryDoctorGroupConsulationRDetail",paramter);
	}
}

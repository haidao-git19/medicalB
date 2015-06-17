package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.vo.Patient;
import com.netbull.shop.common.vo.UserVo;

import com.netbull.shop.common.dao.SqlSession;

@Repository
public class UserBaseInfoDao {
	private static final String MYBATIS_PREFIX = UserBaseInfoDao.class.getName();
	
	@Resource
	private SqlSession session;
    
	/**
	 * 查询用户信息
	 * @param paramter
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Patient queryUserBaseInfo(Map paramter) {		
		Patient patient = session.selectOne(MYBATIS_PREFIX + ".queryUserBaseInfo",paramter);
		return patient;
	}
	
	/**
	 * 查询用户信息
	 * @param map
	 * @return
	 */
	public UserVo queryUser(Map<String,String> map){
		return session.selectOne(MYBATIS_PREFIX+".queryUserByParams", map);
	}
	
	/**
	 * 查询用户信息
	 * @param paramter
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<UserVo> queryUserBaseInfoList(Map paramter) {		
		List<UserVo> users = session.selectList(MYBATIS_PREFIX + ".queryUserBaseInfo",paramter);
		return users;
	}
	
	/**
	 * 修改用户信息
	 * @param paramter
	 * @return
	 */
	public Integer modifyUserBaseInfo(Patient patient) {		
		return session.update(MYBATIS_PREFIX + ".modifyUserBaseInfo",patient);
	}
	
	/**
	 * 修改用户密码信息
	 * @param paramter
	 * @return
	 */
	public Integer modifyPassword(Map paramter) {		
		return session.update(MYBATIS_PREFIX + ".modifyPassword",paramter);
	}
	
	/**
	 * 添加用户信息
	 * @param paramter
	 * @return
	 */
	public Integer saveUserBaseInfo(Patient patient) {		
		return session.update(MYBATIS_PREFIX + ".saveUserBaseInfo",patient);
	}
	
	/**
	 * 更新用户信息
	 * @param map
	 */
	public Integer update(Map<String,String> map){
		return session.update(MYBATIS_PREFIX+".update", map);
	}
	
}

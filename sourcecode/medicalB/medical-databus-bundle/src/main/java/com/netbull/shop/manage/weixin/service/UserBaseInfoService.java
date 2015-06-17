package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.netbull.shop.common.vo.Patient;
import com.netbull.shop.common.vo.UserVo;
import com.netbull.shop.manage.weixin.dao.UserBaseInfoDao;

@Service
@SuppressWarnings({ "unchecked", "rawtypes" })
public class UserBaseInfoService {
	private static final Log log = LogFactory.getLog(UserBaseInfoService.class);
    
	@Resource
	private UserBaseInfoDao userBaseInfoDao;

	/**
	 * 查询用户信息
	 * @param paramter
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Patient queryUserBaseInfo(Map paramter) {		
		return userBaseInfoDao.queryUserBaseInfo(paramter);
	}
	
	/**
	 * 查询用户信息
	 * @param map
	 * @return
	 */
	public UserVo queryUserByParams(Map<String,String> map){
		return userBaseInfoDao.queryUser(map);
	}
	
	/**
	 * 查询用户信息
	 * @param paramter
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<UserVo> queryUserBaseInfoList(Map paramter){
		return userBaseInfoDao.queryUserBaseInfoList(paramter);
	}
	
	/**
	 * 修改用户信息
	 * @param paramter
	 * @return
	 */
	public Integer modifyUserBaseInfo(Patient patient) {		
		return userBaseInfoDao.modifyUserBaseInfo(patient);
	}
	
	/**
	 * 添加用户信息
	 * @param paramter
	 * @return
	 */
	public Integer saveUserBaseInfo(Patient patient) {		
		return userBaseInfoDao.saveUserBaseInfo(patient);
	}

	/**
	 * 更新用户信息
	 * @param map
	 */
	public Integer updateUser(Map<String,String> map){
		return userBaseInfoDao.update(map);
	}
	
	/**
	 * 修改用户密码信息
	 * @param paramter
	 * @return
	 */
	public Integer modifyPassword(Map paramter) {
		return userBaseInfoDao.modifyPassword(paramter);
	}
	
}

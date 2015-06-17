package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.vo.UserVo;
import com.netbull.shop.manage.weixin.dao.UserDao;
import com.netbull.shop.util.MyBatisDao;

@Service
public class UserService extends MyBatisDao<UserVo, Integer>{

	@Autowired
	private UserDao userDao;
	
	/**
	 * 分页查询用户信息
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param requestMap
	 * @return
	 */
	public Page queryUserPage(Integer iDisplayStart, Integer iDisplayLength,Map<String, String> requestMap){
		return userDao.queryPage(iDisplayStart, iDisplayLength, requestMap);
	}
	
	/**
	 * 查询用户信息
	 * @param map
	 * @return
	 */
	public UserVo queryUserByParams(Map<String,String> map){
		return userDao.queryByParams(map);
	}
	
	/**
	 * 查询用户信息
	 * @param paramter
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<UserVo> queryUserBaseInfoList(Map paramter){
		return userDao.queryUserBaseInfoList(paramter);
	}
	
	/**
	 * 更新用户信息
	 * @param map
	 */
	public void updateUser(Map<String,String> map){
		userDao.update(map);
	}
}

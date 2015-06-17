package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.vo.UserAcceptInfoVo;
import com.netbull.shop.manage.weixin.dao.UserAcceptInfoDao;
import com.netbull.shop.util.MyBatisDao;

@Service
public class UserAcceptInfoService extends MyBatisDao<UserAcceptInfoVo, Integer>{

	@Autowired
	private UserAcceptInfoDao userAcceptInfoDao;
	
	/**
	 * 分页查询收货人信息
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param requestMap
	 * @return
	 */
	public Page queryUserAcceptInfoPage(Integer iDisplayStart, Integer iDisplayLength,Map<String, String> requestMap){
		return userAcceptInfoDao.queryPage(iDisplayStart, iDisplayLength, requestMap);
	}
	
	/**
	 * 查询用户所有收货信息
	 * @param userId
	 * @return
	 */
	public List<UserAcceptInfoVo> queryUserAcceptInfoListByUserId(Integer userId){
		return userAcceptInfoDao.queryListByUserId(userId);
	}
	
	/**
	 * 查询用户收货信息
	 * @param map
	 * @return
	 */
	public UserAcceptInfoVo queryUserAcceptInfo(Map<Object,Object> map){
		return userAcceptInfoDao.queryEntityByParams(map);
	}
}

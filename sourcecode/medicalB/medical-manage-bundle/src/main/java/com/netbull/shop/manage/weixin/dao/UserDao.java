package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.vo.UserVo;

@Repository
public class UserDao {

	private static final String MYBATIS_PREFIX=UserDao.class.getName();
	@Autowired
	private SqlSession session;
	
	/**
	 * 分页查询用户信息
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param requestMap
	 * @return
	 */
	public Page queryPage(Integer iDisplayStart, Integer iDisplayLength,Map<String, String> requestMap){ 
		return session.page(MYBATIS_PREFIX + ".queryList", MYBATIS_PREFIX+".queryCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	/**
	 * 查询用户信息
	 * @param map
	 * @return
	 */
	public UserVo queryByParams(Map<String,String> map){
		return session.selectOne(MYBATIS_PREFIX+".queryByParams", map);
	}
	
	/**
	 * 查询用户信息
	 * @param paramter
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<UserVo> queryUserBaseInfoList(Map paramter) {		
		List<UserVo> users = session.selectList(MYBATIS_PREFIX + ".queryByParams",paramter);
		return users;
	}
	
	/**
	 * 更新用户信息
	 * @param map
	 */
	public void update(Map<String,String> map){
		session.update(MYBATIS_PREFIX+".update", map);
	}
}

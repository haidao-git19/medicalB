package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.vo.UserAcceptInfoVo;

@Repository
public class UserAcceptInfoDao {
	
	private static final String MYBATIS_PREFIX=UserAcceptInfoDao.class.getName();

	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * 分页查询收货人信息
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param requestMap
	 * @return
	 */
	public Page queryPage(Integer iDisplayStart, Integer iDisplayLength,Map<String, String> requestMap){ 
		return sqlSession.page(MYBATIS_PREFIX + ".queryList", MYBATIS_PREFIX+".queryCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	/**
	 * 查询用户所有收货信息
	 * @param userId
	 * @return
	 */
	public List<UserAcceptInfoVo> queryListByUserId(Integer userId){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryListByUserId", userId);
	}
	
	/**
	 * 查询用户收货信息
	 * @param map
	 * @return
	 */
	public UserAcceptInfoVo queryEntityByParams(Map<Object,Object> map){
		return sqlSession.selectOne(MYBATIS_PREFIX+".queryEntityByParams",map);
	}
}

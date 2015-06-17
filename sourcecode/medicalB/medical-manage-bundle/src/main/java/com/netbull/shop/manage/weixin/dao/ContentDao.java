package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.vo.ContentVo;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;

@Repository
public class ContentDao {

	private static final String MYBATIS_PREFIX=ContentDao.class.getName();
	
	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * 分页查询内容记录
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param requestMap
	 * @return
	 */
	public Page queryPage(Integer iDisplayStart, Integer iDisplayLength,Map<String, String> requestMap){ 
		return sqlSession.page(MYBATIS_PREFIX + ".queryList", MYBATIS_PREFIX+".queryCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	/**
	 * 查询所有可用内容记录
	 * @return
	 */
	public List<ContentVo> queryAll(){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryList");
	}
	
	/**
	 * 查询内容记录
	 * @param id
	 * @return
	 */
	public ContentVo queryOne(Integer id){
		return sqlSession.selectOne(MYBATIS_PREFIX+".queryOne", id);
	}
}

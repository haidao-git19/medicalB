package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.vo.GoodsAttr;

@Repository
public class GoodsAttrDao {

	private static final String MYBATIS_PREFIX=GoodsAttrDao.class.getName();
	
	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * 分页查询商品属性信息
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param requestMap
	 * @return
	 */
	public Page queryPage(Integer iDisplayStart, Integer iDisplayLength,Map<String, String> requestMap){ 
		return sqlSession.page(MYBATIS_PREFIX + ".queryList", MYBATIS_PREFIX+".queryCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	/**
	 * 查询商品属性信息
	 * @param map
	 * @return
	 */
	public List<GoodsAttr> queryList(Map<Object,Object> map){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryList", map);
	}
}

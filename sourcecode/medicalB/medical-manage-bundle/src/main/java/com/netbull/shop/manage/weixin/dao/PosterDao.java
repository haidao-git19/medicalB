package com.netbull.shop.manage.weixin.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.vo.PosterVo;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;

@Repository
public class PosterDao {

	private static final String MYBATIS_PREFIX=PosterDao.class.getName();
	
	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * 分页查询海报信息
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param requestMap
	 * @return
	 */
	public Page queryPage(Integer iDisplayStart, Integer iDisplayLength,Map<String, String> requestMap){ 
		return sqlSession.page(MYBATIS_PREFIX + ".queryList", MYBATIS_PREFIX+".queryCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	/**
	 * 查询海报信息
	 * @param map
	 * @return
	 */
	public PosterVo queryOne(Integer id){
		return sqlSession.selectOne(MYBATIS_PREFIX+".queryOne", id);
	}
	
	/**
	 * 保存海报信息
	 * @param posterVo
	 */
	public void save(PosterVo posterVo){
		sqlSession.insert(MYBATIS_PREFIX+".save", posterVo);
	}
	
	/**
	 * 更新海报信息
	 * @param posterVo
	 */
	public void update(PosterVo posterVo){
		sqlSession.update(MYBATIS_PREFIX+".update", posterVo);
	}
}

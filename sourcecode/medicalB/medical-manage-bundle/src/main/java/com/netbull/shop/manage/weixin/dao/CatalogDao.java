package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.vo.CatalogVo;
import com.netbull.shop.common.vo.GoodsFilter;

import com.netbull.shop.common.vo.CatalogGoodsVo;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;

@Repository
public class CatalogDao {

	private static final String MYBATIS_PREFIX=CatalogDao.class.getName();
	
	@Autowired
	private SqlSession sqlSession;
	
	public Page queryPage(Integer iDisplayStart, Integer iDisplayLength,Map<String, String> requestMap){ 
		return sqlSession.page(MYBATIS_PREFIX + ".queryList", MYBATIS_PREFIX+".queryCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	public List<CatalogGoodsVo> queryList(Map<String,String> map){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryListByParams", map);
	}
	
	public CatalogVo queryCatalog(Map<String,String> map){
		return sqlSession.selectOne(MYBATIS_PREFIX+".queryCatalog", map);
	}
	
	public Integer saveCatalog(Map<String,String> map){
		return sqlSession.insert(MYBATIS_PREFIX+".saveCatalog", map);
	}
	
	public Integer updateCatalog(Map<String,String> map){
		return sqlSession.update(MYBATIS_PREFIX+".updateCatalog", map);
	}
	
	public Integer saveCatalogGoods(Map<String,String> map){
		return sqlSession.insert(MYBATIS_PREFIX+".saveCatalogGoods", map);
	}
	
	public Integer updateCatalogGoods(Map<String,String> map){
		return sqlSession.update(MYBATIS_PREFIX+".updateCatalogGoods", map);
	}
	
	public CatalogGoodsVo queryCatalogGoods(Map<String,String> map){
		return sqlSession.selectOne(MYBATIS_PREFIX+".queryCatalogGoods", map);
	}
	
	public void deleteCatalogGoods(Integer id){
		sqlSession.delete(MYBATIS_PREFIX+".deleteCatalogGoods", id);
	}
	
	public void deleteCatalog(Map<String,String> map){
		sqlSession.delete(MYBATIS_PREFIX+".deleteCatalog", map);
	}
	
	public List<GoodsFilter> queryFilter(Map<String,String> map){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryFilter", map);
	}
}

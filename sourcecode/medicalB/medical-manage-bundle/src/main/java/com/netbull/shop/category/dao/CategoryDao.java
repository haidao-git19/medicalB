package com.netbull.shop.category.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.category.entity.Category;
import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.dao.BaseDao;

@Repository
public class CategoryDao extends BaseDao{

	private static final String MYBATIS_PREFIX=CategoryDao.class.getName();
	@Autowired
	private SqlSession sqlSession;
	
	@SuppressWarnings("rawtypes")
	public Page page(Integer iDisplayStart, Integer iDisplayLength,Map requestMap) {
		return sqlSession.page(MYBATIS_PREFIX+".queryList",MYBATIS_PREFIX+".queryCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	public Category queryEntityById(long id){
		return sqlSession.selectOne(MYBATIS_PREFIX+".queryEntityById", id);
	}
	
	public int delete(long id){
		return sqlSession.update(MYBATIS_PREFIX+".delete", id);
	}
	
	@SuppressWarnings("static-access")
	public int save(Category category){
		category.setCreatePerson(this.queryCurrentShiroUser().getLoginName());
		return sqlSession.insert(MYBATIS_PREFIX+".save", category);
	}
	
	@SuppressWarnings("static-access")
	public int update(Category category){
		category.setUpdatePerson(this.queryCurrentShiroUser().getLoginName());
		return sqlSession.update(MYBATIS_PREFIX+".update", category);
	}
	
	public List<Map> queryAll(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryAll", parameter);
	}
}

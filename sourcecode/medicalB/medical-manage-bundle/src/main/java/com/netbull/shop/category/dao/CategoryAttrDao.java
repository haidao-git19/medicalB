package com.netbull.shop.category.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.category.entity.CategoryAttr;
import com.netbull.shop.category.entity.CategoryAttrClass;
import com.netbull.shop.category.entity.CategoryAttrDim;
import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.dao.BaseDao;

@Repository
public class CategoryAttrDao extends BaseDao{

	private static final String MYBATIS_PREFIX=CategoryAttrDao.class.getName();
	@Autowired
	private SqlSession sqlSession;
	
	@SuppressWarnings("rawtypes")
	public Page page(Integer iDisplayStart, Integer iDisplayLength,Map requestMap) {
		return sqlSession.page(MYBATIS_PREFIX+".queryList",MYBATIS_PREFIX+".queryCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	@SuppressWarnings("static-access")
	public int save(CategoryAttr categoryAttr){
		categoryAttr.setCreatePerson(this.queryCurrentShiroUser().getLoginName());
		return sqlSession.insert(MYBATIS_PREFIX+".save", categoryAttr);
	}
	
	@SuppressWarnings("static-access")
	public int update(CategoryAttr categoryAttr){
		categoryAttr.setUpdatePerson(this.queryCurrentShiroUser().getLoginName());
		return sqlSession.update(MYBATIS_PREFIX+".update", categoryAttr);
	}
	
	public int delete(long id){
		return sqlSession.delete(MYBATIS_PREFIX+".delete", id);
	}
	
	public List<CategoryAttr> queryAllAttrs(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryAllAttrs", parameter);
	}
	
	public CategoryAttr queryById(Integer id){
		return sqlSession.selectOne(MYBATIS_PREFIX+".queryEntityById", id);
	}
	
	public List<Map> queryAttrClassList(Map parameter){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryAttrClassCollection", parameter);
	}
	
	public int saveAttrClass(CategoryAttrClass categoryAttrClass){
		categoryAttrClass.setCreatePerson(this.queryCurrentShiroUser().getLoginName());
		return sqlSession.insert(MYBATIS_PREFIX+".saveAttrClass", categoryAttrClass);
	}
	
	public int updateAttrClass(CategoryAttrClass categoryAttrClass){
		categoryAttrClass.setUpdatePerson(this.queryCurrentShiroUser().getLoginName());
		return sqlSession.update(MYBATIS_PREFIX+".updateAttrClass", categoryAttrClass);
	}
	
	public int deleteAttrClass(long id){
		return sqlSession.delete(MYBATIS_PREFIX+".deleteAttrClass", id);
	}
	
	public Page attrClassPage(Integer iDisplayStart, Integer iDisplayLength,Map requestMap) {
		return sqlSession.page(MYBATIS_PREFIX+".queryAttrClassList",MYBATIS_PREFIX+".queryAttrClassCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	public CategoryAttrClass queryAttrClassById(Integer id){
		return sqlSession.selectOne(MYBATIS_PREFIX+".queryAttrClassById", id);
	}
	
	public Page attrDimPage(Integer iDisplayStart, Integer iDisplayLength,Map requestMap){
		return sqlSession.page(MYBATIS_PREFIX+".queryAttrDimList", MYBATIS_PREFIX+".queryAttrDimCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	public CategoryAttrDim queryAttrDimById(Integer id){
		return sqlSession.selectOne(MYBATIS_PREFIX+".queryAttrDimById", id);
	}
	
	public int saveAttrDim(CategoryAttrDim categoryAttrDim){
		categoryAttrDim.setCreatePerson(this.queryCurrentShiroUser().getLoginName());
		return sqlSession.insert(MYBATIS_PREFIX+".saveAttrDim", categoryAttrDim);
	}
	
	public int updateAttrDim(CategoryAttrDim categoryAttrDim){
		categoryAttrDim.setUpdatePerson(this.queryCurrentShiroUser().getLoginName());
		return sqlSession.update(MYBATIS_PREFIX+".updateAttrDim", categoryAttrDim);
	}
	
	public int deleteAttrDim(long id){
		return sqlSession.delete(MYBATIS_PREFIX+".deleteAttrDim", id);
	}
}

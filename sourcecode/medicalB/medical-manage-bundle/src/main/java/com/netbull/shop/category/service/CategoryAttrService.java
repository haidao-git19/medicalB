package com.netbull.shop.category.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.category.dao.CategoryAttrDao;
import com.netbull.shop.category.dao.CategoryDao;
import com.netbull.shop.category.entity.CategoryAttr;
import com.netbull.shop.category.entity.CategoryAttrClass;
import com.netbull.shop.category.entity.CategoryAttrDim;
import com.netbull.shop.common.page.Page;

@Service
public class CategoryAttrService {

	@Autowired
	private CategoryAttrDao categoryAttrDao;
	
	public Page page(Integer iDisplayStart, Integer iDisplayLength,
			Map<String, String> requestMap) {
		return categoryAttrDao.page(iDisplayStart, iDisplayLength, requestMap);
	}
	
	public int saveCategoryAttr(CategoryAttr categoryAttr){
		return categoryAttrDao.save(categoryAttr);
	}
	
	public int updateCategoryAttr(CategoryAttr categoryAttr){
		return categoryAttrDao.update(categoryAttr);
	}
	
	public int deleteCategoryAttr(long id){
		return categoryAttrDao.delete(id);
	}
	
	public List<CategoryAttr> queryAllCategoryAttrs(Map parameter){
		return categoryAttrDao.queryAllAttrs(parameter);
	}
	
	public CategoryAttr queryCategoryAttrById(Integer id){
		return categoryAttrDao.queryById(id);
	}
	
	public List<Map> queryAttrClassList(Map parameter){
		return categoryAttrDao.queryAttrClassList(parameter);
	}
	
	public int saveCategoryAttrClass(CategoryAttrClass categoryAttrClass){
		return categoryAttrDao.saveAttrClass(categoryAttrClass);
	}
	
	public int updateCategoryAttrClass(CategoryAttrClass categoryAttrClass){
		return categoryAttrDao.updateAttrClass(categoryAttrClass);
	}
	
	public int deleteCategoryAttrClass(long id){
		return categoryAttrDao.deleteAttrClass(id);
	}
	
	public Page attrClassPage(Integer iDisplayStart, Integer iDisplayLength,
			Map<String, String> requestMap) {
		return categoryAttrDao.attrClassPage(iDisplayStart, iDisplayLength, requestMap);
	}
	
	public CategoryAttrClass queryAttrClassById(Integer id){
		return categoryAttrDao.queryAttrClassById(id);
	}
	
	public Page attrDimPage(Integer iDisplayStart, Integer iDisplayLength,
			Map<String, String> requestMap) {
		return categoryAttrDao.attrDimPage(iDisplayStart, iDisplayLength, requestMap);
	}
	
	public CategoryAttrDim queryCategoryAttrDimById(Integer id){
		return categoryAttrDao.queryAttrDimById(id);
	}
	
	public int saveCategoryAttrDim(CategoryAttrDim categoryAttrDim){
		return categoryAttrDao.saveAttrDim(categoryAttrDim);
	}
	
	public int updateCategoryAttrDim(CategoryAttrDim categoryAttrDim){
		return categoryAttrDao.updateAttrDim(categoryAttrDim);
	}
	
	public int deleteCategoryAttrDim(long id){
		return categoryAttrDao.deleteAttrDim(id);
	}
}

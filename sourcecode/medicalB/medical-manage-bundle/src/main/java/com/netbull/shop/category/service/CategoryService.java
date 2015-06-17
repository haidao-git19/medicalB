package com.netbull.shop.category.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.netbull.shop.category.dao.CategoryDao;
import com.netbull.shop.category.entity.Category;
import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.ImageCompressUtil;
import com.netbull.shop.util.UploadFileUtil;

@Service
public class CategoryService {
	
	private static Integer width=Integer.parseInt(ConfigLoadUtil.loadConfig().getPropertie("imageWidth"));
	private static Integer height=Integer.parseInt(ConfigLoadUtil.loadConfig().getPropertie("imageHeight"));

	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private UploadFileUtil uploadFileUtil;
	
	public Page page(Integer iDisplayStart, Integer iDisplayLength,
			Map<String, String> requestMap) {
		return categoryDao.page(iDisplayStart, iDisplayLength, requestMap);
	}
	
	public Category queryCategoryById(long id){
		return categoryDao.queryEntityById(id);
	}
	
	public int delCategoryById(long id){
		return categoryDao.delete(id);
	}
	
	public int saveCategory(Category category,CommonsMultipartFile file){
		String fileName=uploadFileUtil.createFile(file, "");
		String realPath=ConfigLoadUtil.loadConfig().getPropertie("accessUrl");
		category.setBigImgPath(realPath+fileName);
		
		String uploadPath=ConfigLoadUtil.loadConfig().getPropertie("materialUploadPath");
		ImageCompressUtil.getInstance().compressPic(uploadPath, uploadPath, fileName, "S_"+fileName, width, height, false);
		category.setSmallImgPath(realPath+"S_"+fileName);
		return categoryDao.save(category);
	}
	
	public int updateCategory(Category category,CommonsMultipartFile file){
		String fileName=uploadFileUtil.createFile(file, "");
		String realPath=ConfigLoadUtil.loadConfig().getPropertie("accessUrl");
		category.setBigImgPath(realPath+fileName);
		
		String uploadPath=ConfigLoadUtil.loadConfig().getPropertie("materialUploadPath");
		ImageCompressUtil.getInstance().compressPic(uploadPath, uploadPath, fileName, "S_"+fileName, width, height, false);
		category.setSmallImgPath(realPath+"S_"+fileName);
		return categoryDao.update(category);
	}
	
	public List<Map> queryAllCategorys(Map parameter){
		return categoryDao.queryAll(parameter);
	}
}

package com.netbull.shop.category.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.netbull.shop.category.entity.Category;
import com.netbull.shop.category.service.CategoryService;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.util.DataTableUtils;
import com.netbull.shop.util.RequestUtils;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/category")
	public String initQuery(HttpServletRequest request, HttpServletResponse response){
		return "category/categoryList";
	}
	
	@RequestMapping(value="/category/query")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	public Map<String,Object> query(Integer sEcho, Integer iColumns, Integer iDisplayStart, Integer iDisplayLength, HttpServletRequest request) {
		Map<String, String> requestMap = RequestUtils.parameterToMap(request);
		Page page = categoryService.page(iDisplayStart, iDisplayLength, requestMap);
		List<List<?>> aaData = new ArrayList<List<?>>();
		Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho, page.getTotal());
		for (Object o : page.getResult()) {
			Map map = (Map)o;
			List<Object> list = new ArrayList<Object>();
			list.add(map.get("id"));
			list.add(map.get("categoryCode"));
			list.add(map.get("name"));
			list.add(map.get("alias"));
			list.add(map.get("type"));
			list.add(map.get("createPerson"));
			list.add(map.get("createTime"));
			list.add(map.get("updatePerson"));
			list.add(map.get("updateTime"));
			list.add("");
			aaData.add(list);
		}
		resultMap.put("aaData", aaData);
		return resultMap;
	}
	
	@RequestMapping(value="/category/initAddOrUpdate")
	public String initAddOrUpdate(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
		Integer id=StringUtil.parseInt(request.getParameter("id"), 0);
		Category category=null;
		if(id>0){
			category=categoryService.queryCategoryById(id);
		}
		model.addAttribute("category", category);
		
		return "category/categoryEdit";
	}
	
	@RequestMapping(value="/category/save")
	@ResponseBody
	public String save(Integer id,Category category,HttpServletRequest request, HttpServletResponse response){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("upload");
		JSONObject json = new JSONObject();
		int count=0;
		if(id!=null&&id>0){
			count=categoryService.updateCategory(category,file);
		}else{
			count=categoryService.saveCategory(category,file);
		}
		json.put("flag", count==1);
		return json.toString();
	}
	
	@RequestMapping(value = "/category/del")
	@ResponseBody
	public String delete(long id, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		int count = 0;
		
		if(id > 0){
			count = categoryService.delCategoryById(id);
		}
		
		json.put("flag", count == 1);
		return json.toString();
	} 
	
	@RequestMapping(value="/category/queryAll")
	@ResponseBody
	public List<Map> queryAllCategorys(HttpServletRequest request){
		return categoryService.queryAllCategorys(null);
	}
}

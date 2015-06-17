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

import com.alibaba.fastjson.JSONObject;
import com.netbull.shop.category.entity.Category;
import com.netbull.shop.category.entity.CategoryAttr;
import com.netbull.shop.category.entity.CategoryAttrClass;
import com.netbull.shop.category.entity.CategoryAttrDim;
import com.netbull.shop.category.service.CategoryAttrService;
import com.netbull.shop.category.service.CategoryService;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.util.DataTableUtils;
import com.netbull.shop.util.RequestUtils;

@Controller
public class CategoryAttrController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CategoryAttrService categoryAttrService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/categoryAttr")
	public String initQuery(Integer categoryId,ModelMap model,HttpServletRequest request, HttpServletResponse response){
		Map<String, String> requestMap = RequestUtils.parameterToMap(request);
		Category category=null;
		if(categoryId!=null && categoryId>0){
			category=categoryService.queryCategoryById(categoryId);
		}
		if(!NullUtil.isNull(category)){
			requestMap.put("categoryCode", category.getCategoryCode());
		}
		String categoryCode=StringUtil.getString(requestMap.get("categoryCode"));
		List<Map> attrClassList=new ArrayList<Map>();
		
		if(StringUtil.isEmpty(categoryCode)){
			attrClassList=categoryAttrService.queryAttrClassList(requestMap);
		}
		model.addAttribute("categoryCode", categoryCode);
		model.addAttribute("attrClassList", attrClassList);
		
		return "category/categoryAttrList";
	}
	
	@RequestMapping(value="/categoryAttr/query")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	public Map<String,Object> query(Integer sEcho, Integer iColumns, Integer iDisplayStart, Integer iDisplayLength, HttpServletRequest request) {
		Map<String, String> requestMap = RequestUtils.parameterToMap(request);
		Page page = categoryAttrService.page(iDisplayStart, iDisplayLength, requestMap);
		List<List<?>> aaData = new ArrayList<List<?>>();
		Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho, page.getTotal());
		for (Object o : page.getResult()) {
			Map map = (Map)o;
			List<Object> list = new ArrayList<Object>();
			list.add(map.get("id"));
			list.add(map.get("categoryCode"));
			list.add(map.get("attrClassName"));
			list.add(map.get("attrName"));
			list.add(map.get("attrCode"));
			list.add(map.get("sortNum"));
			list.add(map.get("attrEnterType"));
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
	
	@RequestMapping(value="/categoryAttr/edit")
	public String edit(Integer id,ModelMap model,HttpServletRequest request){
		Map<String, String> requestMap = RequestUtils.parameterToMap(request);
		String categoryCode=StringUtil.getString(requestMap.get("categoryCode"));
		CategoryAttr categoryAttr=null;
		if(id!=null&&id>0){
			categoryAttr=categoryAttrService.queryCategoryAttrById(id);
		}
		
		List<Map> attrClassList=new ArrayList<Map>();
		if(StringUtil.isEmpty(categoryCode)){
			attrClassList=categoryAttrService.queryAttrClassList(requestMap);
		}
		model.addAttribute("categoryCode", categoryCode);
		model.addAttribute("categoryAttr", categoryAttr);
		model.addAttribute("attrClassList", attrClassList);
		return "category/categoryAttrEdit";
	}
	
	@RequestMapping(value="/categoryAttr/addOrUpdate")
	@ResponseBody
	public String save(long id,CategoryAttr categoryAttr,HttpServletRequest request,HttpServletResponse response){
		JSONObject json=new JSONObject();
		int count=0;
		if(id>0){
			count=categoryAttrService.updateCategoryAttr(categoryAttr);
		}else{
			count=categoryAttrService.saveCategoryAttr(categoryAttr);
		}
		json.put("flag", count==1);
		return json.toString();
	}
	
	@RequestMapping(value="/categoryAttr/del")
	@ResponseBody
	public String del(long id){
		JSONObject json=new JSONObject();
		int count=0;
		if(id>0){
			count=categoryAttrService.deleteCategoryAttr(id);
		}
		json.put("flag", count==1);
		return json.toString();
	}
	
	/********************************************属性标识***************************************************/
	 
	@RequestMapping(value="/categoryAttr/attrClassList")
	public String initQueryAttrClass(){
		return "category/attrClassList";
	}
	
	@RequestMapping(value="/categoryAttr/queryAttrClassList")
	@ResponseBody
	public Map<String,Object> queryAttrClassList(Integer sEcho, Integer iColumns, Integer iDisplayStart, Integer iDisplayLength, HttpServletRequest request) {
		Map<String, String> requestMap = RequestUtils.parameterToMap(request);
		Page page = categoryAttrService.attrClassPage(iDisplayStart, iDisplayLength, requestMap);
		List<List<?>> aaData = new ArrayList<List<?>>();
		Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho, page.getTotal());
		for (Object o : page.getResult()) {
			Map map = (Map)o;
			List<Object> list = new ArrayList<Object>();
			list.add(map.get("id"));
			list.add(map.get("categoryCode"));
			list.add(map.get("attrClassName"));
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
	
	@RequestMapping(value="/categoryAttr/addOrUpdateAttrClass")
	@ResponseBody
	public String addOrUpdateAttrClass(Integer id,CategoryAttrClass categoryAttrClass,HttpServletRequest request){
		JSONObject json=new JSONObject();
		int count=0;
		if(id!=null&&id>0){
			categoryAttrClass.setId(id);
			count=categoryAttrService.updateCategoryAttrClass(categoryAttrClass);
		}else{
			count=categoryAttrService.saveCategoryAttrClass(categoryAttrClass);
		}
		json.put("flag", count==1);
		return json.toString();
	}
	
	@RequestMapping(value="/categoryAttr/deleteAttrClass")
	@ResponseBody
	public String deleteAttrClass(long id){
		JSONObject json=new JSONObject();
		int count=0;
		if(id>0)count=categoryAttrService.deleteCategoryAttrClass(id);
		json.put("flag", count==1);
		return json.toString();
	}
	
	@RequestMapping(value="/categoryAttr/queryAttrClass")
	@ResponseBody
	public CategoryAttrClass queryAttrClass(Integer id){
		CategoryAttrClass categoryAttrClass=null;
		if(id>0){
			categoryAttrClass=categoryAttrService.queryAttrClassById(id);
		}
		return categoryAttrClass;
	}
	/********************************************维度***************************************************/
	@RequestMapping(value="/categoryAttr/attrDim")
	public String dim(Integer attrId,ModelMap model,HttpServletRequest request){
		Map<String, String> requestMap = RequestUtils.parameterToMap(request);
		CategoryAttr categoryAttr=null;
		if(attrId!=null && attrId>0){
			categoryAttr=categoryAttrService.queryCategoryAttrById(attrId);
		}
		if(!NullUtil.isNull(categoryAttr)){
			model.addAttribute("attrCode", categoryAttr.getAttrCode());
		}
		model.addAttribute("categoryCode", requestMap.get("categoryCode"));
		return "category/attrDimList";
	}
	
	@RequestMapping(value="/categoryAttr/queryAttrDimList")
	@ResponseBody
	public Map<String,Object> queryAttrDimList(Integer sEcho, Integer iColumns, Integer iDisplayStart, Integer iDisplayLength, HttpServletRequest request) {
		Map<String, String> requestMap = RequestUtils.parameterToMap(request);
		Page page = categoryAttrService.attrDimPage(iDisplayStart, iDisplayLength, requestMap);
		List<List<?>> aaData = new ArrayList<List<?>>();
		Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho, page.getTotal());
		for (Object o : page.getResult()) {
			Map map = (Map)o;
			List<Object> list = new ArrayList<Object>();
			list.add(map.get("id"));
			list.add(map.get("categoryCode"));
			list.add(map.get("attrCode"));
			list.add(map.get("attrShowValue"));
			list.add(map.get("attrValue"));
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
	
	@RequestMapping(value="/categoryAttr/editAttrDim")
	public String editAttrDim(Integer id,ModelMap model,HttpServletRequest request){
		Map<String, String> requestMap = RequestUtils.parameterToMap(request);
		CategoryAttrDim categoryAttrDim=null;
		if(id!=null&&id>0){
			categoryAttrDim=categoryAttrService.queryCategoryAttrDimById(id);
		}
		model.addAttribute("categoryCode", requestMap.get("categoryCode"));
		model.addAttribute("attrCode", requestMap.get("attrCode"));
		model.addAttribute("categoryAttrDim", categoryAttrDim);
		return "category/attrDimEdit";
	}
	
	@RequestMapping(value="/categoryAttr/addOrUpdateAttrDim")
	@ResponseBody
	public String saveAttrDim(Integer id,CategoryAttrDim categoryAttrDim,HttpServletRequest request){
		JSONObject json=new JSONObject();
		int count=0;
		if(id!=null&&id>0){
			count=categoryAttrService.updateCategoryAttrDim(categoryAttrDim);
		}else{
			count=categoryAttrService.saveCategoryAttrDim(categoryAttrDim);
		}
		json.put("flag", count==1);
		return json.toString();
	}
	
	@RequestMapping(value="/categoryAttr/deleteAttrDim")
	@ResponseBody
	public String deleteAttrDim(long id){
		JSONObject json=new JSONObject();
		int count=0;
		if(id>0){
			count=categoryAttrService.deleteCategoryAttrDim(id);
		}
		json.put("flag", count==1);
		return json.toString();
	}
}

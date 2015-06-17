package com.netbull.shop.section.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.manage.common.util.RequestUtils;
import com.netbull.shop.section.entity.Section;
import com.netbull.shop.section.service.SectionService;

@Controller("shop/section/controller/SectionController")
public class SectionController {
	
	@Resource
	private SectionService sectionService; 

	@RequestMapping(value="/section/second_level_sections")
	public String secondLevelSection(long sectionId, HttpServletRequest request, HttpServletResponse response) {
		List<Section> sections = sectionService.findSecondLevelSections(sectionId);
		request.setAttribute("sections", sections);
		return "section/hospital_sections";
	}
	
	@RequestMapping(value="/section/querySectionList")
	@ResponseBody
	public String queryAllSection(HttpServletRequest request){
		Map<String,String> requestMap=RequestUtils.parameterToMap(request);
		JSONObject json=new JSONObject();
		List<Section> sectionList=sectionService.querySectionList(requestMap);
		json.put("sectionList", sectionList);
		return json.toString();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/section/queryGroupSectionList")
	@ResponseBody
	public List<Map<String,Object>> queryGroupSectionList(HttpServletRequest request){
		Map<String,String> requestMap=RequestUtils.parameterToMap(request);
		List<Map<String,Object>> resultList=new ArrayList<Map<String,Object>>();
		
		Map parameter=new HashMap();
		parameter.put("level", "1");
		parameter.put("parentid", "0");
		List<Section> parentList=sectionService.querySectionList(parameter);
		if(!NullUtil.isNull(parentList)){
			for(Section section:parentList){
				Map param=new HashMap();
				param.put("level", "2");
				param.put("parentid", section.getId());
				List<Section> childList=sectionService.querySectionList(param);
				
				Map<String,Object> resultMap=new HashMap<String, Object>();
				resultMap.put("parentid", section.getId());
				resultMap.put("childList", childList);
				resultList.add(resultMap);
			}
		}
		return resultList;
	}
}

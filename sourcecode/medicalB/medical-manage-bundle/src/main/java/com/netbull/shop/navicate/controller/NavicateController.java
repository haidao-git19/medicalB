package com.netbull.shop.navicate.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.manage.common.util.RequestUtils;
import com.netbull.shop.navicate.service.NavicateService;

@Controller
public class NavicateController {

	@Autowired
	private NavicateService navicateService;
	
	@RequestMapping(value="/navicate/queryNavicateList")
	@ResponseBody
	public List<Map> queryNavicateList(HttpServletRequest request){
		Map<String,String> requestMap=RequestUtils.parameterToMap(request);
		return navicateService.queryNavicateList(requestMap);
	}
	
	@RequestMapping(value="/navicate/querySearchConditionList")
	@ResponseBody
	public List<Map> querySearchConditionList(HttpServletRequest request){
		Map<String,String> requestMap=RequestUtils.parameterToMap(request);
		return navicateService.querySearchConditionList(requestMap);
	}
	
	@RequestMapping(value="/navicate/queryConditionOptList")
	@ResponseBody
	public List<Map> queryConditionOptList(HttpServletRequest request){
		Map<String,String> requestMap=RequestUtils.parameterToMap(request);
		return navicateService.queryConditionOptList(requestMap);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/navicate/queryAllGroupConditionOptList")
	@ResponseBody
	public List<Map<String,Object>> queryAllGroupConditionOptList(HttpServletRequest request){
		Map<String,String> requestMap=RequestUtils.parameterToMap(request);
		List<Map<String,Object>> resultList=new ArrayList<Map<String,Object>>();
		List<Map> conditionList=navicateService.querySearchConditionList(requestMap);
		
		if(!NullUtil.isNull(conditionList)){
			for(Map conditionMap:conditionList){
				
				Map parameter=new HashMap();
				parameter.put("scId", conditionMap.get("id"));
				List<Map> conditionOptList=navicateService.queryConditionOptList(parameter);
				
				if(!NullUtil.isNull(conditionOptList)){
					
					Map<String,Object> resultMap=new HashMap<String, Object>();
					resultMap.put("scId", conditionMap.get("id"));
					resultMap.put("conditionOptList", conditionOptList);
					
					resultList.add(resultMap);
				}
			}
		}
		
		return resultList;
	}
	
	@RequestMapping(value="/navicate/queryScMappingByParam")
	@ResponseBody
	public List<Map> queryScMappingByParam(HttpServletRequest request){
		Map<String,String> requestMap=RequestUtils.parameterToMap(request);
		List<Map> scMappingList=navicateService.queryScMappingByParam(requestMap);
		return scMappingList;
	}
}

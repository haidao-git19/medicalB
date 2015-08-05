package com.netbull.shop.area.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.netbull.shop.area.entity.Area;
import com.netbull.shop.area.service.AreaService;
import com.netbull.shop.hospital.entity.Hospital;
import com.netbull.shop.hospital.service.HospitalService;
import com.netbull.shop.util.RequestUtils;

@Controller("areaController")
public class AreaController {

	@Resource
	private AreaService areaService;
	
	@RequestMapping(value="/area/handleAllArea")
	@ResponseBody
	public List<Area> handleAllArea(HttpServletRequest request,HttpServletResponse response) {
		
		return  areaService.handleAllArea();
	}
	
	@RequestMapping(value="/area/queryAreasByParams")
	@ResponseBody
	public List<Area> queryAreasByParams(HttpServletRequest request,HttpServletResponse response){
		Map<String,String> requestMap=RequestUtils.parameterToMap(request);
		return areaService.queryAreasByParams(requestMap);
	}
}

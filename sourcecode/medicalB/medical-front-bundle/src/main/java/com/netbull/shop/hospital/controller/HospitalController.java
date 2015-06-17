package com.netbull.shop.hospital.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netbull.shop.area.entity.Area;
import com.netbull.shop.area.service.AreaService;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.dao.BaseDao;
import com.netbull.shop.hospital.entity.Hospital;
import com.netbull.shop.hospital.service.HospitalService;
import com.netbull.shop.section.service.SectionService;
import com.netbull.shop.shiro.ShiroUser;
import com.netbull.shop.util.DataTableUtils;
import com.netbull.shop.util.RequestUtils;


@Controller("hospitalController")
public class HospitalController {

	@Resource
	private HospitalService hospitalService;
	@Resource
	private AreaService areaService; 
	@Resource
	private SectionService sectionService;
	
	@RequestMapping(value = "/hospitalList.json")
	@ResponseBody
	public  Map<String,Object> query(Integer sEcho, Integer iColumns, Integer iDisplayStart, 
			Integer iDisplayLength,HttpServletRequest request) {
		
		Map<String, String> requestMap =  RequestUtils.parameterToMap(request);
		
		Page page = hospitalService.page(iDisplayStart,iDisplayLength,requestMap);
		 List<List<?>> aaData = new ArrayList<List<?>>();
		 Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho , page.getTotal());
		 List<Area> areaLst=areaService.handleAllArea();
		  for (Object o : page.getResult()) {
				Map map = (Map)o;
				List<Object> list = new ArrayList<Object>();
				list.add(map.get("hospitalName"));
				list.add(map.get("linkMan"));
				list.add(map.get("linkPhone"));
				list.add(map.get("address"));
				
				String latnId="";
				for (int i = 0; i < areaLst.size(); i++) {
					if(String.valueOf(areaLst.get(i).getAreaID()).equals(map.get("areaID").toString())){
						latnId=areaLst.get(i).getAreaName();
					}
				}
				if(!StringTools.isEmpty(latnId)){
					list.add(latnId);
				}else{
					list.add(map.get("areaID"));
				}
				
				list.add(map.get("hospitalLevel"));
				
				list.add(map.get("hospitalID"));
				aaData.add(list);
			}
			resultMap.put("aaData", aaData);
		   return resultMap;
	}
	
	@RequestMapping(value="/hospital.do")
	public String initQuery(HttpServletRequest request,HttpServletResponse response) {
		
		return "hospital/hospitalList";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/hospital/addOrUpdate")
	@ResponseBody
	public String addOrUpdate(Hospital hospital,HttpServletRequest request,HttpServletResponse response) {
		List<Integer> sectionIDList=hospital.getSectionList();
		List<Integer> backupList=null;
		if(!NullUtil.isNull(sectionIDList)){
			backupList=new ArrayList<Integer>(sectionIDList);
		}
		
		JSONObject json=new JSONObject();
		json.put("flag", false);
		if(hospital.getHospitalID()>0){
			hospitalService.update(hospital);
			Map parameter=new HashMap();
			parameter.put("hospitalID", hospital.getHospitalID());
			List<Map> relatedSectionList=sectionService.queryRelatedSectionList(parameter);
			List<Integer> relatedSectionIDList=new ArrayList<Integer>();
			if(!NullUtil.isNull(relatedSectionList)){
				for(Map rMap:relatedSectionList){
					relatedSectionIDList.add(Integer.parseInt(StringUtil.getString(rMap.get("sectionID"))));
				}
			}
			
			Map param=new HashMap();
			param.put("hospitalID", hospital.getHospitalID());
			if(NullUtil.isNull(sectionIDList)&&!NullUtil.isNull(relatedSectionIDList)){
				for(Integer sID:relatedSectionIDList){
					param.put("sectionID", sID);
					sectionService.deleteRelatedSection(param);
				}
			}else if(!NullUtil.isNull(sectionIDList)&&NullUtil.isNull(relatedSectionIDList)){
				for(Integer sID:sectionIDList){
					param.put("sectionID", sID);
					sectionService.saveRelatedSection(param);
				}
			}else if(!NullUtil.isNull(sectionIDList)&&!NullUtil.isNull(relatedSectionIDList)){
				sectionIDList.removeAll(relatedSectionIDList);
				for(Integer sID:sectionIDList){
					param.put("sectionID", sID);
					sectionService.saveRelatedSection(param);
				}
				relatedSectionIDList.removeAll(backupList);
				for(Integer sID:relatedSectionIDList){
					param.put("sectionID", sID);
					sectionService.deleteRelatedSection(param);
				}
			}
			
			json.put("flag", true);
		}else{
			//判断以是否已存在 该名称的医院
			Hospital hospl=hospitalService.findById(hospital);
			if(!StringTools.isEmpty(hospl.getHospitalName())){
				json.put("msg", "该医院已存在不能添加!");
			}else{
				json.put("flag", true);
				hospitalService.save(hospital);
				Map parameter=new HashMap();
				parameter.put("hospitalID", hospital.getHospitalID());
				if(!NullUtil.isNull(sectionIDList)){
					for(Integer sectionID:sectionIDList){
						parameter.put("sectionID", sectionID);
						sectionService.saveRelatedSection(parameter);
					}
				}
			}
		}
		
		return json.toString();
	}
	
	
	@RequestMapping(value="/hospital/initAddOrUpdate")
	public String initAddOrUpdate(Hospital hospital,HttpServletRequest request,HttpServletResponse response) {
		Hospital hospl=new Hospital();
		if(hospital.getHospitalID()>0){
			hospl=hospitalService.findById(hospital);
		}
		
		request.setAttribute("hospl",hospl);
		return "hospital/initAddOrUpdate";
	}
	
	

	@RequestMapping(value="/hospital/showHospital")
	public String showHospital(Hospital hospital,HttpServletRequest request,HttpServletResponse response) {
		if(hospital.getHospitalID()>0){
			Hospital hospl=hospitalService.findById(hospital);
			request.setAttribute("hospl",hospl);
		}
		return "hospital/showHospital";
	}
	
	@RequestMapping(value="/hospital/del")
	@ResponseBody
	public String handleSeletedVillage(Hospital hospital,HttpServletRequest request,HttpServletResponse response){
		JSONObject json=new JSONObject();
		json.put("flag", true);
		try {
			int count=	hospitalService.del(hospital);
			if(count>0){
				json.put("flag", true);
			}
		} catch (Exception e) {
			json.put("flag", false);
		}
		return json.toString();
	}
	
	@RequestMapping(value="/hospital/area_hospitals")
	public String loadHospital(int areaId, HttpServletRequest request, HttpServletResponse response) {
		List<Hospital> hospitals = hospitalService.findByAreaID(areaId);
		request.setAttribute("hospitals", hospitals);
		return "hospital/area_hospitals";
	}
	
	
	@RequestMapping("/hotel/baiduMap")
	public String baiduMap() {
		return "hospital/mapChooser";
	}
	
	@RequestMapping("/hotel/invokenBaiduMap")
	public String invokenBaiduMap() {
		 return "hospital/invokenBaiduMap";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/hospital/queryCheckedSection")
	@ResponseBody
	public String queryCheckedSection(Integer hospitalID,HttpServletRequest request){
		Map<String,String> requestMap=RequestUtils.parameterToMap(request);
		JSONObject json=new JSONObject();
		List<Map> relatedSectionList=null;
		if(hospitalID!=null&&hospitalID!=0){
			Map parameter=new HashMap();
			parameter.put("hospitalID", hospitalID);
			relatedSectionList=sectionService.queryRelatedSectionList(parameter);
		}
		json.put("relatedSectionList", relatedSectionList);
		return json.toString();
	}
	
	@RequestMapping(value="/hospital/queryHospital")
	@ResponseBody
	public Hospital queryHospital(Hospital hospital,HttpServletRequest reuqest){
		Hospital hospl=new Hospital();
		if(hospital.getHospitalID()>0){
			hospl=hospitalService.findById(hospital);
		}
		return hospl;
	}
}

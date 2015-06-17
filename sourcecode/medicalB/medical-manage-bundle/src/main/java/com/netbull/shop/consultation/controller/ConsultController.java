package com.netbull.shop.consultation.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.consultation.entity.Consult;
import com.netbull.shop.consultation.service.ConsultService;
import com.netbull.shop.hospital.entity.Hospital;
import com.netbull.shop.util.DataTableUtils;
import com.netbull.shop.util.RequestUtils;

@Controller("consultController")
public class ConsultController {

	@Resource
	private ConsultService consultService;
	
	@RequestMapping(value="/consult.do")
	public String initQuery(HttpServletRequest request,HttpServletResponse response) {
		
		return "consult/consultList";
	}
	
	@RequestMapping(value = "/consult.json")
	@ResponseBody
	public  Map<String,Object> query(Integer sEcho, Integer iColumns, Integer iDisplayStart, 
			Integer iDisplayLength,HttpServletRequest request) {
		
		Map<String, String> requestMap =  RequestUtils.parameterToMap(request);
		
		Page page = consultService.page(iDisplayStart,iDisplayLength,requestMap);
		 List<List<?>> aaData = new ArrayList<List<?>>();
		 Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho , page.getTotal());
		  for (Object o : page.getResult()) {
				Map map = (Map)o;
				List<Object> list = new ArrayList<Object>();
				list.add(map.get("consultationDate").toString());
				list.add(map.get("question"));
				list.add(map.get("answer"));
				list.add(map.get("isFree"));
				list.add(map.get("evaluation"));
				list.add(map.get("consultationID"));
				aaData.add(list);
			}
			resultMap.put("aaData", aaData);
		   return resultMap;
	}
	
	@RequestMapping(value="/consult/initAddOrUpdate")
	public String initAddOrUpdate(Consult consult,HttpServletRequest request,HttpServletResponse response) {
		Consult consult1=new Consult();
		if(consult.getConsultationID()>0){
			consult1=consultService.findById(consult);
		}
		request.setAttribute("consult1",consult1);
		return "consult/initAddOrUpdate";
	}
	
	@RequestMapping(value="/consult/addOrUpdate")
	@ResponseBody
	public String addOrUpdate(Consult consult,HttpServletRequest request,HttpServletResponse response) {
		JSONObject json=new JSONObject();
		json.put("flag", false);
		if(consult.getConsultationID()>0){
			consultService.update(consult);
			json.put("flag", true);
		}else{
			consult.setConsultationDate(new Date());
			consultService.save(consult);
			json.put("flag", true);
		}
		
		return json.toString();
	}
	
	@RequestMapping(value="/consult/showConsult")
	public String showHospital(Consult consult,HttpServletRequest request,HttpServletResponse response) {
		if(consult.getConsultationID()>0){
			Consult consult1=consultService.findById(consult);
			request.setAttribute("consult11",consult1);
		}
		return "hospital/showHospital";
	}
	
	@RequestMapping(value="/consult/del")
	@ResponseBody
	public String handleSeletedVillage(Consult consult,HttpServletRequest request,HttpServletResponse response){
		JSONObject json=new JSONObject();
		json.put("flag", true);
		try {
			int count=	consultService.del(consult);
			if(count>0){
				json.put("flag", true);
			}
		} catch (Exception e) {
			json.put("flag", false);
		}
		return json.toString();
	}
}

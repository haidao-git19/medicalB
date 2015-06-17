package com.netbull.shop.nursing.controller;

import java.util.ArrayList;
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
import com.netbull.shop.nursing.entity.Nursing;
import com.netbull.shop.nursing.service.NursingService;
import com.netbull.shop.util.DataTableUtils;
import com.netbull.shop.util.RequestUtils;

@Controller("nursingController")
public class NursingController {

	@Resource
	private NursingService nursingService;

	
	@RequestMapping(value = "/nursingList.json")
	@ResponseBody
	public  Map<String,Object> query(Integer sEcho, Integer iColumns, Integer iDisplayStart, 
			Integer iDisplayLength,HttpServletRequest request) {
		
		Map<String, String> requestMap =  RequestUtils.parameterToMap(request);
		
		Page page = nursingService.page(iDisplayStart,iDisplayLength,requestMap);
		 List<List<?>> aaData = new ArrayList<List<?>>();
		 Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho , page.getTotal());
		  for (Object o : page.getResult()) {
				Map map = (Map)o;
				List<Object> list = new ArrayList<Object>();
				list.add(map.get("objectName"));
				list.add(map.get("sectionName"));
				list.add(map.get("adviceTitle"));
				list.add(map.get("adviceID"));
				aaData.add(list);
			}
			resultMap.put("aaData", aaData);
		   return resultMap;
	}
	
	@RequestMapping(value="/nursing.do")
	public String initQuery(HttpServletRequest request,HttpServletResponse response) {
		return "nursing/nursingList";
	}
	
	@RequestMapping(value="/nursing/addOrUpdate")
	@ResponseBody
	public String addOrUpdate(Nursing nursing,HttpServletRequest request,HttpServletResponse response) {
		JSONObject json=new JSONObject();
		json.put("flag", false);
		if(nursing.getAdviceID()>0){
			nursingService.update(nursing);
			json.put("flag", true);
		}else{
			nursingService.save(nursing);
			json.put("flag", true);
		}
		
		return json.toString();
	}
	
	
	@RequestMapping(value="/nursing/initAddOrUpdate")
	public String initAddOrUpdate(Nursing nursing,HttpServletRequest request,HttpServletResponse response) {
		Nursing nurs=new Nursing();
		if(nursing.getAdviceID()>0){
			nurs=nursingService.findById(nursing);
		}
		request.setAttribute("nurs",nurs);
		return "nursing/initAddOrUpdate";
	}
	
	

	@RequestMapping(value="/nursing/showNursing")
	public String showNursing(Nursing nursing,HttpServletRequest request,HttpServletResponse response) {
		if(nursing.getAdviceID()>0){
			Nursing nurs=nursingService.findById(nursing);
			request.setAttribute("nurs",nurs);
		}
		return "nursing/showNursing";
	}
	
	@RequestMapping(value="/nursing/del")
	@ResponseBody
	public String handleSeletedVillage(Nursing nursing,HttpServletRequest request,HttpServletResponse response){
		JSONObject json=new JSONObject();
		json.put("flag", true);
		try {
			int count=	nursingService.del(nursing);
			if(count>0){
				json.put("flag", true);
			}
		} catch (Exception e) {
			json.put("flag", false);
		}
		return json.toString();
	}
	
	
	
}

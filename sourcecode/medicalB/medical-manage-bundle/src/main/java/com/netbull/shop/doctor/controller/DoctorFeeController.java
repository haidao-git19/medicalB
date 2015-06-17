package com.netbull.shop.doctor.controller;

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
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.doctor.entity.DoctorFee;
import com.netbull.shop.doctor.service.DoctorFeeService;
import com.netbull.shop.util.DataTableUtils;
import com.netbull.shop.util.RequestUtils;

@Controller("doctorFeeController")
public class DoctorFeeController {
	
	@Resource
	private DoctorFeeService doctorFeeService;
	
	@RequestMapping(value="/doctorFee")
	public String initQuery(HttpServletRequest request, HttpServletResponse response) {
		return "doctor/doctorFee_list";
	}

	@RequestMapping(value = "/doctorFee/query")
	@ResponseBody
	public Map<String,Object> query(Integer sEcho, Integer iColumns, Integer iDisplayStart, Integer iDisplayLength, HttpServletRequest request) {
		Map<String, String> requestMap = RequestUtils.parameterToMap(request);
		Page page = doctorFeeService.page(iDisplayStart, iDisplayLength, requestMap);
		List<List<?>> aaData = new ArrayList<List<?>>();
		Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho, page.getTotal());
		for (Object obj : page.getResult()) {
			DoctorFee doctorFee = (DoctorFee)obj;
			List<Object> list = new ArrayList<Object>();
			list.add(doctorFee.getId());
			
			String type = "";
			switch(doctorFee.getType()) {
			case 1:
				type = "普通咨询";
				break;
			case 2:
				type = "网络咨询";
				break;
			case 3:
				type = "语音咨询";
				break;
			case 4:
				type = "视频咨询";
				break;
			case 5:
				type = "加号";
				break;
			}
			
			list.add(type);
			list.add(doctorFee.getLevel());
			list.add(doctorFee.getLevelTitle());
			list.add(doctorFee.getFee());
			list.add("");
			aaData.add(list);
		}
		resultMap.put("aaData", aaData);
		return resultMap;
	}
	
	@RequestMapping(value = "/doctorFee/del")
	@ResponseBody
	public String delete(long id, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		int count = 0;
		
		if(id > 0){
			count = doctorFeeService.deleteById(id);
		}
		
		json.put("flag", count == 1);
		return json.toString();
	}
	
	@RequestMapping(value = "/doctorFee/initAddOrUpdate")
	public String initAddOrUpdate(HttpServletRequest request, HttpServletResponse response) {
		DoctorFee doctorFee = new DoctorFee();
		
		int id = StringUtil.parseInt(request.getParameter("id"), 0);
		if(id > 0){
			doctorFee = doctorFeeService.findById(id);
		}
		
		request.setAttribute("doctorFee", doctorFee);
		
		return "doctor/doctorFee_addOrUpdate";
	}
	
	@RequestMapping(value = "/doctorFee/addOrUpdate")
	@ResponseBody
	public String addOrUpdate(DoctorFee doctorFee, HttpServletRequest request, HttpServletResponse response) {
		int count = 0;
		
		if(doctorFee.getId() > 0){ //update
			count = doctorFeeService.update(doctorFee);
		}else{//add
			count = doctorFeeService.save(doctorFee);
		}
		
		JSONObject json = new JSONObject();
		json.put("flag", count > 0);
		return json.toString();
	}
	
}

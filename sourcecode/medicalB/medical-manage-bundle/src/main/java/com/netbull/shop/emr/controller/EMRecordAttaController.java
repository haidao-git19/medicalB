package com.netbull.shop.emr.controller;

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
import com.netbull.shop.emr.entity.EMRecordAtta;
import com.netbull.shop.emr.service.EMRecordAttaService;
import com.netbull.shop.util.DataTableUtils;
import com.netbull.shop.util.RequestUtils;

@Controller("emrecordAttaController")
public class EMRecordAttaController {
	
	@Resource
	private EMRecordAttaService emrecordAttaService;
	
	@RequestMapping(value="/emrecord_atta")
	public String initQuery(HttpServletRequest request, HttpServletResponse response) {
		return "emrecordAtta/emrecordAtta_list";
	}

	@RequestMapping(value = "/emrecord_atta/query")
	@ResponseBody
	public Map<String,Object> query(Integer sEcho, Integer iColumns, Integer iDisplayStart, Integer iDisplayLength, HttpServletRequest request) {
		Map<String, String> requestMap = RequestUtils.parameterToMap(request);
		
		Page page = emrecordAttaService.page(iDisplayStart, iDisplayLength, requestMap);
		List<List<?>> aaData = new ArrayList<List<?>>();
		Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho, page.getTotal());
		for (Object obj : page.getResult()) {
			EMRecordAtta emrecordAtta = (EMRecordAtta)obj;
			List<Object> list = new ArrayList<Object>();
			list.add(emrecordAtta.getAttaID());
			list.add(emrecordAtta.getAttaName());
			list.add(emrecordAtta.getAttaURL());
			list.add(emrecordAtta.getRecordID());
			list.add("");
			aaData.add(list);
		}
		resultMap.put("aaData", aaData);
		return resultMap;
	}
	
	@RequestMapping(value = "/emrecord_atta/details")
	public String details(long attaID, HttpServletRequest request, HttpServletResponse response) {
		if(attaID > 0){
			EMRecordAtta emrecordAtta = emrecordAttaService.findById(attaID);
			request.setAttribute("emrecordAtta", emrecordAtta);
		}
		
		return "emrecordAtta/emrecordAtta_details";
	}
	
	@RequestMapping(value = "/emrecord_atta/del")
	@ResponseBody
	public String delete(long attaID, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		int count = 0;
		
		if(attaID > 0){
			count = emrecordAttaService.deleteById(attaID);
		}
		
		json.put("flag", count == 1);
		return json.toString();
	}
	
	@RequestMapping(value = "/emrecord_atta/initAddOrUpdate")
	public String initAddOrUpdate(HttpServletRequest request, HttpServletResponse response) {
		EMRecordAtta emrecordAtta = new EMRecordAtta();
		
		int attaID = StringUtil.parseInt(request.getParameter("attaID"), 0);
		int recordID = StringUtil.parseInt(request.getParameter("recordID"), 0);
		
		if(attaID > 0){
			emrecordAtta = emrecordAttaService.findById(attaID);
		}
		
		request.setAttribute("recordID", recordID);
		request.setAttribute("emrecordAtta", emrecordAtta);
		
		return "emrecordAtta/emrecordAtta_addOrUpdate";
	}
	
	@RequestMapping(value = "/emrecord_atta/addOrUpdate")
	@ResponseBody
	public String addOrUpdate(EMRecordAtta emrecordAtta, HttpServletRequest request, HttpServletResponse response) {
		int count = 0;
		if(emrecordAtta.getAttaID() > 0){ //update
			count = emrecordAttaService.update(emrecordAtta);
		}else{//add
			count = emrecordAttaService.save(emrecordAtta);
		}
		
		JSONObject json = new JSONObject();
		json.put("flag", count > 0);
		return json.toString();
	}
}

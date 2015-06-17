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
import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.dao.BaseDao;
import com.netbull.shop.emr.entity.EMRecord;
import com.netbull.shop.emr.service.EMRecordService;
import com.netbull.shop.shiro.ShiroUser;
import com.netbull.shop.util.DataTableUtils;
import com.netbull.shop.util.DateUtils;
import com.netbull.shop.util.RequestUtils;

@Controller("emrecordController")
public class EMRecordController {
	
	@Resource
	private EMRecordService emrecordService;
	
	@RequestMapping(value="/emrecord")
	public String initQuery(HttpServletRequest request, HttpServletResponse response) {
		return "emrecord/emrecord_list";
	}

	@RequestMapping(value = "/emrecord/query")
	@ResponseBody
	public Map<String,Object> query(Integer sEcho, Integer iColumns, Integer iDisplayStart, Integer iDisplayLength, HttpServletRequest request) {
		Map<String, String> requestMap = RequestUtils.parameterToMap(request);
		
		ShiroUser currUser = BaseDao.queryCurrentShiroUser();
		requestMap.put("creator", String.valueOf(currUser.getId()));
		
		Page page = emrecordService.page(iDisplayStart, iDisplayLength, requestMap);
		List<List<?>> aaData = new ArrayList<List<?>>();
		Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho, page.getTotal());
		for (Object obj : page.getResult()) {
			EMRecord emrecord = (EMRecord)obj;
			List<Object> list = new ArrayList<Object>();
			list.add(emrecord.getPatientName());
			list.add(emrecord.getHospitalName());
			list.add(emrecord.getSectionName());
			list.add(emrecord.getDiseaseName());
			list.add(ConfigLoadUtil.loadConfig().getPropertie("EMR_SRC_" + emrecord.getSource()));
			list.add(emrecord.getSource() == 2 ? emrecord.getCreatorName() : " - ");
			list.add(DateUtils.toString(emrecord.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
			list.add(DateUtils.toString(emrecord.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
			list.add(emrecord.getId());
			aaData.add(list);
		}
		resultMap.put("aaData", aaData);
		return resultMap;
	}
	
	@RequestMapping(value = "/emrecord/del")
	@ResponseBody
	public String delete(long id, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		int count = 0;
		
		if(id > 0){
			count = emrecordService.deleteById(id);
		}
		
		json.put("flag", count == 1);
		return json.toString();
	}
	
	@RequestMapping(value = "/emrecord/initAddOrUpdate")
	public String initAddOrUpdate(HttpServletRequest request, HttpServletResponse response) {
		EMRecord emrecord = new EMRecord();
		int id = StringUtil.parseInt(request.getParameter("id"), 0);
		if(id > 0){
			emrecord = emrecordService.findById(id);
		}
		
		request.setAttribute("emrecord", emrecord);
		
		return "emrecord/emrecord_addOrUpdate";
	}
	
	@RequestMapping(value = "/emrecord/addOrUpdate")
	@ResponseBody
	public String addOrUpdate(EMRecord emrecord, HttpServletRequest request, HttpServletResponse response) {
		int count = 0;
		
		ShiroUser currUser = BaseDao.queryCurrentShiroUser();
		emrecord.setCreator(currUser.getId());
		
		if(emrecord.getId() > 0){ //update
			count = emrecordService.update(emrecord);
		}else{//add
			count = emrecordService.save(emrecord);
		}
		
		JSONObject json = new JSONObject();
		json.put("flag", count > 0);
		return json.toString();
	}
}

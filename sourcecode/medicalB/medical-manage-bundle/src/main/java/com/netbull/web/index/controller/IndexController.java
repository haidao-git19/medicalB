
package com.netbull.web.index.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.netbull.shop.area.entity.Area;
import com.netbull.shop.area.service.AreaService;
import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.manage.common.util.HttpXmlUtil;
import com.netbull.shop.manage.common.util.RequestUtils;
import com.netbull.shop.util.JsonUtils;
import com.netbull.web.index.entity.HospitalList;
import com.netbull.web.index.entity.PosterResponse;
import com.netbull.web.index.entity.SectionFilterResponse;

@Controller("indexController")
public class IndexController {
	
	@Resource
	private AreaService areaService;

	@RequestMapping(value = "/anon/web")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		
		 List<Area> areaList=areaService.handleAllArea();
		request.setAttribute("areaList",areaList);
		
		//查询医院
		Map<String,String> requestMap = new HashMap<String, String>();
		requestMap.put("type", "1");
		String url = ConfigLoadUtil.loadConfig().getPropertie("queryHospitalList"); 
		String resp = HttpXmlUtil.doPost(url, requestMap);
		HospitalList hospitalList=JSON.parseObject(resp,HospitalList.class);
		request.setAttribute("hospitalList",hospitalList);
		
		
		//加载海报接口
		String posterUrl = ConfigLoadUtil.loadConfig().getPropertie("queryPoster"); 
		String posterRes = HttpXmlUtil.doPost(posterUrl, null);
		PosterResponse posterList=JSON.parseObject(posterRes,PosterResponse.class);
		request.setAttribute("posterList",posterList);
		
		return "web/index/index";
	}
	

	@RequestMapping(value = "/web/index/section")
	public String section(HttpServletRequest request, HttpServletResponse response) {
		
		
		//查询科室
		Map<String,String> requestMap = new HashMap<String, String>();
		String url = ConfigLoadUtil.loadConfig().getPropertie("querySectionFilter"); 
		String resp = HttpXmlUtil.doPost(url, requestMap);
		SectionFilterResponse sectionFilter=JSON.parseObject(resp,SectionFilterResponse.class);
		request.setAttribute("sectionFilter",sectionFilter);
		
		
		return "web/index/section";
	}
}

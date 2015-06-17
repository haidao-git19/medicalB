
package com.netbull.web.hospital.controller;

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
import com.netbull.web.hospital.entity.SectionResponse;
import com.netbull.web.hospital.entity.WebHospitalDetails;
import com.netbull.web.index.entity.HospitalList;

@Controller("webHospitalController")
public class WebHospitalController {
	@Resource
	private AreaService areaService;

	@RequestMapping(value = "/web/hospital")
	public String hospital(HttpServletRequest request, HttpServletResponse response) {

		 List<Area> areaList=areaService.handleAllArea();
			request.setAttribute("areaList",areaList);
			Map<Integer,String> areaMap=areaService.handleAllAreaMap();
			
			//查询医院
			Map<String,String> requestMap = new HashMap<String, String>();
			requestMap.put("type", "1");
			String url = ConfigLoadUtil.loadConfig().getPropertie("queryHospitalList"); 
			String resp = HttpXmlUtil.doPost(url, requestMap);
			HospitalList hospitalList=JSON.parseObject(resp,HospitalList.class);
			
			//设置本地网
			for (int i = 0; i < hospitalList.getHospitalList().size(); i++) {
				hospitalList.getHospitalList().get(i).setLatnName(areaMap.get(Integer.valueOf(hospitalList.getHospitalList().get(i).getKey())));
			}
			
			
			request.setAttribute("hospitalList",hospitalList);
		return "web/hospital/hospitalList";
	}
	
	@RequestMapping(value = "web/hospitalDetais")
	public String hospital(String hospitalID,HttpServletRequest request, HttpServletResponse response) {

		
		//查询医院详情
		Map<String,String> requestMap = new HashMap<String, String>();
		requestMap.put("hospitalID", hospitalID);
		String url = ConfigLoadUtil.loadConfig().getPropertie("queryHospitalDetails"); 
		String resp = HttpXmlUtil.doPost(url, requestMap);
		WebHospitalDetails hospitalDetails=JSON.parseObject(resp,WebHospitalDetails.class);
		
		
		//根据医院id查询科室信息
		
		String urlSection = ConfigLoadUtil.loadConfig().getPropertie("querySectionList"); 
		String respSection = HttpXmlUtil.doPost(urlSection, requestMap);
		SectionResponse sectionResponse=JSON.parseObject(respSection,SectionResponse.class);
		
		request.setAttribute("section",sectionResponse);
		
		request.setAttribute("hospitalDetails",hospitalDetails);
		return "web/hospital/hospitalDetails";
	}
	
}

package com.netbull.shop.disease.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.disease.entity.Disease;
import com.netbull.shop.disease.service.DiseaseService;
import com.netbull.shop.manage.common.util.RequestUtils;

@Controller
public class DiseaseController {

	@Autowired
	private DiseaseService diseaseService;
	
	@RequestMapping(value="/disease/queryListBySectionID")
	@ResponseBody
	public List<Disease> queryListBySectionID(HttpServletRequest request){
		Map<String,String> requestMap=RequestUtils.parameterToMap(request);
		List<Disease> diseaseList=new ArrayList<Disease>();
		
		String sectionID=StringUtil.getString(requestMap.get("sectionID"));
		if(StringUtil.isEmpty(sectionID)){
			diseaseList=diseaseService.findBySectionID(Integer.parseInt(sectionID));
		}
		return diseaseList;
	}
}

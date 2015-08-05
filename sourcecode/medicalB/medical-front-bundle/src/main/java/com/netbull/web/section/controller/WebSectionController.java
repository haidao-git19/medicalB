package com.netbull.web.section.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.section.entity.Section;
import com.netbull.shop.section.service.SectionService;
import com.netbull.shop.util.HttpXmlUtil;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;
import com.netbull.web.index.service.ConsultService;
import com.netbull.web.index.service.DoctorService;
import com.netbull.web.section.service.PlusService;

@Controller
public class WebSectionController {

	@Autowired
	private DoctorService doctorService;
	@Autowired
	private PlusService plusService;
	@Autowired
	private ConsultService consultService;
	@Autowired
	private SectionService sectionService;
	
	@RequestMapping(value="/anon/web/toSection")
	public String section(HttpServletRequest request){
		return "web/section/sectionList";
	}
	
	@RequestMapping(value="/anon/web/querySectionList")
	@ResponseBody
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map> querySectionList(HttpServletRequest request){
		List<Map> sectionList=new ArrayList<Map>();
		
		Map<String,String> requestMap=RequestUtils.parameterToMap(request);
		String sectionFilterUrl=ConfigLoadUtil.loadConfig().getPropertie("querySectionFilter");
		String resp=HttpXmlUtil.doPost(sectionFilterUrl, requestMap);
		Map respMap=JsonUtils.json2Map(resp);
		
		if(StringTools.equals("0", String.valueOf(respMap.get("code")))){
			sectionList=(List<Map>) respMap.get("filter");
		}
		return sectionList;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/anon/web/sectionDetail")
	public String sectionDetail(ModelMap model,HttpServletRequest request){
		String hospitalID=request.getParameter("hospitalID");
		String sectionID=request.getParameter("sectionID");
		Map<String,String> requestMap=new HashMap<String, String>();
		requestMap.put("hospitalID", hospitalID);
		
		String url = ConfigLoadUtil.loadConfig().getPropertie("queryHospitalDetails"); 
		String resp = HttpXmlUtil.doPost(url, requestMap);
		Map respMap=JsonUtils.json2Map(resp);
		
		if(StringTools.equals("0", String.valueOf(respMap.get("code")))){
			model.addAttribute("hospitalDetail", respMap.get("hospitalDetail"));
		}
		
		Section section=sectionService.findById(Long.parseLong(sectionID));
		model.addAttribute("section", section);
		
		Map pram=new HashMap();
		pram.put("hospitalID", hospitalID);
		pram.put("sectionID", section.getParentid());
		Map parentSection=sectionService.queryRelatedSection(pram);
		String sectionIntroduction=String.valueOf(parentSection.get("introduction"));
		if(StringUtil.isEmpty(sectionIntroduction)&&!sectionIntroduction.equals("null")){
			if(sectionIntroduction.length()>150){
				model.addAttribute("sectionIntroduction", sectionIntroduction.substring(0, 150)+"...");
			}else{
				model.addAttribute("sectionIntroduction", sectionIntroduction);
			}
		}else{
			model.addAttribute("sectionIntroduction", "暂无数据");
		}
		
		requestMap.put("sectionID", sectionID);
		
		model.addAttribute("subscribe_plus_count", doctorService.querySubscribePlusCount(requestMap));
		model.addAttribute("isAudioCT_count", doctorService.queryIsAudioCTCount(requestMap));
		model.addAttribute("plus_success_count", plusService.queryPlusSuccessCount(requestMap));
		
		return "web/section/sectionDetail";
	}
	
	@RequestMapping(value="/anon/web/queryIsConsultDoctorList")
	@ResponseBody
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map> queryIsConsultDoctorList(HttpServletRequest request){
		List<Map> doctorList=new ArrayList<Map>();
		
		Map<String,String> requestMap=RequestUtils.parameterToMap(request);
		String url=ConfigLoadUtil.loadConfig().getPropertie("queryDoctorListByParams");
		String resp = HttpXmlUtil.doPost(url, requestMap);
		Map respMap=JsonUtils.json2Map(resp);
		
		if(StringTools.equals("0", String.valueOf(respMap.get("code")))){
			doctorList=(List<Map>) respMap.get("doctorList");
		}
		return doctorList;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/anon/web/queryLatestConsultQuestionList")
	@ResponseBody
	public List<Map> queryLatestConsultQuestionList(HttpServletRequest request){
		Map<String,Object> requestMap=new HashMap<String, Object>();
		requestMap.put("hospitalID", request.getParameter("hospitalID"));
		requestMap.put("sectionID", request.getParameter("sectionID"));
		requestMap.put("doctorID", request.getParameter("doctorID")==null?null:request.getParameter("doctorID"));
		requestMap.put("state", "1");
		requestMap.put("isRepeat", "0");
		requestMap.put("start", 0);
		requestMap.put("limit", 5);
		List<Map> consultQuestionList=consultService.queryLatestConsultList(requestMap);
		return consultQuestionList;
	}
}

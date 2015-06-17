package com.netbull.web.doctor.controller;

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
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.util.RequestUtils;
import com.netbull.web.index.entity.Doctor;
import com.netbull.web.index.service.DoctorService;

@Controller("web/doctor/controller/WebDoctorController")
public class WebDoctorController {

	@Autowired
	private DoctorService doctorService;
	
	@RequestMapping(value="/anon/web/doctor")
	public String doctor(Long doctorID,ModelMap model,HttpServletRequest request){
		model.addAttribute("hospitalID", request.getParameter("hospitalID"));
		model.addAttribute("sectionID", request.getParameter("sectionID"));
		Doctor doctor=doctorService.findById(doctorID);
		String skill=doctor.getSkill();
		String experience=doctor.getExperience();
		if(skill.length()>20){
			doctor.setSkill(skill.substring(0, 20)+"...");
		}
		if(experience.length()>40){
			doctor.setExperience(experience.substring(0, 40)+"...");
		}
		
		String realPath=ConfigLoadUtil.loadConfig().getPropertie("accessUrl");
		if(StringUtil.isEmpty(doctor.getAvatar())){
			doctor.setAvatar(realPath+doctor.getAvatar());
		}
		model.addAttribute("doctor", doctor);
		return "web/doctor/doctor";
	}
	
	@RequestMapping(value="/anon/web/queryRecomDoctorList")
	@ResponseBody
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> queryRecomDoctorList(HttpServletRequest request){
		Map<String,Object> requestMap=new HashMap<String, Object>();
		requestMap.put("hospitalID", request.getParameter("hospitalID"));
		requestMap.put("sectionID", request.getParameter("sectionID"));

		List<Map> resultList=new ArrayList<Map>();
		
		List<Map> recomDoctorList=doctorService.queryRecomDoctorList(requestMap);
		List<Map> diseaseList=new ArrayList<Map>();
		
		if(!NullUtil.isNull(recomDoctorList)){
			for(Map m:recomDoctorList){
				if(!diseaseList.contains(m.get("diseaseID"))){
					Map diseaseMap=new HashMap();
					diseaseMap.put("diseaseID", StringUtil.getString(m.get("diseaseID")));
					diseaseMap.put("diseaseName", StringUtil.getString(m.get("diseaseName")));
					diseaseList.add(diseaseMap);
				}
			}
			
			int count=0;
			for(Map diseaseMap:diseaseList){
				count++;
				Map resultMap=new HashMap();
				List<Map> doctorList=new ArrayList<Map>();
				for(Map map:recomDoctorList){
					if(StringTools.equals(String.valueOf(diseaseMap.get("diseaseID"))
							, String.valueOf(map.get("diseaseID")))){
						doctorList.add(map);
					}
				}
				resultMap.put("diseaseMap", diseaseMap);
				resultMap.put("doctorList", doctorList);
				resultList.add(resultMap);
				if(count==5){
					break;
				}
			}
		}
		
		return resultList;
	}
	
	@RequestMapping(value="/anon/web/queryDoctorDutyList")
	@ResponseBody
	public List<Map> queryDoctorDutyList(HttpServletRequest request){
		Map<String,String> requestMap=RequestUtils.parameterToMap(request);
		List<Map> doctorDutyList=doctorService.queryDoctorDutyList(requestMap);
		return doctorDutyList;
	}
	
}

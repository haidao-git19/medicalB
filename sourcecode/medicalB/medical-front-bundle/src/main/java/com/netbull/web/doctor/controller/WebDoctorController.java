package com.netbull.web.doctor.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
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
import com.netbull.web.hospital.dao.WebHospitalDao;
import com.netbull.web.hospital.entity.Hospital;
import com.netbull.web.hospital.service.HospitalSectionService;
import com.netbull.web.hospital.service.WebHospitalService;
import com.netbull.web.index.entity.Doctor;
import com.netbull.web.index.service.ConsultService;
import com.netbull.web.index.service.DoctorService;

import com.netbull.shop.util.HttpXmlUtil;
import com.netbull.shop.util.JsonUtils;


@Controller("web/doctor/controller/WebDoctorController")
public class WebDoctorController {

	@Autowired
	private ConsultService consultService;
	
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private WebHospitalService hospitalService; 
	
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
		
		String realPath=ConfigLoadUtil.loadConfig().getPropertie("accessUrl");
		if(StringUtil.isEmpty(doctor.getAvatar())){
			doctor.setAvatar(realPath+doctor.getAvatar());
		}
		model.addAttribute("doctor", doctor);
		
		Hospital hospital=hospitalService.queryByHospitalID(Integer.parseInt(request.getParameter("hospitalID")));
		model.addAttribute("hospital", hospital);
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/anon/web/queryDoctorDutyList")
	@ResponseBody
	public List<Map> queryDoctorDutyList(HttpServletRequest request){
		List<Map> resultList=new ArrayList<Map>();
		
		Map<String,String> requestMap=RequestUtils.parameterToMap(request);
		String url=ConfigLoadUtil.loadConfig().getPropertie("queryDoctorListByParams");
		String resp=HttpXmlUtil.doPost(url, requestMap);
		Map jsonMap=JsonUtils.json2Map(resp);
		
		List<Map> doctorList=null;
		if(StringTools.equals("0", String.valueOf(jsonMap.get("code")))){
			doctorList=(List<Map>) jsonMap.get("doctorList");
		}
		List<Map> doctorDutyList=doctorService.queryDoctorDutyList(requestMap);
		
		List<Map> doctorFeeList=doctorService.queryDoctorFeeList(requestMap);
		
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -14);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		requestMap.put("date_start", sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_YEAR, 14);
		requestMap.put("date_end", sdf.format(cal.getTime()));
		
		List<Map> consultCountList=consultService.queryConsultCountList(requestMap);
		
		if(!NullUtil.isNull(doctorList)&&!NullUtil.isNull(doctorDutyList)){
			for(Map doctor:doctorList){
				
				if(!NullUtil.isNull(doctorFeeList)){
					for(Map fee:doctorFeeList){
						if(StringTools.equals(String.valueOf(fee.get("doctorID")),String.valueOf(doctor.get("doctorID")))){
							doctor.put("fee", fee.get("fee")==null?"0":fee.get("fee"));
						}
					}
				}else{
					doctor.put("fee", "0");
				}
				
				if(!NullUtil.isNull(consultCountList)){
					for(Map consult:consultCountList){
						if(StringTools.equals(String.valueOf(consult.get("doctorID")),String.valueOf(doctor.get("doctorID")))){
							doctor.put("consult_answer_count", consult.get("consult_answer_count")==null?"0":consult.get("consult_answer_count"));
						}
					}
				}else{
					doctor.put("consult_answer_count", "0");
				}
				
				List<Map> personalDutyList=new ArrayList<Map>();
				for(Map duty:doctorDutyList){
					if(StringTools.equals(String.valueOf(duty.get("doctorID")),String.valueOf(doctor.get("doctorID")))){
						personalDutyList.add(duty);
					}
				}
				
				if(!NullUtil.isNull(personalDutyList)){
					Map map=new HashMap();
					if(doctor.get("consult_answer_count")==null){
						doctor.put("consult_answer_count", "0");
					}
					if(doctor.get("fee")==null){
						doctor.put("fee", "0");
					}
					map.put("doctor", doctor);
					map.put("dutyList", personalDutyList);
					resultList.add(map);
				}
			}
		}
		return resultList;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/anon/web/queryPersonalDutyTimeList")
	@ResponseBody
	public List<Map> queryPersonalDutyTimeList(HttpServletRequest request){
		List<Map> resultList=null;
		List<Map> dutyTimeList=new ArrayList<Map>();
		Map<String,String> requestMap=RequestUtils.parameterToMap(request);
		
		String url=ConfigLoadUtil.loadConfig().getPropertie("queryFreeTimeList");
		String resp=HttpXmlUtil.doPost(url, requestMap);
		Map<String,Object> jsonMap=JsonUtils.json2Map(resp);
		
		if(StringTools.equals("0", String.valueOf(jsonMap.get("code")))){
			Map<String,Object> freeTimeMap=(Map<String, Object>) jsonMap.get("freeTimeList");
			dutyTimeList=(List<Map>) freeTimeMap.get("this");
		}
		
		Map doctorFeeMap=doctorService.queryDoctorFee(requestMap);
		if(!NullUtil.isNull(dutyTimeList)){
			if(!NullUtil.isNull(doctorFeeMap)){
				for(int i=dutyTimeList.size()-1;i>=0;i--){
					dutyTimeList.get(i).put("fee", doctorFeeMap.get("fee"));
				}
			}else{
				for(int i=dutyTimeList.size()-1;i>=0;i--){
					dutyTimeList.get(i).put("fee", 0);
				}
			}
		}
		if(!NullUtil.isNull(dutyTimeList)){
			resultList=new ArrayList<Map>(Arrays.asList(new HashMap[dutyTimeList.size()]));
			Collections.copy(resultList, dutyTimeList);
			
			for(int i=dutyTimeList.size()-1;i>=0;i--){
				int count=0;
				Iterator<Map> iter=resultList.iterator();
				for(;iter.hasNext();){
					Map dutyMap=iter.next();
					if(String.valueOf(dutyMap.get("weekNum")).equals(String.valueOf(dutyTimeList.get(i).get("weekNum")))&&
							String.valueOf(dutyMap.get("dayFlag")).equals(String.valueOf(dutyTimeList.get(i).get("dayFlag")))){
						count++;
					}
					if(count==2){
						iter.remove();
						count--;
					}
				}
			}
		}
		return resultList;
	}
	
}

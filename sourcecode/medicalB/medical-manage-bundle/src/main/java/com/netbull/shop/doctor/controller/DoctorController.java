package com.netbull.shop.doctor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.netbull.shop.area.entity.Area;
import com.netbull.shop.area.service.AreaService;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.dao.BaseDao;
import com.netbull.shop.disease.entity.Disease;
import com.netbull.shop.disease.service.DiseaseService;
import com.netbull.shop.doctor.entity.Doctor;
import com.netbull.shop.doctor.entity.DoctorDisease;
import com.netbull.shop.doctor.service.DoctorService;
import com.netbull.shop.hospital.entity.Hospital;
import com.netbull.shop.hospital.service.HospitalService;
import com.netbull.shop.section.entity.Section;
import com.netbull.shop.section.service.SectionService;
import com.netbull.shop.shiro.ShiroUser;
import com.netbull.shop.util.DataTableUtils;
import com.netbull.shop.util.RequestUtils;

@Controller("doctorController")
public class DoctorController {
	
	@Resource
	private AreaService areaService;
	
	@Resource
	private DoctorService doctorService;
	@Resource
	private SectionService sectionService;
	@Resource
	private DiseaseService diseaseService;
	@Resource
	private HospitalService hospitalService;
	
	@RequestMapping(value="/doctor")
	public String initQuery(HttpServletRequest request, HttpServletResponse response) {
		return "doctor/doctor_list";
	}

	@RequestMapping(value = "/doctor/query")
	@ResponseBody
	public Map<String,Object> query(Integer sEcho, Integer iColumns, Integer iDisplayStart, Integer iDisplayLength, HttpServletRequest request) {
		Map<String, String> requestMap = RequestUtils.parameterToMap(request);
		
		ShiroUser currUser = BaseDao.queryCurrentShiroUser();
		requestMap.put("currUserId", String.valueOf(currUser.getId()));
		requestMap.put("childUserIds", String.valueOf(currUser.getChildNodes()));
		
		Page page = doctorService.page(iDisplayStart, iDisplayLength, requestMap);
		List<List<?>> aaData = new ArrayList<List<?>>();
		Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho, page.getTotal());
		for (Object obj : page.getResult()) {
			Doctor doctor = (Doctor)obj;
			List<Object> list = new ArrayList<Object>();
			list.add(doctor.getRealName());
			list.add(doctor.getSectionName());
			list.add(doctor.getPhone());
			list.add(doctor.getDoctorLevel());
			list.add(doctor.getIsNetCT() == 1?"支持":"不支持");
			list.add(doctor.getIsAudioCT() == 1?"支持":"不支持");
			list.add(doctor.getIsVideoCT() == 1?"支持":"不支持");
			list.add(doctor.getIsZZ() == 1?"支持":"不支持");
			list.add(doctor.getDoctorID());
			aaData.add(list);
		}
		resultMap.put("aaData", aaData);
		return resultMap;
	}
	
	@RequestMapping(value = "/doctor/del")
	@ResponseBody
	public String delete(long doctorID, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		int count = 0;
		
		if(doctorID > 0){
			count = doctorService.deleteById(doctorID);
		}
		
		json.put("flag", count == 1);
		return json.toString();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/doctor/initAddOrUpdate")
	public String initAddOrUpdate(HttpServletRequest request, HttpServletResponse response) {
		Doctor doctor = new Doctor();
		List<Section> firstLevelSections=null;
		int doctorID = StringUtil.parseInt(request.getParameter("doctorID"), 0);
		if(doctorID > 0){
			doctor = doctorService.findById(doctorID);
			long hospitalId = doctor.getHospitalID();
			Hospital hospital = hospitalService.findByHospitalId((int)hospitalId);
			request.setAttribute("hospitalAreaId", hospital.getAreaID());
			
			List<Hospital> hospitals = hospitalService.findByAreaID(hospital.getAreaID());
			request.setAttribute("hospitals", hospitals);
			
			Section section = sectionService.findById(doctor.getSectionID());
			request.setAttribute("firstLevelSectionId", NullUtil.isNull(section)?0:section.getParentid());
			
			Map secondParams=new HashMap();
			secondParams.put("hospitalID", hospitalId);
			secondParams.put("level", 2);
			secondParams.put("parentid", NullUtil.isNull(section)?0:section.getParentid());
			List<Section> sections = sectionService.queryAimLevelSections(secondParams);
			request.setAttribute("sections", sections);
			
			Map firstParams=new HashMap();
			firstParams.put("hospitalID", hospitalId);
			firstParams.put("level", 1);
			firstLevelSections = sectionService.queryAimLevelSections(firstParams);
		}
		
		List<Area> areaList = areaService.handleAllArea();
		request.setAttribute("areaList", areaList);
		
		request.setAttribute("firstLevelSections", firstLevelSections);
		request.setAttribute("doctor", doctor);
		
		return "doctor/doctor_addOrUpdate";
	}
	
	@RequestMapping(value = "/doctor/addOrUpdate")
	@ResponseBody
	public String addOrUpdate(Doctor doctor, HttpServletRequest request, HttpServletResponse response) {
		int count = 0;
		
		ShiroUser currUser = BaseDao.queryCurrentShiroUser();
		doctor.setCreator(currUser.getId());
		
		Hospital _hospital=hospitalService.findByHospitalId(doctor.getHospitalID());//现所在医院
		Integer _ctNum=_hospital.getCtNum()==null?0:_hospital.getCtNum();//现所在医院咨询大夫数量
		Integer _cgNum=_hospital.getCgNum()==null?0:_hospital.getCgNum();//现所在医院转诊大夫数量
		Integer _zx=doctor.getIsNetCT()+doctor.getIsVideoCT()+doctor.getIsAudioCT();//现数据是否支持咨询
		Integer _zz=doctor.getIsZZ();//现数据是否支持转诊
		
		if(doctor.getDoctorID() > 0){ //update
			
			Doctor d=doctorService.findById(doctor.getDoctorID());
			Hospital hospital=hospitalService.findByHospitalId(d.getHospitalID());//原所在医院
			Integer ctNum=hospital.getCtNum()==null?0:hospital.getCtNum();//原所在医院咨询大夫数量
			Integer cgNum=hospital.getCgNum()==null?0:hospital.getCgNum();//原所在医院转诊大夫数量
			Integer zx=(d.getIsNetCT()==null?0:d.getIsNetCT())+(d.getIsVideoCT()==null?0:d.getIsVideoCT())+(d.getIsAudioCT()==null?0:d.getIsAudioCT());//原数据是否支持咨询
			Integer zz=d.getIsZZ()==null?0:d.getIsZZ();//原数据是否支持转诊
			//咨询
			if(zx>0&&_zx==0){
				if(doctor.getHospitalID()==d.getHospitalID()&&_ctNum>0){
					_hospital.setCtNum(_ctNum-1);
				}else if(doctor.getHospitalID()!=d.getHospitalID()&&ctNum>0){
					hospital.setCtNum(ctNum-1);
				}
			}else if(zx==0&&_zx>0){
				_hospital.setCtNum(_ctNum+1);
			}else if(zx>0&&_zx>0){
				if(doctor.getHospitalID()!=d.getHospitalID()&&ctNum>0){
					hospital.setCtNum(ctNum-1);
					_hospital.setCtNum(_ctNum+1);
				}
			}
			//转诊
			if(zz==1&&_zz==0){
				if(doctor.getHospitalID()==d.getHospitalID()&&_cgNum>0){
					_hospital.setCgNum(_cgNum-1);
				}else if(doctor.getHospitalID()!=d.getHospitalID()&&cgNum>0){
					hospital.setCgNum(cgNum-1);
				}
			}else if(zz==0&&_zz==1){
				_hospital.setCgNum(_cgNum+1);
			}else if(zz>0&&_zz>0){
				if(doctor.getHospitalID()!=d.getHospitalID()&&cgNum>0){
					hospital.setCgNum(cgNum-1);
					_hospital.setCgNum(_cgNum+1);
				}
			}
			
			if(doctor.getHospitalID()!=d.getHospitalID()){
				hospitalService.update(hospital);
			}
			hospitalService.update(_hospital);
			
			count = doctorService.update(doctor);
		
		}else{//add
			
			if(_zx>0){
				_hospital.setCtNum(_ctNum+1);
			}
			if(_zz>0){
				_hospital.setCgNum(_cgNum+1);
			}
			hospitalService.update(_hospital);
			
			count = doctorService.save(doctor, currUser.getId());
		}
		
		JSONObject json = new JSONObject();
		json.put("flag", count > 0);
		return json.toString();
	}
	
	@RequestMapping(value = "/doctor/diseaseSetting")
	public String diseaseSetting(long doctorID, HttpServletRequest request, HttpServletResponse response) {
		Doctor doctor = doctorService.findById(doctorID);
		List<Disease> diseaseList = diseaseService.findBySectionID(doctor.getSectionID());
		List<DoctorDisease> doctorDiseaseList = doctorService.findDoctorDiseases(doctorID);
		
		StringBuilder doctorDiseases = new StringBuilder();
		doctorDiseases.append(",");
		for(DoctorDisease dd :doctorDiseaseList) {
			doctorDiseases.append(dd.getDiseaseID()).append(",");
		}
		
		request.setAttribute("doctor", doctor);
		request.setAttribute("diseaseList", diseaseList);
		request.setAttribute("doctorDiseases", doctorDiseases);
		
		return "/doctor/doctor_diseaseSetting";
	}
	
	@RequestMapping(value = "/doctor/commitDiseaseSetting")
	@ResponseBody
	public String commitDiseaseSetting(long doctorID, long[] diseaseID, HttpServletRequest request, HttpServletResponse response) {
		int rc = doctorService.saveDoctorDisease(doctorID, diseaseID);
		
		JSONObject json = new JSONObject();
		json.put("flag", rc > 0);
		return json.toString();
	}
	
	@RequestMapping(value="/doctor/commitDutyRemind")
	@ResponseBody
	public String commitDutyRemind(Integer doctorID,HttpServletRequest request){
		JSONObject json=new JSONObject();
		int count=0;
		Map<String,String> requestMap=RequestUtils.parameterToMap(request);
		count=doctorService.updateRemind(requestMap);
		json.put("flag", count==1);
		return json.toString();
	}
}

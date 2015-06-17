package com.netbull.shop.doctor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.netbull.shop.doctor.entity.Doctor;
import com.netbull.shop.doctor.entity.Duty;
import com.netbull.shop.doctor.service.DoctorService;
import com.netbull.shop.doctor.service.DutyService;

@Controller("dutyController")
public class DutyController {
	
	@Resource
	private DutyService dutyService;
	@Resource
	private DoctorService doctorService;
	
	@RequestMapping(value="/duty/initSetting")
	public String initSetting(long doctorId, HttpServletRequest request, HttpServletResponse response) {
		List<Duty> thisWeek = dutyService.findByDoctorIDWeekFlag(doctorId, 0);
		List<Duty> nextWeek = dutyService.findByDoctorIDWeekFlag(doctorId, 1);
		
		Map<String, Duty> thisWeekDutys = new HashMap<String, Duty>();
		for(Duty duty : thisWeek) {
			thisWeekDutys.put(doctorId + "_0_" + duty.getWeekNum() + "_" + duty.getDayFlag(), duty);
		}
		
		Map<String, Duty> nextWeekDutys = new HashMap<String, Duty>();
		for(Duty duty : nextWeek) {
			nextWeekDutys.put(doctorId + "_1_" + duty.getWeekNum() + "_" + duty.getDayFlag(), duty);
		}
		
		Doctor doctor = doctorService.findById(doctorId);
		
		request.setAttribute("doctorId", doctorId);
		request.setAttribute("doctorName", doctor.getRealName());
		request.setAttribute("thisWeekDutys", thisWeekDutys);
		request.setAttribute("nextWeekDutys", nextWeekDutys);
		
		return "duty/duty_initSetting";
	}
	
	@RequestMapping(value="/duty/setting")
	public String setting(Duty duty, HttpServletRequest request, HttpServletResponse response) {
		
		if(duty.getId() > 0) {
			Duty tmp = dutyService.findById(duty.getId());
			BeanUtils.copyProperties(tmp, duty);
		}
		
		Doctor doctor = doctorService.findById(duty.getDoctorID());
		duty.setDoctorName(doctor.getRealName());
		request.setAttribute("duty", duty);
		
		return "duty/duty_setting";
	}
	
	@RequestMapping(value="/duty/cancel")
	@ResponseBody
	public String cancel(Duty duty, HttpServletRequest request, HttpServletResponse response) {
		//TODO 校验当前操作用户 是否是医生本人 或 该医院管理员
		
		int count = dutyService.deleteById(duty.getId());
		
		JSONObject json = new JSONObject();
		if(count > 0) {
			json.put("flag", true);
			json.put("eid", "_" + duty.getWeekFlag() + "_" + duty.getWeekNum() + "_" + duty.getDayFlag());
			json.put("html", "<a href='javascript:setting(" + duty.getWeekFlag() + ", " + duty.getWeekNum() + ", " + duty.getDayFlag() + ", 0);'>新增</a>");
		}else{
			json.put("flag", false);
		}

		return json.toString();
	}
	
	@RequestMapping(value="/duty/commit")
	@ResponseBody
	public String commit(Duty duty, HttpServletRequest request, HttpServletResponse response) {
		//TODO 校验当前操作用户 是否是医生本人 或 该医院管理员
		
		int count = 0;
		if(duty.getId() > 0) {
			count = dutyService.update(duty);
		} else {
			count = dutyService.save(duty);
		}
		
		JSONObject json = new JSONObject();
		json.put("flag", count > 0);
		json.put("doctorId", duty.getDoctorID());
		
		return json.toString();
	}

}
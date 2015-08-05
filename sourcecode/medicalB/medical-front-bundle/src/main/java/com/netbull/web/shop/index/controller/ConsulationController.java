package com.netbull.web.shop.index.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netbull.web.index.entity.Doctor;
import com.netbull.web.index.service.DoctorService;

@Controller
public class ConsulationController {
	
	private static final Logger logger = Logger.getLogger("controlerLog");
	
	@Autowired
	private DoctorService doctorService;
	
	@RequestMapping(value="/anon/consulation")
	public String consulation(Long doctorID,HttpServletRequest request, HttpServletResponse response) {
		if(doctorID!=null&&doctorID>0){
			Doctor doctor=doctorService.findById(doctorID);
			request.setAttribute("doctor", doctor);
		}
		return "web/consulation/consulation";
	}
}

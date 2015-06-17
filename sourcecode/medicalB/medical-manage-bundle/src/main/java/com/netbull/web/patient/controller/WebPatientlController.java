
package com.netbull.web.patient.controller;

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
import com.netbull.web.hospital.entity.WebHospitalDetails;
import com.netbull.web.index.entity.HospitalList;

@Controller("webPatientlController")
public class WebPatientlController {

	@RequestMapping(value = "/web/register")
	public String hospital(HttpServletRequest request, HttpServletResponse response) {
		return "web/user/register";
	}
	
	
}

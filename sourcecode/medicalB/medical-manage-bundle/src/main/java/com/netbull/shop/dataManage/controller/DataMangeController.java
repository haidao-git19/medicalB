package com.netbull.shop.dataManage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("dataMangeController")
public class DataMangeController {

	@RequestMapping(value="/hospitalData.do")
	public String initQueryData(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("11");;
		return "dataManage/hospitalData";
	}
}

package com.netbull.web.shop.index.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConsulationController {
	
	private static final Logger logger = Logger.getLogger("controlerLog");
	
	@RequestMapping(value="/anon/consulation")
	public String consulation(HttpServletRequest request, HttpServletResponse response) {
		return "web/consulation/consulation";
	}
}

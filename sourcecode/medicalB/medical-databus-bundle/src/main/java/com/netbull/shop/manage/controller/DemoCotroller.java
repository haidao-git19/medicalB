package com.netbull.shop.manage.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.common.config.ServerInfo;
import com.netbull.shop.util.IOUtils;


@Controller
public class DemoCotroller {

	private static final Logger logger = Logger.getLogger("controlerLog");
	private static String path = ServerInfo.getSystemWorkPath() + ServerInfo.FILE_SEP+"jsonFile"+ ServerInfo.FILE_SEP;
/*
	@RequestMapping(value = "/anon/queryGoodsList")
	@ResponseBody
	public String queryGoodsList(HttpServletRequest request) throws IOException {
		return IOUtils.readFileByLines(path+"queryGoodsList.json");
	}
	
	@RequestMapping(value = "/anon/queryGoodsDetail" , produces="text/plain;charset=utf-8")
	@ResponseBody
	public String queryGoodsDetail(HttpServletRequest request) throws IOException {
		return IOUtils.readFileByLines(path+"queryGoodsDetail.json");
	}*/
	
}

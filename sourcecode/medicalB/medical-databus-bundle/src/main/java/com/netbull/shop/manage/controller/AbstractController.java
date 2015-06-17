package com.netbull.shop.manage.controller;

import com.netbull.shop.common.config.ConfigLoadUtil;

/**
 * 定义抽象处理类,service注入、公共方法
 * @author guzx
 *
 */
public class AbstractController {

	private static final long serialVersionUID = 1L;
	public static final String TAB_String = "";
	public String SID = ConfigLoadUtil.loadConfig().getPropertie("SID");
	public String APPID = ConfigLoadUtil.loadConfig().getPropertie("APPID");
	public String TOKEN = ConfigLoadUtil.loadConfig().getPropertie("TOKEN");
	public String Rest_URL = ConfigLoadUtil.loadConfig().getPropertie("Rest_URL");
	
	
}

/*
 * 版权所有：安徽米品信息科技有限公司
 * 系统名称：微掌通
 * 系统编号：COP 2.0
 * 模块名称：
 * 文件名称：SystemConfig.java
 */
package com.netbull.shop.common.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.netbull.shop.common.activeload.PropertiesEx;
import com.netbull.shop.common.util.StringUtil;

public class ConfigLoadUtil {

	private static final Logger logger = Logger.getLogger("mboss_logger");
	
	private static ConfigLoadUtil instance = new ConfigLoadUtil();
	
	private PropertiesEx p = new PropertiesEx();

	public static ConfigLoadUtil loadConfig() {
		if (instance == null) {
			instance = new ConfigLoadUtil();
		}
		return instance;
	}

	private ConfigLoadUtil() {

	}

	/**
	 * 加载service-resources.properties
	 * 
	 * @param fileName
	 */
	public void load(String configPath,String fileName) {
		try {

			if (logger.isDebugEnabled()) {
				logger.debug("加载资源文件的路径: " + configPath);
			}
			File f = new File(configPath + fileName);
			try {
				p.load(f);
			} catch (FileNotFoundException e1) {
				logger.error("不能发现配置文件错误!", e1);
			} catch (IOException e1) {
				logger.error("读取配置文件错误!", e1);
			}

		} catch (Throwable e) {
			logger.error("读取配置文件错误!", e);
		}
	}
	
	/**
	 * 获取属性文件中的值；
	 */
	public String getPropertie(String key){
		return p.getProperty(key);
	}
	
	/**
	 * 获取属性文件中的值；
	 */
	public String getPropertie(String key,String defaultVal){
		if(!StringUtil.isEmpty(p.getProperty(key))){
			return defaultVal;
		}
		return p.getProperty(key);
	}
	
}

/*
 * 版权所有：深圳天源迪科信息技术股份有限公司
 * 系统名称：中国电信网上营业厅
 * 系统编号：COP 2.0
 * 模块名称：
 * 创建时间：Nov 22, 2010-11:09:08 AM 
 */
package com.netbull.shop.common.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.log4j.Logger;

import com.netbull.shop.common.activeload.Log4jPropertiesEx;

public class Log4jConfigLoader {

	private static final Logger logger = Logger.getLogger("microLog");
	
	private static Log4jConfigLoader instance = new Log4jConfigLoader();
	
	private static File f = null;

	public static Log4jConfigLoader getLog4jConfigLoader() {
		if (instance == null) {
			instance = new Log4jConfigLoader();
		}
		return instance;
	}

	private Log4jConfigLoader() {

	}

	/**
	 * 加载cache.properties
	 * 
	 * @param fileName
	 */
	public void load(String configPath,String fileName) {
		try {

			System.out.println("加载log4j属性文件的路径: " + configPath);
			if (logger.isDebugEnabled()) {
				logger.debug("加载log4j属性文件的路径: " + configPath);
			}
			
			Log4jPropertiesEx p = new Log4jPropertiesEx();
			File f = new File(configPath + fileName);
			try {
				p.load(f);
			} catch (FileNotFoundException e1) {
				System.out.println(e1.getMessage());
				logger.error("不能发现配置文件错误!", e1);
			} catch (IOException e1) {
				System.out.println(e1.getMessage());
				logger.error("读取配置文件错误!", e1);
			}

		} catch (Throwable e) {
			System.out.println(e.getMessage());
			logger.error("读取配置文件错误!", e);
		}

	}
}

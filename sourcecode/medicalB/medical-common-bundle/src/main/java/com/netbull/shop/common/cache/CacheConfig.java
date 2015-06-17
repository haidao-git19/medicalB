/*
 * 版权所有：深圳天源迪科信息技术股份有限公司
 * 系统名称：中国电信网上营业厅
 * 系统编号：COP 2.0
 * 模块名称：
 * 文件名称：SystemConfig.java
 * 
 * 创建时间：Nov 22, 2010-11:09:08 AM 
 */
package com.netbull.shop.common.cache;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.netbull.shop.common.activeload.CachePropertiesEx;

import com.netbull.shop.common.log.JLogger;
import com.netbull.shop.common.log.LoggerFactory;


public class CacheConfig {

	private static JLogger logger = LoggerFactory.getLogger("biz.wss.cache");
	
	private static CacheConfig instance = new CacheConfig();

	public static CacheConfig getCacheConfig() {
		if (instance == null) {
			instance = new CacheConfig();
		}
		return instance;
	}

	private CacheConfig() {

	}

	/**
	 * 加载cache.properties
	 * 
	 * @param fileName
	 */
	public void load(String fileName,String configPath) {
		try {

			if (logger.isDebugEnabled()) {
				logger.debug("加载缓存文件的路径: " + configPath);
			}
			
			CachePropertiesEx p = new CachePropertiesEx();
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
}

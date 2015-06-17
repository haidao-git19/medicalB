package com.netbull.shop.common.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * <p>
 * 标题: —— 要求能简洁地表达出类的功能和职责.
 * </p>
 * <p>
 * 描述: —— 简要描述类的职责、实现方式、使用注意事项等
 * </p>
 * <p>
 * 模块: 通用平台
 * </p>
 * 
 * @author guzx
 * @version 1.0
 * @since 2012-5-29 下午11:09:36
 *        <p>
 *        修订历史:（历次修订内容、修订人、修订时间等）
 *        </p>
 */
public class PropertiesUtil {
	/** 日志. */
	private static final Logger logger = Logger.getLogger("microLog");
	/**
	 * 定义属性对象.
	 */
	private static Properties properties;
	/**
	 * 文件名.
	 */
	public static String fileName;

	/*
	 * 构造方法
	 */
	public PropertiesUtil() {
		this("system.properties");
	}

	/**
	 * 构造方法，获取配置文件流.
	 * 
	 * @param fileNameTemp
	 *            文件名称
	 */
	public PropertiesUtil(String fileNameTemp) {
		fileName = "/" + fileNameTemp;
		try {
			PropertiesUtil.setProperties(new Properties());
			properties.load(getClass().getResourceAsStream(fileName));

		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * <p>
	 * 方法说明.
	 * </p>
	 * <p>
	 * 创建时间:2011-8-10下午02:37:45
	 * </p>
	 * <p>
	 * </p>
	 * 
	 * @param fileNameTemp
	 *            void
	 */
	public static void setFilename(String fileNameTemp) {
		fileName = fileNameTemp;
		FileInputStream fis = null;
		try {
			PropertiesUtil.setProperties(new Properties());
			fis = new FileInputStream(getAppClassPath() + fileName);
			properties.load(fis);
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}

	}

	/**
	 * 
	 * @method :getAppPath
	 * @description: 获取系统CLASSPATH物理路径
	 * @return String 返回CLASSPATH物理路径
	 * @createTime:2012-5-11下午03:23:00
	 */
	public static String getAppClassPath() {
		URL url = PropertiesUtil.class.getClassLoader().getResource("/");
		System.out.println(url.getPath());
		return url.getPath();
	}

	/**
	 * 获取某个属性.
	 * 
	 * @param key
	 *            配置文件中的key值
	 * @return string 返回配置文件key对应的值
	 */
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	/**
	 * 获取所有属性，返回一个map,不常用 可以试试props.putAll(t).
	 * 
	 * @return map 返回配置文件中数据
	 */
	public static Map<String, String> getAllProperty() {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<?> enu = properties.propertyNames();
		while (enu.hasMoreElements()) {
			String key = (String) enu.nextElement();
			String value = properties.getProperty(key);
			map.put(key, value);
		}
		return map;
	}

	/**
	 * 在控制台上打印出所有属性，调试时用。.
	 */
	public static void printProperties() {
		properties.list(System.out);
	}

	/**
	 * 
	 * @method :writeProperties
	 * @description: 方法说明
	 * @param key
	 * @param value
	 * @createTime:2012-5-11下午03:30:08
	 */
	public static void writeProperties(String key, String value) {
		OutputStream fos = null;
		try {
			fos = new FileOutputStream(fileName);
			properties.setProperty(key, value);
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			properties.store(fos, "『comments』Update key：" + key);

		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
	}

	public static Properties getProperties() {
		return properties;
	}

	public static void setProperties(Properties properties) {
		PropertiesUtil.properties = properties;
	}

	public static String getFileName() {
		return fileName;
	}

	public static void setFileName(String fileName) {
		PropertiesUtil.fileName = fileName;
	}

}

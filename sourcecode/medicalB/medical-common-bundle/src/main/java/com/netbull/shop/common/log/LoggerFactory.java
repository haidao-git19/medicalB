package com.netbull.shop.common.log;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import com.netbull.shop.common.config.ServerInfo;


public class LoggerFactory {

	private static Map<String,JLogger> bizLoggerMap = new HashMap<String,JLogger>(5);

	/**
	 * 获取日志接口
	 * @param className
	 * @return
	 */
	public static JLogger getLogger(String className) {
		JLogger runLog = null;
		if (bizLoggerMap.containsKey(className)) {
			return (JLogger) bizLoggerMap.get(className);
		}
		
		if (className.startsWith(LogConstant.BIZ_LOG_TYPE)) {
			runLog = new BizLogger(className);
		} else {
			 runLog = new JLogger(className);
		}
		bizLoggerMap.put(className, runLog);
		return runLog;
	}

	/**
	 * �?��对象日志接口
	 * @param className
	 * @return
	 */
	public static Logger getLogger(Class<?> className)
	{
		Logger runLog = Logger.getLogger(className);
		return runLog;
	}

	/**
	 * 加载log4j的配置文�?
	 * @param logConfigFile 配置文件的路�?
	 * @param refresh 配置文件刷新的时间，以秒为单�?
	 */
	public static void config() {
		String logConfigFile = ServerInfo.getLogFilePath();
		File file = new File(logConfigFile);
		if (!file.exists()) {
			System.err.println("logConfigFile="+logConfigFile);
			System.err.println("[Warnning]Log4j Config File Set Not Exist!");
			return;
		}
		String refresh = ServerInfo.getLogRefresh();
		System.err.println("[Message]Log Refresh Time(s):" + refresh);
		if ((refresh == null) || refresh.trim().length() == 0) { // no
			if (logConfigFile.endsWith(".properties")) {
				PropertyConfigurator.configure(logConfigFile);
			} else if (logConfigFile.endsWith(".xml")) {
				DOMConfigurator.configure(logConfigFile);
			}
		} else { 
			//�?��对日志的配置信息进行刷新，默认为3分钟刷新�?��
			long period = 180;
			try {
				period = Long.parseLong(refresh);
			} catch (Exception e) {
				System.err.println("[Warnning]Refresh is invalid type:"
						+ refresh);
			}
			if (logConfigFile.endsWith(".properties")) {
				PropertyConfigurator.configureAndWatch(logConfigFile,
						period * 1000);
			} else if (logConfigFile.endsWith(".xml")) {
				DOMConfigurator.configureAndWatch(logConfigFile, period * 1000);
			}
		}
	}
	
	/**
	 * 加载log4j的配置文�?
	 * @param logConfigFile
	 */
	public static void config(String logConfigFile) {
		config();
	}

}

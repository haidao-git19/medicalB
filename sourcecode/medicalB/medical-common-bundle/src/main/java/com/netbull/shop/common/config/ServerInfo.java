package com.netbull.shop.common.config;

import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.netbull.shop.common.log.LoggerFactory;


public class ServerInfo {

	private static Logger logger = LoggerFactory.getLogger(ServerInfo.class);
	public final static String FILE_SEP = System.getProperty("file.separator");
	public final static String NEXT_LINE = System.getProperty("line.separator");
	
	private static String serverName = null;
	private static String hostName = null;
	private static String localIP = null;
	private static final String WEBLOGIC_SERVERS = "servers";
	private static String systemConfigFile = null;
	private static String dbConnJdniName = "jdbc/mboss";
	
	private static String logFilePath = null;
	private static String logRefresh = null;
	
	//日志文件名（log4j.xml）；
    private final static String logFileName = "log4j.xml";
	// 系统名称，如COP
	private static String systemName = null;
	// 系统根路�?
	private static String systemRootPath = null;
	
	private static String systemWorkPath = null;
	
	//Lucene生成索引文件的路�?
	private static String systemIndexPath = null;

	/**
	 * 返回当前WebLogic Server的servers路径
	 * 
	 * @return 路径�?
	 */
	public static String getServersPath() {
		String path = null;
		java.io.File file = new java.io.File(".");
		try {
			path = file.getCanonicalPath() + FILE_SEP + WEBLOGIC_SERVERS;
		} catch (IOException ex) {
			logger.error("Get Server Path error:" + ex.toString());
		}
		return path;
	}

	/**
	 * 获取系统的服务路�? servers + [servername]
	 * @return
	 */
	public static String getSystemRootPath() {
		if (systemRootPath == null) {
			String serverName = getServerName();
			if(serverName==null || "".equals(serverName)){
				systemRootPath = getServersPath();
			}else{
				systemRootPath = getServersPath() + FILE_SEP
				+ getServerName();
			}
			
		}
		return systemRootPath;
	}
	
	/**
	 * 获取系统的防止配置信息和日志信息的基�?���?
	 * @return
	 */
	public static String getSystemWorkPath(){
		if(systemWorkPath==null){
			systemWorkPath = getSystemRootPath() + FILE_SEP + getSystemName();
		}
		return systemWorkPath;
	}
	
	public static String getServerName() {
		try {
			serverName = System.getProperty("weblogic.Name");
			
			if(serverName == null || "".equals(serverName)){
				serverName = "";
			}
		} catch (Exception ex) {
			logger.error("get server name error: " + ex.toString());
		}
		return serverName;
	}

	/**
	 * 获取机器名称
	 * 
	 * @return 机器�?
	 */
	public static String getHostName() {
		try {
			hostName = java.net.InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException ex) {
			logger.error("get host name error: " + ex.toString());
		}
		return hostName;
	}

	

	/**
	 * 获取机器IP地址
	 * 
	 * @return IP地址
	 */
	public static String getLoaclIP() {
		try {
			localIP = java.net.InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ex) {
			logger.error("get local IP error: " + ex.toString());
		}
		return localIP;
	}

	public static String getSystemName() {
		return systemName;
	}

	public static void setSystemName(String systemName) {
		ServerInfo.systemName = systemName;
	}

	public static String getLogFilePath() {
		return logFilePath;
	}

	public static void setLogFilePath(String logFilePath) {
		ServerInfo.logFilePath = logFilePath;
	}

	public static String getLogRefresh() {
		return logRefresh;
	}

	public static void setLogRefresh(String logRefresh) {
		ServerInfo.logRefresh = logRefresh;
	}

	public static String getSystemConfigFile() {
		return systemConfigFile;
	}

	public static void setSystemConfigFile(String systemConfigFile) {
		ServerInfo.systemConfigFile = systemConfigFile;
	}

	public static String getDbConnJdniName() {
		return dbConnJdniName;
	}

	public static void setDbConnJdniName(String dbConnJdniName) {
		ServerInfo.dbConnJdniName = dbConnJdniName;
	}

}

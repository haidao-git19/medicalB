package com.netbull.shop.common.config;

public class SysInfo {
	
	//系统文件名称�?
	String sysFileName ;
	//日志文件名（log4j.xml）；
	String logFileName ;
	//日志刷新�?
	String logRefresh;
	//配置文件(SystemConfig.xml)�?
	String configFile;
	//jndi名称�?
	String jndiName;
	
	public String getJndiName() {
		return jndiName;
	}
	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}
	public String getConfigFile() {
		return configFile;
	}
	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}
	public String getLogFileName() {
		return logFileName;
	}
	public void setLogFileName(String logFileName) {
		this.logFileName = logFileName;
	}
	public String getLogRefresh() {
		return logRefresh;
	}
	public void setLogRefresh(String logRefresh) {
		this.logRefresh = logRefresh;
	}
	public String getSysFileName() {
		return sysFileName;
	}
	public void setSysFileName(String sysFileName) {
		this.sysFileName = sysFileName;
	}

}



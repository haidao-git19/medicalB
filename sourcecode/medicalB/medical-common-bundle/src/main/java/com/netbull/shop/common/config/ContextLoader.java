package com.netbull.shop.common.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.netbull.shop.common.cache.CacheConfig;
import com.netbull.shop.common.datacache.DCManager;

/**
 * 工程基本信息加载
 * @author elvis
 *
 */
public class ContextLoader implements ServletContextListener{
	
	private static String DEFAULT_RESOURCE_CONFIG = "service-resources.properties";
	private static final String LOG4J = "log4j.properties";
	public static final String DEFAULT_CACHE_CONFIG = "cache.properties";
	
	private WebApplicationContext springContent = null;

	private ServletContext context = null;
	
	public void contextDestroyed(ServletContextEvent arg0) {

	}
	
	public void contextInitialized(ServletContextEvent event) {
		
		try {
			
			this.context = event.getServletContext();
			WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
			
			springContent = wac;
			SysInfo info = (SysInfo) wac.getBean("sysInfo");
			ServerInfo.setSystemName(info.getSysFileName());
			String configPath = ServerInfo.getSystemWorkPath() + ServerInfo.FILE_SEP + "config" + ServerInfo.FILE_SEP;

			//初始化sysinfo对象
			ServerInfo.setLogFilePath(configPath + info.getLogFileName());
			ServerInfo.setLogRefresh(info.getLogRefresh());
			ServerInfo.setSystemConfigFile(configPath + info.getConfigFile());
			ServerInfo.setDbConnJdniName(info.getJndiName());
			
			//设置工程系统路径；
			System.setProperty ("WORKDIR", ServerInfo.getSystemWorkPath());

			/**** 加载log4j属性文件 ******/
			System.out.println("加载log4j属性文件 ============"+LOG4J);
			Log4jConfigLoader.getLog4jConfigLoader().load(configPath,LOG4J);
			
			/**** 加载系统配置文件 ******/
			System.out.println("加载系统配置文件 ============"+DEFAULT_RESOURCE_CONFIG);
			ConfigLoadUtil.loadConfig().load(configPath,DEFAULT_RESOURCE_CONFIG);
			
			/**** 加载缓存文件 ******/
			System.out.println("加载缓存文件 ============"+DEFAULT_CACHE_CONFIG);
			CacheConfig.getCacheConfig().load(DEFAULT_CACHE_CONFIG,configPath);
			
			// 加载DC
			DCManager.getInstance();
		} catch (Exception e) {
			System.out.println("初始系统配置信息出错了，错误原因:"+e.getMessage());
		}
	}

	public WebApplicationContext getSpringContent() {
		return springContent;
	}
}

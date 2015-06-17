package com.netbull.shop.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil implements ApplicationContextAware{

	private static ApplicationContext wac;

	public static ApplicationContext getWac() {
		return wac;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextUtil.wac = applicationContext;
	}
	
	public static Object getBean(String name){
		return wac.getBean(name);
	}
}

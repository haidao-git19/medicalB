package com.netbull.shop.shiro;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.util.StringUtils;

import com.netbull.shop.auth.service.AuthRemoteService;

public class SSOShiroFilterFactoryBean extends ShiroFilterFactoryBean {

	private static final Log logger = LogFactory.getLog(SSOShiroFilterFactoryBean.class);

	@Resource
	private AuthRemoteService authRemoteService;

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		
		if (StringUtils.isEmpty(authRemoteService.getAuthUrl())) {
			String loginUrl = getLoginUrl();
			Pattern pattern = Pattern.compile("^(.*?)\\?.*?service=(.*?)(&|$)");
			Matcher matcher = pattern.matcher(loginUrl);
			if (matcher.find()) {
				String authUrl = matcher.group(1);
				String serviceUrl = matcher.group(2);
				authRemoteService.setAuthUrl(authUrl);
				authRemoteService.setServiceUrl(serviceUrl);
				
				logger.info("sso auth url : " + authUrl);
				logger.info("sso service url : " + serviceUrl);
			}
		}
		return super.postProcessBeforeInitialization(bean, beanName);
	}

}

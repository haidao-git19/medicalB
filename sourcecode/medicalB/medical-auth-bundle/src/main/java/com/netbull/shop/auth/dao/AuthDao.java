package com.netbull.shop.auth.dao;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.netbull.shop.dao.HDao;
import com.netbull.shop.dao.SessionFactoryBuilder;
import com.netbull.shop.util.PropertiesUtils;

@Repository("authDao")
public class AuthDao extends HDao{
	
	@PostConstruct
	public void initSessionFactory(){
		SessionFactoryBuilder sfb = new SessionFactoryBuilder();
		sfb.setUrl(PropertiesUtils.getProperty("hibernate.connection.url"));
		sfb.setUsername(PropertiesUtils.getProperty("hibernate.connection.username"));
		sfb.setPassword(PropertiesUtils.getProperty("hibernate.connection.password"));
		sfb.setHbm2ddl("update");
		setSessionFactory(sfb.buildSessionFactory());
	}
}

package com.netbull.shop.dao;

import javax.persistence.Entity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class SessionFactoryBuilder {

	private static final Log logger = LogFactory.getLog(SessionFactoryBuilder.class);

	private String url;
	private String username;
	private String password;
	private String driverClass = "com.mysql.jdbc.Driver";
	private String dialect = "org.hibernate.dialect.MySQLDialect";
	private String hbm2ddl = "none";
	private String[] packagesToScan = new String[] { "com.netbull.shop" };

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getDialect() {
		return dialect;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	public String getHbm2ddl() {
		return hbm2ddl;
	}

	public void setHbm2ddl(String hbm2ddl) {
		this.hbm2ddl = hbm2ddl;
	}

	public String[] getPackagesToScan() {
		return packagesToScan;
	}

	public void setPackagesToScan(String[] packagesToScan) {
		this.packagesToScan = packagesToScan;
	}

	public SessionFactory buildSessionFactory() {
		Configuration cfg = new Configuration();

		cfg.setProperty("hibernate.connection.driver_class", driverClass);
		cfg.setProperty("hibernate.connection.url", url);
		cfg.setProperty("hibernate.connection.username", username);
		cfg.setProperty("hibernate.connection.password", password);
		cfg.setProperty("hibernate.connection.release_mode", "on_close");
		cfg.setProperty("hibernate.dialect", dialect);
		cfg.setProperty("hibernate.hbm2ddl.auto", hbm2ddl);
		cfg.setProperty("hibernate.c3p0.min_size", "5");
		cfg.setProperty("hibernate.c3p0.max_size", "20");
		cfg.setProperty("hibernate.c3p0.timeout", "1800");
		cfg.setProperty("hibernate.c3p0.max_statements", "50");

		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
		for (String packageToScan : packagesToScan) {
			for (BeanDefinition bd : scanner.findCandidateComponents(packageToScan)) {
				String name = bd.getBeanClassName();
				try {
					Class<?> clazz = Class.forName(name);
					cfg.addAnnotatedClass(clazz);
				} catch (Exception e) {
					logger.error(e, e);
				}
			}
		}
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
		return cfg.buildSessionFactory(reg);
	}
}
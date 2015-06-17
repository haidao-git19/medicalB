package com.netbull.shop.common.dbutil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import com.netbull.shop.common.config.ServerInfo;
import com.netbull.shop.common.log.LoggerFactory;


public class ServiceLocator {
	private static Logger logger = LoggerFactory.getLogger(ServiceLocator.class);
	
	private static ServiceLocator serviceLocatorRef = null;
	@SuppressWarnings("unchecked")
	private static Map dataSourceCache = new HashMap();
	private static final String serviceName = "MySql";

//	static {
//		serviceLocatorRef = new ServiceLocator();
//	}

//	@SuppressWarnings("unchecked")
	private  ServiceLocator() {
		dataSourceCache = new HashMap();
	}

	public static ServiceLocator getInstance() {
		if(serviceLocatorRef ==  null){
			serviceLocatorRef = new ServiceLocator();
		}
		return serviceLocatorRef;
	}

	static private String getServiceName() {
		return serviceName;
	}

	/**
	 * 从JDNI定义的数据源中获取数据库连接
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Connection getDBConn() throws SQLException {
		/* Getting the JNDI Service Name */
		Connection conn = null;
		String serviceName = getServiceName();
		logger.debug("获取服务名称为： "+serviceName);
		try {
			/* Checking to see if the requested DataSource is in the Cache */
			if (serviceName != null && dataSourceCache.containsKey(serviceName)) {
				DataSource ds = (DataSource) dataSourceCache.get(serviceName);
				
				logger.debug("获取数据源对象为： " + ds);
				
				conn = ((DataSource) ds).getConnection();
				return conn;
			} else {
				/*
				 * The DataSource was not in the cache. Retrieve it from JNDI
				 * and put it in the cache.
				 */
				DataSource newDataSource = DataSourceFactory
						.getDataSource(ServerInfo.getDbConnJdniName());
				dataSourceCache.put(serviceName, newDataSource);
				conn = newDataSource.getConnection();
				return conn;
			}
		} catch (SQLException e) {
			logger.error("ServiceLocator.getDBConn Error!", e);
			throw new SQLException("GetDBConn Error!");
		}
	}
}

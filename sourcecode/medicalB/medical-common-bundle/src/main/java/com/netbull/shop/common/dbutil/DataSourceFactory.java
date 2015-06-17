package com.netbull.shop.common.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import com.netbull.shop.common.log.LoggerFactory;

public class DataSourceFactory {
	private static Logger logger = LoggerFactory
			.getLogger(DataSourceFactory.class);

	/**
	 * 通过配置文件设置来得到Context
	 * 
	 * @return Context
	 * @throws DataAccessException
	 */
	public static Context getDefaultContext() {
		InitialContext ctx = null;
		try {
			ctx = new InitialContext();
		} catch (NamingException e) {
			logger.error("DataSourceFactory getDefaultContext Error!", e);
		}
		return ctx;
	}

	/**
	 * 按照JNDI取连�?
	 * 
	 * @param pJndi
	 * @return DataSource
	 * @throws DataAccessException
	 */
	public static DataSource getDataSource(String pJndi) {
		return getDataSource(pJndi, getDefaultContext());
	}

	/**
	 * 按照JNDI和Context得到连接
	 * 
	 * @param pJndi
	 * @param pCtx
	 * @return DataSource
	 * @throws DataAccessException
	 */
	private static DataSource getDataSource(String pJndi, Context pCtx) {
		DataSource ds = null;
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("DataSource JNDIName is: " + pJndi);
			}
			ds = (DataSource) pCtx.lookup(pJndi);
		} catch (NamingException e) {
			logger.error("Get DataSource Error! jndi=" + pJndi, e);
		}
		return ds;
	}

	/**
	 * 得到jdbc连接
	 * 
	 * @param jdbcDriver
	 * @param jdbcUrl
	 * @param jdbcUsername
	 * @param jdbcPassword
	 * @return 数据库连�?
	 */
	public static Connection getConnection(String jdbcDriver, String jdbcUrl,
			String jdbcUsername, String jdbcPassword) {
		Connection conn = null;
		try {
			java.sql.Driver driver = (java.sql.Driver) Class
					.forName(jdbcDriver).newInstance();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(jdbcUrl, jdbcUsername,
					jdbcPassword);
		} catch (Exception e) {
			logger.error("Get Database Connection Error! jdbcUrl=" + jdbcUrl
					+ " user= " + jdbcUsername + " passwd= " + jdbcPassword, e);
		}
		return conn;
	}

}

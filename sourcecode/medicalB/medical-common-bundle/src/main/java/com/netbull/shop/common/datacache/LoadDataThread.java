package com.netbull.shop.common.datacache;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.netbull.shop.common.dbutil.ClearDBResource;
import com.netbull.shop.common.dbutil.ServiceLocator;
import com.netbull.shop.common.log.LoggerFactory;


/**
 * 系统从数据库加载数据
 * @author yeah
 *
 */
public class LoadDataThread extends Thread {

	private static Logger logger = LoggerFactory
			.getLogger(LoadDataThread.class);

	private Connection oracleConn = null;

	private Table dcTable = null;

	protected LoadDataThread(Connection oracleConn, Table dcTable) {
		this.oracleConn = oracleConn;
		this.dcTable = dcTable;
		
		this.setName("LoadDataThread_" + dcTable.name);
	}

	public void run() {
		boolean loadSuccess = false;
		if (logger.isDebugEnabled()) {
			logger.debug("Table " + dcTable.name + " load from db start ... ");
		}
		loadSuccess = loadFromDB();

		if (logger.isDebugEnabled()) {
			logger.debug("Table " + dcTable.name + " load from db success:"
					+ loadSuccess);
			logger.debug("Table " + dcTable.name
					+ " notify DCManager begin.....");
		}
		DCManager.reduceThreadCount(dcTable.name);

		if (logger.isDebugEnabled()) {
			logger.debug("Table " + dcTable.name + " notify DCManager end.");
		}
		try {
			sleep(3 * 60 * 1000);
		} catch (InterruptedException ie) {
			logger.error("error while sleep befor sync data, table name= "
					+ dcTable.name, ie);
		}
	}

	/**
	 * 从数据库加载数据
	 * 
	 */
	private boolean loadFromDB() {
		boolean result = false;
		Statement oracleStmt = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Begin to Load Data From DB, Table: " + dcTable.name);
		}
		try {
			// 此操作很耗时，放在线程里边来做。
			oracleConn = ServiceLocator.getInstance().getDBConn();
			oracleStmt = oracleConn.createStatement();
			HashDBTable hashDBTable = null;

			// 数据缓存加载时候通过该语句进行过滤
			String sqlWhereClause = "";
			if (dcTable.sqlWhereClause != null
					&& dcTable.sqlWhereClause.trim().length() > 0) {
				sqlWhereClause = " where " + dcTable.sqlWhereClause;
			}

			// 在HashDB中创建该表
			hashDBTable = HashDBManager.createTable(dcTable);

			String selectSQL = "select * from " + dcTable.name + sqlWhereClause;
			if (logger.isDebugEnabled()) {
				logger.debug("Data load selectSQL = " + selectSQL);
			}
			// 开始加载数据
			ResultSet rs = oracleStmt.executeQuery(selectSQL);
			int iCount = 0;
			while (rs.next()) { // 加载每一条记录
				try {
					HashDBManager.insertRow(rs, hashDBTable);
				} catch (SQLException e) {
					logger.error("loadFromDB SQLException:",e);
				}
				iCount++;
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Load Data from DB, Table " + dcTable.name
						+ " done, total " + iCount + " Records Loaded...");
			}
			result = true;
		} catch (SQLException ex) {
			System.out.println("!!!Load Table " + dcTable.name
					+ " From DB Init Error, Check Config and Error Log!!!");
			logger.error("Load data to datacache error!", ex);
		} finally {
			ClearDBResource.closeStatment(oracleStmt);
			ClearDBResource.closeConnection(oracleConn);
		}
		return result;
	}
}

package com.netbull.shop.common.datacache;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.netbull.shop.common.dbutil.ClearDBResource;
import com.netbull.shop.common.dbutil.ServiceLocator;
import com.netbull.shop.common.log.LoggerFactory;

/**
 * 数据更新监控线程
 * 
 * @author yeah
 * 
 */
public class RefreshThread implements Runnable {
	private static final Logger logger = Logger.getLogger("microLog");

	private static int iMaxSN = 0;

	private static final String DC_LOG_TABLE_NAME = "tb_medical_notify_log";

	/**
	 * 最大刷新数据量，如果需要更新的数据量超过此参数值，则直接重新载入所有数据。
	 */
	// private static int iMaxRefreshCount = 1000;
	/**
	 * 唯一实例
	 */
	private static RefreshThread dcRefreshThread = null;

	private int iCounter = 0;

	private int iDelTimes = 1000;

	private int iMsPerDay = 24 * 60 * 60 * 1000;

	private RefreshThread() { // 私有构造方法, 只容许自己NEW

	}

	/**
	 * 只允许运行一个线程，防止重复刷新造成数据不一致
	 */
	static synchronized void runSingleThread() {
		if (dcRefreshThread == null) {
			dcRefreshThread = new RefreshThread();
			new Thread(dcRefreshThread, "DCRefreshThread").start();
		}
	}

	/**
	 * 主运行入口，调用其他方法完成数据刷新
	 */
	public void run() {
		while (DCManager.notDestroyed && DCManager.allowDCRefreshThreadRun) {
			doRefresh();

			// 执行完后休眠指定时间，等待下次执行
			try {
				Thread.sleep(DCManager.refreshTime);
			} catch (InterruptedException ex) {
				logger.error("DCRefreshThread Interrrupted!!", ex);
			}
		}
	}

	/**
	 * 获取数据更新记录表中当前最大序列号
	 * 
	 * @param oracleConn
	 */
	static void setMaxSN(Connection oracleConn) {
		Statement stmt = null;
		String strSQL = "select MAX(SN) from " + DC_LOG_TABLE_NAME;
		try {
			stmt = oracleConn.createStatement();
			ResultSet rs = stmt.executeQuery(strSQL);
			if (rs.next()) {
				iMaxSN = rs.getInt(1);
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Get Max SN from " + DC_LOG_TABLE_NAME + ":"
						+ iMaxSN);
			}
		} catch (Exception ex) {
			logger.error("Read MAX(SN) Error!!", ex);
		}
	}

	static int getMaxSN() {
		return iMaxSN;
	}

	/**
	 * 获取自上次更新以来的所有更新记录
	 * 
	 * @param stmt
	 * @return
	 */
	@SuppressWarnings("unchecked")
	List getModifyList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String strSQL = "select * from " + DC_LOG_TABLE_NAME
				+ " where SN > ? order by SN";
		if (logger.isDebugEnabled()) {
			logger.debug("Begin getModifyList By Script " + strSQL);
			logger.debug("iMaxSN=" + iMaxSN);
		}
		try {
			pstmt = conn.prepareStatement(strSQL);
			pstmt.setInt(1, iMaxSN);
			rs = pstmt.executeQuery();
			List<DataNotifyLog> arrList = new ArrayList<DataNotifyLog>();
			while (rs.next()) {
				DataNotifyLog aLog = new DataNotifyLog();

				aLog.SN = rs.getInt("SN");
				aLog.CMD_TYPE = rs.getString("CMD_TYPE");
				if (!"INS".equalsIgnoreCase(aLog.CMD_TYPE)
						&& !"DEL".equalsIgnoreCase(aLog.CMD_TYPE)
						&& !"UPD".equalsIgnoreCase(aLog.CMD_TYPE)) {
					continue;
				}
				aLog.TABLE_NAME = rs.getString("TABLE_NAME").toUpperCase();
				aLog.PK_VALUES = rs.getString("PK_VALUES");
				aLog.WHERE_CONDITION = rs.getString("WHERE_CONDITION");
				arrList.add(aLog);
			}

			iCounter++;
			// 执行指定次数后删除数据库的过期数据
			if (iCounter % iDelTimes == 0) {
				strSQL = "delete from " + DC_LOG_TABLE_NAME
						+ " where (notify_time < (sysdate - ? / ?))";
				if (logger.isDebugEnabled()) {
					logger.debug("Run Conter is " + iCounter
							+ " Begin Exec Del Script: " + strSQL);
				}
				pstmt = conn.prepareStatement(strSQL);
				pstmt.setInt(1, DCManager.refreshTime * iDelTimes);
				pstmt.setInt(2, iMsPerDay);
				int i = pstmt.executeUpdate();
				if (logger.isDebugEnabled()) {
					logger.debug("Exec Del Script Over, Total " + i
							+ " Record be Del...");
				}
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Need Refresh Data Number :" + arrList.size());
			}

			return arrList;
		} catch (Exception ex) {
			logger.error("From DB getModify Log List!", ex);
			return null;
		} finally {
			ClearDBResource.closeResultSet(rs);
			ClearDBResource.closeStatment(pstmt);
		}
	}

	/**
	 * 数据刷新主方法
	 */
	@SuppressWarnings("unchecked")
	public void doRefresh() {
		Connection conn = null;
		Statement stmt = null;
		String strSQL = null;
		try {
			conn = ServiceLocator.getInstance().getDBConn();
			stmt = conn.createStatement();

			List arrList = getModifyList(conn);
			int iSize = arrList.size();
			if (iSize < 1) {
				if (logger.isDebugEnabled()) {
					logger.debug("No Altered Record, I Will Run Next Time...");
				}
				return;
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Total Altered Records : " + iSize);
			}

			DataNotifyLog[] arrAlterLogs = new DataNotifyLog[iSize];
			arrList.toArray(arrAlterLogs);

			HashSet hsTableNames = new HashSet();

			for (int i = 0; i < iSize; i++) {
				try {
					DataNotifyLog aLog = arrAlterLogs[i];
					iMaxSN = aLog.SN;

					String tableName = aLog.TABLE_NAME;
					hsTableNames.add(tableName);

					Table dcTable = DCManager.getDCTable(tableName);
					if (dcTable == null) {
						continue;
					}

					if ("INS".equalsIgnoreCase(aLog.CMD_TYPE)
							|| "UPD".equalsIgnoreCase(aLog.CMD_TYPE)) {
						// 产生具体的过滤条件子句
						if (dcTable.sqlWhereClause != null
								&& dcTable.sqlWhereClause.trim().length() > 0) {
							strSQL = "select * from " + tableName + " where "
									+ aLog.WHERE_CONDITION + " and "
									+ dcTable.sqlWhereClause;
						} else {
							strSQL = "select * from " + tableName + " where "
									+ aLog.WHERE_CONDITION;
						}
						if (logger.isDebugEnabled()) {
							logger.debug("Altered Record NO." + i + " : "
									+ strSQL);
						}

						ResultSet rs = stmt.executeQuery(strSQL);
						if (rs.next()) {
							HashDBManager.insertRow(rs, dcTable);
						}
					} else if ("DEL".equalsIgnoreCase(aLog.CMD_TYPE)) {
						HashDBManager
								.deleteRow(aLog.TABLE_NAME, aLog.PK_VALUES);
					} else {
						logger.error("Wrong CMD_TYPE of " + aLog.CMD_TYPE);
					}
				} catch (Exception e) {
					logger.error("Data Cahce Refresh Error!", e);
				} // end of try
			} // end of for
			if (logger.isDebugEnabled()) {
				logger
						.debug("Notify all DCDataRefreshListener with Table Names: "
								+ hsTableNames);
			}
			DCManager.notifyAllDCDataRefreshListener(hsTableNames);
		} catch (Exception ex) {
			logger.error("Data Cache Fresh Error!", ex);
		} finally {
			ClearDBResource.closeStatment(stmt);
			ClearDBResource.closeConnection(conn);
		}
	}
}

package com.netbull.shop.common.datacache;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.netbull.shop.common.config.ConfigDataListener;
import com.netbull.shop.common.dbutil.ClearDBResource;
import com.netbull.shop.common.dbutil.ServiceLocator;
import com.netbull.shop.common.log.LoggerFactory;

public class DCManager implements ConfigDataListener {

	private static DCManager instance = null;

	static final Logger logger = Logger.getLogger("microLog");

	// 定义表数据过滤时候使用的配置项名称扩展
	private static final String SQL_WHERE_CLAUSE = "_Load_Condition";

	static final String DEFALUT_INDEX_NAME = "default";

	public String[] arrStrTableNames = null;

	String[] arrSchema = { "TBS_BILL_DATA01" };

	// 数据加载时候的条件字句
	public String[] arrSqlWhereClause = null;

	String[][] arrIndexValues = {};

	String[][] arrIndexNames = {};

	private static Map<String, Table> allDCTables = new HashMap<String, Table>();

	public static boolean allowDCRefreshThreadRun = true;

	public static boolean notDestroyed = true;

	public static boolean needDCRefresh = true;

	/**
	 * 检查DC_NOTIFY_LOG的周期
	 */
	static int refreshTime = 60000;

	static int loadDataThreadCount = 0;

	@SuppressWarnings("unchecked")
	static HashSet hsDCDataRefreshListeners = new HashSet();

	static DataRefreshListener[] arrListener = null;

	private static Object lockObj = new Object();

	public synchronized static DCManager getInstance() {
		if (instance == null) {
			instance = new DCManager();
		}
		return instance;
	}

	/**
	 * 默认构造方法,初始化DC模块
	 */
	private DCManager() {
		init();
	}

	private static boolean isInit = false;

	/**
	 * 回调方法，Undeploy该应用时由应用服务器负责调用 关闭数据库，设置标志位，让数据库刷新线程停止运行
	 */
	public void destroy() {
		DCManager.notDestroyed = false;
		DCManager.allowDCRefreshThreadRun = false;
	}


	/**
	 * 静态初始化类, 启动初始化线程完成数据缓存的初始化和数据加载
	 * 
	 * @return 初始化成功返回true, 失败返回false
	 */
	private void init() {
		if (logger.isDebugEnabled()) {
			logger.debug("DataCache Begin Init...");
		}
		// 启动数据更新线程
		RefreshThread.runSingleThread();
	}

	public static void reduceThreadCount(String tableName) {
		if(logger.isDebugEnabled()){
			logger.debug("DCManager: Table " + tableName + " load over.");
		}
		synchronized (lockObj) {
			loadDataThreadCount--;
			if (loadDataThreadCount == 0) {
				if(logger.isDebugEnabled()){
					logger.debug("DataCahce: All Table loaded over!");
				}
				System.out.println("DataCahce: All Table loaded over!");
				lockObj.notifyAll();
			}
		}
	}

	/**
	 * 专供本类获取数据库连接
	 * 
	 * @return
	 * @throws Exception
	 */
	public Connection getConn() throws SQLException {
		return ServiceLocator.getInstance().getDBConn();
	}

	static Table getDCTable(String name) {
		return (Table) allDCTables.get(name);
	}

	@SuppressWarnings("unchecked")
	static Iterator getDCTables() {
		return allDCTables.keySet().iterator();
	}

	/**
	 * 取指定数据表的字段列表和描述 <br/>要求连接数据库的用户必须具备select on all_indexes和select on
	 * all_ind_columns权限
	 * 
	 * @param dbmd
	 *            数据库元数据
	 * @param tableSchema
	 *            该表的Schema
	 * @param tableName
	 *            表名
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Column[] getArrCols(DatabaseMetaData dbmd, String tableSchema,
			String tableName) {
		try {
			ResultSet rs = dbmd.getColumns(null, tableSchema, tableName, null);
			List arrList = new ArrayList();
			if (!rs.next()) {
				System.out.println("!!!DateCache Init Error, table "
						+ tableName + " Check Config and Error Log!!!");
				logger.error("Failed to getColumns from table " + tableName);
				return null;
			}

			do {
				Column col = new Column();
				col.name = rs.getString("COLUMN_NAME");
				arrList.add(col);
			} while (rs.next());
			Column[] arrCols = new Column[arrList.size()];
			arrList.toArray(arrCols);
			return arrCols;
		} catch (Exception ex) {
			System.err.println("DateCache Init Error,Please Check!!!");
			logger.error("DateCache Init Error,Please Check!!!", ex);
			return null;
		}
	}

	public static void reInit() {
		if(logger.isDebugEnabled()){
			logger.debug("Config File Modified, Now ReInit DCManager...");
		}
		try {
			refreshTime = 60000;
			if(logger.isDebugEnabled()){
				logger.debug("Get iRefreshTime from CFG : " + refreshTime);
			}
		} catch (Exception ex) {
			logger.error("", ex);
			// 初始化读配置失败, 在启动日志中输出失败信息, 供部署人员检查
			LoggerFactory.getLogger("biz.startup").error(
					"DCManager fail to read config item: iRefreshTime", ex);
			return;
		}
		if(logger.isDebugEnabled()){
			logger.debug("ReInit DCManager Over...");
		}
	}

	public void doConfigRefresh() {
		reInit();
	}

	public String getListenerName() {
		return "DCManager";
	}

	@SuppressWarnings("unchecked")
	public static void addDCDataRefreshListener(DataRefreshListener listener) {
		hsDCDataRefreshListeners.add(listener);
		arrListener = new DataRefreshListener[hsDCDataRefreshListeners.size()];
		hsDCDataRefreshListeners.toArray(arrListener);
		
		if(logger.isDebugEnabled()){
			logger.debug("New DCDataRefreshListener added: " + listener.getClass());
			logger.debug(hsDCDataRefreshListeners);
		}
	}

	@SuppressWarnings("unchecked")
	public static void notifyAllDCDataRefreshListener(HashSet hsTableNames) {
		if (arrListener == null) {
			return;
		}
		for (int i = 0; i < arrListener.length; i++) {
			new DataRefreshNotifyThread().doNotify(arrListener[i],
					hsTableNames);
		}
	}

	public void reLoarder() {
		instance = new DCManager();
		
	}
}

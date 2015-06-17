package com.netbull.shop.common.datacache;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.log4j.Logger;

import com.netbull.shop.common.dbutil.ClearDBResource;
import com.netbull.shop.common.dbutil.ServiceLocator;
import com.netbull.shop.common.log.LoggerFactory;
import com.netbull.shop.common.vo.SystemParam;


/**
 * 缓存系统表中数据；
 * @author elvis
 * @version v1.0
 * @throws 抛出NullPointerException异常
 * */
public class SysParamDC implements DataRefreshListener{
	//logger对象
	private Logger logger = LoggerFactory.getLogger(SysParamDC.class);
	
	private static Map<String,SystemParam> sysParamMap = new HashMap<String,SystemParam>();

	private static SysParamDC instance = null; 
	
	private SysParamDC() {
		DCManager.addDCDataRefreshListener(this);
	}
	
	/**
	 * 单例模式
	 * @return
	 */
	public static synchronized SysParamDC getInstance() {
		if (instance == null) {
			instance = new SysParamDC();
			instance.reloadFromDB();
		}
		return instance;
	}
	
	public void doDCDataRefresh(HashSet hsTableNames) {
		if (hsTableNames.contains("tb_micro_material")) {
			this.reloadFromDB();
		}		
	}
	
	/**
	 * 从数据库中读取数据
	 */
	public void reloadFromDB() {
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		try {
			conn = serviceLocator.getDBConn();
			//查询所有的规则tb_ems_sysParam表中的数据
			sta = conn.
					prepareStatement("select tablename,fieldname,fieldvalue,fielddesc from tb_micro_material");
			rs = sta.executeQuery();
			if (rs != null) {
				SystemParam systemParam = null;
				while (rs.next()) {
					
					systemParam = new SystemParam();
					
					systemParam.setTablename(rs.getString("tablename"));
					systemParam.setFieldname(rs.getString("fieldname"));
					systemParam.setFieldvalue(rs.getString("fieldvalue"));
					systemParam.setFielddesc(rs.getString("fielddesc"));
					
					sysParamMap.put(systemParam.getFieldname(), systemParam);
				}
			}

		} catch (Exception ex) {
			logger.error("EnterpriseParamDC reloadFromDB error:", ex);
		} finally {
			ClearDBResource.closeResultSet(rs);
			ClearDBResource.closeStatment(sta);
			ClearDBResource.closeConnection(conn);
		}
	}
	
	
	/**
	 * 获取参数hash列表
	 * @return
	 */
	public Map<String,SystemParam> getSysParamHashMap() {
		return sysParamMap;
	}
}

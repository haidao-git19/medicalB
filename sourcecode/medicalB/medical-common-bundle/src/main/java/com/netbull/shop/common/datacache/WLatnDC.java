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


/**
 * 缓存系统表中数据；
 * @author elvis
 * @version v1.0
 * @throws 抛出NullPointerException异常
 * */
public class WLatnDC implements DataRefreshListener{
	//logger对象
	private Logger logger = LoggerFactory.getLogger(WLatnDC.class);
	
	private static Map<String,String> wlatnMap = new HashMap<String,String>();

	private static WLatnDC instance = null; 
	
	private WLatnDC() {
		DCManager.addDCDataRefreshListener(this);
	}
	
	/**
	 * 单例模式
	 * @return
	 */
	public static synchronized WLatnDC getInstance() {
		if (instance == null) {
			instance = new WLatnDC();
			instance.reloadFromDB();
		}
		return instance;
	}
	
	public void doDCDataRefresh(HashSet hsTableNames) {
		if (hsTableNames.contains("TB_EMS_WAP_LATN")) {
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
					prepareStatement("SELECT T.LATN_ID,T.MOBILENUM_ID FROM TB_EMS_WAP_LATN T");
			rs = sta.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					wlatnMap.put(rs.getString("MOBILENUM_ID"), rs.getString("LATN_ID"));
				}
			}

		} catch (Exception ex) {
			logger.error("WLatnDC reloadFromDB error:", ex);
		} finally {
			ClearDBResource.closeResultSet(rs);
			ClearDBResource.closeStatment(sta);
			ClearDBResource.closeConnection(conn);
		}
	}
	
	
	/**
	 * 根据号码判断是否是C网号码；
	 * @return
	 */
	public boolean isCDMA(String phone) {
		
		if(wlatnMap.containsKey(phone)){
			return true;
		}else{
			return false;
		}
	}
}

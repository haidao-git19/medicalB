package com.netbull.shop.auth.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.netbull.shop.common.datacache.DCManager;
import com.netbull.shop.common.datacache.DataRefreshListener;
import com.netbull.shop.common.dbutil.ClearDBResource;
import com.netbull.shop.common.dbutil.ServiceLocator;
import com.netbull.shop.common.log.LoggerFactory;

public class GeneralModelDC implements DataRefreshListener{
	private static Logger logger = LoggerFactory.getLogger(GeneralModelDC.class);
	private static GeneralModelDC instance = null; // 实现Singleton模式的静态变�?
	// 定义内存中取本地网对
	private static Map<String, List<Map>> generalModelMap = new HashMap<String, List<Map>>();
	
	/**
	 * 构�?函数（增加到监听�?
	 */
	private GeneralModelDC() {
		DCManager.addDCDataRefreshListener(this);
	}
	
	/**
	 * 实例当前对象
	 * 
	 * @return
	 */
	public static synchronized GeneralModelDC getInstance() {
		if (instance == null) {
				instance = new GeneralModelDC();
				instance.reloadGeneralModelFromDB();
		}
		return instance;
	}
	
	/**
	 * 更新�?
	 */
	@SuppressWarnings("unchecked")
	public void doDCDataRefresh(HashSet hsTableNames) {
		if (hsTableNames.contains("TB_MICRO_GENERAL_MODEL")||hsTableNames.contains("tb_micro_general_model")) {
			this.reloadGeneralModelFromDB();
		}
	}
	
	/**
	 * 加载当前�?
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void reloadGeneralModelFromDB() {
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		List generalModelList = new ArrayList();
		try {
			conn = serviceLocator.getDBConn();
			sta = conn.prepareStatement(" select id,generalName,generalUrl, loginCode,createDate,updateDate,icon from tb_micro_general_model");
			rs = sta.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					String loginCode = rs.getString("loginCode");
					Map<String,Object> temp = new HashMap<String,Object>();
					temp.put("id", rs.getInt("id"));
					temp.put("generalName", rs.getString("generalName"));
					temp.put("generalUrl", rs.getString("generalUrl"));
					temp.put("loginCode", loginCode);
					temp.put("icon", rs.getString("icon"));
					
					if(generalModelMap.containsKey(loginCode)){
						List _generalModelList = generalModelMap.get(loginCode);
						_generalModelList.add(temp);
					}else{
						generalModelList.add(temp);
						generalModelMap.put(loginCode, generalModelList);
					}
				}
			}
		} catch (Exception ex) {
			// 写入异常日志
			logger.error("LatnDC reloadFromDB error:", ex);
		} finally {
			ClearDBResource.closeResultSet(rs);
			ClearDBResource.closeStatment(sta);
			ClearDBResource.closeConnection(conn);
		}
	}
	
	public List<Map> getGeneralModel(String loginCode)
	{
		if(generalModelMap.containsKey(loginCode))
		{
			return  generalModelMap.get(loginCode);
		}
		return null;
	}
	
}


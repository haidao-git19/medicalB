package com.netbull.shop.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.log4j.Logger;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.datacache.DCManager;
import com.netbull.shop.common.datacache.DataRefreshListener;
import com.netbull.shop.common.dbutil.ClearDBResource;
import com.netbull.shop.common.dbutil.ServiceLocator;
import com.netbull.shop.common.log.LoggerFactory;

public class ReflectDC implements DataRefreshListener{
	private static Logger logger = LoggerFactory.getLogger(ReflectDC.class);
	private static ReflectDC instance = null; // 实现Singleton模式的静态变�?
	private static String MODEL_SERVICE_NAME = "模块服务";
	private static String MODEL_CLASS_NAME = "com.netbull.shop.manage.weixin.service.InvokenModelService";
	private static String MODEL_METHOD_NAME = "invokenModel";
	
	// 定义内存中取本地网对
	private static Map<String, Map> tokenMap = new HashMap<String, Map>();
	/**
	 */
	private ReflectDC() {
		DCManager.addDCDataRefreshListener(this);
	}
	
	/**
	 * 实例当前对象
	 * 
	 * @return
	 */
	public static synchronized ReflectDC getInstance() {
		if (instance == null) {
				instance = new ReflectDC();
				instance.reloadFromDB();
		}
		return instance;
	}
	
	/**
	 * 更新�?
	 */
	@SuppressWarnings("unchecked")
	public void doDCDataRefresh(HashSet hsTableNames) {
		if (hsTableNames.contains("TB_MICRO_INVOKEN_REFLECT")||hsTableNames.contains("tb_micro_invoken_reflect")) {
			this.reloadFromDB();
		}
	}
	
	/**
	 * 加载当前�?
	 */
	public void reloadFromDB() {
		
		String devMode = ConfigLoadUtil.loadConfig().getPropertie("devMode"); 
		boolean mode = !NullUtil.isNull(devMode)?Boolean.parseBoolean(devMode):false;
		
		String sql = "";
		//如果是开发模式；
		if(mode){
			sql = "select id,modelId,modelKey,menuKey,className,method,wechatId,name,enable from wechat_ceshi.tb_micro_invoken_reflect where enable = 0";
		}else{
			sql = "select id,modelId,modelKey,menuKey,className,method,wechatId,name,enable from tb_micro_invoken_reflect where enable = 0";
		}
		
		Connection conn  = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		Map<String, Map> temp = new HashMap<String, Map> ();
		try {
			ServiceLocator serviceLocator = ServiceLocator.getInstance();
			conn = serviceLocator.getDBConn();
			sta = conn.prepareStatement(sql);
			rs = sta.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Integer tag = rs.getInt("wechatId");
					String menuKey = rs.getString("menuKey");
					String queryKey = tag+"|"+menuKey;
					temp.remove(queryKey);
					
					Map<String,String> tempMap = new HashMap<String,String>();
					tempMap.put("id", rs.getInt("id")+"");
					tempMap.put("menuKey", menuKey);
					tempMap.put("className", rs.getString("className"));
					tempMap.put("method", rs.getString("method"));
					tempMap.put("name", rs.getString("name"));
					tempMap.put("modelId", rs.getString("modelId"));
					tempMap.put("modelKey", rs.getString("modelKey"));
					temp.put(queryKey, tempMap);
				}
			}
		} catch (SQLException ex) {
			// 写入异常日志
			logger.error("LatnDC reloadFromDB error:", ex);
		} finally {
			ClearDBResource.closeResultSet(rs);
			ClearDBResource.closeStatment(sta);
			ClearDBResource.closeConnection(conn);
		}
		tokenMap = temp;
	}
	
	/**
	 * 加载当前�?
	 */
	public Map<String,String> queryModelFromDB(String _menuKey,String _wechatId) {
		String devMode = ConfigLoadUtil.loadConfig().getPropertie("devMode"); 
		boolean mode = !NullUtil.isNull(devMode)?Boolean.parseBoolean(devMode):false;
		
		String sql = "";
		//如果是开发模式；
		if(mode){
			sql = "select id,modelId,modelKey,menuKey,className,method,wechatId,name,enable from wechat_ceshi.tb_micro_invoken_reflect where enable = 0 and menuKey = ? and wechatId = ?";
		}else{
			sql = "select id,modelId,modelKey,menuKey,className,method,wechatId,name,enable from tb_micro_invoken_reflect where enable = 0 and menuKey = ? and wechatId = ?";
		}
		
		Connection conn  = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		try {
			ServiceLocator serviceLocator = ServiceLocator.getInstance();
			conn = serviceLocator.getDBConn();
			sta = conn.prepareStatement(sql);
			sta.setString(1, _menuKey);
			sta.setString(2, _wechatId);
			
			rs = sta.executeQuery();
			if (rs != null && rs.next()) {
				Integer tag = rs.getInt("wechatId");
				String menuKey = rs.getString("menuKey");
				
				Map<String,String> tempMap = new HashMap<String,String>();
				tempMap.put("id", rs.getInt("id")+"");
				tempMap.put("menuKey", menuKey);
				tempMap.put("className", rs.getString("className"));
				tempMap.put("method", rs.getString("method"));
				tempMap.put("name", rs.getString("name"));
				tempMap.put("modelId", rs.getString("modelId"));
				tempMap.put("modelKey", rs.getString("modelKey"));
				return tempMap;
			}
		} catch (SQLException ex) {
			// 写入异常日志
			logger.error("LatnDC reloadFromDB error:", ex);
		} finally {
			ClearDBResource.closeResultSet(rs);
			ClearDBResource.closeStatment(sta);
			ClearDBResource.closeConnection(conn);
		}
		return null;
	}
	
	/**
	 * 绑定模块到对应的菜单上；
	 * @param menuKey
	 * @param modelId
	 * @param modelKey
	 * @param wechatId
	 */
	public void modelBindMenu(String menuKey,String modelId,String modelKey,String wechatId){
		if(menuKey == null || "".equals(menuKey)){
			return ;
		}
		deleteReflect(menuKey);
		saveReflect(menuKey,modelId,modelKey,wechatId);
	}
	
	public Integer deleteReflect(String menuKey) {
		String devMode = ConfigLoadUtil.loadConfig().getPropertie("devMode"); 
		boolean mode = !NullUtil.isNull(devMode)?Boolean.parseBoolean(devMode):false;
		
		String sql = "";
		//如果是开发模式；
		if(mode){
			sql = "delete from  wechat_ceshi.tb_micro_invoken_reflect where menuKey = ?";
		}else{
			sql = "delete from  tb_micro_invoken_reflect where menuKey = ?";
		}
		
		Connection conn  = null;
		PreparedStatement sta = null;
		try {
			ServiceLocator serviceLocator = ServiceLocator.getInstance();
			conn = serviceLocator.getDBConn();
			sta = conn.prepareStatement(sql);
			sta.setString(1, menuKey);
			return sta.executeUpdate();
		} catch (SQLException ex) {
			// 写入异常日志
			logger.error("LatnDC reloadFromDB error:", ex);
		} finally {
			ClearDBResource.closeStatment(sta);
			ClearDBResource.closeConnection(conn);
		}
		return 0;
	}
	
	public void saveReflect(String menuKey,String modelId,String modelKey,String wechatId) {
		String devMode = ConfigLoadUtil.loadConfig().getPropertie("devMode"); 
		boolean mode = !NullUtil.isNull(devMode)?Boolean.parseBoolean(devMode):false;
		
		String sql = "";
		//如果是开发模式；
		if(mode){
			sql = "insert  wechat_ceshi.tb_micro_invoken_reflect (menuKey,modelId,modelKey,className,method,enable,wechatId,name) values (?,?,?,?,?,?,?,?)";
		}else{
			sql = "insert  tb_micro_invoken_reflect (menuKey,modelId,modelKey,className,method,enable,wechatId,name) values (?,?,?,?,?,?,?,?)";
		}
		
		Connection conn  = null;
		PreparedStatement sta = null;
		try {
			ServiceLocator serviceLocator = ServiceLocator.getInstance();
			conn = serviceLocator.getDBConn();
			sta = conn.prepareStatement(sql);
			sta.setString(1, menuKey);
			sta.setString(2, modelId);
			sta.setString(3, modelKey);
			sta.setString(4, MODEL_CLASS_NAME);
			sta.setString(5, MODEL_METHOD_NAME);
			sta.setString(6, "0");
			sta.setString(7, wechatId);
			sta.setString(8, MODEL_SERVICE_NAME);
			sta.executeUpdate();
		} catch (SQLException ex) {
			// 写入异常日志
			logger.error("LatnDC reloadFromDB error:", ex);
		} finally {
			ClearDBResource.closeStatment(sta);
			ClearDBResource.closeConnection(conn);
		}
	}
	
	/**
	 * 根据wechatId取当前对象信息
	 * @param latnId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> get(String wechatId,String menuKey)
	{
		String key = wechatId+"|"+menuKey;
		if(tokenMap.containsKey(key))
		{
			return  tokenMap.get(key)!=null?tokenMap.get(key):null;
		}
		return null;
	}
	
}


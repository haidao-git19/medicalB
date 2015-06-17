package com.netbull.shop.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.datacache.DCManager;
import com.netbull.shop.common.datacache.DataRefreshListener;
import com.netbull.shop.common.dbutil.ClearDBResource;
import com.netbull.shop.common.dbutil.ServiceLocator;
import com.netbull.shop.common.log.LoggerFactory;

public class ModelReflectDC implements DataRefreshListener{
	private static Logger logger = LoggerFactory.getLogger(ModelReflectDC.class);
	private static ModelReflectDC instance = null; // 实现Singleton模式的静态变�?

	private static Map<String, Map> modelMap = new HashMap<String, Map>();
	private static Map<String, Map> modelMapByWechatId = new HashMap<String, Map>();
	
	/**
	 * 构造函数（增加到监听）
	 */
	private ModelReflectDC() {
		DCManager.addDCDataRefreshListener(this);
	}
	
	/**
	 * 实例当前对象
	 * 
	 * @return
	 */
	public static synchronized ModelReflectDC getInstance() {
		if (instance == null) {
				instance = new ModelReflectDC();
				instance.reloadFromDB();
		}
		return instance;
	}
	
	/**
	 * 更新�?
	 */
	@SuppressWarnings("unchecked")
	public void doDCDataRefresh(HashSet hsTableNames) {
		if (hsTableNames.contains("TB_MICRO_MODEL_REFLECT")||hsTableNames.contains("tb_micro_model_reflect")) {
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
			sql = "select id,modelKey,modelVal,className,method,method1,wechatId,name,enable,remoteQueryList,remoteQueryDetail from wechat_ceshi.tb_micro_model_reflect where enable = 0";
		}else{
			sql = "select id,modelKey,modelVal,className,method,method1,wechatId,name,enable,remoteQueryList,remoteQueryDetail from tb_micro_model_reflect where enable = 0";
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
					String modelKey = rs.getString("modelKey");
					String queryKey = tag+"|"+modelKey;
					temp.remove(queryKey);
					
					Map<String,String> tempMap = new HashMap<String,String>();
					tempMap.put("id", rs.getInt("id")+"");
					tempMap.put("modelKey", modelKey);
					tempMap.put("modelVal", rs.getString("modelVal"));
					tempMap.put("className", rs.getString("className"));
					tempMap.put("wechatId", StringUtil.getString(tag));
					tempMap.put("method", rs.getString("method"));
					tempMap.put("method1", rs.getString("method1"));
					tempMap.put("name", rs.getString("name"));
					tempMap.put("remoteQueryList", rs.getString("remoteQueryList"));
					tempMap.put("remoteQueryDetail", rs.getString("remoteQueryDetail"));
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
		modelMap = temp;
	}
	
	/**
	 * 根据wechatId取当前对象信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> get(String wechatId,String modelKey)
	{
		String key = wechatId+"|"+modelKey;
		if(modelMap.containsKey(key))
		{
			return  modelMap.get(key)!=null?modelMap.get(key):null;
		}
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Map> getAll(String wechatId)
	{
		Map respMap = new HashMap();
		Iterator iterator = modelMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry oo = (Entry)iterator.next();
			Object key = oo.getKey();
			Object val = oo.getValue();
			Map _temp = (Map)val;
			if(StringUtil.getString(_temp.get("wechatId")).equals(wechatId)){
				respMap.put(key, val);
			}
		}
		return respMap;
	}
	
}


package com.netbull.shop.common.util;

import java.sql.Connection;
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

public class TokenDC implements DataRefreshListener{
	private static Logger logger = LoggerFactory.getLogger(TokenDC.class);
	private static TokenDC instance = null; // 实现Singleton模式的静态变�?
	// 定义内存中取本地网对
	private static Map<String, Map> tokenMap = new HashMap<String, Map>();
	/**
	 * 构�?函数（增加到监听�?
	 */
	private TokenDC() {
		DCManager.addDCDataRefreshListener(this);
	}
	
	/**
	 * 实例当前对象
	 * 
	 * @return
	 */
	public static synchronized TokenDC getInstance() {
		if (instance == null) {
				instance = new TokenDC();
				instance.reloadFromDB();
		}else{
			instance.reloadFromDB();
		}
		return instance;
	}
	
	/**
	 * 更新�?
	 */
	@SuppressWarnings("unchecked")
	public void doDCDataRefresh(HashSet hsTableNames) {
		if (hsTableNames.contains("TB_MICRO_WECHAT")||hsTableNames.contains("tb_micro_wechat")) {
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
			sql = "select id,entcode,logincode,appId,appSecret,mch_id,mch_key,url,jsapi_ticket,token,state,createtime,accessToken,remark,expiresIn,updatetime,now()-updatetime expiretime,orignId,orignType,isOauth2 from wechat_ceshi.tb_micro_wechat";
		}else{
			sql = "select id,entcode,logincode,appId,appSecret,mch_id,mch_key,url,jsapi_ticket,token,state,createtime,accessToken,remark,expiresIn,updatetime,now()-updatetime expiretime,orignId,orignType,isOauth2 from wechat.tb_micro_wechat";
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
					String tag = rs.getString("logincode");
					temp.remove(tag);
					
					Map<String,Object> tempMap = new HashMap<String,Object>();
					tempMap.put("id", rs.getInt("id"));
					tempMap.put("entcode", rs.getString("entcode"));
					tempMap.put("logincode", rs.getString("logincode"));
					tempMap.put("appId", rs.getString("appId"));
					tempMap.put("appSecret", rs.getString("appSecret"));
					tempMap.put("mch_id", rs.getString("mch_id"));
					tempMap.put("mch_key", rs.getString("mch_key"));
					tempMap.put("url", rs.getString("url"));
					tempMap.put("jsapi_ticket", rs.getString("jsapi_ticket"));
					tempMap.put("token", rs.getString("token"));
					tempMap.put("state", rs.getString("state"));
					tempMap.put("accessToken", rs.getString("accessToken"));
					tempMap.put("expiresIn", rs.getInt("expiresIn"));
					tempMap.put("expiretime", rs.getString("expiretime"));
					tempMap.put("orignId", rs.getString("orignId"));
					tempMap.put("isOauth2", rs.getString("isOauth2"));
					tempMap.put("orignType", rs.getString("orignType"));
					temp.put(tag, tempMap);
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
		
		// 内存对象赋�?
		tokenMap = temp;
	}
	
	/**
	 * 加载当前�?
	 */
	public void updateTokenFromDB(Map accessToken,Integer id) {
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		Connection conn = null;
		PreparedStatement sta = null;
		try {
			String devMode = ConfigLoadUtil.loadConfig().getPropertie("devMode"); 
			boolean mode = !NullUtil.isNull(devMode)?Boolean.parseBoolean(devMode):false;
			
			//如果是开发模式；
			String sql = "";
			if(mode){
				sql = "update wechat_ceshi.tb_micro_wechat set accessToken=?,jsapi_ticket=?,updatetime=now(),expiresIn=? where id=?";
			}else{
				sql = "update wechat.tb_micro_wechat set accessToken=?,jsapi_ticket=?,updatetime=now(),expiresIn=? where id=?";
			}
			
			conn = serviceLocator.getDBConn();
			sta = conn.
			prepareStatement(sql);
			sta.setString(1, accessToken.get("accessToken")+"");
			sta.setString(2, accessToken.get("tick")+"");
			sta.setInt(3, Integer.parseInt(accessToken.get("expiresIn")+""));
			sta.setInt(4, id);
			sta.executeUpdate();
			this.reloadFromDB();
		} catch (SQLException ex) {
			// 写入异常日志
			logger.error("LatnDC updateTokenFromDB error:", ex);
		} finally {
			ClearDBResource.closeStatment(sta);
			ClearDBResource.closeConnection(conn);
		}
	}
	
	public Map<String, Map> getAllWechatList()
	{
		return tokenMap;
	}
	
	/**
	 * 根据APPID取当前token信息
	 * @param latnId
	 * @return
	 */
	public Map getWechatVo(String loginCode)
	{
		if(tokenMap.containsKey(loginCode))
		{
			return  tokenMap.get(loginCode)!=null?tokenMap.get(loginCode):null;
		}
		return new HashMap();
	}
	
	/**
	 * 根据APPID取当前token信息
	 * @param latnId
	 * @return
	 */
	public String getToken(String appid)
	{
		if(tokenMap.containsKey(appid))
		{
			return  tokenMap.get(appid)!=null?(String)tokenMap.get(appid).get("accessToken"):null;
		}
		return null;
	}
}


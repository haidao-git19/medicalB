package com.netbull.shop.manage.common.util;

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
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringUtil;

public class UserDC implements DataRefreshListener{
	private static Logger logger = LoggerFactory.getLogger(UserDC.class);
	private static UserDC instance = null; // 实现Singleton模式的静态变�?
	
	//商品对象；
	private static Map<String, Map> userMap = new HashMap<String, Map>();
	
	/**
	 * 构造商品DC监听；
	 */
	private UserDC() {
		DCManager.addDCDataRefreshListener(this);
	}
	
	/**
	 * 实例当前对象
	 * 
	 * @return
	 */
	public static synchronized UserDC getInstance() {
		if (instance == null) {
				instance = new UserDC();
				instance.reloadUserFromDB();
		}
		return instance;
	}
	
	/**
	 * 更新
	 */
	@SuppressWarnings("unchecked")
	public void doDCDataRefresh(HashSet hsTableNames) {
		if (hsTableNames.contains("tb_user_base_info")||hsTableNames.contains("TB_USER_BASE_INFO")) {
			this.reloadUserFromDB();
		}
	}
	
	/**
	 * 加载当前
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void reloadUserFromDB() {
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		try {
			conn = serviceLocator.getDBConn();
			sta = conn.prepareStatement("select id,userId,loginName,openId,icon,sign,age,nickName,realName,sex,phone,email,status,password,updateTime from tb_user_base_info");
			rs = sta.executeQuery();
			if (rs != null) {
				this.userMap.clear();
				while (rs.next()) {
					String userId = StringUtil.getString(rs.getString("userId"));
					
					Map _userMap = new HashMap();
					_userMap.put("id", StringUtil.getString(rs.getInt("id")));
					_userMap.put("userId", StringUtil.getString(userId));
					_userMap.put("loginName", rs.getString("loginName"));
					_userMap.put("openId", rs.getString("openId"));
					_userMap.put("icon", rs.getString("icon"));
					_userMap.put("sign", rs.getString("sign"));
					_userMap.put("age", rs.getInt("age"));
					_userMap.put("nickName", rs.getString("nickName"));
					_userMap.put("realName", rs.getString("realName"));
					_userMap.put("sex", rs.getString("sex"));
					_userMap.put("phone", rs.getString("phone"));
					_userMap.put("email", rs.getString("email"));
					_userMap.put("status", rs.getString("status"));
					_userMap.put("password", rs.getString("password"));
					_userMap.put("updateTime", rs.getString("updateTime"));
					
					userMap.put(userId, _userMap);
				}
			}
		} catch (SQLException ex) {
			logger.error("UserDC reloadFromDB error:", ex);
		} finally {
			ClearDBResource.closeResultSet(rs);
			ClearDBResource.closeStatment(sta);
			ClearDBResource.closeConnection(conn);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String,Object> queryUserDetail(Map<String,String> paramter){
		if(NullUtil.isNull(paramter)){
			return null;
		}
		String userId = !NullUtil.isNull(paramter.get("userId"))?paramter.get("userId"):null;
		Map<String, String> _userMap = this.userMap.get(userId);
		if(NullUtil.isNull(_userMap)){
			return null;
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("userId", StringUtil.getString(_userMap.get("userId")));
		resultMap.put("loginName", StringUtil.getString(_userMap.get("loginName")));
		if(!NullUtil.isNull(_userMap.get("icon"))){
			String updateTime = StringUtil.getString(_userMap.get("updateTime"));
			resultMap.put("icon", ConfigLoadUtil.loadConfig().getPropertie("accessAddress")+StringUtil.getString(_userMap.get("icon"))+"?ver="+updateTime);
		}
		resultMap.put("nickName", StringUtil.getString(_userMap.get("nickName")));
		
		return resultMap;
	}
	
}


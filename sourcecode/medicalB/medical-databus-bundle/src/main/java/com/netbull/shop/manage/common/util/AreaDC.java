package com.netbull.shop.manage.common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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

public class AreaDC implements DataRefreshListener{
	private static Logger logger = LoggerFactory.getLogger(AreaDC.class);
	private static AreaDC instance = null; // 实现Singleton模式的静态变�?
	
	private static Map<String, List<Map>> provinceMap = new HashMap<String, List<Map>>();
	private static Map<String, List<Map>> cityMap = new HashMap<String, List<Map>>();
	private static Map<String, List<Map>> areaMap = new HashMap<String, List<Map>>();
	
	/**
	 * 构造监听；
	 */
	private AreaDC() {
		DCManager.addDCDataRefreshListener(this);
	}
	
	/**
	 * 实例当前对象
	 * 
	 * @return
	 */
	public static synchronized AreaDC getInstance() {
		if (instance == null) {
				instance = new AreaDC();
				instance.reloadAreaFromDB();
		}
		return instance;
	}
	
	/**
	 * 更新
	 */
	@SuppressWarnings("unchecked")
	public void doDCDataRefresh(HashSet hsTableNames) {
		if (hsTableNames.contains("tb_shop_area")||hsTableNames.contains("TB_SHOP_AREA")) {
			this.reloadAreaFromDB();
		}
	}
	
	/**
	 * 加载当前
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void reloadAreaFromDB() {
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		try {
			conn = serviceLocator.getDBConn();
			sta = conn.prepareStatement("select id,areaname,parentid,shortname,areacode,zipcode,pinyin,lng,lat,level,position,sort from tb_shop_area");
			rs = sta.executeQuery();
			if (rs != null) {
				this.provinceMap.clear();
				this.cityMap.clear();
				this.areaMap.clear();
				while (rs.next()) {
					String parentid = StringUtil.getString(rs.getInt("parentid"));
					String level = StringUtil.getString(rs.getInt("level"));
					
					Map _areaMap = new HashMap();
					_areaMap.put("id", StringUtil.getString(rs.getInt("id")));
					_areaMap.put("areaname", rs.getString("areaname"));
					_areaMap.put("parentid", parentid);
					_areaMap.put("shortname", rs.getString("shortname"));
					_areaMap.put("areacode", rs.getInt("areacode"));
					_areaMap.put("zipcode", rs.getInt("zipcode"));
					_areaMap.put("pinyin", rs.getString("pinyin"));
					
					if(level.equals("1")){
						if(provinceMap.containsKey(parentid)){
							List<Map> _tempList = provinceMap.get(parentid);
							_tempList.add(_areaMap);
							provinceMap.put(parentid, _tempList);
						}else{
							List<Map> _tempList = new ArrayList<Map>();
							_tempList.add(_areaMap);
							provinceMap.put(parentid, _tempList);
						}
					}else if(level.equals("2")){
						if(cityMap.containsKey(parentid)){
							List<Map> _tempList = cityMap.get(parentid);
							_tempList.add(_areaMap);
							cityMap.put(parentid, _tempList);
						}else{
							List<Map> _tempList = new ArrayList<Map>();
							_tempList.add(_areaMap);
							cityMap.put(parentid, _tempList);
						}
					}else if(level.equals("3")){
						if(areaMap.containsKey(parentid)){
							List<Map> _tempList = areaMap.get(parentid);
							_tempList.add(_areaMap);
							areaMap.put(parentid, _tempList);
						}else{
							List<Map> _tempList = new ArrayList<Map>();
							_tempList.add(_areaMap);
							areaMap.put(parentid, _tempList);
						}
					}
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
	
	@SuppressWarnings("unchecked")
	public List<Map> queryAreaList(String parentid,String type){
		if("1".equals(type)){
			return !NullUtil.isNull(provinceMap.get("0"))?provinceMap.get("0"):new ArrayList();
		}else if("2".equals(type)){
			return !NullUtil.isNull(cityMap.get(parentid))?cityMap.get(parentid):new ArrayList();
		}else if("3".equals(type)){
			return !NullUtil.isNull(areaMap.get(parentid))?areaMap.get(parentid):new ArrayList();
		}
		return null;
	}
	
}


package com.netbull.shop.manage.common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.netbull.shop.common.datacache.DCManager;
import com.netbull.shop.common.datacache.DataRefreshListener;
import com.netbull.shop.common.dbutil.ClearDBResource;
import com.netbull.shop.common.dbutil.ServiceLocator;
import com.netbull.shop.common.log.LoggerFactory;
import com.netbull.shop.common.util.StringUtil;

@SuppressWarnings("rawtypes")
public class HospitalDC implements DataRefreshListener{
	private static Logger logger = LoggerFactory.getLogger(HospitalDC.class);
	private static HospitalDC instance = null; // 实现Singleton模式的静态变�?
	
	private static Map<String, Map> hospitalMap = new HashMap<String, Map>();
	private static Map<String, List<Map>> hospitalLevelMap = new HashMap<String, List<Map>>();
	private static Map<String, List<Map>> hospitalAreaMap = new HashMap<String, List<Map>>();
	
	/**
	 * 构造商品DC监听；
	 */
	private HospitalDC() {
		DCManager.addDCDataRefreshListener(this);
	}
	
	/**
	 * 实例当前对象
	 * 
	 * @return
	 */
	public static synchronized HospitalDC getInstance() {
		if (instance == null) {
				instance = new HospitalDC();
				instance.reloadHospitalFromDB();
		}
		return instance;
	}
	
	/**
	 * 更新
	 */
	@SuppressWarnings("unchecked")
	public void doDCDataRefresh(HashSet hsTableNames) {
		if (hsTableNames.contains("tb_medical_hospital")||hsTableNames.contains("TB_MEDICAL_HOSPITAL")) {
			this.reloadHospitalFromDB();
		}
	}
	
	/**
	 * 加载当前
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	public void reloadHospitalFromDB() {
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		try {
			conn = serviceLocator.getDBConn();
			sta = conn.prepareStatement("SELECT hospitalID,areaName,hospitalName,summary,linkMan,linkPhone,areaID,hospitalLevel,address,lng,lat,ctNum,cgNum,skill,(select count(1) from tb_medical_doctor d where d.hospitalID = h.hospitalID) as doctorCount from tb_medical_hospital h where h.type != 0 ");
			rs = sta.executeQuery();
			if (rs != null) {
				this.hospitalMap.clear();
				this.hospitalLevelMap.clear();
				this.hospitalAreaMap.clear();
				
				while (rs.next()) {
					String hospitalID = StringUtil.getString(rs.getInt("hospitalID"));
					String hospitalLevel = StringUtil.getString(rs.getString("hospitalLevel"));
					String areaName = StringUtil.getString(rs.getString("areaName"));
					
					Map _hospitalMap = new HashMap();
					_hospitalMap.put("hospitalID", hospitalID);
					_hospitalMap.put("hospitalName", rs.getString("hospitalName"));
					_hospitalMap.put("areaName", areaName);
					_hospitalMap.put("summary", rs.getString("summary"));
					_hospitalMap.put("linkMan", rs.getString("linkMan"));
					_hospitalMap.put("linkPhone", rs.getString("linkPhone"));
					_hospitalMap.put("areaID", rs.getInt("areaID"));
					_hospitalMap.put("hospitalLevel", hospitalLevel);
					_hospitalMap.put("address", rs.getString("address"));
					_hospitalMap.put("lng", rs.getDouble("lng"));
					_hospitalMap.put("lat", rs.getDouble("lat"));
					_hospitalMap.put("ctNum", rs.getInt("ctNum"));
					_hospitalMap.put("cgNum", rs.getInt("cgNum"));
					_hospitalMap.put("skill", rs.getString("skill"));
					_hospitalMap.put("doctorCount", rs.getInt("doctorCount"));

					this.hospitalMap.put(hospitalID, _hospitalMap);
					
					if(hospitalLevelMap.containsKey(hospitalLevel)){
						List<Map> _hospitalLevelList = hospitalLevelMap.get(hospitalLevel);
						_hospitalLevelList.add(_hospitalMap);
					}else{
						List<Map> _hospitalLevelList = new ArrayList<Map>();
						_hospitalLevelList.add(_hospitalMap);
						hospitalLevelMap.put(hospitalLevel, _hospitalLevelList);
					}
					
					if(hospitalAreaMap.containsKey(areaName)){
						List<Map> _hospitalAreaList = hospitalAreaMap.get(areaName);
						_hospitalAreaList.add(_hospitalMap);
					}else{
						List<Map> _hospitalAreaList = new ArrayList<Map>();
						_hospitalAreaList.add(_hospitalMap);
						hospitalAreaMap.put(areaName, _hospitalAreaList);
					}
				}
			}
		} catch (SQLException ex) {
			logger.error("GoodsDC reloadFromDB error:", ex);
		} finally {
			ClearDBResource.closeResultSet(rs);
			ClearDBResource.closeStatment(sta);
			ClearDBResource.closeConnection(conn);
		}
	}
	
	public List<Map> queryHospitalLevelList(String key){
		List<Map> result = new ArrayList<Map>();
		Iterator iterator = hospitalLevelMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map temp = new HashMap();
			Entry oo = (Entry)iterator.next();
			Object _key = oo.getKey();
			Object _val = oo.getValue();
			temp.put("key", _key);
			temp.put("list", _val);
			result.add(temp);
		}
		return result;
	}
	
	public List<Map> queryHospitalAreaList(String key){
		List<Map> result = new ArrayList<Map>();
		Iterator iterator = hospitalAreaMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map temp = new HashMap();
			Entry oo = (Entry)iterator.next();
			Object _key = oo.getKey();
			Object _val = oo.getValue();
			temp.put("key", _key);
			temp.put("list", _val);
			result.add(temp);
		}
		return result;
	}
	
	public Map<String, Map> queryHospitalDetail(String key){
		if(hospitalMap.containsKey(key)){
			return hospitalMap.get(key);
		}else{
			return null;
		}
	}
	
}


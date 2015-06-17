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
public class DoctorDC implements DataRefreshListener{
	private static Logger logger = LoggerFactory.getLogger(DoctorDC.class);
	private static DoctorDC instance = null; // 实现Singleton模式的静态变�?
	
	private static Map<String, List<Map>> doctorFeeMap = new HashMap<String, List<Map>>();
	
	/**
	 * 构造商品DC监听；
	 */
	private DoctorDC() {
		DCManager.addDCDataRefreshListener(this);
	}
	
	/**
	 * 实例当前对象
	 * 
	 * @return
	 */
	public static synchronized DoctorDC getInstance() {
		if (instance == null) {
				instance = new DoctorDC();
				instance.reloadHospitalFromDB();
		}
		return instance;
	}
	
	/**
	 * 更新
	 */
	@SuppressWarnings("unchecked")
	public void doDCDataRefresh(HashSet hsTableNames) {
		if (hsTableNames.contains("tb_medical_doctor_fee")||hsTableNames.contains("TB_MEDICAL_DOCTOR_FEE")) {
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
			sta = conn.prepareStatement("SELECT id,doctorID,type,level,levelTitle,fee,time from tb_medical_doctor_fee h ");
			rs = sta.executeQuery();
			if (rs != null) {
				this.doctorFeeMap.clear();
				
				while (rs.next()) {
					String doctorID = StringUtil.getString(rs.getInt("doctorID"));
					
					Map _doctorFeeMap = new HashMap();
					_doctorFeeMap.put("id", rs.getString("id"));
					_doctorFeeMap.put("doctorID", doctorID);
					_doctorFeeMap.put("type", rs.getString("type"));
					_doctorFeeMap.put("level", rs.getString("level"));
					_doctorFeeMap.put("levelTitle", rs.getString("levelTitle"));
					_doctorFeeMap.put("fee", rs.getString("fee"));
					_doctorFeeMap.put("time", rs.getInt("time"));

					
					if(doctorFeeMap.containsKey(doctorID)){
						List<Map> _doctorFeeList = doctorFeeMap.get(doctorID);
						_doctorFeeList.add(_doctorFeeMap);
					}else{
						List<Map> _doctorFeeList = new ArrayList<Map>();
						_doctorFeeList.add(_doctorFeeMap);
						doctorFeeMap.put(doctorID, _doctorFeeList);
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
	
	public List<Map> querydoctorFeeMap(String key){
		if(doctorFeeMap.containsKey(key)){
			return doctorFeeMap.get(key);
		}else{
			return null;
		}
	}
	
}


package com.netbull.web.shop.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.netbull.shop.common.datacache.DCManager;
import com.netbull.shop.common.datacache.DataRefreshListener;
import com.netbull.shop.common.dbutil.ClearDBResource;
import com.netbull.shop.common.dbutil.ServiceLocator;
import com.netbull.shop.common.log.LoggerFactory;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringUtil;

@SuppressWarnings("static-access")
public class SectionFilterDC implements DataRefreshListener{
	private static Logger logger = LoggerFactory.getLogger(SectionFilterDC.class);
	private static SectionFilterDC instance = null; // 实现Singleton模式的静态变�?
	
	private static Map<String, List<Map>> topSectionMap = new HashMap<String, List<Map>>();
	private static Map<String, List<Map>> filterMap = new HashMap<String, List<Map>>();
	
	/**
	 * 构造监听；
	 */
	private SectionFilterDC() {
		DCManager.addDCDataRefreshListener(this);
	}
	
	/**
	 * 实例当前对象
	 * 
	 * @return
	 */
	public static synchronized SectionFilterDC getInstance() {
		if (instance == null) {
				instance = new SectionFilterDC();
				instance.reloadAreaFromDB();
		}
		return instance;
	}
	
	/**
	 * 更新
	 */
	@SuppressWarnings("unchecked")
	public void doDCDataRefresh(HashSet hsTableNames) {
		if (hsTableNames.contains("tb_medical_section")||hsTableNames.contains("TB_MEDICAL_SECTION")) {
			this.reloadAreaFromDB();
		}
	}
	
	/**
	 * 加载当前
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map reloadAreaFromDB() {
		try {
			Map requestMap = new HashMap();
			requestMap.put("id", "0");
			filterMap = callBackSectionFilter(requestMap);
			return filterMap;
		} catch (Exception ex) {
			logger.error("UserDC reloadFromDB error:", ex);
		} 
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<Integer,List> queryAreaFromDB(Integer parentid) {
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		Map<Integer, List> tempMap = new HashMap<Integer, List>();
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		try {
			conn = serviceLocator.getDBConn();
			sta = conn.prepareStatement("select id,attrname,attrvalue,parentid,sort,level,(select count(1) from tb_medical_doctor d where d.sectionID = s.id) as doctorCount from tb_medical_section s where parentid = ?");
			sta.setInt(1, parentid);
			rs = sta.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Map _areaMap = new HashMap();
					_areaMap.put("id", rs.getInt("id"));
					_areaMap.put("attrname", rs.getString("attrname"));
					_areaMap.put("parentid", parentid);
					_areaMap.put("attrvalue", rs.getString("attrvalue"));
					_areaMap.put("sort", rs.getInt("sort"));
					_areaMap.put("level", rs.getString("level"));
					_areaMap.put("doctorCount", rs.getInt("doctorCount"));
					
					if(tempMap.containsKey(parentid)){
						List<Map> _tempList = tempMap.get(parentid);
						_tempList.add(_areaMap);
						tempMap.put(parentid, _tempList);
					}else{
						List<Map> _tempList = new ArrayList<Map>();
						_tempList.add(_areaMap);
						tempMap.put(parentid, _tempList);
					}
				}
			}
			return tempMap;
		} catch (SQLException ex) {
			logger.error("UserDC reloadFromDB error:", ex);
		} finally {
			ClearDBResource.closeResultSet(rs);
			ClearDBResource.closeStatment(sta);
			ClearDBResource.closeConnection(conn);
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map callBackSectionFilter(Map paramter) throws Exception{
		Integer parentid = !NullUtil.isNull(paramter.get("id"))?Integer.parseInt(paramter.get("id")+""):0;
		Map<Integer,List> _map = queryAreaFromDB(parentid);
		if(NullUtil.isNull(_map)){
			return null;
		}
		List list = _map.get(parentid);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			Map _tempMap = callBackSectionFilter(map);
			if(!NullUtil.isNull(_tempMap)){
				String level = StringUtil.getString(map.get("level"));
				if("3".equals(level)||"2".equals(level)||"1".equals(level)){
					map.put("list",_tempMap.get(Integer.parseInt(map.get("id")+"")));
				}else{
					map.put("list",_tempMap);
				}
			}
		}
		return _map;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map queryFilter(){
		if(filterMap.size() > 0){
			return filterMap;
		}
		filterMap = reloadAreaFromDB();
		return filterMap;
	}
}


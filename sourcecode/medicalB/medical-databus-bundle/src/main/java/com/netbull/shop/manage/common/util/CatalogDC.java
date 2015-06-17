package com.netbull.shop.manage.common.util;

import java.io.File;
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

import org.apache.log4j.Logger;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.datacache.DCManager;
import com.netbull.shop.common.datacache.DataRefreshListener;
import com.netbull.shop.common.dbutil.ClearDBResource;
import com.netbull.shop.common.dbutil.ServiceLocator;
import com.netbull.shop.common.log.LoggerFactory;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringUtil;

public class CatalogDC implements DataRefreshListener{
	private static Logger logger = LoggerFactory.getLogger(CatalogDC.class);
	private static CatalogDC instance = null; // 实现Singleton模式的静态变�?
	
	//商品属性对象；
	private static List<Map> catalogList = new ArrayList<Map>();
	private static Map<String, List<Map>> catalogGoodsMap = new HashMap<String, List<Map>>();
	/**
	 * 构造商品DC监听；
	 */
	private CatalogDC() {
		DCManager.addDCDataRefreshListener(this);
	}
	
	/**
	 * 实例当前对象
	 * 
	 * @return
	 */
	public static synchronized CatalogDC getInstance() {
		if (instance == null) {
				instance = new CatalogDC();
				instance.reloadCatalogFromDB();
				instance.reloadCatalogGoodsFromDB();
		}
		return instance;
	}
	
	/**
	 * 更新
	 */
	@SuppressWarnings("unchecked")
	public void doDCDataRefresh(HashSet hsTableNames) {
		if (hsTableNames.contains("tb_shop_catalog_goods")||hsTableNames.contains("TB_SHOP_CATALOG_GOODS")||hsTableNames.contains("tb_shop_catalog")||hsTableNames.contains("TB_SHOP_CATALOG")) {
			this.reloadCatalogFromDB();
			this.reloadCatalogGoodsFromDB();
		}
	}
	
	/**
	 * 加载当前
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void reloadCatalogFromDB() {
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		try {
			conn = serviceLocator.getDBConn();
			sta = conn.prepareStatement("select id,catalogName,catalogCode,attrName,attrValue from tb_shop_catalog order by seq asc");
			rs = sta.executeQuery();
			if (rs != null) {
				this.catalogList.clear();
				while (rs.next()) {
					String catalogCode = StringUtil.getString(rs.getString("catalogCode"));
					
					Map _catalogMap = new HashMap();
					_catalogMap.put("catalogName", rs.getString("catalogName"));
					_catalogMap.put("catalogCode", catalogCode);
					_catalogMap.put("attrName", rs.getString("attrName"));
					_catalogMap.put("attrValue", rs.getString("attrValue"));
					
					catalogList.add(_catalogMap);
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
	
	
	/**
	 * 加载当前
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void reloadCatalogGoodsFromDB() {
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		try {
			conn = serviceLocator.getDBConn();
			sta = conn.prepareStatement("select c.id,c.catalogCode,c.goodsCode,c.goodsVersion,c.coverImage from tb_shop_catalog_goods c");
			rs = sta.executeQuery();
			if (rs != null) {
				this.catalogGoodsMap.clear();
				while (rs.next()) {
					String catalogCode = StringUtil.getString(rs.getString("catalogCode"));
					String goodsCode = StringUtil.getString(rs.getString("goodsCode"));
					String goodsVersion = StringUtil.getString(rs.getString("goodsVersion"));
					String coverImage = StringUtil.getString(rs.getString("coverImage"));
					
					Map paramter = new HashMap();
					paramter.put("goodsCode", goodsCode);
					paramter.put("goodsCode", goodsCode);
					paramter.put("goodsVersion", goodsVersion);
					paramter.put("coverImage", ConfigLoadUtil.loadConfig().getPropertie("accessAddress") +coverImage);
					
					if(catalogGoodsMap.containsKey(catalogCode)){
						List<Map> _catalogGoodsList = catalogGoodsMap.get(catalogCode);
						_catalogGoodsList.add(paramter);
					}else{
						List<Map> _catalogGoodsList = new ArrayList<Map>();
						_catalogGoodsList.add(paramter);
						catalogGoodsMap.put(catalogCode, _catalogGoodsList);
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map> queryCatalogList(){
		if(NullUtil.isNull(catalogList)){
			return null;
		}
		for (Iterator iterator = catalogList.iterator(); iterator.hasNext();) {
			Map catalogMap = (Map) iterator.next();
			catalogMap.put("list", catalogGoodsMap.get(catalogMap.get("catalogCode")));
		}
		return catalogList;
	}
}


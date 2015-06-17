package com.netbull.shop.manage.common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.netbull.shop.common.util.StringUtil;

public class GoodsContentDC implements DataRefreshListener{
	private static Logger logger = LoggerFactory.getLogger(GoodsContentDC.class);
	private static GoodsContentDC instance = null; // 实现Singleton模式的静态变�?
	
	//商品属性对象；
	private static Map<String, Map> goodsContentMap = new HashMap<String, Map>();
	
	/**
	 * 构造商品DC监听；
	 */
	private GoodsContentDC() {
		DCManager.addDCDataRefreshListener(this);
	}
	
	/**
	 * 实例当前对象
	 * 
	 * @return
	 */
	public static synchronized GoodsContentDC getInstance() {
		if (instance == null) {
				instance = new GoodsContentDC();
				instance.reloadGoodsContentFromDB();
		}
		return instance;
	}
	
	/**
	 * 更新
	 */
	@SuppressWarnings("unchecked")
	public void doDCDataRefresh(HashSet hsTableNames) {
		if (hsTableNames.contains("tb_product_goods_clob")||hsTableNames.contains("TB_PRODUCT_GOODS_CLOB")) {
			this.reloadGoodsContentFromDB();
		}
	}
	
	/**
	 * 加载当前
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void reloadGoodsContentFromDB() {
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		try {
			conn = serviceLocator.getDBConn();
			sta = conn.prepareStatement("select id,title,text from tb_product_goods_clob");
			rs = sta.executeQuery();
			if (rs != null) {
				this.goodsContentMap.clear();
				while (rs.next()) {
					String goodsCode = StringUtil.getString(rs.getString("goodsCode"));
					
					Map _goodReportMap = new HashMap();
					_goodReportMap.put("id", rs.getInt("id"));
					_goodReportMap.put("title", rs.getString("title"));
					_goodReportMap.put("text", rs.getString("text"));
					goodsContentMap.put(goodsCode, _goodReportMap);
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
	
	public Map queryGoodsContentDetail(String key){
		if(goodsContentMap.containsKey(key)){
			return goodsContentMap.get(key);
		}else{
			return null;
		}
	}
	
}


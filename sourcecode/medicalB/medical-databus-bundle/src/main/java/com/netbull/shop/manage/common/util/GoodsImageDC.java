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

import com.netbull.shop.common.datacache.DCManager;
import com.netbull.shop.common.datacache.DataRefreshListener;
import com.netbull.shop.common.dbutil.ClearDBResource;
import com.netbull.shop.common.dbutil.ServiceLocator;
import com.netbull.shop.common.log.LoggerFactory;
import com.netbull.shop.common.util.StringUtil;

public class GoodsImageDC implements DataRefreshListener{
	private static Logger logger = LoggerFactory.getLogger(GoodsImageDC.class);
	private static GoodsImageDC instance = null; // 实现Singleton模式的静态变�?
	
	//商品属性对象；
	private static Map<String, List<Map>> goodsImageMap = new HashMap<String, List<Map>>();
	
	/**
	 * 构造商品DC监听；
	 */
	private GoodsImageDC() {
		DCManager.addDCDataRefreshListener(this);
	}
	
	/**
	 * 实例当前对象
	 * 
	 * @return
	 */
	public static synchronized GoodsImageDC getInstance() {
		if (instance == null) {
				instance = new GoodsImageDC();
				instance.reloadGoodsImageFromDB();
		}
		return instance;
	}
	
	/**
	 * 更新
	 */
	@SuppressWarnings("unchecked")
	public void doDCDataRefresh(HashSet hsTableNames) {
		if (hsTableNames.contains("tb_product_goods_image")||hsTableNames.contains("TB_PRODUCT_GOODS_IMAGE")) {
			this.reloadGoodsImageFromDB();
		}
	}
	
	/**
	 * 加载当前
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void reloadGoodsImageFromDB() {
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		try {
			conn = serviceLocator.getDBConn();
			sta = conn.prepareStatement("select id,goodsCode,imgPath,sort from tb_product_goods_image");
			rs = sta.executeQuery();
			if (rs != null) {
				this.goodsImageMap.clear();
				while (rs.next()) {
					String goodsCode = StringUtil.getString(rs.getString("goodsCode"));
					String key = goodsCode;
					
					Map _goodImageMap = new HashMap();
					_goodImageMap.put("id", rs.getInt("id"));
					_goodImageMap.put("goodsCode", goodsCode);
					_goodImageMap.put("imgPath", rs.getString("imgPath"));
					_goodImageMap.put("sort", rs.getString("sort"));
					
					if(goodsImageMap.containsKey(key)){
						List<Map> _goodReportList = goodsImageMap.get(key);
						_goodReportList.add(_goodImageMap);
					}else{
						List<Map> _goodReportList = new ArrayList<Map>();
						_goodReportList.add(_goodImageMap);
						goodsImageMap.put(key, _goodReportList);
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
	
	public List<Map> queryGoodsImageList(String key){
		if(goodsImageMap.containsKey(key)){
			return goodsImageMap.get(key);
		}else{
			return null;
		}
	}
}


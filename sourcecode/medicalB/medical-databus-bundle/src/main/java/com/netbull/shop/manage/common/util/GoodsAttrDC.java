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

public class GoodsAttrDC implements DataRefreshListener{
	private static Logger logger = LoggerFactory.getLogger(GoodsAttrDC.class);
	private static GoodsAttrDC instance = null; // 实现Singleton模式的静态变�?
	
	//商品属性对象；
	private static Map<String, List<Map>> goodsAttrMap = new HashMap<String, List<Map>>();
	
	/**
	 * 构造商品DC监听；
	 */
	private GoodsAttrDC() {
		DCManager.addDCDataRefreshListener(this);
	}
	
	/**
	 * 实例当前对象
	 * 
	 * @return
	 */
	public static synchronized GoodsAttrDC getInstance() {
		if (instance == null) {
				instance = new GoodsAttrDC();
				instance.reloadGoodsAttrFromDB();
		}
		return instance;
	}
	
	/**
	 * 更新
	 */
	@SuppressWarnings("unchecked")
	public void doDCDataRefresh(HashSet hsTableNames) {
		if (hsTableNames.contains("tb_product_goods_attr")||hsTableNames.contains("TB_PRODUCT_GOODS_ATTR")) {
			this.reloadGoodsAttrFromDB();
		}
	}
	
	/**
	 * 加载当前
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void reloadGoodsAttrFromDB() {
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		try {
			conn = serviceLocator.getDBConn();
			sta = conn.prepareStatement("select id,goodsCode,attrCode,attrValue,createTime,updateTime,hold1,hold2 from tb_product_goods_attr");
			rs = sta.executeQuery();
			if (rs != null) {
				this.goodsAttrMap.clear();
				while (rs.next()) {
					String goodsCode = StringUtil.getString(rs.getString("goodsCode"));
					String key = goodsCode;
					
					Map _goodAttrMap = new HashMap();
					_goodAttrMap.put("id", rs.getInt("id"));
					_goodAttrMap.put("goodsCode", goodsCode);
					_goodAttrMap.put("attrCode", rs.getString("attrCode"));
					_goodAttrMap.put("attrValue", rs.getString("attrValue"));
					_goodAttrMap.put("createTime", rs.getString("createTime"));
					_goodAttrMap.put("updateTime", rs.getString("updateTime"));
					_goodAttrMap.put("hold1", rs.getString("hold1"));
					_goodAttrMap.put("hold2", rs.getString("hold2"));
					
					if(goodsAttrMap.containsKey(key)){
						List<Map> _goodAttrList = goodsAttrMap.get(key);
						_goodAttrList.add(_goodAttrMap);
					}else{
						List<Map> _goodAttrList = new ArrayList<Map>();
						_goodAttrList.add(_goodAttrMap);
						goodsAttrMap.put(key, _goodAttrList);
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
	
	public List<Map> queryGoodsAttrList(String key){
		if(goodsAttrMap.containsKey(key)){
			return goodsAttrMap.get(key);
		}else{
			return null;
		}
	}
}


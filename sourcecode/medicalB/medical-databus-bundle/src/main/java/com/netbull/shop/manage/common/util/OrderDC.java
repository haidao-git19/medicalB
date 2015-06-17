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
import com.netbull.shop.util.JsonUtils;

public class OrderDC implements DataRefreshListener{
	private static Logger logger = LoggerFactory.getLogger(OrderDC.class);
	private static OrderDC instance = null; // 实现Singleton模式的静态变�?
	
	//商品对象；
	private static Map<String, Map> orderMap = new HashMap<String, Map>();
	
	/**
	 * 构造商品DC监听；
	 */
	private OrderDC() {
		DCManager.addDCDataRefreshListener(this);
	}
	
	/**
	 * 实例当前对象
	 * 
	 * @return
	 */
	public static synchronized OrderDC getInstance() {
		if (instance == null) {
				instance = new OrderDC();
				instance.reloadOrderFromDB();
		}
		return instance;
	}
	
	/**
	 * 更新
	 */
	@SuppressWarnings("unchecked")
	public void doDCDataRefresh(HashSet hsTableNames) {
		if (hsTableNames.contains("tb_product_sell_record")||hsTableNames.contains("TB_PRODUCT_SELL_RECORD")) {
			this.reloadOrderFromDB();
		}
	}
	
	/**
	 * 加载当前
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void reloadOrderFromDB() {
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		try {
			conn = serviceLocator.getDBConn();
			sta = conn.prepareStatement("select id,userId,orderCode,goodsCode,goodsVersion,localCode,totalFee,style,sellTime,productNum,status,ispay from tb_product_sell_record");
			rs = sta.executeQuery();
			if (rs != null) {
				this.orderMap.clear();
				while (rs.next()) {
					String userId = StringUtil.getString(rs.getString("userId"));
					
					Map _orderMap = new HashMap();
					_orderMap.put("id", StringUtil.getString(rs.getInt("id")));
					_orderMap.put("userId", StringUtil.getString(userId));
					_orderMap.put("orderCode", rs.getString("orderCode"));
					_orderMap.put("goodsCode", rs.getString("goodsCode"));
					_orderMap.put("goodsVersion", rs.getString("goodsVersion"));
					_orderMap.put("localCode", rs.getString("localCode"));
					_orderMap.put("totalFee", StringUtil.getString(rs.getFloat("totalFee")));
					_orderMap.put("style", rs.getString("style"));
					_orderMap.put("sellTime", rs.getString("sellTime"));
					_orderMap.put("productNum", StringUtil.getString(rs.getInt("productNum")));
					_orderMap.put("status", rs.getString("status"));
					_orderMap.put("ispay", rs.getString("ispay"));
					
					orderMap.put(userId, _orderMap);
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
	public Map<String,Object> queryOrderDetail(Map<String,String> paramter){
		if(NullUtil.isNull(paramter)){
			return null;
		}
		String userId = !NullUtil.isNull(paramter.get("userId"))?paramter.get("userId"):null;
		Map<String, String> _orderMap = this.orderMap.get(userId);
		if(NullUtil.isNull(_orderMap)){
			return null;
		}
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String,Object> goodsDetail = GoodsDC.getInstance().queryGoodsDetail(_orderMap);
		Map<String,Object> userDetail = UserDC.getInstance().queryUserDetail(paramter);
		resultMap.put("orderDetail", _orderMap);
		resultMap.put("goodDetail", goodsDetail);
		resultMap.put("userDetail", userDetail);
		
		return resultMap;
	}
	
}


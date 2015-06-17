package com.netbull.shop.manage.common.util;

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
import com.netbull.shop.common.util.Md5;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.manage.common.constant.Constants;

public class GoodsDC implements DataRefreshListener{
	private static Logger logger = LoggerFactory.getLogger(GoodsDC.class);
	private static GoodsDC instance = null; // 实现Singleton模式的静态变�?
	
	//商品对象；
	private static Map<String, Map> goodsMap = new HashMap<String, Map>();
	private static Map<String, List> recomMap = new HashMap<String, List>();
	
	/**
	 * 构造商品DC监听；
	 */
	private GoodsDC() {
		DCManager.addDCDataRefreshListener(this);
	}
	
	/**
	 * 实例当前对象
	 * 
	 * @return
	 */
	public static synchronized GoodsDC getInstance() {
		if (instance == null) {
				instance = new GoodsDC();
				instance.reloadGoodsFromDB();
		}
		return instance;
	}
	
	/**
	 * 更新
	 */
	@SuppressWarnings("unchecked")
	public void doDCDataRefresh(HashSet hsTableNames) {
		if (hsTableNames.contains("tb_product_goods")||hsTableNames.contains("TB_PRODUCT_GOODS")) {
			this.reloadGoodsFromDB();
		}
	}
	
	/**
	 * 加载当前
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void reloadGoodsFromDB() {
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		try {
			conn = serviceLocator.getDBConn();
			sta = conn.prepareStatement("select id,categoryCode,longName,mediumName,shortName,secondName,brandId,style," +
					"model,coverImgPath,imgPath,goodsCode,isDraft,marketPrice,shopPrice,goodsType,sellCharacter,sellAttr,label,status," +
					"isShow,sellingPoints,packageList,buyNote,content,createTime,createPerson,updateTime,updatePerson,saleTime,hold1,hold2,shopId from tb_product_goods where status = 2");
			rs = sta.executeQuery();
			if (rs != null) {
				this.goodsMap.clear();
				this.recomMap.clear();
				while (rs.next()) {
					String goodsCode = StringUtil.getString(rs.getString("goodsCode"));
					String label = StringUtil.getString(rs.getInt("label"));
					
					String key = goodsCode;
					
					Map _goodMap = new HashMap();
					_goodMap.put("id", rs.getInt("id"));
					_goodMap.put("categoryCode", rs.getString("categoryCode"));
					_goodMap.put("longName", rs.getString("longName"));
					_goodMap.put("mediumName", rs.getString("mediumName"));
					_goodMap.put("shortName", rs.getString("shortName"));
					_goodMap.put("goodsCode", goodsCode);
					_goodMap.put("secondName", rs.getString("secondName"));
					_goodMap.put("brandId", rs.getInt("brandId"));
					_goodMap.put("style", rs.getString("style"));
					_goodMap.put("model", rs.getInt("model"));
					_goodMap.put("coverImgPath", rs.getString("coverImgPath"));
					_goodMap.put("imgPath", rs.getString("imgPath"));
					_goodMap.put("isDraft", rs.getInt("isDraft"));
					_goodMap.put("marketPrice", rs.getDouble("marketPrice"));
					_goodMap.put("shopPrice", rs.getDouble("shopPrice"));
					_goodMap.put("goodsType", rs.getInt("goodsType"));
					_goodMap.put("sellCharacter", rs.getInt("sellCharacter"));
					_goodMap.put("sellAttr", rs.getInt("sellAttr"));
					_goodMap.put("label", rs.getInt("label"));
					_goodMap.put("status", rs.getString("status"));
					_goodMap.put("isShow", rs.getInt("isShow"));
					_goodMap.put("sellingPoints", rs.getLong("sellingPoints"));
					_goodMap.put("packageList", rs.getLong("packageList"));
					_goodMap.put("buyNote", rs.getLong("buyNote"));
					_goodMap.put("content", rs.getLong("content"));
					_goodMap.put("createTime", rs.getString("createTime"));
					_goodMap.put("createPerson", rs.getString("createPerson"));
					_goodMap.put("updateTime", rs.getString("updateTime"));
					_goodMap.put("updatePerson", rs.getString("updatePerson"));
					_goodMap.put("saleTime", rs.getString("saleTime"));
					_goodMap.put("hold1", rs.getString("hold1"));
					_goodMap.put("hold2", rs.getString("hold2"));
					_goodMap.put("shopId", rs.getString("shopId"));
					
					goodsMap.put(key, _goodMap);
					
					if(recomMap.containsKey(label)){
						List<Map> _recomList = recomMap.get(label);
						_recomList.add(_goodMap);
					}else{
						List<Map> _recomList = new ArrayList<Map>();
						_recomList.add(_goodMap);
						recomMap.put(label, _recomList);
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
	
	/**
	 * 查询促销或推荐商品；
	 * @param paramter
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List queryDiscountOrRecomGoods(Map<String,String> paramter){
		if(NullUtil.isNull(paramter)){
			return null;
		}
		String label = !NullUtil.isNull(paramter.get("label"))?paramter.get("label"):null;
		
		List _recomGoodMap = this.recomMap.get(label);
		return _recomGoodMap;
	}
	
	/**
	 * 查询商品基本信息；
	 * @param paramter
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String,String> queryGoods(Map<String,String> paramter){
		if(NullUtil.isNull(paramter)){
			return null;
		}
		String goodsCode = !NullUtil.isNull(paramter.get("goodsCode"))?paramter.get("goodsCode"):null;
		String key = goodsCode;
		
		Map<String, String> _goodMap = this.goodsMap.get(key);
		return _goodMap;
	}
	
	/**
	 * 查询商品详情信息，包括商品的属性、图片、内容；
	 * @param paramter
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String,Object> queryGoodsDetail(Map<String,String> paramter){
		if(NullUtil.isNull(paramter)){
			return null;
		}
		String goodsCode = !NullUtil.isNull(paramter.get("goodsCode"))?paramter.get("goodsCode"):null;
		String key = goodsCode;
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String, Map> _goodsMap = this.goodsMap.get(key);
		if(NullUtil.isNull(_goodsMap)){
			return null;
		}
		
		List<Map> _goodAttrList = GoodsAttrDC.getInstance().queryGoodsAttrList(key);
		List<Map> _goodImageList = GoodsImageDC.getInstance().queryGoodsImageList(key);
		Map _goodContentMap = GoodsContentDC.getInstance().queryGoodsContentDetail(key);
		
		resultMap.put("attrList", _goodAttrList);
		resultMap.put("imageList", _goodImageList);
		resultMap.put("goodDetail", _goodContentMap);
		
		return resultMap;
	}
	
}


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
			sta = conn.prepareStatement("select id,goodsName,goodsBrandId,goodsModel,coverImage,goodsCode,goodsVersion,isDraft,isCurrentVersion,marketPrice,goodsType,sellCharacter,sellAttr,label,status,sellingPoints,packageList,createTime,createPerson,updateTime,updatePerson,saleTime,shopPrice,buyNote,isShow,goodsdetailUrl from tb_product_goods");
			rs = sta.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					String goodsCode = StringUtil.getString(rs.getString("goodsCode"));
					String goodsVersion = StringUtil.getString(rs.getString("goodsVersion"));
					String key = goodsCode+"_"+goodsVersion;
					
					Map _goodMap = new HashMap();
					_goodMap.put("id", rs.getInt("id"));
					_goodMap.put("goodsName", rs.getString("goodsName"));
					_goodMap.put("goodsBrandId", rs.getInt("goodsBrandId"));
					_goodMap.put("goodsModel", rs.getString("goodsModel"));
					_goodMap.put("coverImage", rs.getString("coverImage"));
					_goodMap.put("goodsCode", goodsCode);
					_goodMap.put("goodsVersion", goodsVersion);
					_goodMap.put("isDraft", rs.getString("isDraft"));
					_goodMap.put("isCurrentVersion", rs.getString("isCurrentVersion"));
					_goodMap.put("marketPrice", rs.getString("marketPrice"));
					_goodMap.put("goodsType", rs.getString("goodsType"));
					_goodMap.put("sellCharacter", rs.getInt("sellCharacter"));
					_goodMap.put("sellAttr", rs.getInt("sellAttr"));
					_goodMap.put("label", rs.getInt("label"));
					_goodMap.put("status", rs.getString("status"));
					_goodMap.put("sellingPoints", rs.getString("sellingPoints"));
					_goodMap.put("packageList", rs.getString("packageList"));
					_goodMap.put("createTime", rs.getString("createTime"));
					_goodMap.put("createPerson", rs.getString("createPerson"));
					_goodMap.put("updateTime", rs.getString("updateTime"));
					_goodMap.put("updatePerson", rs.getString("updatePerson"));
					_goodMap.put("saleTime", rs.getString("saleTime"));
					_goodMap.put("shopPrice", StringUtil.getString(rs.getFloat("shopPrice")));
					_goodMap.put("buyNote", rs.getString("buyNote"));
					_goodMap.put("isShow", rs.getString("isShow"));
					_goodMap.put("goodsdetailUrl", rs.getString("goodsdetailUrl"));
					goodsMap.put(key, _goodMap);
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
	public Map<String,String> queryGoodsMap(Map<String,String> paramter){
		if(NullUtil.isNull(paramter)){
			return null;
		}
		String goodsCode = !NullUtil.isNull(paramter.get("goodsCode"))?paramter.get("goodsCode"):null;
		String goodsVersion = !NullUtil.isNull(paramter.get("goodsVersion"))?paramter.get("goodsVersion"):"1.0";
		String key = goodsCode+"_"+goodsVersion;
		
		Map<String, String> _goodMap = this.goodsMap.get(key);
		return _goodMap;
	}
	
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public Map<String,Object> queryGoodsDetail(Map<String,String> paramter){
//		if(NullUtil.isNull(paramter)){
//			return null;
//		}
//		String goodsCode = !NullUtil.isNull(paramter.get("goodsCode"))?paramter.get("goodsCode"):null;
//		String goodsVersion = !NullUtil.isNull(paramter.get("goodsVersion"))?paramter.get("goodsVersion"):"1.0";
//		String key = goodsCode+"_"+goodsVersion;
//		
//		Map<String,Object> resultMap = new HashMap<String,Object>();
//		Map<String, Map> _goodsMap = this.goodsMap.get(key);
//		if(NullUtil.isNull(_goodsMap)){
//			return null;
//		}
//		Map _goodReportMap = GoodsReportDC.getInstance().queryGoodsReportDetail(goodsCode);
//		List<Map> _goodAttrList = GoodsAttrDC.getInstance().queryGoodsAttrList(key);
//		List<Map> _goodImageList = GoodsImageDC.getInstance().queryGoodsImageList(key);
////		List<Map> _commentList = CommentDC.getInstance().queryCommentList(goodsCode);
//		/***添加数字签名***/
//		String shopPrice = !NullUtil.isNull(_goodsMap.get("shopPrice")+"")?StringUtil.getString(_goodsMap.get("shopPrice")+""):"0";
//		this.addDataSign(_goodsMap, goodsCode, goodsVersion, shopPrice);
//		
//		this.constructGoodsDetail(resultMap,_goodsMap);
//		if(!NullUtil.isNull(_goodReportMap)){
//			resultMap.put("praiseCount", _goodReportMap.get("praiseCount"));
//			resultMap.put("goodsStock", _goodReportMap.get("goodsStock"));
//		}
//		this.constructGoodsAttr(resultMap, _goodAttrList);
//		this.constructGoodsImage(resultMap, _goodImageList);
////		if(!NullUtil.isNull(_commentList)){
////			resultMap.put("commentList", _commentList);
////		}
//		
//		return resultMap;
//	}
	
	@SuppressWarnings("unchecked")
	private void addDataSign(Map _goodsMap,String goodsCode,String goodsVersion,String shopPrice){
		try {
			if(NullUtil.isNull(_goodsMap)){
				return;
			}
			StringBuffer sb = new StringBuffer();
			sb.append(Constants.SHOP_PRODUCT_KEY);
			sb.append(goodsCode);
			sb.append(goodsVersion);
			sb.append(shopPrice);
			_goodsMap.put("dataSign", Md5.md5Digest(sb.toString()));
		} catch (Exception e) {
			this.logger.error(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	private void constructGoodsDetail(Map resultMap,Map _goodsMap){
		if(!NullUtil.isNull(_goodsMap)){
			resultMap.put("id", _goodsMap.get("id"));
			resultMap.put("goodsCode", _goodsMap.get("goodsCode"));
			resultMap.put("goodsName", _goodsMap.get("goodsName"));
			resultMap.put("goodsVersion", _goodsMap.get("goodsVersion"));
			resultMap.put("shopPrice", _goodsMap.get("shopPrice"));
			resultMap.put("dataSign", _goodsMap.get("dataSign"));
			resultMap.put("imageUrl", ConfigLoadUtil.loadConfig().getPropertie("accessAddress")+_goodsMap.get("coverImage"));
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void constructGoodsAttr(Map resultMap,List<Map> _goodAttrList){
		if(!NullUtil.isNull(_goodAttrList)){
			List<Map> tempList = new ArrayList<Map>();
			for (Iterator iterator = _goodAttrList.iterator(); iterator.hasNext();) {
				Map temMap = new HashMap();
				Map map = (Map) iterator.next();
				temMap.put("attrKey", map.get("attrName"));
				temMap.put("attrValue", map.get("attrValue"));
				tempList.add(temMap);
			}
			resultMap.put("attrList", tempList);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void constructGoodsImage(Map resultMap,List<Map> _goodImageList){
		if(!NullUtil.isNull(_goodImageList)){
			List<Map> tempList = new ArrayList<Map>();
			for (Iterator iterator = _goodImageList.iterator(); iterator.hasNext();) {
				Map temMap = new HashMap();
				Map map = (Map) iterator.next();
				temMap.put("imageUrl", ConfigLoadUtil.loadConfig().getPropertie("accessAddress") + map.get("image1"));
				tempList.add(temMap);
			}
			resultMap.put("imageList", tempList);
		}
	}
}


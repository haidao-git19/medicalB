package com.netbull.shop.manage.common.util;

import java.io.File;
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

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.datacache.DCManager;
import com.netbull.shop.common.datacache.DataRefreshListener;
import com.netbull.shop.common.dbutil.ClearDBResource;
import com.netbull.shop.common.dbutil.ServiceLocator;
import com.netbull.shop.common.log.LoggerFactory;
import com.netbull.shop.common.util.StringUtil;

public class CommentDC implements DataRefreshListener{
	private static Logger logger = LoggerFactory.getLogger(CommentDC.class);
	private static CommentDC instance = null; // 实现Singleton模式的静态变�?
	
	//商品属性对象；
	private static Map<String, List<Map>> commentMap = new HashMap<String, List<Map>>();
	
	/**
	 * 构造商品DC监听；
	 */
	private CommentDC() {
		DCManager.addDCDataRefreshListener(this);
	}
	
	/**
	 * 实例当前对象
	 * 
	 * @return
	 */
	public static synchronized CommentDC getInstance() {
		if (instance == null) {
				instance = new CommentDC();
				instance.reloadCommentFromDB();
		}
		return instance;
	}
	
	/**
	 * 更新
	 */
	@SuppressWarnings("unchecked")
	public void doDCDataRefresh(HashSet hsTableNames) {
		if (hsTableNames.contains("tb_product_comment")||hsTableNames.contains("TB_PRODUCT_COMMENT")) {
			this.reloadCommentFromDB();
		}
	}
	
	/**
	 * 加载当前
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void reloadCommentFromDB() {
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		try {
			conn = serviceLocator.getDBConn();
			sta = conn.prepareStatement("select goodsCode,contents,u.realName,u.icon,c.createTime,c.updateTime from tb_product_comment c LEFT JOIN tb_user_base_info u on u.id = c.userId");
			rs = sta.executeQuery();
			if (rs != null) {
				this.commentMap.clear();
				while (rs.next()) {
					String goodsCode = StringUtil.getString(rs.getString("goodsCode"));
					
					Map _commentMap = new HashMap();
					_commentMap.put("commentContent", rs.getString("contents"));
					_commentMap.put("userName", rs.getString("realName"));
					_commentMap.put("userIcon", ConfigLoadUtil.loadConfig().getPropertie("accessAddress") + rs.getString("icon"));
					_commentMap.put("commentTime", rs.getString("createTime"));
					
					if(commentMap.containsKey(goodsCode)){
						List<Map> _commentList = commentMap.get(goodsCode);
						_commentList.add(_commentMap);
					}else{
						List<Map> _commentList = new ArrayList<Map>();
						_commentList.add(_commentMap);
						commentMap.put(goodsCode, _commentList);
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
	
	public List<Map> queryCommentList(String key){
		if(commentMap.containsKey(key)){
			return commentMap.get(key);
		}else{
			return null;
		}
	}
}


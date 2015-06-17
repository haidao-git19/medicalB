package com.netbull.shop.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
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
import com.netbull.shop.common.vo.TipsVO;

/**
 * 提示信息缓存
 *
 */
public class TipDC implements DataRefreshListener{
	private static Logger logger = LoggerFactory.getLogger(TipDC.class);
	private static TipDC instance = null; // 实现Singleton模式的静态变�?
	
	private Map<String, TipsVO> tipMap = null;
	/**
	 * 构�?函数（增加到监听�?
	 */
	private TipDC() {
		DCManager.addDCDataRefreshListener(this);
	}
	/**
	 * 实例当前对象
	 * 
	 * @return
	 */
	public static synchronized TipDC getInstance() {
		if (instance == null) {
				instance = new TipDC();
				instance.reloadFromDB();
		}
		return instance;
	}
	/**
	 * 更新�?
	 */
	@SuppressWarnings("unchecked")
	public void doDCDataRefresh(HashSet hsTableNames) {
		if (hsTableNames.contains("TB_MICRO_TIPS")||hsTableNames.contains("tb_micro_tips")) {
			this.reloadFromDB();
		}
	}
	/**
	 * 加载当前
	 */
	public void reloadFromDB() {
		
		String devMode = ConfigLoadUtil.loadConfig().getPropertie("devMode"); 
		boolean mode = !NullUtil.isNull(devMode)?Boolean.parseBoolean(devMode):false;
		
		String sql = "";
		//如果是开发模式；
		if(mode){
			sql = "SELECT id,orignId,tipType,tipKey,tipValue FROM wechat_ceshi.tb_micro_tips ORDER BY id";
		}else{
			sql = "SELECT id,orignId,tipType,tipKey,tipValue FROM tb_micro_tips ORDER BY id";
		}
		
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		Map<String, TipsVO> temp = new HashMap<String, TipsVO> ();
		try {
			ServiceLocator serviceLocator = ServiceLocator.getInstance();
			conn = serviceLocator.getDBConn();
			sta = conn.prepareStatement(sql);
			rs = sta.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					String tag = rs.getString("tipKey");
					if (temp.containsKey(tag)) {
						temp.remove(tag);
						TipsVO tipsVO = new TipsVO();
						tipsVO.setId(rs.getLong("id"));
						tipsVO.setOrignId(rs.getString("orignId"));
						tipsVO.setType(rs.getString("tipType"));
						tipsVO.setKey(rs.getString("tipKey"));
						tipsVO.setValue(rs.getString("tipValue"));
						temp.put(tag, tipsVO);
					} else {
						TipsVO tipsVO = new TipsVO();
						tipsVO.setId(rs.getLong("id"));
						tipsVO.setOrignId(rs.getString("orignId"));
						tipsVO.setType(rs.getString("tipType"));
						tipsVO.setKey(rs.getString("tipKey"));
						tipsVO.setValue(rs.getString("tipValue"));
						temp.put(tag, tipsVO);
					}
				}
			}
		} catch (SQLException ex) {
			logger.error("LatnDC reloadFromDB error:", ex);
		} finally {
			ClearDBResource.closeResultSet(rs);
			ClearDBResource.closeStatment(sta);
			ClearDBResource.closeConnection(conn);
		}
		tipMap = temp;
	}

	
	public TipsVO getTipsVO(String tipkey)
	{
		if(tipMap.containsKey(tipkey))
		{
			return (TipsVO) tipMap.get(tipkey);
		}
		return null;
	}
}


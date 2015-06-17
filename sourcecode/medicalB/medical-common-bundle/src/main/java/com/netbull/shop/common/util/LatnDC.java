package com.netbull.shop.common.util;

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

/**
 * 本地网缓
 *
 */
public class LatnDC implements DataRefreshListener{
	private static Logger logger = LoggerFactory.getLogger(LatnDC.class);
	private static LatnDC instance = null; // 实现Singleton模式的静态变�?
	// 定义内存中取本地网对
	private Map<String, LatnVO> latnMap = null;
	List<LatnVO> latnList = new ArrayList<LatnVO>();
	/**
	 * 构�?函数（增加到监听�?
	 */
	private LatnDC() {
		DCManager.addDCDataRefreshListener(this);
	}
	/**
	 * 实例当前对象
	 * 
	 * @return
	 */
	public static synchronized LatnDC getInstance() {
		if (instance == null) {
				instance = new LatnDC();
				instance.reloadFromDB();
		}
		return instance;
	}
	/**
	 * 更新�?
	 */
	@SuppressWarnings("unchecked")
	public void doDCDataRefresh(HashSet hsTableNames) {
		if (hsTableNames.contains("TB_EMS_LATN")) {
			this.reloadFromDB();
		}
	}
	/**
	 * 加载当前�?
	 */
	public void reloadFromDB() {
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		Map<String, LatnVO> temp = new HashMap<String, LatnVO> ();
		// load from TB_WSS_IP_LIB
		try {
			conn = serviceLocator.getDBConn();
			sta = conn.prepareStatement("  SELECT LATN_ID,LATN_NAME,DIST_NUM FROM TB_Ems_LATN ORDER BY LATN_ID");
			rs = sta.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					String tag = rs.getString("LATN_ID");
					if (temp.containsKey(tag)) {
						temp.remove(tag);
						LatnVO latnVO = new LatnVO();
						latnVO.setLatnID(rs.getString("LATN_ID"));
						latnVO.setLatnName(rs.getString("LATN_NAME"));
						latnVO.setDistNum(rs.getString("DIST_NUM"));
						temp.put(tag, latnVO);
					} else {
						LatnVO latnVO = new LatnVO();
						latnVO.setLatnID(rs.getString("LATN_ID"));
						latnVO.setLatnName(rs.getString("LATN_NAME"));
						latnVO.setDistNum(rs.getString("DIST_NUM"));
						temp.put(tag, latnVO);
					}
				}
			}
		} catch (SQLException ex) {
			// 写入异常日志
			logger.error("LatnDC reloadFromDB error:", ex);
		} finally {
			ClearDBResource.closeResultSet(rs);
			ClearDBResource.closeStatment(sta);
			ClearDBResource.closeConnection(conn);
		}
		
		try {
			conn = serviceLocator.getDBConn();
			sta = conn.prepareStatement("SELECT LATN_ID,LATN_NAME,DIST_NUM FROM TB_Ems_LATN ORDER BY LATN_ID");
			rs = sta.executeQuery();
			if (rs != null) {
				latnList.clear();
				while (rs.next()) {
					LatnVO latnVO = new LatnVO();
					latnVO.setLatnID(rs.getString("LATN_ID"));
					latnVO.setLatnName(rs.getString("LATN_NAME"));
					latnVO.setDistNum(rs.getString("DIST_NUM"));
					latnList.add(latnVO);
				}
			}
		} catch (Exception ex) {
			// 写入异常日志
			logger.error("LatnDC reloadFromDB error:", ex);
		} finally {
			ClearDBResource.closeResultSet(rs);
			ClearDBResource.closeStatment(sta);
			ClearDBResource.closeConnection(conn);
		}
		// 内存对象赋�?
		latnMap = temp;
	}
	/**
	 * 取得�?��本地网信�?包含全省)
	 * @return
	 */
	public List<LatnVO> getAllLatn()
	{
		return latnList;
	}
	/**
	 * 取得�?��本地网信�?不包含全�?
	 * @return
	 */
	public List<LatnVO> getLatn()
	{
		List<LatnVO> lalist = new ArrayList<LatnVO>();
		if(latnList != null && latnList.size()>0)
		{
			for(int i=0;i<latnList.size();i++)
			{
				if(!"999".equals(latnList.get(i).getLatnID()))
				{
					lalist.add(latnList.get(i));
				}
			}
		}
		return lalist;
	}
	/**
	 * 根据本地网取当前地市信息
	 * @param latnId
	 * @return
	 */
	public LatnVO getLatnVO(String latnId)
	{
		if(latnMap.containsKey(latnId))
		{
			return (LatnVO) latnMap.get(latnId);
		}
		return null;
	}
}


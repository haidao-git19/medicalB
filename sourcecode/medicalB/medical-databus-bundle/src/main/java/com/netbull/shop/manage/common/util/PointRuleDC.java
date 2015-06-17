package com.netbull.shop.manage.common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.log4j.Logger;

import com.netbull.shop.common.datacache.DCManager;
import com.netbull.shop.common.datacache.DataRefreshListener;
import com.netbull.shop.common.dbutil.ClearDBResource;
import com.netbull.shop.common.dbutil.ServiceLocator;
import com.netbull.shop.common.log.LoggerFactory;
import com.netbull.shop.common.util.StringUtil;

public class PointRuleDC implements DataRefreshListener{

	private static Logger logger = LoggerFactory.getLogger(PointRuleDC.class);
	private static PointRuleDC instance=null;//实现单例模式的静态变量
	private static Map<String,Map<String,Object>> pointRuleMap=new HashMap<String, Map<String,Object>>();
	
	/**
	 * 构造积分规则监听
	 */
	private PointRuleDC(){
		DCManager.addDCDataRefreshListener(this);
	}
	
	/**
	 * 实例化当前对象
	 * @return
	 */
	public static synchronized PointRuleDC getInstance(){
		if(instance==null){
			instance=new PointRuleDC();
			instance.reloadPointRuleFromDB();
		}
		return instance;
	}
	
	/**
	 * 更新
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void doDCDataRefresh(HashSet hsTableNames) {
		// TODO Auto-generated method stub
		if(hsTableNames.contains("tb_product_point_rule")||hsTableNames.contains("TB_PRODUCT_POINT_RULE")){
			reloadPointRuleFromDB();
		}
	}
	
	/**
	 * 加载当前
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void reloadPointRuleFromDB(){
		ServiceLocator serviceLocator=ServiceLocator.getInstance();
		Connection conn = null;
		PreparedStatement sta = null;
		ResultSet rs = null;
		try {
			conn=serviceLocator.getDBConn();
			sta=conn.prepareStatement("select id,ruleName,presentPoint,peroid,suspendHandle,createTime,remark from tb_product_point_rule");
			rs=sta.executeQuery();
			if(rs!=null){
				this.pointRuleMap.clear();
				while(rs.next()){
					String key=StringUtil.getString(rs.getString("ruleName"));
					
					Map _pointRule=new HashMap();
					_pointRule.put("id", rs.getInt("id"));
					_pointRule.put("ruleName", rs.getString("ruleName"));
					_pointRule.put("presentPoint", rs.getInt("presentPoint"));
					_pointRule.put("peroid", rs.getInt("peroid"));
					_pointRule.put("suspendHandle", rs.getString("suspendHandle"));
					_pointRule.put("createTime", rs.getString("createTime"));
					_pointRule.put("remark", rs.getString("remark"));
					
					if(pointRuleMap.containsKey(key)){
						pointRuleMap.remove(key);
						pointRuleMap.put(key, _pointRule);
					}else{
						pointRuleMap.put(key,_pointRule);
					}
				}
			}
		} catch (SQLException ex) {
			logger.error("PointRuleDC reloadFromDB error:", ex);
		}finally{
			ClearDBResource.closeResultSet(rs);
			ClearDBResource.closeStatment(sta);
			ClearDBResource.closeConnection(conn);
		}
	}
	
	public Map<String,Object> queryPointRule(String key){
		if(pointRuleMap.containsKey(key)){
			return pointRuleMap.get(key);
		}else{
			return null;
		}
	}

}

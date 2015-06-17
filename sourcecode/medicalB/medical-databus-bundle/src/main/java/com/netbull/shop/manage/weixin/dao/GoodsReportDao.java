package com.netbull.shop.manage.weixin.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.vo.GoodsReport;

@Repository
public class GoodsReportDao {

	private static final String MYBATIS_PREFIX=GoodsReportDao.class.getName();
	@Autowired
	private SqlSession session;
	
	/**
	 * 更新商品统计信息
	 * @param map
	 * @return
	 */
	public Integer update(Map<String,String> map){
		return session.update(MYBATIS_PREFIX+".update", map);
	}
	
	/**
	 * 查询商品统计信息
	 * @param map
	 * @return
	 */
	public GoodsReport queryByParams(Map<String,String> map){
		return session.selectOne(MYBATIS_PREFIX+".queryByParams", map);
	}
}

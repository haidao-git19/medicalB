package com.netbull.shop.manage.weixin.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.common.vo.GoodsReport;
import com.netbull.shop.manage.weixin.dao.GoodsReportDao;

@Service
public class GoodsReportService {

	@Autowired
	private GoodsReportDao goodsReportDao;
	
	/**
	 * 更新商品统计信息
	 * @param map
	 */
	public void updateGoodsReport(Map<String,String> map){
		goodsReportDao.update(map);
	}
	
	/**
	 * 查询商品统计信息
	 * @param map
	 * @return
	 */
	public GoodsReport queryGoodsReportByParams(Map<String,String> map){
		return goodsReportDao.queryByParams(map);
	}
}

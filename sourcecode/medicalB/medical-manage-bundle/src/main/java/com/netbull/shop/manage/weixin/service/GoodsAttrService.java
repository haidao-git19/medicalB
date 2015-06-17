package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.vo.GoodsAttr;
import com.netbull.shop.manage.weixin.dao.GoodsAttrDao;
import com.netbull.shop.util.MyBatisDao;

@Service
public class GoodsAttrService extends MyBatisDao<GoodsAttr, Integer>{

	@Autowired
	private GoodsAttrDao goodsAttrDao;
	
	/**
	 * 分页查询商品属性信息
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param requestMap
	 * @return
	 */
	public Page queryGoodsAttrPage(Integer iDisplayStart, Integer iDisplayLength,Map<String, String> requestMap){
		return goodsAttrDao.queryPage(iDisplayStart, iDisplayLength, requestMap);
	}
	
	/**
	 * 查询商品属性信息
	 * @param map
	 * @return
	 */
	public List<GoodsAttr> queryGoodsAttrList(Map<Object,Object> map){
		return goodsAttrDao.queryList(map);
	}
}

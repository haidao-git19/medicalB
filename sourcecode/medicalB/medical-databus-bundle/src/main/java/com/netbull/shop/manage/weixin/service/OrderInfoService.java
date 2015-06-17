package com.netbull.shop.manage.weixin.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.vo.OrderVo;
import com.netbull.shop.manage.common.util.GoodsDC;
import com.netbull.shop.manage.common.util.UserDC;
import com.netbull.shop.manage.weixin.dao.OrderInfoDao;

@Service
@SuppressWarnings({ "unchecked", "rawtypes" })
public class OrderInfoService {
	private static final Log log = LogFactory.getLog(OrderInfoService.class);
    
	@Resource
	private OrderInfoDao orderInfoDao;

	/**
	 * 查询订单信息
	 * @param paramter
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	public  List<Map> queryOrderInfo(Map paramter) throws ParseException{
		List<OrderVo> order = orderInfoDao.queryOrderInfoList(paramter);
		if(NullUtil.isNull(order)){
			return null;
		}
		List<Map> resultList = new ArrayList<Map>();
		for (OrderVo orderVo : order) {
			paramter.put("goodsCode", orderVo.getGoodsCode());
			paramter.put("goodsVersion", orderVo.getGoodsVersion());
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date date=sdf.parse(orderVo.getSellTime());
			String sellTime=sdf.format(date);
			orderVo.setSellTime(sellTime);
			
			Map<String,Object> resultMap = new HashMap<String,Object>();
			Map<String,Object> goodsDetail = GoodsDC.getInstance().queryGoodsDetail(paramter);
			Map<String,Object> userDetail = UserDC.getInstance().queryUserDetail(paramter);
			resultMap.put("orderDetail", orderVo);
			resultMap.put("goodDetail", goodsDetail);
			resultMap.put("userDetail", userDetail);
			resultList.add(resultMap);
		}
		return resultList;
	}

	/**
	 * 修改订单信息
	 * @param paramter
	 * @return
	 */
	public Integer modifyOrderInfo(Map paramter) {	
		return orderInfoDao.modifyOrderInfo(paramter);
	}
	/**
	 * 保存订单信息
	 * @param paramter
	 * @return
	 */
	public Integer saveOrderInfo(Map paramter) {
		return orderInfoDao.saveOrderInfo(paramter);
	}
	
	/**
	 * 查询订单返回记录
	 * @param paramter
	 * @return
	 */
	public OrderVo queryOrderDetail(Map paramter) {		
		return orderInfoDao.queryOrderDetail(paramter);
	}
}

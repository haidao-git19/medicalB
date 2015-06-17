package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.netbull.shop.common.vo.OrderVo;

import com.netbull.shop.common.dao.SqlSession;

@Repository
public class OrderInfoDao {
	private static final String MYBATIS_PREFIX = OrderInfoDao.class.getName();
	
	@Resource
	private SqlSession session;
    
	/**
	 * 查询订单信息
	 * @param paramter
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<OrderVo> queryOrderInfoList(Map paramter) {		
		List<OrderVo> userObjList = session.selectList(MYBATIS_PREFIX + ".queryOrderInfoList",paramter);
		return userObjList;
	}
	
	/**
	 * 查询订单信息
	 * @param paramter
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public OrderVo queryOrderDetail(Map paramter) {		
		OrderVo orderObj = session.selectOne(MYBATIS_PREFIX + ".queryOrderDetail",paramter);
		return orderObj;
	}
	
	/**
	 * 修改订单信息
	 * @param paramter
	 * @return
	 */
	public Integer modifyOrderInfo(Map paramter) {		
		return session.update(MYBATIS_PREFIX + ".modifyOrderInfo",paramter);
	}
	
	/**
	 * 保存订单信息
	 * @param paramter
	 * @return
	 */
	public Integer saveOrderInfo(Map paramter) {		
		return session.update(MYBATIS_PREFIX + ".saveOrderInfo",paramter);
	}
}

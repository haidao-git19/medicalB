package com.netbull.web.shop.order.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.web.shop.order.domain.OrderBase;
import com.netbull.web.shop.order.domain.OrderDelivery;
import com.netbull.web.shop.order.domain.OrderItem;
import com.netbull.web.shop.order.domain.OrderPayment;
import com.netbull.web.shop.order.domain.ServiceOrder;

@Repository
public class OrderDao {

	private static final String NAMESPACE = OrderBase.class.getName();

	@Resource
	private SqlSession session;
	
	public void saveOrderBase(OrderBase orderBase) {
		session.insert(NAMESPACE + ".save_OrderBase", orderBase);
	}
	
	public void saveOrderItem(OrderItem orderItem) {
		session.insert(NAMESPACE + ".save_OrderItem", orderItem);
	}
	
	public void saveServiceOrder(ServiceOrder service) {
		session.insert(NAMESPACE + ".save_ServiceOrder", service);
	}
	
	public void saveOrderPayment(OrderPayment orderPayment) {
		session.insert(NAMESPACE + ".save_OrderPayment", orderPayment);
	}
	
	public void saveOrderDelivery(OrderDelivery orderDelivery) {
		session.insert(NAMESPACE + ".save_OrderDelivery", orderDelivery);
	}
	
	public int updateOrderPayment(OrderPayment orderPayment) {
		return session.insert(NAMESPACE + ".update_OrderPayment", orderPayment);
	}
	
}
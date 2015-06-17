package com.netbull.web.shop.order.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.netbull.web.shop.order.dao.OrderDao;
import com.netbull.web.shop.order.domain.OrderBase;
import com.netbull.web.shop.order.domain.OrderDelivery;
import com.netbull.web.shop.order.domain.OrderItem;
import com.netbull.web.shop.order.domain.OrderPayment;
import com.netbull.web.shop.order.domain.ServiceOrder;

@Service
public class OrderService {

	@Resource
	private OrderDao orderDao;
	
	public void saveOrderBase(OrderBase orderBase) {
		orderDao.saveOrderBase(orderBase);
	}
	
	public void saveOrderItem(OrderItem orderItem) {
		orderDao.saveOrderItem(orderItem);
	}
	
	public void saveOrderPayment(OrderPayment orderPayment) {
		orderDao.saveOrderPayment(orderPayment);
	}
	
	public void saveOrderDelivery(OrderDelivery orderDelivery) {
		orderDao.saveOrderDelivery(orderDelivery);
	}

	public void saveServiceOrder(ServiceOrder service) {
		orderDao.saveServiceOrder(service);
	}
	
	public int updateOrderPayment(OrderPayment orderPayment) {
		return orderDao.updateOrderPayment(orderPayment);
	}

}

package com.netbull.shop.databus.order.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.databus.order.dto.DoctorOrder;
import com.netbull.shop.databus.order.dto.PatientOrder;
import com.netbull.shop.databus.order.dto.ShopOrder;
import com.netbull.shop.databus.order.model.OrderBase;
import com.netbull.shop.databus.order.model.OrderPayment;
import com.netbull.shop.databus.order.model.ServiceOrder;

@Repository
public class OrderDao {
	private static final Logger logger = Logger.getLogger("daoLog");
	
	private static final String NAMESPACE = OrderBase.class.getName();

	@Resource
	private SqlSession session;

	public void saveOrderBase(OrderBase orderBase) {
		session.insert(NAMESPACE + ".save_OrderBase", orderBase);
	}
	
	public void saveServiceOrder(ServiceOrder service) {
		session.insert(NAMESPACE + ".save_ServiceOrder", service);
	}
	
	public ServiceOrder findServiceOrder(String orderNumber) {
		return session.selectOne(NAMESPACE + ".findServiceOrderByOrderNumber", orderNumber);
	}
	
	public void saveOrderPayment(OrderPayment orderPayment) {
		session.insert(NAMESPACE + ".save_OrderPayment", orderPayment);
	}
	
	public int updateOrderPayment(OrderPayment orderPayment) {
		return session.insert(NAMESPACE + ".update_OrderPayment", orderPayment);
	}
	
	public int patientOrderListTotalCount(Map<String, Object> param) {
		return (Integer)session.selectOne(NAMESPACE + ".patientOrderListTotalCount", param);
	}
	
	public List<PatientOrder> patientOrderList(Map<String, Object> param) {
		return session.selectList(NAMESPACE + ".patientOrderList", param);
	}
	
	public int doctorOrderListTotalCount(Map<String, Object> param) {
		return (Integer)session.selectOne(NAMESPACE + ".doctorOrderListTotalCount", param);
	}
	
	public List<DoctorOrder> doctorOrderList(Map<String, Object> param) {
		return session.selectList(NAMESPACE + ".doctorOrderList", param);
	}
	
	public int totalIncome(Map<String, Object> param) {
		return (Integer)session.selectOne(NAMESPACE + ".totalIncome", param);
	}
	
	public int recentMonthIncome(Map<String, Object> param) {
		return (Integer)session.selectOne(NAMESPACE + ".recentMonthIncome", param);
	}

	public int shopOrderListTotalCount(Map<String, Object> param) {
		return (Integer)session.selectOne(NAMESPACE + ".shopOrderListTotalCount", param);
	}
	
	public List<ShopOrder> shopOrderList(Map<String, Object> param) {
		return session.selectList(NAMESPACE + ".shopOrderList", param);
	}
}

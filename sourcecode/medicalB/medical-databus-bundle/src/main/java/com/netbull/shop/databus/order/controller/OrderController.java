package com.netbull.shop.databus.order.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.netbull.shop.common.util.RequestUtil;
import com.netbull.shop.databus.order.dto.DoctorOrderListReq;
import com.netbull.shop.databus.order.dto.DoctorOrderListResp;
import com.netbull.shop.databus.order.dto.DoctorRefundListResp;
import com.netbull.shop.databus.order.dto.PatientOrderListReq;
import com.netbull.shop.databus.order.dto.PatientOrderListResp;
import com.netbull.shop.databus.order.dto.PutServiceOrderReq;
import com.netbull.shop.databus.order.dto.PutServiceOrderResp;
import com.netbull.shop.databus.order.dto.ShopOrderListReq;
import com.netbull.shop.databus.order.dto.ShopOrderListResp;
import com.netbull.shop.databus.order.dto.UpdateOrderPaymentReq;
import com.netbull.shop.databus.order.model.ServiceOrder;
import com.netbull.shop.databus.order.service.OrderService;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.common.http.Resp;
import com.netbull.shop.manage.weixin.service.AccountmanageService;
import com.netbull.shop.manage.weixin.service.BusinessPayService;
import com.netbull.shop.util.RequestUtils;

@Controller("orderController")
public class OrderController {
	private static final Logger logger = Logger.getLogger("controlerLog");
	private static final String SUC_BIZ_STATE = "0";
	@Resource
	private AccountmanageService accService;
	@Resource
	private OrderService orderService;
	@Resource
	private BusinessPayService businessPayService;

	@RequestMapping(value = "/anon/put/service/order", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String putServiceOrder(HttpServletRequest reqest) throws Exception {
		String reqJson = RequestUtil.getPostData(reqest);
		logger.info("req=" + reqJson);
		
		PutServiceOrderReq req = JSON.parseObject(reqJson,
				PutServiceOrderReq.class);
		
		PutServiceOrderResp resp = orderService.putServiceOrder(req);
		return JSON.toJSONString(resp);
	}

	@RequestMapping(value = "/anon/update/order/payment", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String updateOrderPayment(HttpServletRequest reqest)
			throws Exception {
		String reqJson = RequestUtil.getPostData(reqest);
		logger.info("req=" + reqJson);

		UpdateOrderPaymentReq req = JSON.parseObject(reqJson,
				UpdateOrderPaymentReq.class);
		Resp resp = orderService.updateOrderPayment(req);
		String rslt = JSON.toJSONString(resp);
		if (Constants.SUCCESS.equals(resp.getCode())) {
			ServiceOrder serviceOrder = orderService.findServiceOrder(req
					.getOrderNum());
			if (serviceOrder.getServiceType() != 6) {
				accService.payAccountLog(serviceOrder);
				rslt = businessPayService.modifyBizStatus(
						String.valueOf(serviceOrder.getServiceType()),
						String.valueOf(serviceOrder.getServiceNumber()),
						SUC_BIZ_STATE);
			} else {
				rslt=accService.rechargeFee(serviceOrder);
			}
		}
		return rslt;
	}

	@RequestMapping(value = "/anon/patient/order/list", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String patientOrderList(HttpServletRequest reqest) throws Exception {
		String reqJson = RequestUtil.getPostData(reqest);
		logger.info("req=" + reqJson);

		PatientOrderListReq req = JSON.parseObject(reqJson,
				PatientOrderListReq.class);
		PatientOrderListResp resp = orderService.patientOrderList(req);
		return JSON.toJSONString(resp);
	}

	@RequestMapping(value = "/anon/doctor/order/list", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String doctorOrderList(HttpServletRequest reqest) throws Exception {
		String reqJson = RequestUtil.getPostData(reqest);
		logger.info("req=" + reqJson);

		DoctorOrderListReq req = JSON.parseObject(reqJson,
				DoctorOrderListReq.class);
		DoctorOrderListResp resp = orderService.doctorOrderList(req);
		return JSON.toJSONString(resp);
	}

	@RequestMapping(value = "/anon/shop/order/list", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String shopOrderList(HttpServletRequest reqest) throws Exception {
		String reqJson = RequestUtil.getPostData(reqest);
		logger.info("req=" + reqJson);

		ShopOrderListReq req = JSON
				.parseObject(reqJson, ShopOrderListReq.class);
		ShopOrderListResp resp = orderService.shopOrderList(req);
		return JSON.toJSONString(resp);
	}
	
	@RequestMapping(value = "/anon/doctor/refund/list", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String doctorRefundList(HttpServletRequest request) throws Exception {
		Map<String, String> requestMap = RequestUtils.parameterToMap(request);
		logger.info("req=" + requestMap);
		
		DoctorRefundListResp resp = orderService.doctorRefundList(requestMap);
		return JSON.toJSONString(resp);
	}
}

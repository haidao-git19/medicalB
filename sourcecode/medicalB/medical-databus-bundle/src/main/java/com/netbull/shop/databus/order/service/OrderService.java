package com.netbull.shop.databus.order.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.netbull.shop.common.util.DateUtil;
import com.netbull.shop.common.util.HttpClientUtil;
import com.netbull.shop.databus.order.dao.OrderDao;
import com.netbull.shop.databus.order.dto.DoctorOrder;
import com.netbull.shop.databus.order.dto.DoctorOrderListReq;
import com.netbull.shop.databus.order.dto.DoctorOrderListResp;
import com.netbull.shop.databus.order.dto.PatientOrder;
import com.netbull.shop.databus.order.dto.PatientOrderListReq;
import com.netbull.shop.databus.order.dto.PatientOrderListResp;
import com.netbull.shop.databus.order.dto.PutServiceOrderReq;
import com.netbull.shop.databus.order.dto.PutServiceOrderResp;
import com.netbull.shop.databus.order.dto.ShopOrder;
import com.netbull.shop.databus.order.dto.ShopOrderListReq;
import com.netbull.shop.databus.order.dto.ShopOrderListResp;
import com.netbull.shop.databus.order.dto.TradeResp;
import com.netbull.shop.databus.order.dto.UpdateOrderPaymentReq;
import com.netbull.shop.databus.order.model.OrderBase;
import com.netbull.shop.databus.order.model.OrderPayment;
import com.netbull.shop.databus.order.model.ServiceOrder;
import com.netbull.shop.databus.order.util.OrderUtil;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.common.http.Resp;

@Service
public class OrderService {
	private static final Logger logger = Logger.getLogger("serviceLog");
	
	private static final String UNION_URL = "http://180.153.55.124:9999/pay/unionpay!getTnByMobile.do"; //TODO 写配置文件
	private static final int SHOP_SERVICE_TYPE = 4; //药店购买处方的订单类型
	private static final String SIGN_CODE_KEY = "code";
	private static final String SIGN_MSG_KEY = "msg";
	private static final String INVALID_MSG = "invalid sign";
	private static final String INVALID_SIGN_CODE = "403";
	private static final String VALID_SIGN_CODE = "200";
	private static final String SIGN_KEY = "123456";
	
	@Resource
	private OrderDao orderDao;
	
	public ServiceOrder findServiceOrder(String orderNumber) {
		return orderDao.findServiceOrder(orderNumber);
	}
	
	public PutServiceOrderResp putServiceOrder(PutServiceOrderReq req) {
		PutServiceOrderResp resp = new PutServiceOrderResp();
		
		try{
			//签名校验
			Map<String, String> rc = verfySign(req);
			if(INVALID_SIGN_CODE.equals(rc.get(SIGN_CODE_KEY))) {
				resp.setCode(rc.get(SIGN_CODE_KEY));
				resp.setMsg(rc.get(SIGN_MSG_KEY));
				return resp;
			}
			
			String orderNumber = OrderUtil.buildOrderNumber();
			
			OrderBase orderBase = new OrderBase();
			orderBase.setOrderNumber(orderNumber);
			orderBase.setTotalPrice(req.getFee());
			
			String rsp = HttpClientUtil.httpGet(UNION_URL, "order_no=" + orderNumber + "&txnAmt=" + req.getFee());
			TradeResp tradeResp = JSON.parseObject(rsp, TradeResp.class);
			
			ServiceOrder service = new ServiceOrder();
			service.setDoctorId(req.getDoctorId());
			service.setPatientId(req.getPatientId());
			service.setOrderNumber(orderNumber);
			service.setServiceType(req.getServiceType());
			service.setServiceNumber(req.getServiceNumber());
			service.setShopId(req.getShopId());
			service.setPrescriptionId(req.getPrescriptionId());
			service.setUnionTradeNumber(tradeResp.getTn());
			service.setUnionTradeState(tradeResp.getStat());
			service.setUnionTradeMsg(tradeResp.getMsg());
			
			OrderPayment orderPayment = new OrderPayment();
			orderPayment.setOrderNumber(orderNumber);
			orderPayment.setPayPrice(req.getFee());
			orderPayment.setPayMode(OrderPayment.ONLINE_PAY_MODE);
			orderPayment.setPayStatus(OrderPayment.UN_PAY);
			
			orderDao.saveOrderBase(orderBase);
			orderDao.saveServiceOrder(service);
			orderDao.saveOrderPayment(orderPayment);
			
			resp.setOrderNum(orderNumber);
			resp.setTradeNum(tradeResp.getTn());
			resp.setCode(Constants.SUCCESS);
			resp.setMsg(Constants.SUCCESS_MSG);
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}
		
		return resp;
	}
	
	private Map<String, String> verfySign(PutServiceOrderReq req) {
		StringBuilder src = new StringBuilder(100);
		if(req.getServiceType() == SHOP_SERVICE_TYPE) {
			src
			.append(req.getPatientId())
			.append(req.getShopId())
			.append(req.getPrescriptionId())
			.append(req.getServiceType())
			.append(req.getFee())
			.append(SIGN_KEY).toString();	
		}else{
			src
			.append(req.getPatientId())
			.append(req.getDoctorId())
			.append(req.getServiceType())
			.append(req.getServiceNumber())
			.append(req.getFee())
			.append(SIGN_KEY).toString();
		}
		
		String currSign = DigestUtils.md5Hex(src.toString());
		
		Map<String, String> rc = new HashMap<String, String>();
		if(!currSign.equalsIgnoreCase(req.getSign())) {
			rc.put(SIGN_CODE_KEY, INVALID_SIGN_CODE);
			rc.put(SIGN_MSG_KEY, INVALID_MSG);
		}else{
			rc.put(SIGN_CODE_KEY, VALID_SIGN_CODE);
		}
		return rc;
	}
	
	public Resp updateOrderPayment(UpdateOrderPaymentReq req) {
		Resp resp = new Resp();
		try{
			//签名校验
			Map<String, String> rc = checkSign(req);
			if(INVALID_SIGN_CODE.equals(rc.get(SIGN_CODE_KEY))) {
				resp.setCode(rc.get(SIGN_CODE_KEY));
				resp.setMsg(rc.get(SIGN_MSG_KEY));
				return resp;
			}
			
			OrderPayment orderPayment = new OrderPayment();
			orderPayment.setOrderNumber(req.getOrderNum());
			orderPayment.setPayStatus(OrderPayment.ALREADY_PAY);
			int rows = orderDao.updateOrderPayment(orderPayment);
			if(rows == 1) {
				resp.setCode(Constants.SUCCESS);
				resp.setMsg(Constants.SUCCESS_MSG);
			}else{
				resp.setCode(Constants.FAIL);
				resp.setMsg(Constants.FAIL_MSG);
			}
		}catch(Exception e) {
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}
		return resp;
	}
	
	private Map<String, String> checkSign(UpdateOrderPaymentReq req) {
		String src = new StringBuilder()
				.append(req.getOrderNum())
				.append(SIGN_KEY).toString();
		String currSign = DigestUtils.md5Hex(src);
		
		Map<String, String> rc = new HashMap<String, String>();
		if(!currSign.equalsIgnoreCase(req.getSign())) {
			rc.put(SIGN_CODE_KEY, INVALID_SIGN_CODE);
			rc.put(SIGN_MSG_KEY, INVALID_MSG);
		}else{
			rc.put(SIGN_CODE_KEY, VALID_SIGN_CODE);
		}
		return rc;
	}
	
	public PatientOrderListResp patientOrderList(PatientOrderListReq req) {
		PatientOrderListResp resp = new PatientOrderListResp();
		try{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("patientId", req.getPatientId());
			param.put("serviceType", req.getServiceType());
			param.put("pageSize", req.getPageSize());
			param.put("pageIndex", req.getPageSize() * (req.getPageNum() - 1));
			
			int totalCount = orderDao.patientOrderListTotalCount(param);
			List<PatientOrder> list = orderDao.patientOrderList(param);
			
			resp.setPageNum(req.getPageNum());
			resp.setPageSize(req.getPageSize());
			resp.setTotalCount(totalCount);
			resp.setList(list);
			resp.setCode(Constants.SUCCESS);
			resp.setMsg(Constants.SUCCESS_MSG);
		}catch(Exception e) {
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}
		return resp;
	}
	
	public DoctorOrderListResp doctorOrderList(DoctorOrderListReq req) {
		DoctorOrderListResp resp = new DoctorOrderListResp();
		try{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("doctorId", req.getDoctorId());
			param.put("serviceType", req.getServiceType());
			param.put("pageSize", req.getPageSize());
			param.put("pageIndex", req.getPageSize() * (req.getPageNum() - 1));
			param.put("firstDayOfMonth", DateUtil.fotmatDateYYYYMMDD(DateUtil.getFirstDayOfMonth(new Date())));
			
			int totalCount = orderDao.doctorOrderListTotalCount(param);
			List<DoctorOrder> list = orderDao.doctorOrderList(param);
			int totalIncome = orderDao.totalIncome(param);
			int recentMonthIncome = orderDao.recentMonthIncome(param);
			
			resp.setTotalIncome(totalIncome);
			resp.setRecentMonthIncome(recentMonthIncome);
			resp.setPageNum(req.getPageNum());
			resp.setPageSize(req.getPageSize());
			resp.setTotalCount(totalCount);
			resp.setList(list);
			resp.setCode(Constants.SUCCESS);
			resp.setMsg(Constants.SUCCESS_MSG);
		}catch(Exception e) {
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}
		return resp;
	}
	
	public ShopOrderListResp shopOrderList(ShopOrderListReq req) {
		ShopOrderListResp resp = new ShopOrderListResp();
		try{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("shopId", req.getShopId());
			param.put("serviceType", req.getServiceType());
			param.put("pageSize", req.getPageSize());
			param.put("pageIndex", req.getPageSize() * (req.getPageNum() - 1));
			
			int totalCount = orderDao.shopOrderListTotalCount(param);
			List<ShopOrder> list = orderDao.shopOrderList(param);
			
			resp.setPageNum(req.getPageNum());
			resp.setPageSize(req.getPageSize());
			resp.setTotalCount(totalCount);
			resp.setList(list);
			resp.setCode(Constants.SUCCESS);
			resp.setMsg(Constants.SUCCESS_MSG);
		}catch(Exception e) {
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}
		return resp;
	}
}

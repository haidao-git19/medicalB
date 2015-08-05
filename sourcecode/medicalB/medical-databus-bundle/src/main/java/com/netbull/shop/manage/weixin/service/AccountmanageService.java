package com.netbull.shop.manage.weixin.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.netbull.shop.common.util.DesEncrypt;
import com.netbull.shop.common.util.HttpClientUtil;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.databus.order.dao.OrderDao;
import com.netbull.shop.databus.order.dto.PatientOrder;
import com.netbull.shop.databus.order.dto.PatientOrderListResp;
import com.netbull.shop.databus.order.dto.PutServiceOrderReq;
import com.netbull.shop.databus.order.dto.TradeResp;
import com.netbull.shop.databus.order.model.OrderPayment;
import com.netbull.shop.databus.order.model.ServiceOrder;
import com.netbull.shop.databus.order.util.OrderUtil;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.common.http.Resp;
import com.netbull.shop.manage.weixin.dao.AccountDao;
import com.netbull.shop.manage.weixin.vo.Account;
import com.netbull.shop.manage.weixin.vo.AccountLog;
import com.netbull.shop.util.JsonUtils;

@Service
public class AccountmanageService {
	private static final Logger logger = Logger.getLogger("serviceLog");
	public final int ACC_ROLE_PATIENT = 0;
	public final int ACC_ROLE_DOC = 1;
	private static final String SIGN_KEY = "123456";
	private static final String SIGN_CODE_KEY = "code";
	private static final String SIGN_MSG_KEY = "msg";
	private static final String INVALID_MSG = "invalid sign";
	private static final String INVALID_SIGN_CODE = "403";
	private static final String VALID_SIGN_CODE = "200";
	
	private static final String REFUND_URL = "http://180.153.55.124:9999/pay/unionpay!postRefundByMobile.do";
	
	@Resource
	private AccountDao accountDao;
	@Resource
	private OrderDao orderDao;

	// 查询病人的账户ID查询账户的资金信息
	public Account queryPatientAccount(Long id) {
		// 查询病人的账户
		return accountDao.queryAccountByPatient(id);
	}

	// 退费服务
	@Transactional
	public Resp returnsFee(String serviceType, String serviceNumber,
			String patientID) {
		Resp resp = new Resp();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("serviceNumber", serviceNumber);
		param.put("serviceType", Integer.parseInt(serviceType));
		// 查询已经支付完成的支付订单
		OrderPayment payment = orderDao.findOrderPaymentByService(param);
		if (payment != null) {
			String refundNo = OrderUtil.buildOrderNumber();
			String rsp;
			try {
				Account acc = accountDao.queryAccountByPatient(Long.valueOf(patientID));
				rsp = HttpClientUtil.httpGet(REFUND_URL, "order_no="
						+ payment.getOrderNumber() + "&refund_no=" + refundNo);
				TradeResp tradeResp = JSON.parseObject(rsp, TradeResp.class);
				AccountLog accLog = new AccountLog();
				accLog.setAccId(acc.getAccId());
				accLog.setServiceNumber(serviceNumber);
				accLog.setServiceType(Integer.parseInt(serviceType));
				accLog.setTradeType(AccountLog.TradeType.REFUND);
				accLog.setOrderNumber(payment.getOrderNumber());
				accLog.setFee(payment.getPayPrice());
				accLog.setRefundNo(refundNo);
				if("1".equals(tradeResp.getStat())){
					accLog.setLogstate(1);
				}else{
					accLog.setLogstate(0);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		resp.setCode("0");
		resp.setMsg("ok");
		return resp;
//		Account acc = accountDao.queryAccountByPatient(Long.valueOf(patientID));
//		if (acc.validAcc()) {
//			// serviceType 1咨询 2 预约 3 加号 4 处方 5充值
//			logger.debug("账户可使用");
//			resp.setCode("0");
//			resp.setMsg("ok");
//			Map<String, Object> param = new HashMap<String, Object>();
//			param.put("serviceNumber", serviceNumber);
//			param.put("serviceType", Integer.parseInt(serviceType));
//			// 查询已经支付完成的支付订单
//			OrderPayment payment = orderDao.findOrderPaymentByService(param);
//			if (payment == null) {
//				logger.debug("没用可以退的费用");
//				return resp;
//			}
//			AccountLog accLog = new AccountLog();
//			accLog.setAccId(acc.getAccId());
//			accLog.setServiceNumber(serviceNumber);
//			accLog.setServiceType(Integer.parseInt(serviceType));
//			accLog.setTradeType(AccountLog.TradeType.REFUND);
//			accLog.setOrderNumber(payment.getOrderNumber());
//			accLog.setFee(payment.getPayPrice());
//			accLog.setLogstate(1);
//			Long availd = acc.getAvaildbalance();
//			Long balance = acc.getBalance();
//			balance += payment.getPayPrice();
//			availd += payment.getPayPrice();
//			acc.setAvaildbalance(availd);
//			acc.setBalance(balance);
//			String accounttext = DesEncrypt
//					.getEncryptString(Account.ACC_SALT_KEY + "-"
//							+ acc.getBalance() + "-" + acc.getAvaildbalance());
//			acc.setAccounttext(accounttext);
//			// 保存账户信息
//			accountDao.updateAccount(acc);
//			// 保存资金动态信息
//			accountDao.saveAccountLog(accLog);
//			// 将支付服务更改状态为 已退款
//			Map<String, Object> params = new HashMap<String, Object>();
//			params.put("refund", 1);
//			params.put("orderNumber", payment.getOrderNumber());
//			orderDao.updateRefundOrderService(params);
//		} else {
//			logger.error("账户不可使用");
//			resp.setCode("403");
//			resp.setMsg("invalid account");
//		}
//		return resp;
	}

	// 充值服务
	public String rechargeFee(ServiceOrder serviceOrder) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName()
						+ " class rechargeFee method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("orderNumber：" + serviceOrder.getOrderNumber());
				logger.debug("patientId：" + serviceOrder.getPatientId());
			}
			Account acc = accountDao.queryAccountByPatient(serviceOrder
					.getPatientId());
			if (acc.validAcc()) {
				AccountLog accLog = accountDao.queryAccLogReCharge(serviceOrder
						.getOrderNumber());
				accLog.setLogstate(1);
				Long availd = acc.getAvaildbalance();
				Long balance = acc.getBalance();
				balance += accLog.getFee();
				availd += accLog.getFee();
				acc.setAvaildbalance(availd);
				acc.setBalance(balance);
				String accounttext = DesEncrypt
						.getEncryptString(Account.ACC_SALT_KEY + "-"
								+ acc.getBalance() + "-"
								+ acc.getAvaildbalance());
				acc.setAccounttext(accounttext);
				// 保存账户信息
				accountDao.updateAccount(acc);
				// 保存资金动态信息
				accountDao.updateAccountLogState(accLog);
				resultMap.put("code", Constants.SUCCESS);
				resultMap.put("msg", Constants.SUCCESS_MSG);
			}
		} catch (Exception e) {
			logger.error("操作失败，原因：" + e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}

	/**
	 * 银联支付是保存资金动态为银联支付
	 * 
	 * @param acc2
	 * 
	 * @createTime: 2015年6月27日 下午8:54:50
	 * @history:
	 * @param serviceOrder
	 * @return Integer
	 */
	public Integer payAccountLog(ServiceOrder serviceOrder) {
		Account acc = accountDao.queryAccountByPatient(Long
				.valueOf(serviceOrder.getPatientId()));
		AccountLog accLog = new AccountLog();
		accLog.setAccId(acc.getAccId());
		accLog.setServiceNumber(serviceOrder.getServiceNumber());
		accLog.setServiceType(serviceOrder.getServiceType());
		accLog.setTradeType(AccountLog.TradeType.UNION_PAY);
		accLog.setOrderNumber(serviceOrder.getOrderNumber());
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderNumber", serviceOrder.getOrderNumber());
		// 查询已经支付完成的支付订单
		OrderPayment payment = orderDao.findOrderPaymentByService(param);
		accLog.setFee(payment.getPayPrice());
		accLog.setLogstate(1);
		return accountDao.saveAccountLog(accLog);
	}

	public Map<String, Object> queryAccLogList(Map<String, String> requestMap) {
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			Integer pageNum = Integer.parseInt(requestMap.get("pageNum"));
			Integer pageSize = Integer.parseInt(requestMap.get("pageSize"));
			requestMap.put("startIndex",
					String.valueOf((pageNum - 1) * pageSize));
//			requestMap.put("logstate",requestMap.get("logstate"));
			int totalCount = accountDao.queryAccLogListTotalCount(requestMap);
			List<AccountLog> list = accountDao.queryAccLogList(requestMap);
			resp.put("pageNum", pageNum);
			resp.put("pageSize", pageSize);
			resp.put("totalCount", totalCount);
			resp.put("list", list);
			resp.put("code", Constants.SUCCESS);
			resp.put("msg", Constants.SUCCESS_MSG);
		} catch (Exception e) {
			resp.put("code", Constants.FAIL);
			resp.put("msg", Constants.FAIL_MSG);
		}
		return resp;
	}
	
	private Map<String, String> verfySign(Map<String, String> requestMap) {
		StringBuilder src = new StringBuilder(100);
		src.append(requestMap.get("patientId")).append(requestMap.get("bankACC"));
		src.append(requestMap.get("fee")).append(requestMap.get("bankUserName")).append(SIGN_KEY);
		String currSign = DigestUtils.md5Hex(src.toString());
		Map<String, String> rc = new HashMap<String, String>();
		
		if (!currSign.equalsIgnoreCase(requestMap.get("sign"))) {
			rc.put(SIGN_CODE_KEY, INVALID_SIGN_CODE);
			rc.put(SIGN_MSG_KEY, INVALID_MSG);
		} else {
			rc.put(SIGN_CODE_KEY, VALID_SIGN_CODE);
		}
		return rc;
	}

	@Transactional
	public Resp saveApplyCash(Map<String, String> requestMap) {
		Resp resp = new Resp();
		// 签名校验
		Map<String, String> rc = verfySign(requestMap);
		if (INVALID_SIGN_CODE.equals(rc.get(SIGN_CODE_KEY))) {
			resp.setCode(rc.get(SIGN_CODE_KEY));
			resp.setMsg(rc.get(SIGN_MSG_KEY));
			return resp;
		}
		Account acc = accountDao.queryAccountByPatient(Long.valueOf(requestMap
				.get("patientId")));
		if (acc.validAcc()) {
			AccountLog accLog = new AccountLog();
			accLog.setAccId(acc.getAccId());
			accLog.setTradeType(AccountLog.TradeType.GET_CASH);
			// 银行账号
			accLog.setBankACC(requestMap.get("bankACC"));
			// 银行名称
			accLog.setTradebank(requestMap.get("tradebank"));
			Integer f = Integer.parseInt(requestMap.get("fee"));
			accLog.setBankUserName(requestMap.get("bankUserName"));
			accLog.setBranchBank(requestMap.get("branchBank"));
			accLog.setFee(f);
			accLog.setLogstate(0);
			// 余额不足
			if (acc.getAvaildbalance() - f < 0) {
				resp.setCode("400");
				resp.setMsg("余额不足");
			} else {
				Long availd = acc.getAvaildbalance();
				Long balance = acc.getBalance();
				balance -= f;
				availd -= f;
				acc.setAvaildbalance(availd);
				acc.setBalance(balance);
				String accounttext = DesEncrypt
						.getEncryptString(Account.ACC_SALT_KEY + "-"
								+ acc.getBalance() + "-"
								+ acc.getAvaildbalance());
				acc.setAccounttext(accounttext);
				// 保存账户信息
				accountDao.updateAccount(acc);
				// 保存资金动态信息
				accountDao.saveAccountLog(accLog);
				resp.setCode("0");
				resp.setMsg("成功");
			}
		} else {
			resp.setCode("403");
			resp.setMsg("invalid account");
		}
		return resp;
	}

}

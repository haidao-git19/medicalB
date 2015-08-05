package com.netbull.shop.databus.order.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.netbull.shop.common.util.DateUtil;
import com.netbull.shop.common.util.DesEncrypt;
import com.netbull.shop.common.util.HttpClientUtil;
import com.netbull.shop.databus.order.dao.OrderDao;
import com.netbull.shop.databus.order.dto.DoctorOrder;
import com.netbull.shop.databus.order.dto.DoctorOrderListReq;
import com.netbull.shop.databus.order.dto.DoctorOrderListResp;
import com.netbull.shop.databus.order.dto.DoctorRefundListResp;
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
import com.netbull.shop.manage.weixin.dao.AccountDao;
import com.netbull.shop.manage.weixin.service.BusinessPayService;
import com.netbull.shop.manage.weixin.vo.Account;
import com.netbull.shop.manage.weixin.vo.AccountLog;

@Service
public class OrderService {
	private static final Logger logger = Logger.getLogger("serviceLog");

	private static final String UNION_URL = "http://180.153.55.124:9999/pay/unionpay!getTnByMobile.do"; // TODO
																										// 写配置文件
	private static final int SHOP_SERVICE_TYPE = 4; // 药店购买处方的订单类型
	private static final String SIGN_CODE_KEY = "code";
	private static final String SIGN_MSG_KEY = "msg";
	private static final String INVALID_MSG = "invalid sign";
	private static final String INVALID_SIGN_CODE = "403";
	private static final String VALID_SIGN_CODE = "200";
	private static final String SIGN_KEY = "123456";
	@Resource
	private BusinessPayService businessPayService;
	@Resource
	private OrderDao orderDao;
	@Resource
	private AccountDao accountDao;

	public ServiceOrder findServiceOrder(String orderNumber) {
		return orderDao.findServiceOrder(orderNumber);
	}

	public PutServiceOrderResp putServiceOrder(PutServiceOrderReq req) {
		PutServiceOrderResp resp = new PutServiceOrderResp();

		try {
			// 签名校验
			Map<String, String> rc = verfySign(req);
			if (INVALID_SIGN_CODE.equals(rc.get(SIGN_CODE_KEY))) {
				resp.setCode(rc.get(SIGN_CODE_KEY));
				resp.setMsg(rc.get(SIGN_MSG_KEY));
				return resp;
			}

			String orderNumber = OrderUtil.buildOrderNumber();

			// 余额支付
			int accpay = req.getAccpay();
			int accpayfee = 0;
			int un_payfee = req.getFee();
			if (accpay == 1 && req.getServiceType() != 6) {
				accpayfee = req.getAccpayfee();
				un_payfee = un_payfee - accpayfee;
				Account acc = accountDao.queryAccountByPatient(req
						.getPatientId());
				if (acc != null && acc.validAcc()) {
					if (acc.getAvaildbalance() - accpayfee < 0) {
						resp = new PutServiceOrderResp();
						resp.setCode("400");
						resp.setMsg("余额不足");
						return resp;
					} else {
						// 保存余额支付方式订单
						OrderPayment orderPayment = new OrderPayment();
						orderPayment.setOrderNumber(orderNumber);
						orderPayment.setPayPrice(req.getAccpayfee());
						orderPayment.setPayMode(OrderPayment.ACC_PAY_MODE);
						if (un_payfee > 0) {
							orderPayment.setPayStatus(OrderPayment.UN_PAY);
						} else {
							orderPayment.setPayStatus(OrderPayment.UN_PAY);
							businessPayService.modifyBizStatus(
									req.getServiceType() + "",
									req.getServiceNumber(), "0");
						}
						orderDao.saveOrderPayment(orderPayment);
						// 冻结支付余额
						accpay(acc, req, orderPayment, un_payfee);
					}
				} else {
					resp = new PutServiceOrderResp();
					resp.setCode("403");
					resp.setMsg("invalid account");
					return resp;
				}
			}

			OrderBase orderBase = new OrderBase();
			orderBase.setOrderNumber(orderNumber);
			orderBase.setTotalPrice(req.getFee());
			ServiceOrder service = new ServiceOrder();
			service.setDoctorId(req.getDoctorId());
			service.setPatientId(req.getPatientId());
			service.setOrderNumber(orderNumber);
			service.setServiceType(req.getServiceType());
			service.setServiceNumber(req.getServiceNumber());
			service.setShopId(req.getShopId());
			service.setPrescriptionId(req.getPrescriptionId());
			// 如果全部余额支付的话，不进行银联支付
			if (un_payfee > 0) {
				String rsp = HttpClientUtil.httpGet(UNION_URL, "order_no="
						+ orderNumber + "&txnAmt=" + un_payfee);
				TradeResp tradeResp = JSON.parseObject(rsp, TradeResp.class);

				service.setUnionTradeNumber(tradeResp.getTn());
				service.setUnionTradeState(tradeResp.getStat());
				service.setUnionTradeMsg(tradeResp.getMsg());
				resp.setTradeNum(tradeResp.getTn());
				// 判断进行充值的业务
				if (req.getServiceType() == 6) {
					Account acc = accountDao.queryAccountByPatient(req
							.getPatientId());
					if (acc != null && acc.validAcc()) {
						payAccountLog(req, acc.getAccId(), orderNumber);
					} else {
						resp = new PutServiceOrderResp();
						resp.setCode("403");
						resp.setMsg("invalid account");
						return resp;
					}
				}

				OrderPayment orderPayment = new OrderPayment();
				orderPayment.setOrderNumber(orderNumber);
				orderPayment.setPayPrice(un_payfee);
				orderPayment.setPayMode(OrderPayment.ONLINE_PAY_MODE);
				orderPayment.setPayStatus(OrderPayment.UN_PAY);
				orderDao.saveOrderPayment(orderPayment);
			}

			orderDao.saveOrderBase(orderBase);
			orderDao.saveServiceOrder(service);
			resp.setOrderNum(orderNumber);

			resp.setCode(Constants.SUCCESS);
			resp.setMsg(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}

		return resp;
	}

	/**
	 * 账户余额扣减和资金动态保存
	 * 
	 * @createTime: 2015年6月30日 下午10:36:08
	 * @history:
	 * @param acc
	 * @param orderPayment
	 *            void
	 * @param un_payfee
	 */
	@Transactional
	private void accpay(Account acc, PutServiceOrderReq req,
			OrderPayment orderPayment, int un_payfee) {
		AccountLog accLog = new AccountLog();
		accLog.setAccId(acc.getAccId());
		accLog.setServiceNumber(req.getServiceNumber());
		accLog.setServiceType(req.getServiceType());
		accLog.setTradeType(AccountLog.TradeType.ACC_PAY);
		accLog.setOrderNumber(orderPayment.getOrderNumber());
		accLog.setFee(orderPayment.getPayPrice());
		if (un_payfee > 0) {
			accLog.setLogstate(0);
		} else {
			accLog.setLogstate(1);
		}
		Long availd = acc.getAvaildbalance();
		// balance -= orderPayment.getPayPrice();
		availd -= orderPayment.getPayPrice();
		acc.setAvaildbalance(availd);
		String accounttext = DesEncrypt.getEncryptString(Account.ACC_SALT_KEY
				+ "-" + acc.getBalance() + "-" + acc.getAvaildbalance());
		acc.setAccounttext(accounttext);
		// 保存账户信息
		accountDao.updateAccount(acc);
		// 保存资金动态信息
		accountDao.saveAccountLog(accLog);
	}

	/**
	 * 充值动态
	 */
	private Integer payAccountLog(PutServiceOrderReq req, Long accId,
			String orderNumber) {
		AccountLog accLog = new AccountLog();
		accLog.setAccId(accId);
		accLog.setServiceNumber(req.getServiceNumber());
		accLog.setServiceType(req.getServiceType());
		accLog.setTradeType(AccountLog.TradeType.RECHARGE);
		accLog.setOrderNumber(orderNumber);
		accLog.setFee(req.getFee());
		accLog.setLogstate(0);
		return accountDao.saveAccountLog(accLog);
	}

	private Map<String, String> verfySign(PutServiceOrderReq req) {
		StringBuilder src = new StringBuilder(100);
		if (req.getServiceType() == SHOP_SERVICE_TYPE) {
			src.append(req.getPatientId()).append(req.getShopId())
					.append(req.getPrescriptionId())
					.append(req.getServiceType()).append(req.getFee())
					.append(SIGN_KEY).toString();
		} else {
			src.append(req.getPatientId()).append(req.getDoctorId())
					.append(req.getServiceType())
					.append(req.getServiceNumber()).append(req.getFee())
					.append(SIGN_KEY).toString();
		}

		String currSign = DigestUtils.md5Hex(src.toString());

		Map<String, String> rc = new HashMap<String, String>();
		if (!currSign.equalsIgnoreCase(req.getSign())) {
			rc.put(SIGN_CODE_KEY, INVALID_SIGN_CODE);
			rc.put(SIGN_MSG_KEY, INVALID_MSG);
		} else {
			rc.put(SIGN_CODE_KEY, VALID_SIGN_CODE);
		}
		return rc;
	}

	@Transactional
	public Resp updateOrderPayment(UpdateOrderPaymentReq req) {
		Resp resp = new Resp();
		try {
			// 签名校验
			Map<String, String> rc = checkSign(req);
			if (INVALID_SIGN_CODE.equals(rc.get(SIGN_CODE_KEY))) {
				resp.setCode(rc.get(SIGN_CODE_KEY));
				resp.setMsg(rc.get(SIGN_MSG_KEY));
				return resp;
			}

			OrderPayment orderPayment = new OrderPayment();
			orderPayment.setOrderNumber(req.getOrderNum());
			orderPayment.setPayStatus(OrderPayment.ALREADY_PAY);
			int rows = orderDao.updateOrderPayment(orderPayment);
			// 设置资金为支付完成状态 支付时
			AccountLog accLog = accountDao.queryAccLogByOrderNumber(req
					.getOrderNum());
			if (accLog != null) {
				accLog.setLogstate(1);
				accountDao.updateAccountLogState(accLog);
				Account acc = accountDao.queryAccountByAccId(accLog.getAccId());
				acc.setBalance(acc.getBalance() - accLog.getFee());
				String accounttext = DesEncrypt
						.getEncryptString(Account.ACC_SALT_KEY + "-"
								+ acc.getBalance() + "-"
								+ acc.getAvaildbalance());
				acc.setAccounttext(accounttext);
				accountDao.updateAccount(acc);
			}
			if (rows >= 1) {
				resp.setCode(Constants.SUCCESS);
				resp.setMsg(Constants.SUCCESS_MSG);
			} else {
				resp.setCode(Constants.FAIL);
				resp.setMsg(Constants.FAIL_MSG);
			}
		} catch (Exception e) {
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}
		return resp;
	}

	private Map<String, String> checkSign(UpdateOrderPaymentReq req) {
		String src = new StringBuilder().append(req.getOrderNum())
				.append(SIGN_KEY).toString();
		String currSign = DigestUtils.md5Hex(src);

		Map<String, String> rc = new HashMap<String, String>();
		if (!currSign.equalsIgnoreCase(req.getSign())) {
			rc.put(SIGN_CODE_KEY, INVALID_SIGN_CODE);
			rc.put(SIGN_MSG_KEY, INVALID_MSG);
		} else {
			rc.put(SIGN_CODE_KEY, VALID_SIGN_CODE);
		}
		return rc;
	}

	public PatientOrderListResp patientOrderList(PatientOrderListReq req) {
		PatientOrderListResp resp = new PatientOrderListResp();
		try {
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
		} catch (Exception e) {
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}
		return resp;
	}

	public DoctorOrderListResp doctorOrderList(DoctorOrderListReq req) {
		DoctorOrderListResp resp = new DoctorOrderListResp();
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("doctorId", req.getDoctorId());
			param.put("serviceType", req.getServiceType());
			param.put("pageSize", req.getPageSize());
			param.put("pageIndex", req.getPageSize() * (req.getPageNum() - 1));
			param.put("firstDayOfMonth", DateUtil.fotmatDateYYYYMMDD(DateUtil
					.getFirstDayOfMonth(new Date())));

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
		} catch (Exception e) {
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}
		return resp;
	}

	public ShopOrderListResp shopOrderList(ShopOrderListReq req) {
		ShopOrderListResp resp = new ShopOrderListResp();
		try {
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
		} catch (Exception e) {
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}
		return resp;
	}

	public DoctorRefundListResp doctorRefundList(Map<String, String> requestMap) {
		DoctorRefundListResp resp = new DoctorRefundListResp();
		try {
			Integer pageNum = Integer.parseInt(requestMap.get("pageNum"));
			Integer pageSize = Integer.parseInt(requestMap.get("pageSize"));
			requestMap.put("pageIndex",
					String.valueOf(pageSize * (pageNum - 1)));
			int totalCount = orderDao.findReFundServiceTotal(requestMap);
			List<Map<String, Object>> list = orderDao
					.findReFundService(requestMap);

			resp.setPageNum(pageNum);
			resp.setPageSize(pageSize);
			resp.setTotalCount(totalCount);
			resp.setList(list);
			resp.setCode(Constants.SUCCESS);
			resp.setMsg(Constants.SUCCESS_MSG);
		} catch (Exception e) {
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}
		return resp;
	}
}

package com.netbull.shop.manage.weixin.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.weixin.vo.ReservationVo;
import com.netbull.shop.util.JsonUtils;

@Service
public class BusinessPayService {
	private static final Logger logger = Logger.getLogger("controlerLog");

	@Resource
	private ConsultationService consultationService;
	@Autowired
	private SubscribeService subscribeService;
	@Autowired
	private PlusService plusService;

	@Autowired
	private MonthServiceService monthServiceService;
	@Autowired
	private ReservationService reservationService;

	public String modifyBizStatus(String bizType, String bizCode,
			String bizState) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName()
						+ " class modifyBizStatus method.");
			}

			if (logger.isDebugEnabled()) {
				logger.debug("bizType：" + bizType);
				logger.debug("bizCode：" + bizCode);
				logger.debug("bizState：" + bizState);
			}

			if (NullUtil.isNull(bizType) || NullUtil.isNull(bizCode)) {
				resultMap.put("code", Constants.FAIL_2);
				resultMap.put("msg", Constants.FAIL_MSG_8);
				return JsonUtils.obj2Json(resultMap);
			}

			if (Constants.CONSULATOIN_PAY_TYPE.equals(bizType)) {
				this.modifyConsultation(bizCode, bizState);
			} else if (Constants.SUBSCRIBE_PAY_TYPE.equals(bizType)) {
				this.modifySubscribe(bizCode, bizState);
			} else if (Constants.PLUS_PAY_TYPE.equals(bizType)) {
				this.modifyPlus(bizCode, bizState);
			} else if (Constants.MONTHSERVICE_PAY_TYPE.equals(bizType)) {
				this.modifyMonthService(bizCode, bizState);
			} else if (Constants.HOMESERVICE_PAY_TYPE.equals(bizType)) {
				this.modifyHomeService(bizCode, bizState);
			}

			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
		} catch (Exception e) {
			logger.error("操作失败，原因：" + e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}

	private void modifyHomeService(String bizCode, String bizState) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap.put("consultationID", bizCode);
		// requestMap
		// .put("paystate",
		// !NullUtil.isNull(bizState)
		// && Constants.SUCCESS.equals(bizState) ?
		// Constants.CONSULATOIN_PAY_STATE_FINISH
		// : Constants.CONSULATOIN_PAY_STATE_FAIL);
		Integer paystate = !NullUtil.isNull(bizState)
				&& Constants.SUCCESS.equals(bizState) ? 1 : 2;
		ReservationVo reservationVo = new ReservationVo();
		Integer id = Integer.parseInt((String) requestMap.get("id"));
		reservationVo.setId(id);
		reservationVo.setPaystatus(paystate);
		this.reservationService.modifyReservation(reservationVo);
	}

	private void modifyConsultation(String bizCode, String bizState) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap.put("consultationID", bizCode);
		requestMap
				.put("paystate",
						!NullUtil.isNull(bizState)
								&& Constants.SUCCESS.equals(bizState) ? Constants.CONSULATOIN_PAY_STATE_FINISH
								: Constants.CONSULATOIN_PAY_STATE_FAIL);

		this.consultationService.modifyConsultation(requestMap);
	}

	private void modifySubscribe(String bizCode, String bizState) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap.put("orderID", bizCode);
		requestMap
				.put("paystate",
						!NullUtil.isNull(bizState)
								&& Constants.SUCCESS.equals(bizState) ? Constants.SUBSCRIBE_PAY_STATE_FINISH
								: Constants.SUBSCRIBE_PAY_STATE_FAIL);

		this.subscribeService.modifyOrder(requestMap);
	}

	private void modifyPlus(String bizCode, String bizState) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap.put("plusID", bizCode);
		requestMap
				.put("paystate",
						!NullUtil.isNull(bizState)
								&& Constants.SUCCESS.equals(bizState) ? Constants.SUBSCRIBE_PAY_STATE_FINISH
								: Constants.SUBSCRIBE_PAY_STATE_FAIL);

		this.plusService.updatePlus(requestMap);
	}

	private void modifyMonthService(String bizCode, String bizState) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap.put("msid", bizCode);
		requestMap
				.put("payState",
						!NullUtil.isNull(bizState)
								&& Constants.SUCCESS.equals(bizState) ? Constants.SUBSCRIBE_PAY_STATE_FINISH
								: Constants.SUBSCRIBE_PAY_STATE_FAIL);

		this.monthServiceService.updateMonthPayState(requestMap);
	}

}

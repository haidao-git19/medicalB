package com.netbull.shop.manage.weixin.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.common.http.Resp;
import com.netbull.shop.manage.weixin.dao.MonthServiceDao;
import com.netbull.shop.manage.weixin.vo.MonthService;
import com.netbull.shop.util.JsonUtils;

@Service
public class MonthServiceService {
	private static final Logger logger = Logger.getLogger("controlerLog");
	@Autowired
	private MonthServiceDao monthServiceDao;

	@Resource
	private ConsultationService consultationService;
	@Autowired
	private SubscribeService subscribeService;
	@Autowired
	private PlusService plusService;

	public Map<String,Object> saveMonthService(Map<String, String> requestMap) {
		Map<String,Object> resultMap=new HashMap<String,Object>();
		// 本月已经包月 不能再包月
		MonthService monthService = monthServiceDao.findAreadyMonth(requestMap);
		if (monthService != null) {
			resultMap.put("code","200");
			resultMap.put("msg","该时间段已经包月，包月时间为："+monthService.getBeginDate()+"到"+monthService.getEndDate());
			return resultMap;
		}

		monthServiceDao.saveMonthService(requestMap);
		resultMap.put("code","0");
		resultMap.put("msg","成功");
		resultMap.put("sourceID",requestMap.get("msid"));
		return resultMap;
	}

	public MonthService querymonthService(Map<String, String> requestMap) {
		return monthServiceDao.queryMonthService(requestMap);
	}

	public void updateMonthPayState(Map<String, Object> requestMap) {
		monthServiceDao.updateMonthPayState(requestMap);
	}

	public Map<String, Object> modifyBizStatus(String bizType, String bizCode,
			String bizState) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("bizType：" + bizType);
				logger.debug("bizCode：" + bizCode);
				logger.debug("bizState：" + bizState);
			}
			if (NullUtil.isNull(bizType) || NullUtil.isNull(bizCode)) {
				resultMap.put("code", Constants.FAIL_2);
				resultMap.put("msg", Constants.FAIL_MSG_8);
				return resultMap;
			}

			if (Constants.CONSULATOIN_PAY_TYPE.equals(bizType)) {
				this.modifyConsultation(bizCode, bizState);
			} else if (Constants.SUBSCRIBE_PAY_TYPE.equals(bizType)) {
				this.modifySubscribe(bizCode, bizState);
			} else if (Constants.PLUS_PAY_TYPE.equals(bizType)) {
				this.modifyPlus(bizCode, bizState);
			}

			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("ismonthPay", 1);
		} catch (Exception e) {
			logger.error("操作失败，原因：" + e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return resultMap;
	}

	private void modifyConsultation(String bizCode, String bizState) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap.put("consultationID", bizCode);
		requestMap.put("paystate", Constants.MONTH_SERVICE_PAY_STATE_FINISH);
		this.consultationService.modifyConsultation(requestMap);
	}

	private void modifySubscribe(String bizCode, String bizState) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap.put("orderID", bizCode);
		requestMap.put("paystate", Constants.MONTH_SERVICE_PAY_STATE_FINISH);
		this.subscribeService.modifyOrder(requestMap);
	}

	private void modifyPlus(String bizCode, String bizState) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap.put("plusID", bizCode);
		requestMap.put("paystate", Constants.MONTH_SERVICE_PAY_STATE_FINISH);

		this.plusService.updatePlus(requestMap);
	}

}

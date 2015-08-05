package com.netbull.shop.databus.prescription.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.netbull.shop.databus.prescription.dao.FetchRecordDao;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.common.http.Resp;
import com.netbull.shop.manage.weixin.vo.AccountLog;

@Service("fetchRecordService")
public class FetchRecordService {
	private static final String SIGN_CODE_KEY = "code";
	private static final String SIGN_MSG_KEY = "msg";
	private static final String INVALID_MSG = "invalid sign";
	private static final String INVALID_SIGN_CODE = "403";
	private static final String VALID_SIGN_CODE = "200";
	private static final String SIGN_KEY = "123456";

	@Resource
	private FetchRecordDao fetchRecordDao;

	public Resp saveRecord(Map<String, String> requestMap) {
		/**
		 * 0,msg 成功 500 已经购买 403 未找到购买记录 保存购要记录
		 * orderNumber,sign=patientIdorderNumber123456
		 */
		Resp resp = new Resp();
		// 签名校验
//		Map<String, String> rc = verfySign(requestMap);
//		if (INVALID_SIGN_CODE.equals(rc.get(SIGN_CODE_KEY))) {
//			resp.setCode(rc.get(SIGN_CODE_KEY));
//			resp.setMsg(rc.get(SIGN_MSG_KEY));
//			return resp;
//		}
		Map result = fetchRecordDao.findFetchRecord(requestMap);
		if (result != null) {
			resp.setCode("500");
			resp.setMsg("该订单已经取药");
			return resp;
		}

		fetchRecordDao.saveRecord(requestMap);

		resp.setCode("0");
		resp.setMsg("success");
		return resp;
	}

	private Map<String, String> verfySign(Map<String, String> requestMap) {
		StringBuilder src = new StringBuilder(100);
		src.append(requestMap.get("patientId"))
				.append(requestMap.get("prescriptionId")).append(SIGN_KEY);
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
}

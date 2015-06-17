package com.netbull.shop.manage.weixin.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.netbull.shop.common.util.MapClone;
import com.netbull.shop.manage.weixin.dao.AliPayAuthDao;

@Service
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AlipayAuthService {
	private static final Log log = LogFactory.getLog(AlipayAuthService.class);
    
	@Resource
	private AliPayAuthDao aliPayAuthDao;

	/**
	 * 添加请求订单记录
	 * @param paramter
	 * @return
	 */
	public Integer saveAliPayAuthReq(Map paramter,String seller_email,String out_trade_no,String subject,String total_fee) {
		Map requestMap = MapClone.clone(paramter);
		requestMap.put("seller_account_name", seller_email);
		requestMap.put("out_trade_no", out_trade_no);
		requestMap.put("subject", subject);
		requestMap.put("total_fee", total_fee);
		return aliPayAuthDao.saveAliPayAuthReq(requestMap);
	}
	
	/**
	 * 添加返回订单记录
	 * @param paramter
	 * @return
	 */
	public Integer saveAliPayAuthResp(Map paramter) {		
		return aliPayAuthDao.saveAliPayAuthResp(paramter);
	}
	
	/**
	 * 更新返回订单记录
	 * @param paramter
	 * @return
	 */
	public Integer modifyAliAuthPayResp(Map paramter) {		
		return aliPayAuthDao.modifyAliAuthPayResp(paramter);
	}
	
}

package com.netbull.shop.manage.weixin.service;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.netbull.shop.common.util.MapClone;
import com.netbull.shop.manage.weixin.dao.AliPayTransDao;

@Service
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AlipayTransService {
	private static final Log log = LogFactory.getLog(AlipayTransService.class);
    
	@Resource
	private AliPayTransDao aliPayTransDao;

	/**
	 * 添加请求订单记录
	 * @param paramter
	 * @return
	 */
	public Integer saveAliPayTransReq(Map paramter,String request_token,String out_trade_no) {
		Map requestMap = MapClone.clone(paramter);
		requestMap.put("request_token", request_token);
		requestMap.put("out_trade_no", out_trade_no);
		return aliPayTransDao.saveAliPayTransReq(paramter);
	}
	
	/**
	 * 添加返回订单记录
	 * @param paramter
	 * @return
	 */
	public Integer saveAliPayTransResp(Map paramter) {		
		return aliPayTransDao.saveAliPayTransResp(paramter);
	}
	
	/**
	 * 更新返回订单记录
	 * @param paramter
	 * @return
	 */
	public Integer modifyAliTransPayResp(Map paramter) {		
		return aliPayTransDao.modifyAliTransPayResp(paramter);
	}
}

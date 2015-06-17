package com.netbull.shop.manage.weixin.service;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.netbull.shop.manage.weixin.dao.WXPayRecordDao;

@Service
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WXPayRecordService {
	private static final Log log = LogFactory.getLog(WXPayRecordService.class);
    
	@Resource
	private WXPayRecordDao wxPayRecordDao;

	/**
	 * 查询订单返回数
	 * @param paramter
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer queryWXPayRespCount(Map paramter) {		
		return wxPayRecordDao.queryWXPayRespCount(paramter);
	}
	
	/**
	 * 查询订单返回记录
	 * @param paramter
	 * @return
	 */
	public Map<String,Object> queryWXPayRespRecord(Map paramter) {		
		return wxPayRecordDao.queryWXPayRespRecord(paramter);
	}
	
	/**
	 * 添加请求订单记录
	 * @param paramter
	 * @return
	 */
	public Integer saveWXPayReqRecord(Map paramter) {		
		return wxPayRecordDao.saveWXPayReqRecord(paramter);
	}
	
	/**
	 * 添加返回订单记录
	 * @param paramter
	 * @return
	 */
	public Integer saveWXPayRespRecord(Map paramter) {		
		return wxPayRecordDao.saveWXPayRespRecord(paramter);
	}
	
	/**
	 * 更新返回订单记录
	 * @param paramter
	 * @return
	 */
	public Integer modifyWXPayRespRecord(Map paramter) {		
		return wxPayRecordDao.modifyWXPayRespRecord(paramter);
	}
}

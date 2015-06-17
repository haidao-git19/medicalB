package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.vo.SellRecordVo;
import com.netbull.shop.manage.weixin.dao.SellRecordDao;
import com.netbull.shop.util.MyBatisDao;

@Service
public class SellRecordService extends MyBatisDao<SellRecordVo, Integer>{

	@Autowired
	private SellRecordDao sellRecordDao;
	
	/**
	 * 分页查询订单记录
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param requestMap
	 * @return
	 */
	public Page querySellRecordsPage(Integer iDisplayStart, Integer iDisplayLength,Map<String, String> requestMap){
		return sellRecordDao.queryPage(iDisplayStart, iDisplayLength, requestMap);
	}
	
	/**
	 * 查询已支付订单
	 * @param map
	 * @return
	 */
	public List<SellRecordVo> queryPayedSellRecord(Map<String,String> map){
		return sellRecordDao.queryByParams(map);
	}
	
	/**
	 * 更新订单状态
	 * @param map
	 * @return
	 */
	public Integer updateStatus(Map<String,String> map){
		return sellRecordDao.updateStatus(map);
	}
}

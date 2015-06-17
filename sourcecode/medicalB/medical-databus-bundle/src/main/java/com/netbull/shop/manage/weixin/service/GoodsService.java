package com.netbull.shop.manage.weixin.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.netbull.shop.manage.weixin.dao.GoodsDao;

@Service
@SuppressWarnings({ "unchecked", "rawtypes" })
public class GoodsService {
	private static final Log log = LogFactory.getLog(GoodsService.class);
    
	@Resource
	private GoodsDao goodsDao;

	/**
	 * 分页查询商品列表
	 * @param paramter
	 * @return
	 */
	public List<Map> queryGoods(Map paramter) throws RuntimeException{
		return goodsDao.queryGoods(paramter);
	}

	public Map queryGoodsDetail(Map paramter) {		
		return goodsDao.queryGoodsDetail(paramter);
	}
	
	/**
	 * 修改信息
	 * @param paramter
	 * @return
	 */
	public Integer modifyGoodReport(Map paramter) {		
		return goodsDao.modifyGoodReport(paramter);
	}
	
}

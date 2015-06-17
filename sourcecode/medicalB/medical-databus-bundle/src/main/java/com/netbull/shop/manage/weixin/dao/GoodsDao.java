package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.netbull.shop.common.dao.SqlSession;

@Repository
public class GoodsDao {
	private static final String MYBATIS_PREFIX = GoodsDao.class.getName();
	
	@Resource
	private SqlSession session;
    
	/**
	 * 分页查询商品列表
	 * @param paramter
	 * @return
	 */
	public List<Map> queryGoods(Map paramter) {		
		List<Map> list = session.selectList(MYBATIS_PREFIX + ".queryGoods",paramter);
		return list;
	}
	
	public Map queryGoodsDetail(Map paramter) {		
		Map goods = session.selectOne(MYBATIS_PREFIX + ".queryGoodsDetail",paramter);
		return goods;
	}
	/**
	 * 修改信息
	 * @param paramter
	 * @return
	 */
	public Integer modifyGoodReport(Map paramter) {		
		return session.update(MYBATIS_PREFIX + ".modifyGoodReport",paramter);
	}
	
}

package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.vo.SellRecordVo;

@Repository
public class SellRecordDao {
	
	private static final String MYBATIS_PREFIX=SellRecordDao.class.getName();

	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * 分页查询订单记录
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param requestMap
	 * @return
	 */
	public Page queryPage(Integer iDisplayStart, Integer iDisplayLength,Map<String, String> requestMap){ 
		return sqlSession.page(MYBATIS_PREFIX + ".queryList", MYBATIS_PREFIX+".queryCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	/**
	 * 查询已支付订单
	 * @param map
	 * @return
	 */
	public List<SellRecordVo> queryByParams(Map<String,String> map){
		return sqlSession.selectList(MYBATIS_PREFIX+".queryByParams", map);
	}
	
	/**
	 * 更新订单状态
	 * @param map
	 * @return
	 */
	public Integer updateStatus(Map<String,String> map){
		return sqlSession.update(MYBATIS_PREFIX+".updateStatus", map);
	}
}

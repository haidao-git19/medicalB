package com.netbull.shop.manage.weixin.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.vo.UserVo;

import com.netbull.shop.common.dao.SqlSession;

@Repository
public class WXPayRecordDao {
	private static final String MYBATIS_PREFIX = WXPayRecordDao.class.getName();
	
	@Resource
	private SqlSession session;
    
	/**
	 * 查询订单返回数
	 * @param paramter
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Integer queryWXPayRespCount(Map paramter) {		
		Integer count = session.selectOne(MYBATIS_PREFIX + ".queryWXPayRespCount",paramter);
		return count;
	}
	
	/**
	 * 查询订单返回记录
	 * @param paramter
	 * @return
	 */
	public Map<String,Object> queryWXPayRespRecord(Map paramter) {		
		return session.selectOne(MYBATIS_PREFIX + ".queryWXPayRespRecord",paramter);
	}
	
	/**
	 * 添加请求订单记录
	 * @param paramter
	 * @return
	 */
	public Integer saveWXPayReqRecord(Map paramter) {		
		return session.update(MYBATIS_PREFIX + ".saveWXPayReqRecord",paramter);
	}
	
	/**
	 * 添加返回订单记录
	 * @param paramter
	 * @return
	 */
	public Integer saveWXPayRespRecord(Map paramter) {		
		return session.update(MYBATIS_PREFIX + ".saveWXPayRespRecord",paramter);
	}
	
	/**
	 * 更新返回订单记录
	 * @param paramter
	 * @return
	 */
	public Integer modifyWXPayRespRecord(Map paramter) {		
		return session.update(MYBATIS_PREFIX + ".modifyWXPayRespRecord",paramter);
	}
}

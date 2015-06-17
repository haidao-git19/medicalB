package com.netbull.shop.manage.weixin.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.vo.UserVo;

import com.netbull.shop.common.dao.SqlSession;

@Repository
public class AliPayAuthDao {
	private static final String MYBATIS_PREFIX = AliPayAuthDao.class.getName();
	
	@Resource
	private SqlSession session;
    
	/**
	 * 添加请求订单记录
	 * @param paramter
	 * @return
	 */
	public Integer saveAliPayAuthReq(Map paramter) {		
		return session.update(MYBATIS_PREFIX + ".saveAliPayAuthReq",paramter);
	}
	
	/**
	 * 添加返回订单记录
	 * @param paramter
	 * @return
	 */
	public Integer saveAliPayAuthResp(Map paramter) {		
		return session.update(MYBATIS_PREFIX + ".saveAliPayAuthResp",paramter);
	}
	
	/**
	 * 更新返回订单记录
	 * @param paramter
	 * @return
	 */
	public Integer modifyAliAuthPayResp(Map paramter) {		
		return session.update(MYBATIS_PREFIX + ".modifyAliAuthPayResp",paramter);
	}
}

package com.netbull.shop.manage.weixin.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.netbull.shop.common.vo.UserVo;

import com.netbull.shop.common.dao.SqlSession;

@Repository
public class AliPayTransDao {
	private static final String MYBATIS_PREFIX = AliPayTransDao.class.getName();
	
	@Resource
	private SqlSession session;
    
	/**
	 * 添加请求订单记录
	 * @param paramter
	 * @return
	 */
	public Integer saveAliPayTransReq(Map paramter) {		
		return session.update(MYBATIS_PREFIX + ".saveAliPayTransReq",paramter);
	}
	
	/**
	 * 添加返回订单记录
	 * @param paramter
	 * @return
	 */
	public Integer saveAliPayTransResp(Map paramter) {		
		return session.update(MYBATIS_PREFIX + ".saveAliPayTransResp",paramter);
	}
	
	/**
	 * 更新返回订单记录
	 * @param paramter
	 * @return
	 */
	public Integer modifyAliTransPayResp(Map paramter) {		
		return session.update(MYBATIS_PREFIX + ".modifyAliTransPayResp",paramter);
	}
}

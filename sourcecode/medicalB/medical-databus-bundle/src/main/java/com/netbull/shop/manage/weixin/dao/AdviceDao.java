package com.netbull.shop.manage.weixin.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.manage.weixin.vo.AdviceVo;

@Repository
public class AdviceDao {

	private static final String MYBATIS_PREFIX=AdviceDao.class.getName();
	@Autowired
	private SqlSession sqlSession;
	
	public void save(AdviceVo adviceVo){
		sqlSession.insert(MYBATIS_PREFIX+".save", adviceVo);
	}
}

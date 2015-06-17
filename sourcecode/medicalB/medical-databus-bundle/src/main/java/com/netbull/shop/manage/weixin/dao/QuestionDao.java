package com.netbull.shop.manage.weixin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.vo.QuestionVo;

@Repository
public class QuestionDao {

	private static final String MYBATIS_PREFIX=QuestionDao.class.getName();
	@Autowired
	private SqlSession session;
	
	public List<Map> queryList(Map parameter){
		return session.selectList(MYBATIS_PREFIX+".queryQuestion", parameter);
	}
	
	public Map queryOne(Map parameter){
		return session.selectOne(MYBATIS_PREFIX+".queryQuestion", parameter);
	}
	
	public Integer save(QuestionVo questionVo){
		return session.insert(MYBATIS_PREFIX+".save", questionVo);
	}
	
	public Integer update(Map parameter){
		return session.update(MYBATIS_PREFIX+".update", parameter);
	}
	
	public Integer saveReply(Map parameter){
		return session.insert(MYBATIS_PREFIX+".saveReply", parameter);
	}
	
	public List<Map> queryReplyList(Map parameter){
		return session.selectList(MYBATIS_PREFIX+".queryReply", parameter);
	}
}

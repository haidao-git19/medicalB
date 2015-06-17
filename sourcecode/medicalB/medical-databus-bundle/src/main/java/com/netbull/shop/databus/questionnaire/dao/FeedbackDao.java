package com.netbull.shop.databus.questionnaire.dao;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.databus.questionnaire.model.Feedback;

/**
 * 题目Dao
 * 
 * @author Ade
 */
@Repository
public class FeedbackDao {
	private static final Logger logger = Logger.getLogger("daoLog");
	
	private static final String NAMESPACE = Feedback.class.getName();

	@Resource
	private SqlSession session;
	
	public int save(Feedback feedback) {
		return session.insert(NAMESPACE + ".save", feedback);
	}

	public Feedback findById(long id) {
		return session.selectOne(NAMESPACE + ".find_by_id", id);
	}
	
}

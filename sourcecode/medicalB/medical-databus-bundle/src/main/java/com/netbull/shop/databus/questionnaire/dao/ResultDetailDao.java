package com.netbull.shop.databus.questionnaire.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.databus.questionnaire.model.ExceptionOption;
import com.netbull.shop.databus.questionnaire.model.ResultDetail;

/**
 * 题目Dao
 * 
 * @author Ade
 */
@Repository
public class ResultDetailDao {
	private static final Logger logger = Logger.getLogger("daoLog");
	
	private static final String NAMESPACE = ResultDetail.class.getName();

	@Resource
	private SqlSession session;
	
	public int save(ResultDetail detail) {
		return session.insert(NAMESPACE + ".save", detail);
	}

	public List<ExceptionOption> findExceptionOptions(long rId) {
		return session.selectList(NAMESPACE + ".find_ExceptionOption_list", rId);
	}
}

package com.netbull.shop.databus.questionnaire.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.databus.questionnaire.dto.ExceptionResult;
import com.netbull.shop.databus.questionnaire.model.Result;

/**
 * @author Ade
 */
@Repository
public class ResultDao {
	private static final Logger logger = Logger.getLogger("daoLog");
	
	private static final String NAMESPACE = Result.class.getName();

	@Resource
	private SqlSession session;
	
	public long save(Result result) {
		session.insert(NAMESPACE + ".save", result);
		return result.getId();
	}
	
	public List<ExceptionResult> findExceptionResults(long doctorId) {
		return session.selectList(NAMESPACE + ".find_exceptionResults", doctorId);
	}
	
}
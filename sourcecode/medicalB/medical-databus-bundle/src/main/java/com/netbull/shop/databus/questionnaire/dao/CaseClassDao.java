package com.netbull.shop.databus.questionnaire.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.databus.questionnaire.model.CaseClass;

/**
 * 题目大类Dao
 * 
 * @author Ade
 */
@Repository
public class CaseClassDao {
	private static final Logger logger = Logger.getLogger("daoLog");
	
	private static final String NAMESPACE = CaseClass.class.getName();

	@Resource
	private SqlSession session;

	public List<CaseClass> findCaseClassList(long qnId) {
		return session.selectList(NAMESPACE + ".query_CaseClass_list_by_qnId", qnId);
	}
}

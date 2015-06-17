package com.netbull.shop.databus.questionnaire.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.databus.questionnaire.model.Case;

/**
 * @author Ade
 */
@Repository
public class CaseDao {
	private static final Logger logger = Logger.getLogger("daoLog");
	
	private static final String NAMESPACE = Case.class.getName();

	@Resource
	private SqlSession session;
	
	public List<Case> findCasesByClsId(long classId) {
		return session.selectList(NAMESPACE + ".query_Case_list_by_classId", classId);
	}

}

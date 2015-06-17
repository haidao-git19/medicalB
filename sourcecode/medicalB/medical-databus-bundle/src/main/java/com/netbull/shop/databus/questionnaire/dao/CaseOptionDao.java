package com.netbull.shop.databus.questionnaire.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.databus.questionnaire.model.CaseOption;

/**
 * 题目选项Dao
 * 
 * @author Ade
 */
@Repository
public class CaseOptionDao {
	private static final Logger logger = Logger.getLogger("daoLog");
	
	private static final String NAMESPACE = CaseOption.class.getName();

	@Resource
	private SqlSession session;
	
	public List<CaseOption> findCaseOptions(long qId) {
		return session.selectList(NAMESPACE + ".query_CaseOption_list_by_qId", qId);
	}

}

package com.netbull.shop.databus.questionnaire.dao;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.databus.questionnaire.model.PatientBind;

/**
 * @author Ade
 */
@Repository
public class PatientBindDao {
	private static final Logger logger = Logger.getLogger("daoLog");
	
	private static final String NAMESPACE = PatientBind.class.getName();

	@Resource
	private SqlSession session;
	
	public int save(PatientBind bind) {
		return session.insert(NAMESPACE + ".save", bind);
	}

}

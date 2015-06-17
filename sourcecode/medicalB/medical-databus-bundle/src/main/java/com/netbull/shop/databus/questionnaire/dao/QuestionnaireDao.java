package com.netbull.shop.databus.questionnaire.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.databus.questionnaire.model.Questionnaire;

/**
 * 问卷Dao
 * 
 * @author Ade
 */
@Repository
public class QuestionnaireDao {
	private static final Logger logger = Logger.getLogger("daoLog");
	
	private static final String NAMESPACE = Questionnaire.class.getName();

	@Resource
	private SqlSession session;
	
	public Questionnaire getById(long id) {
		return session.selectOne(NAMESPACE + ".query_Questionnaire_by_id", id);
	}

	public List<Questionnaire> getQuestionnairesByDoctorId(long doctorId) {
		return session.selectList(NAMESPACE + ".query_Questionnaires_by_doctorId", doctorId);
	}
	
	public Integer update(Map parameter){
		return session.update(NAMESPACE+".update", parameter);
	}
	
	public List<Map> queryRcentiQuestionnaires(Map parameter){
		return session.selectList(NAMESPACE+".queryRcentiQuestionnaires", parameter);
	}
}

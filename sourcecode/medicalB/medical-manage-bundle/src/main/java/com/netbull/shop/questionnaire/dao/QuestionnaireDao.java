package com.netbull.shop.questionnaire.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netbull.shop.common.dao.SqlSession;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.dao.BaseDao;
import com.netbull.shop.doctor.dao.DoctorDao;
import com.netbull.shop.doctor.entity.Doctor;
import com.netbull.shop.questionnaire.entity.Questionnaire;
import com.netbull.shop.questionnaire.entity.QuestionnaireCase;
import com.netbull.shop.questionnaire.entity.QuestionnaireCaseClass;
import com.netbull.shop.questionnaire.entity.QuestionnaireCaseOption;
import com.netbull.shop.shiro.ShiroUser;

@Repository
public class QuestionnaireDao extends BaseDao{

	private static final String MYBATIS_PREFIX=QuestionnaireDao.class.getName();
	
	@Autowired
	private SqlSession session;
	@Autowired
	private DoctorDao doctorDao;
	
	/*************************************************************************/
	public Page queryPage(Integer iDisplayStart, Integer iDisplayLength,Map requestMap){
		ShiroUser currentUser=queryCurrentShiroUser();
		long loginID=currentUser.getId();
		Map param=new HashMap();
		param.put("loginID", loginID);
		Doctor doctor=doctorDao.queryByParam(param);
		if(!NullUtil.isNull(doctor)){
			requestMap.put("doctorId", doctor.getDoctorID());
		}
		return session.page(MYBATIS_PREFIX+".queryQuestionnaireList", MYBATIS_PREFIX+".queryQuestionnaireCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	public Questionnaire queryQuestionnaire(Map parameter){
		return session.selectOne(MYBATIS_PREFIX+".queryQuestionnaire", parameter);
	}
	
	public List<Questionnaire> queryQuestionnaireList(Map parameter){
		return session.selectList(MYBATIS_PREFIX+".queryQuestionnaire", parameter);
	}
	
	public void saveQuestionnaire(Questionnaire questionnaire){
		ShiroUser currentUser=queryCurrentShiroUser();
		long loginID=currentUser.getId();
		Map param=new HashMap();
		param.put("loginID", loginID);
		Doctor doctor=doctorDao.queryByParam(param);
		if(!NullUtil.isNull(doctor)){
			questionnaire.setDoctorId(doctor.getDoctorID());
		}
		session.insert(MYBATIS_PREFIX+".saveQuestionnaire", questionnaire);
	}
	
	public void updateQuestionnaire(Questionnaire questionnaire){
		session.update(MYBATIS_PREFIX+".updateQuestionnaire", questionnaire);
	}
	
	public void deleteQuestionnaire(Integer id){
		session.delete(MYBATIS_PREFIX+".deleteQuestionnaire", id);
	}
	/*************************************************************************/
	public Page queryCaseClassPage(Integer iDisplayStart, Integer iDisplayLength,Map requestMap){
		return session.page(MYBATIS_PREFIX+".queryQuestionnaireCaseClassList", MYBATIS_PREFIX+".queryQuestionnaireCaseClassCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	public List<QuestionnaireCaseClass> queryQuestionnaireCaseClassList(Map parameter){
		return session.selectList(MYBATIS_PREFIX+".queryQuestionnaireCaseClass", parameter);
	}
	
	public QuestionnaireCaseClass queryQuestionnaireCaseClass(Map parameter){
		return session.selectOne(MYBATIS_PREFIX+".queryQuestionnaireCaseClass", parameter);
	}
	
	public void saveQuestionnaireCaseClass(QuestionnaireCaseClass caseClass){
		session.insert(MYBATIS_PREFIX+".saveQuestionnaireCaseClass", caseClass);
	}
	
	public void updateQuestionnaireCaseClass(QuestionnaireCaseClass caseClass){
		session.update(MYBATIS_PREFIX+".updateQuestionnaireCaseClass", caseClass);
	}
	
	public void deleteQuestionnaireCaseClass(Integer id){
		session.delete(MYBATIS_PREFIX+".deleteQuestionnaireCaseClass", id);
	}
	
	/*************************************************************************/
	public Page queryCasePage(Integer iDisplayStart, Integer iDisplayLength,Map requestMap){
		return session.page(MYBATIS_PREFIX+".queryQuestionnaireCaseList", MYBATIS_PREFIX+".queryQuestionnaireCaseCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	public List<QuestionnaireCase> queryQuestionnaireCaseList(Map parameter){
		return session.selectList(MYBATIS_PREFIX+".queryQuestionnaireCase", parameter);
	}
	
	public QuestionnaireCase queryQuestionnaireCase(Map parameter){
		return session.selectOne(MYBATIS_PREFIX+".queryQuestionnaireCase", parameter);
	}
	
	public void saveQuestionnaireCase(QuestionnaireCase questionnaireCase){
		session.insert(MYBATIS_PREFIX+".saveQuestionnaireCase", questionnaireCase);
	}
	
	public void updateQuestionnaireCase(QuestionnaireCase questionnaireCase){
		session.update(MYBATIS_PREFIX+".updateQuestionnaireCase", questionnaireCase);
	}
	
	public void deleteQuestionnaireCase(Integer id){
		session.delete(MYBATIS_PREFIX+".deleteQuestionnaireCase", id);
	}
	
	/*************************************************************************/
	public Page queryCaseOptionPage(Integer iDisplayStart, Integer iDisplayLength,Map requestMap){
		return session.page(MYBATIS_PREFIX+".queryQuestionnaireCaseOptionList", MYBATIS_PREFIX+".queryQuestionnaireCaseOptionCount", requestMap, iDisplayStart, iDisplayLength);
	}
	
	public List<QuestionnaireCaseOption> queryQuestionnaireCaseOptionList(Map parameter){
		return session.selectList(MYBATIS_PREFIX+".queryQuestionnaireCaseOption", parameter);
	}
	
	public QuestionnaireCaseOption queryQuestionnaireCaseOption(Map parameter){
		return session.selectOne(MYBATIS_PREFIX+".queryQuestionnaireCaseOption", parameter);
	}
	
	public void saveQuestionnaireCaseOption(QuestionnaireCaseOption questionnaireCaseOption){
		session.insert(MYBATIS_PREFIX+".saveQuestionnaireCaseOption", questionnaireCaseOption);
	}
	
	public void updateQuestionnaireCaseOption(QuestionnaireCaseOption questionnaireCaseOption){
		session.update(MYBATIS_PREFIX+".updateQuestionnaireCaseOption", questionnaireCaseOption);
	}
	
	public void deleteQuestionnaireCaseOption(Integer id){
		session.delete(MYBATIS_PREFIX+".deleteQuestionnaireCaseOption", id);
	}
}

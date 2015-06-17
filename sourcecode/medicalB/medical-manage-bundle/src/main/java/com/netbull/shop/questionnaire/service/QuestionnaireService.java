package com.netbull.shop.questionnaire.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netbull.shop.common.page.Page;
import com.netbull.shop.questionnaire.dao.QuestionnaireDao;
import com.netbull.shop.questionnaire.entity.Questionnaire;
import com.netbull.shop.questionnaire.entity.QuestionnaireCase;
import com.netbull.shop.questionnaire.entity.QuestionnaireCaseClass;
import com.netbull.shop.questionnaire.entity.QuestionnaireCaseOption;

@Service
public class QuestionnaireService {

	@Autowired
	private QuestionnaireDao questionnaireDao;
	
	/******************************************************/
	public Page queryPage(Integer iDisplayStart, Integer iDisplayLength,Map requestMap){
		return questionnaireDao.queryPage(iDisplayStart, iDisplayLength, requestMap);
	}
	
	public Questionnaire queryQuestionnaire(Map parameter){
		return questionnaireDao.queryQuestionnaire(parameter);
	}
	
	public List<Questionnaire> queryQuestionnaireList(Map parameter){
		return questionnaireDao.queryQuestionnaireList(parameter);
	}
	
	public void saveQuestionnaire(Questionnaire questionnaire){
		questionnaireDao.saveQuestionnaire(questionnaire);
	}
	
	public void updateQuestionnaire(Questionnaire questionnaire){
		questionnaireDao.updateQuestionnaire(questionnaire);
	}
	
	public void deleteQuestionnaire(Integer id){
		questionnaireDao.deleteQuestionnaire(id);
	}
	/******************************************************/
	public Page queryCaseClassPage(Integer iDisplayStart, Integer iDisplayLength,Map requestMap){
		return questionnaireDao.queryCaseClassPage(iDisplayStart, iDisplayLength, requestMap);
	}
	
	public List<QuestionnaireCaseClass> queryQuestionnaireCaseClassList(Map parameter){
		return questionnaireDao.queryQuestionnaireCaseClassList(parameter);
	}
	
	public QuestionnaireCaseClass queryQuestionnaireCaseClass(Map parameter){
		return questionnaireDao.queryQuestionnaireCaseClass(parameter);
	}
	
	public void saveQuestionnaireCaseClass(QuestionnaireCaseClass caseClass){
		questionnaireDao.saveQuestionnaireCaseClass(caseClass);
	}
	
	public void updateQuestionnaireCaseClass(QuestionnaireCaseClass caseClass){
		questionnaireDao.updateQuestionnaireCaseClass(caseClass);
	}
	
	public void deleteQuestionnaireCaseClass(Integer id){
		questionnaireDao.deleteQuestionnaireCaseClass(id);
	}
	
	/********************************************************/
	public Page queryCasePage(Integer iDisplayStart, Integer iDisplayLength,Map requestMap){
		return questionnaireDao.queryCasePage(iDisplayStart, iDisplayLength, requestMap);
	}
	
	public List<QuestionnaireCase> queryQuestionnaireCaseList(Map parameter){
		return questionnaireDao.queryQuestionnaireCaseList(parameter);
	}
	
	public QuestionnaireCase queryQuestionnaireCase(Map parameter){
		return questionnaireDao.queryQuestionnaireCase(parameter);
	}
	
	public void saveQuestionnaireCase(QuestionnaireCase questionnaireCase){
		questionnaireDao.saveQuestionnaireCase(questionnaireCase);
	}
	
	public void updateQuestionnaireCase(QuestionnaireCase questionnaireCase){
		questionnaireDao.updateQuestionnaireCase(questionnaireCase);
	}
	
	public void deleteQuestionnaireCase(Integer id){
		questionnaireDao.deleteQuestionnaireCase(id);
	}
	/********************************************************/
	public Page queryCaseOptionPage(Integer iDisplayStart, Integer iDisplayLength,Map requestMap){
		return questionnaireDao.queryCaseOptionPage(iDisplayStart, iDisplayLength, requestMap);
	}
	
	public List<QuestionnaireCaseOption> queryQuestionnaireCaseOptionList(Map parameter){
		return questionnaireDao.queryQuestionnaireCaseOptionList(parameter);
	}
	
	public QuestionnaireCaseOption queryQuestionnaireCaseOption(Map parameter){
		return questionnaireDao.queryQuestionnaireCaseOption(parameter);
	}
	
	public void saveQuestionnaireCaseOption(QuestionnaireCaseOption questionnaireCaseOption){
		questionnaireDao.saveQuestionnaireCaseOption(questionnaireCaseOption);
	}
	
	public void updateQuestionnaireCaseOption(QuestionnaireCaseOption questionnaireCaseOption){
		questionnaireDao.updateQuestionnaireCaseOption(questionnaireCaseOption);
	}
	
	public void deleteQuestionnaireCaseOption(Integer id){
		questionnaireDao.deleteQuestionnaireCaseOption(id);
	}
}

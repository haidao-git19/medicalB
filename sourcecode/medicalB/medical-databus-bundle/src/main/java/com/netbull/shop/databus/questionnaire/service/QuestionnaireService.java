package com.netbull.shop.databus.questionnaire.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.netbull.shop.databus.questionnaire.dao.CaseClassDao;
import com.netbull.shop.databus.questionnaire.dao.CaseDao;
import com.netbull.shop.databus.questionnaire.dao.CaseOptionDao;
import com.netbull.shop.databus.questionnaire.dao.FeedbackDao;
import com.netbull.shop.databus.questionnaire.dao.PatientBindDao;
import com.netbull.shop.databus.questionnaire.dao.QuestionnaireDao;
import com.netbull.shop.databus.questionnaire.dao.ResultDao;
import com.netbull.shop.databus.questionnaire.dao.ResultDetailDao;
import com.netbull.shop.databus.questionnaire.dto.ExceptionOptionResp;
import com.netbull.shop.databus.questionnaire.dto.ExceptionResult;
import com.netbull.shop.databus.questionnaire.dto.ExceptionResultResp;
import com.netbull.shop.databus.questionnaire.dto.FeedbackDto;
import com.netbull.shop.databus.questionnaire.dto.QuestionnaireListResp;
import com.netbull.shop.databus.questionnaire.dto.QuestionnaireResp;
import com.netbull.shop.databus.questionnaire.model.Case;
import com.netbull.shop.databus.questionnaire.model.CaseClass;
import com.netbull.shop.databus.questionnaire.model.CaseOption;
import com.netbull.shop.databus.questionnaire.model.ExceptionOption;
import com.netbull.shop.databus.questionnaire.model.Feedback;
import com.netbull.shop.databus.questionnaire.model.PatientBind;
import com.netbull.shop.databus.questionnaire.model.Questionnaire;
import com.netbull.shop.databus.questionnaire.model.Result;
import com.netbull.shop.databus.questionnaire.model.ResultDetail;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.common.http.Resp;

/**
 * 问卷Service
 * 
 * @author Ade
 */
@Service
public class QuestionnaireService {
	private static final Logger logger = Logger.getLogger("serviceLog");
	
	@Resource
	private CaseDao caseDao;
	@Resource
	private CaseClassDao caseClassDao;
	@Resource
	private QuestionnaireDao questionnaireDao;
	@Resource
	private CaseOptionDao caseOptionDao;
	@Resource
	private ResultDao resultDao;
	@Resource
	private ResultDetailDao resultDetailDao;
	@Resource
	private FeedbackDao feedbackDao;
	@Resource
	private PatientBindDao patientBindDao;
	
	public QuestionnaireListResp getQuestionnaireListResp(long doctorId) {
		QuestionnaireListResp resp = new QuestionnaireListResp();
		
		try{
			List<Questionnaire> questionnaires = questionnaireDao.getQuestionnairesByDoctorId(doctorId);
			resp.setQuestionnaires(questionnaires);
			resp.setCode(Constants.SUCCESS);
			resp.setMsg(Constants.SUCCESS_MSG);
		}catch(Exception e) {
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}
		
		return resp;
	}
	
	/**
	 * @param qnId 问卷ID
	 * @return
	 */
	public QuestionnaireResp getQuestionnaireResp(long qnId) {
		QuestionnaireResp resp = new QuestionnaireResp();
		
		try{
			List<CaseClass> questionClasses = buildQuestionClasses(qnId);
			
			Questionnaire questionnaire = questionnaireDao.getById(qnId);
			questionnaire.setCaseClasses(questionClasses);
			
			resp.setQuestionnaire(questionnaire);
			resp.setCode(Constants.SUCCESS);
			resp.setMsg(Constants.SUCCESS_MSG);
		}catch(Exception e) {
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}
		
		return resp;
	}
	
	private List<CaseClass> buildQuestionClasses(long qnId) {
		List<CaseClass> classes = caseClassDao.findCaseClassList(qnId);
		for(CaseClass cls : classes) {
			List<Case> questions = buildClassQuestions(cls.getId());
			cls.setCases(questions);
		}
		return classes;
	}
	
	private List<Case> buildClassQuestions(long classId) {
		List<Case> questions = caseDao.findCasesByClsId(classId);
		for(Case question : questions) {
			List<CaseOption> questionOptions = caseOptionDao.findCaseOptions(question.getId());
			question.setCaseOptions(questionOptions);
		}
		return questions;
	}
	
	public Resp commit(Result rslt) {
		Resp resp = new Resp();
		
		try{
			long rId = resultDao.save(rslt);
			for(ResultDetail detail : rslt.getDetails()) {
				detail.setRId(rId);
				resultDetailDao.save(detail);
			}
			resp.setCode(Constants.SUCCESS);
			resp.setMsg(Constants.SUCCESS_MSG);
		}catch(Exception e) {
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}
		
		return resp;
	}

	public ExceptionOptionResp exceptionOption(long rId) {
		ExceptionOptionResp resp = new ExceptionOptionResp();
		
		try{
			List<ExceptionOption> options = resultDetailDao.findExceptionOptions(rId);
			resp.setOptions(options);
			resp.setCode(Constants.SUCCESS);
			resp.setMsg(Constants.SUCCESS_MSG);
		}catch(Exception e) {
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}
		
		return resp;
	}
	
	public Resp feedback(Feedback feedback) {
		Resp resp = new Resp();
		
		try{
			feedbackDao.save(feedback);
			resp.setCode(Constants.SUCCESS);
			resp.setMsg(Constants.SUCCESS_MSG);
		}catch(Exception e) {
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}
		
		return resp;
	}
	
	public Resp bind(PatientBind bind) {
		Resp resp = new Resp();
		
		try{
			patientBindDao.save(bind);
			resp.setCode(Constants.SUCCESS);
			resp.setMsg(Constants.SUCCESS_MSG);
		}catch(Exception e) {
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}
		
		return resp;
	}
	
	public FeedbackDto getFeedback(long id) {
		FeedbackDto resp = new FeedbackDto();
		
		try{
			Feedback feedback = feedbackDao.findById(id);
			resp.setFeedback(feedback);
			resp.setCode(Constants.SUCCESS);
			resp.setMsg(Constants.SUCCESS_MSG);
		}catch(Exception e) {
			logger.error("", e);
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}
		
		return resp;
	}
	
	public ExceptionResultResp exceptionResultList(long doctorId) {
		ExceptionResultResp resp = new ExceptionResultResp();
		
		try{
			List<ExceptionResult> list = resultDao.findExceptionResults(doctorId);
			resp.setList(list);
			resp.setCode(Constants.SUCCESS);
			resp.setMsg(Constants.SUCCESS_MSG);
		}catch(Exception e) {
			logger.error("", e);
			resp.setCode(Constants.FAIL);
			resp.setMsg(Constants.FAIL_MSG);
		}
		
		return resp;
	}
	
	public List<Map> queryRcentiQuestionnaires(Map parameter){
		return questionnaireDao.queryRcentiQuestionnaires(parameter);
	}
	
	public Integer update(Map parameter){
		return questionnaireDao.update(parameter);
	}
}

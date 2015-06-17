package com.netbull.shop.databus.questionnaire.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.netbull.shop.common.util.RequestUtil;
import com.netbull.shop.databus.questionnaire.dto.ExceptionOptionResp;
import com.netbull.shop.databus.questionnaire.dto.ExceptionResultResp;
import com.netbull.shop.databus.questionnaire.dto.FeedbackDto;
import com.netbull.shop.databus.questionnaire.dto.QuestionnaireListResp;
import com.netbull.shop.databus.questionnaire.dto.QuestionnaireResp;
import com.netbull.shop.databus.questionnaire.model.Feedback;
import com.netbull.shop.databus.questionnaire.model.PatientBind;
import com.netbull.shop.databus.questionnaire.model.Questionnaire;
import com.netbull.shop.databus.questionnaire.model.Result;
import com.netbull.shop.databus.questionnaire.service.QuestionnaireService;
import com.netbull.shop.manage.common.http.Resp;

/**
 * 问卷Controller
 * 
 * @author Ade
 */
@Controller("questionnaireController")
public class QuestionnaireController {
	
	private static final Logger logger = Logger.getLogger("controlerLog");

	@Resource
	private QuestionnaireService questionnaireService;
	
	/**
	 * 医生问卷列表
	 * @return
	 */
	@RequestMapping(value = "/anon/doctor/questionnaire/list" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String questionnaireList(HttpServletRequest reqest) throws Exception {
		String req = RequestUtil.getPostData(reqest);
		logger.info("req=" + req);
		
		Questionnaire qn = JSON.parseObject(req, Questionnaire.class);
		QuestionnaireListResp resp = questionnaireService.getQuestionnaireListResp(qn.getDoctorId());
		return JSON.toJSONString(resp);
	}

	/**
	 * 问卷详情
	 * @return
	 */
	@RequestMapping(value = "/anon/doctor/questionnaire/detail" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String questionnaireDetail(HttpServletRequest reqest) throws Exception {
		String req = RequestUtil.getPostData(reqest);
		logger.info("req=" + req);
		
		Questionnaire qn = JSON.parseObject(req, Questionnaire.class);
		QuestionnaireResp resp = questionnaireService.getQuestionnaireResp(qn.getId());
		return JSON.toJSONString(resp);
	}
	
	/**
	 * 问卷绑定给患者
	 * @return
	 */
	@RequestMapping(value = "/anon/questionnaire/patient/bind" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String bind(HttpServletRequest reqest) throws Exception {
		String req = RequestUtil.getPostData(reqest);
		logger.info("req=" + req);
		
		PatientBind bind = JSON.parseObject(req, PatientBind.class);
		Resp resp = questionnaireService.bind(bind);
		return JSON.toJSONString(resp);
	}
	
	/**
	 * 提交问卷
	 * @return
	 */
	@RequestMapping(value = "/anon/questionnaire/commit" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String questionnaireCommit(HttpServletRequest reqest) throws Exception {
		String req = RequestUtil.getPostData(reqest);
		logger.info("req=" + req);
		
		Result rslt = JSON.parseObject(req, Result.class);
		Resp resp = questionnaireService.commit(rslt);
		return JSON.toJSONString(resp);
	}
	
	/**
	 * 异常列表查询
	 * @return
	 */
	@RequestMapping(value = "/anon/questionnaire/exception/list" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String exceptionList(HttpServletRequest reqest) throws Exception {
		String req = RequestUtil.getPostData(reqest);
		logger.info("req=" + req);
		
		Questionnaire qn = JSON.parseObject(req, Questionnaire.class);
		ExceptionResultResp resp = questionnaireService.exceptionResultList(qn.getDoctorId());
		return JSON.toJSONString(resp);
	}
	
	/**
	 * 异常项查询
	 * @return
	 */
	@RequestMapping(value = "/anon/questionnaire/exceptionOption" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String exceptionOption(HttpServletRequest reqest) throws Exception {
		String req = RequestUtil.getPostData(reqest);
		logger.info("req=" + req);
		
		Result rslt = JSON.parseObject(req, Result.class);
		ExceptionOptionResp resp = questionnaireService.exceptionOption(rslt.getId());
		return JSON.toJSONString(resp);
	}
	
	/**
	 * 问卷反馈提交
	 * @return
	 */
	@RequestMapping(value = "/anon/questionnaire/feedback/commit" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String feedbackCommit(HttpServletRequest reqest) throws Exception {
		String req = RequestUtil.getPostData(reqest);
		logger.info("req=" + req);
		
		Feedback feedback = JSON.parseObject(req, Feedback.class);
		Resp resp = questionnaireService.feedback(feedback);
		return JSON.toJSONString(resp);
	}
	
	/**
	 * 问卷反馈查询
	 * @return
	 */
	@RequestMapping(value = "/anon/questionnaire/feedback/query" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String feedbackQuery(HttpServletRequest reqest) throws Exception {
		String req = RequestUtil.getPostData(reqest);
		logger.info("req=" + req);
		
		Feedback feedback = JSON.parseObject(req, Feedback.class);
		FeedbackDto resp = questionnaireService.getFeedback(feedback.getId());
		return JSON.toJSONString(resp);
	}
}

package com.netbull.shop.manage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.common.vo.QuestionVo;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.weixin.service.QuestionService;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;

@Controller
public class QuestionController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	@Autowired
	private QuestionService questionService;
	
	/**
	 * 患者免费提问
	 * @param questionVo
	 * @param request 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/anon/referQuestion", produces="application/json;charset=utf-8")
	@ResponseBody
	public String referQuestion(QuestionVo questionVo,HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class referQuestion method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("患者免费提问，参数：" + requestMap.toString());
			}
			
			questionService.saveQuestion(questionVo);
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 查看病人提交的问题列表
	 * @param questionVo
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/queryQuestionList", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryQuestionList(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryQuestionList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查看病人提交的问题列表，参数：" + requestMap.toString());
			}
			
			Map parameter=new HashMap();
			parameter.put("status", 0);
			List<Map> questionList=questionService.queryQuestionList(parameter);
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("questionList", questionList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 查看问题详情
	 * @param request 参数：questionID-问题ID 
	 * @return 
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/anon/queryQuestion", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryQuestion(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryQuestion method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查看病人提交的问题详情，参数：" + requestMap.toString());
			}
			Map questionMap=null;
			if(StringUtil.isEmpty(requestMap.get("questionID"))){
				Map parameter=new HashMap();
				parameter.put("questionID", requestMap.get("questionID"));
				questionMap=questionService.queryQuestion(parameter);
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("questionDetail", questionMap);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 提交答复信息
	 * @param request 参数questionID-问题ID
	 * 						submitterID-答复人ID
	 * 						submitterType-答复人类型
	 * 						replyContent-答复内容
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/commitReply", produces="application/json;charset=utf-8")
	@ResponseBody
	public String commitReply(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class commitReply method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("提交答复信息，参数：" + requestMap.toString());
			}
			if(StringUtil.isEmpty(requestMap.get("questionID"))&&StringUtil.isEmpty(requestMap.get("submitterID"))
					&&StringUtil.isEmpty(requestMap.get("submitterType"))&&StringUtil.isEmpty(requestMap.get("replyContent"))){
				Map parameter=new HashMap();
				parameter.put("questionID", requestMap.get("questionID"));
				parameter.put("submitterID", requestMap.get("submitterID"));
				parameter.put("submitterType", requestMap.get("submitterType"));
				parameter.put("replyContent", requestMap.get("replyContent"));
				questionService.saveReply(parameter);
				
				if(StringTools.equals(requestMap.get("submitterType"), "0")){
					Map param=new HashMap();
					param.put("questionID", requestMap.get("questionID"));
					Map questionMap=questionService.queryQuestion(param);
					if(!NullUtil.isNull(questionMap)&&StringTools.equals(StringUtil.getString(questionMap.get("status")), "0")){
						param.put("status", 1);
						questionService.updateQuestion(param);
					}
				}
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 查看问题解答信息
	 * @param request 参数questionID-问题ID
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/queryReplyList", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryReplyList(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryReplyList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查看问题解答信息，参数：" + requestMap.toString());
			}
			List<Map> replyList=null;
			if(StringUtil.isEmpty(requestMap.get("questionID"))){
				Map parameter=new HashMap();
				parameter.put("questionID", requestMap.get("questionID"));
				replyList=questionService.queryReplyList(parameter);
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("replyList", replyList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
}

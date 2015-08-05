package com.netbull.shop.questionnaire.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.questionnaire.entity.Questionnaire;
import com.netbull.shop.questionnaire.entity.QuestionnaireCase;
import com.netbull.shop.questionnaire.entity.QuestionnaireCaseClass;
import com.netbull.shop.questionnaire.entity.QuestionnaireCaseOption;
import com.netbull.shop.questionnaire.service.QuestionnaireService;
import com.netbull.shop.util.DataTableUtils;
import com.netbull.shop.util.RequestUtils;

@Controller
public class QuestionnaireController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	
	@Autowired
	private QuestionnaireService questionnaireService;
	
	/************************************************************/
	@RequestMapping(value="/questionnaire/questionnaireList")
	public String questionnaireList(HttpServletRequest request){
		return "questionnaire/questionnaireList";
	}
	
	@RequestMapping(value="/questionnaire/queryQuestionnairePage")
	@ResponseBody
	public  Map<String,Object> queryQuestionnairePage(Integer sEcho, Integer iColumns, Integer iDisplayStart, 
			Integer iDisplayLength,HttpServletRequest request) {
		Map<String, String> requestMap =  RequestUtils.parameterToMap(request);
		Page page = questionnaireService.queryPage(iDisplayStart, iDisplayLength, requestMap);
		 List<List<?>> aaData = new ArrayList<List<?>>();
		 Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho , page.getTotal());
		  for (Object o : page.getResult()) {
				Map map = (Map)o;
				List<Object> list = new ArrayList<Object>();
				list.add(map.get("id"));
				list.add(map.get("doctorName"));
				list.add(map.get("name"));
				list.add(map.get("createTime"));
				list.add(map.get("note"));
				list.add(map.get("pushDays"));
				list.add(map.get(""));
				aaData.add(list);
			}
			resultMap.put("aaData", aaData);
		   return resultMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/questionnaire/queryQuestionnaire")
	@ResponseBody
	public Questionnaire queryQuestionnaire(Integer id,HttpServletRequest request){
		Questionnaire questionnaire=null;
		if(id!=null&&id>0){
			Map parameter=new HashMap();
			parameter.put("id", id);
			questionnaire=questionnaireService.queryQuestionnaire(parameter);
		}
		return questionnaire;
	}
	
	@RequestMapping(value="/questionnaire/addOrUpdateQuestionnaire")
	@ResponseBody
	public void addOrUpdateQuestionnaire(Integer id,Questionnaire questionnaire){
		if(id!=null&&id>0){
			questionnaireService.updateQuestionnaire(questionnaire);
		}else{
			questionnaireService.saveQuestionnaire(questionnaire);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/questionnaire/deleteQuestionnaire")
	@ResponseBody
	public void deleteQuestionnaire(Integer id,HttpServletRequest request){
		if(id!=null&&id>0){
			
			Map parameter=new HashMap();
			parameter.put("qnId", id);
			List<QuestionnaireCaseClass> caseClassList=questionnaireService.queryQuestionnaireCaseClassList(parameter);
			if(!NullUtil.isNull(caseClassList)){
				for(QuestionnaireCaseClass caseClass:caseClassList){
					questionnaireService.deleteQuestionnaireCaseClass(caseClass.getId());
				}
			}
			
			List<QuestionnaireCase> caseList=questionnaireService.queryQuestionnaireCaseList(parameter);
			if(!NullUtil.isNull(caseList)){
				for(QuestionnaireCase _case:caseList){
					
					Map param=new HashMap();
					param.put("qId", _case.getId());
					List<QuestionnaireCaseOption> optionList=questionnaireService.queryQuestionnaireCaseOptionList(param);
					if(!NullUtil.isNull(optionList)){
						for(QuestionnaireCaseOption option:optionList){
							questionnaireService.deleteQuestionnaireCaseOption(option.getId());
						}
					}
					
					questionnaireService.deleteQuestionnaireCase(_case.getId());
				}
			}
			
			questionnaireService.deleteQuestionnaire(id);
		}
	}
	
	/************************************************************/
	@RequestMapping(value="/questionnaire/questionnaireCaseClassList")
	public String questionnaireCaseClassList(Integer qnId,HttpServletRequest request){
		request.setAttribute("qnId", qnId);
		return "questionnaire/questionnaireCaseClassList";
	}
	
	@RequestMapping(value="/questionnaire/queryQuestionnaireCaseClassPage")
	@ResponseBody
	public  Map<String,Object> queryQuestionnaireCaseClassPage(Integer sEcho, Integer iColumns, Integer iDisplayStart, 
			Integer iDisplayLength,HttpServletRequest request) {
		Map<String, String> requestMap =  RequestUtils.parameterToMap(request);
		Page page = questionnaireService.queryCaseClassPage(iDisplayStart, iDisplayLength, requestMap);
		 List<List<?>> aaData = new ArrayList<List<?>>();
		 Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho , page.getTotal());
		  for (Object o : page.getResult()) {
				Map map = (Map)o;
				List<Object> list = new ArrayList<Object>();
				list.add(map.get("id"));
				list.add(map.get("qnName"));
				list.add(map.get("name"));
				list.add(map.get(""));
				aaData.add(list);
			}
			resultMap.put("aaData", aaData);
		   return resultMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/questionnaire/queryQuestionnaireCaseClass")
	@ResponseBody
	public QuestionnaireCaseClass queryQuestionnaireCaseClass(Integer id,HttpServletRequest request){
		QuestionnaireCaseClass caseClass=null;
		if(id!=null&&id>0){
			Map parameter=new HashMap();
			parameter.put("id", id);
			caseClass=questionnaireService.queryQuestionnaireCaseClass(parameter);
		}
		return caseClass;
	}
	
	@RequestMapping(value="/questionnaire/addOrUpdateQuestionnaireCaseClass")
	@ResponseBody
	public void addOrUpdateQuestionnaireCaseClass(Integer id,QuestionnaireCaseClass questionnaireCaseClass){
		if(id!=null&&id>0){
			questionnaireService.updateQuestionnaireCaseClass(questionnaireCaseClass);
		}else{
			questionnaireService.saveQuestionnaireCaseClass(questionnaireCaseClass);
		}
	}
	
	@RequestMapping(value="/questionnaire/deleteQuestionnaireCaseClass")
	@ResponseBody
	public void deleteQuestionnaireCaseClass(Integer id,HttpServletRequest request){
		if(id!=null&&id>0){
			questionnaireService.deleteQuestionnaireCaseClass(id);
		}
	}
	
	/************************************************************/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/questionnaire/questionnaireCaseList")
	public String questionnaireCaseList(Integer qnId,HttpServletRequest request){
		Map parameter=new HashMap();
		parameter.put("qnId", qnId);
		List<QuestionnaireCaseClass> caseClassList=questionnaireService.queryQuestionnaireCaseClassList(parameter);
		request.setAttribute("qnId", qnId);
		request.setAttribute("caseClassList", caseClassList);
		return "questionnaire/questionnaireCaseList";
	}
	
	@RequestMapping(value="/questionnaire/queryQuestionnaireCasePage")
	@ResponseBody
	public  Map<String,Object> queryQuestionnaireCasePage(Integer sEcho, Integer iColumns, Integer iDisplayStart, 
			Integer iDisplayLength,HttpServletRequest request) {
		Map<String, String> requestMap =  RequestUtils.parameterToMap(request);
		Page page = questionnaireService.queryCasePage(iDisplayStart, iDisplayLength, requestMap);
		 List<List<?>> aaData = new ArrayList<List<?>>();
		 Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho , page.getTotal());
		  for (Object o : page.getResult()) {
				Map map = (Map)o;
				List<Object> list = new ArrayList<Object>();
				list.add(map.get("id"));
				list.add(map.get("qnName"));
				list.add(map.get("className"));
				list.add(map.get("type"));
				list.add(map.get("title"));
				list.add(map.get("unit"));
				list.add(map.get("type"));
				aaData.add(list);
			}
			resultMap.put("aaData", aaData);
		   return resultMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/questionnaire/queryQuestionnaireCase")
	@ResponseBody
	public QuestionnaireCase queryQuestionnaireCase(Integer id,HttpServletRequest request){
		QuestionnaireCase questionnaireCase=null;
		if(id!=null&&id>0){
			Map parameter=new HashMap();
			parameter.put("id", id);
			questionnaireCase=questionnaireService.queryQuestionnaireCase(parameter);
		}
		return questionnaireCase;
	}
	
	@RequestMapping(value="/questionnaire/addOrUpdateQuestionnaireCase")
	@ResponseBody
	public void addOrUpdateQuestionnaireCase(Integer id,QuestionnaireCase questionnaireCase){
		if(id!=null&&id>0){
			questionnaireService.updateQuestionnaireCase(questionnaireCase);
		}else{
			questionnaireService.saveQuestionnaireCase(questionnaireCase);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/questionnaire/deleteQuestionnaireCase")
	@ResponseBody
	public void deleteQuestionnaireCase(Integer id,HttpServletRequest request){
		if(id!=null&&id>0){
			Map parameter=new HashMap();
			parameter.put("qId", id);
			List<QuestionnaireCaseOption> optionList=questionnaireService.queryQuestionnaireCaseOptionList(parameter);
			if(!NullUtil.isNull(optionList)){
				for(QuestionnaireCaseOption option:optionList){
					questionnaireService.deleteQuestionnaireCaseOption(option.getId());
				}
			}
			questionnaireService.deleteQuestionnaireCase(id);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/questionnaire/queryCaseAndOptionInfo")
	@ResponseBody
	public Map<String,Object> queryCaseAndOptionInfo(Integer qnId,HttpServletRequest request){
		Map<String,Object> resultMap=new HashMap<String, Object>();
		List<QuestionnaireCase> caseList=null;
		if(qnId!=null&&qnId>0){
			Map parameter=new HashMap();
			parameter.put("qnId", qnId);
			caseList=questionnaireService.queryQuestionnaireCaseList(parameter);
			
			if(!NullUtil.isNull(caseList)){
				for(QuestionnaireCase qCase:caseList){
					Map param=new HashMap();
					param.put("qId", qCase.getId());
					List<QuestionnaireCaseOption> optionList=questionnaireService.queryQuestionnaireCaseOptionList(param);
					if(!NullUtil.isNull(optionList)){
						resultMap.put("optionList_"+qCase.getId(), optionList);
					}
				}
			}
		}
		resultMap.put("caseList", caseList);
		return resultMap;
	}
	
	/**********************************************************************/
	
	@RequestMapping(value="/questionnaire/questionnaireCaseOptionList")
	public String questionnaireCaseOptionList(Integer qnId,Integer qId,Integer type,HttpServletRequest request){
		request.setAttribute("qnId", qnId);
		request.setAttribute("qId", qId);
		request.setAttribute("type", type);
		return "questionnaire/questionnaireCaseOptionList";
	}
	
	@RequestMapping(value="/questionnaire/queryQuestionnaireCaseOptionPage")
	@ResponseBody
	public  Map<String,Object> queryQuestionnaireCaseOptionPage(Integer sEcho, Integer iColumns, Integer iDisplayStart, 
			Integer iDisplayLength,HttpServletRequest request) {
		Map<String, String> requestMap =  RequestUtils.parameterToMap(request);
		Page page = questionnaireService.queryCaseOptionPage(iDisplayStart, iDisplayLength, requestMap);
		 List<List<?>> aaData = new ArrayList<List<?>>();
		 Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho , page.getTotal());
		  for (Object o : page.getResult()) {
				Map map = (Map)o;
				List<Object> list = new ArrayList<Object>();
				list.add(map.get("id"));
				list.add(map.get("qName"));
				if(!StringTools.equals(StringUtil.getString(map.get("type")), "1")){
					list.add(map.get("option"));
				}
				list.add(map.get("exceptionFlag"));
				list.add(map.get("level"));
				list.add(map.get("index"));
			
				if(StringTools.equals(StringUtil.getString(map.get("type")), "1")){
					list.add(map.get("rangeFrom"));
					list.add(map.get("rangeTo"));
				}
				
				list.add("");
				aaData.add(list);
			}
			resultMap.put("aaData", aaData);
		   return resultMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/questionnaire/queryQuestionnaireCaseOption")
	@ResponseBody
	public QuestionnaireCaseOption queryQuestionnaireCaseOption(Integer id,HttpServletRequest request){
		QuestionnaireCaseOption questionnaireCaseOption=null;
		if(id!=null&&id>0){
			Map parameter=new HashMap();
			parameter.put("id", id);
			questionnaireCaseOption=questionnaireService.queryQuestionnaireCaseOption(parameter);
		}
		return questionnaireCaseOption;
	}
	
	@RequestMapping(value="/questionnaire/addOrUpdateQuestionnaireCaseOption")
	@ResponseBody
	public void addOrUpdateQuestionnaireCaseOption(Integer id,QuestionnaireCaseOption questionnaireCaseOption){
		if(id!=null&&id>0){
			questionnaireService.updateQuestionnaireCaseOption(questionnaireCaseOption);
		}else{
			questionnaireService.saveQuestionnaireCaseOption(questionnaireCaseOption);
		}
	}
	
	@RequestMapping(value="/questionnaire/deleteQuestionnaireCaseOption")
	@ResponseBody
	public void deleteQuestionnaireCaseOption(Integer id,HttpServletRequest request){
		if(id!=null&&id>0){
			questionnaireService.deleteQuestionnaireCaseOption(id);
		}
	}
}

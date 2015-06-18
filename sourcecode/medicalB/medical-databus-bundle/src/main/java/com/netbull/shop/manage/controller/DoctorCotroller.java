package com.netbull.shop.manage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netbull.shop.manage.weixin.service.RecordService;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.Md5;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.common.util.DoctorDC;
import com.netbull.shop.manage.common.util.HospitalDC;
import com.netbull.shop.manage.common.util.SectionFilterDC;
import com.netbull.shop.manage.weixin.service.ConsultationService;
import com.netbull.shop.manage.weixin.service.DoctorService;
import com.netbull.shop.manage.weixin.service.EvaluateService;
import com.netbull.shop.manage.weixin.service.MultiMediaService;
import com.netbull.shop.manage.weixin.service.SubscribeService;
import com.netbull.shop.manage.weixin.vo.Doctor;
import com.netbull.shop.manage.weixin.vo.DoctorGroup;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;


@Controller
public class DoctorCotroller extends AbstractController{

	private static final Logger logger = Logger.getLogger("controlerLog");

	@Resource
	private DoctorService doctorService;
	@Resource
	private MultiMediaService multiMediaService;
	@Autowired
	private EvaluateService evaluateService;
	@Autowired
	private SubscribeService subscribeService;
	@Autowired
	private ConsultationService consultationService;
	@Autowired
	private RecordService recordService;
	
	
	/**
	 * 根据医院查询医生列表
	 * @param request 参数hospitalID-医院ID
	 * 						pageId-页码
	 * 						showCount-展示记录条数
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/queryDoctorListByHospital", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryDoctorListByHospital(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryDoctorListByHospital method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("根据医院查询医生列表信息，参数：" + requestMap.toString());
			}
			String showCount = !NullUtil.isNull(requestMap.get("showCount"))?requestMap.get("showCount"):"10";
			Map paramter = new HashMap();
			paramter.put("start", Integer.parseInt(requestMap.get("pageID"))*Integer.parseInt(showCount));
			paramter.put("limit", Integer.parseInt(showCount));
			paramter.put("hospitalID", requestMap.get("hospitalID"));
			List<Map> doctorList = doctorService.queryDoctorListByParams(paramter);
			
			for (Iterator iterator = doctorList.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				if(!NullUtil.isNull(map.get("avatar"))){
					map.put("avatar", ConfigLoadUtil.loadConfig().getPropertie("accessAddress")+map.get("avatar"));
				}
			}
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("doctorList", doctorList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/queryDoctorList", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryDoctorList(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryDoctorList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查询医生列表信息，参数：" + requestMap.toString());
			}
			String showCount = !NullUtil.isNull(requestMap.get("showCount"))?requestMap.get("showCount"):"10";
			Map paramter = new HashMap();
			paramter.put("start", Integer.parseInt(requestMap.get("pageId"))*Integer.parseInt(showCount));
			paramter.put("limit", Integer.parseInt(showCount));
			paramter.put("diseaseId", requestMap.get("diseaseId"));
			paramter.put("sectionid", requestMap.get("sectionid"));
			paramter.put("hospitalID", requestMap.get("hospitalID"));
			
			List<Map> doctorList = null;
			if(requestMap.containsKey("startTime") 
					&& requestMap.containsKey("endTime") 
					&& !NullUtil.isNull(requestMap.get("startTime")) 
					&& !NullUtil.isNull(requestMap.get("endTime"))){
				
				paramter.put("startTime", requestMap.get("startTime"));
				paramter.put("endTime", requestMap.get("endTime"));
				doctorList = doctorService.queryDoctorListByTime(paramter);
			}else{
				doctorList = doctorService.queryDoctorList(paramter);
			}
			
			if(NullUtil.isNull(doctorList)){
				resultMap.put("code", Constants.FAIL_NULL_RESULT);
				resultMap.put("msg", Constants.FAIL_NULL_RESULT_MSG);
				return JsonUtils.obj2Json(resultMap);
			}
			
			for (Iterator iterator = doctorList.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				if(!NullUtil.isNull(map.get("doctorID"))){
					map.put("feeList", DoctorDC.getInstance().querydoctorFeeMap(StringUtil.getString(map.get("doctorID"))));
					map.put("objectID", StringUtil.getString(map.get("doctorID")));
					map.put("objectType", "2");
					List evaluateList=evaluateService.queryEvaluateList(map);
					map.put("betterRate", !NullUtil.isNull(evaluateList)?evaluateList.get(0):"0");
				}
				if(!NullUtil.isNull(map.get("avatar"))){
					map.put("avatar", ConfigLoadUtil.loadConfig().getPropertie("accessAddress")+map.get("avatar"));
				}
				if(!NullUtil.isNull(map.get("hospitalID"))){
					map.put("hospitalDetail", HospitalDC.getInstance().queryHospitalDetail(StringUtil.getString(map.get("hospitalID"))));
				}
			}
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			
			resultMap.put("doctorList", doctorList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/queryDoctorListByParams", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryDoctorListByParams(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryDoctorListByHospital method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("根据参数查询医生列表信息，参数：" + requestMap.toString());
			}
			Map paramter = new HashMap();
			paramter.put("hospitalID", requestMap.get("hospitalID"));
			paramter.put("sectionID", requestMap.get("sectionID"));
			List<Map> doctorList = doctorService.queryDoctorListByParams(paramter);
			
			for (Iterator iterator = doctorList.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				if(!NullUtil.isNull(map.get("avatar"))){
					map.put("avatar", ConfigLoadUtil.loadConfig().getPropertie("accessAddress")+map.get("avatar"));
				}
			}
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("doctorList", doctorList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/anon/myDoctorList", produces="application/json;charset=utf-8")
	@ResponseBody
	public String myDoctorList(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class myDoctorList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查询医生列表信息，参数：" + requestMap.toString());
			}
			List<Map> doctorList = doctorService.myDoctorList(requestMap);
			
			if(NullUtil.isNull(doctorList)){
				resultMap.put("code", Constants.FAIL_NULL_RESULT);
				resultMap.put("msg", Constants.FAIL_NULL_RESULT_MSG);
				return JsonUtils.obj2Json(resultMap);
			}
			
			for (Iterator iterator = doctorList.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				if(!NullUtil.isNull(map.get("doctorID"))){
					map.put("feeList", DoctorDC.getInstance().querydoctorFeeMap(StringUtil.getString(map.get("doctorID"))));
				}
				if(!NullUtil.isNull(map.get("avatar"))){
					map.put("avatar", ConfigLoadUtil.loadConfig().getPropertie("accessAddress")+map.get("avatar"));
				}
			}
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("doctorList", doctorList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/queryRecommendDoctorList", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryRecommendDoctorList(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("enter the " + this.getClass().getName() + " class queryRecommendDoctorList method.");
			}
			if (logger.isDebugEnabled()) {
				logger.debug("查询医生列表信息，参数：" + requestMap.toString());
			}
			String showCount = !NullUtil.isNull(requestMap.get("showCount"))?requestMap.get("showCount"):"10";
			Map paramter = new HashMap();
			paramter.put("start", Integer.parseInt(requestMap.get("pageId"))*Integer.parseInt(showCount));
			paramter.put("limit", Integer.parseInt(showCount));
			paramter.put("sectionid", requestMap.get("sectionid"));
			List<Map> doctorList = doctorService.queryRecommendDoctorList(paramter);
			
			for (Iterator iterator = doctorList.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				if(!NullUtil.isNull(map.get("doctorCount")) && Integer.parseInt(map.get("doctorCount")+"") > 0){
					List<Map> list = this.doctorService.queryRecommendDoctorListByDisease(map);
					map.put("recomDoctorList", list);
				}
			}
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("diseaseList", doctorList);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 修改/新增用户基本信息；
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/anon/addOrModifyDoctorInfo" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String modifyUserInfo(Doctor doctor,HttpServletRequest request) throws Exception {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("修改/新增用户基本信息，参数：" + requestMap.toString());
			}
			String loginName = doctor.getDoctorName();
			String password = doctor.getDoctorPassword();
			
			if(NullUtil.isNull(loginName)){
				resultMap.put("code", Constants.FAIL);
				resultMap.put("msg", Constants.FAIL_MSG);
				return JsonUtils.obj2Json(resultMap);
			}
			if(!NullUtil.isNull(password)&&password.length()<32){
				doctor.setDoctorPassword(Md5.md5Digest(password));
			}
			int count = doctorService.modifyDoctorBaseInfo(doctor);
			if(count <= 0){
				doctorService.saveDoctorBaseInfo(doctor);
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("userId", doctor.getDoctorID());
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 修改用户密码信息；
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/anon/modifyDoctorPassword" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String modifyPassword(HttpServletRequest request) throws Exception {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("修改用户密码信息，参数：" + requestMap.toString());
			}
			String password = requestMap.get("password");
			if(NullUtil.isNull(password)){
				resultMap.put("code", Constants.FAIL);
				resultMap.put("msg", Constants.FAIL_MSG);
				return JsonUtils.obj2Json(resultMap);
			}
			if(!NullUtil.isNull(password)&&password.length()<32){
				requestMap.put("password", Md5.md5Digest(password));
			}
			Integer count = doctorService.modifyPassword(requestMap);
			if(count > 0){
				resultMap.put("code", Constants.SUCCESS);
				resultMap.put("msg", Constants.SUCCESS_MSG);
			}else{
				resultMap.put("code", Constants.FAIL);
				resultMap.put("msg", Constants.FAIL_MSG);
			}
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	@RequestMapping(value = "/anon/saveOrUpdateGroup" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String saveDoctorGroupConsulation(DoctorGroup doctorGroup,HttpServletRequest request) throws Exception {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("修改用户密码信息，参数：" + requestMap.toString());
			}
			String type = requestMap.get("type");
			String answerDoctorIds = StringUtil.getString(requestMap.get("answerDoctorIds"));
			if(NullUtil.isNull(type)){
				resultMap.put("code", Constants.FAIL);
				resultMap.put("msg", Constants.FAIL_MSG);
				return JsonUtils.obj2Json(resultMap);
			}
			
			if("0".equals(type)){//添加和修改会诊信息；
				Integer count = doctorService.modifyDoctorGroupConsulation(requestMap);
				if(count > 0){
					resultMap.put("code", Constants.SUCCESS);
					resultMap.put("msg", Constants.SUCCESS_MSG);
				}else{
					doctorService.saveDoctorGroupConsulation(doctorGroup);
					if(!NullUtil.isNull(answerDoctorIds)){
						String[] answerDoctorId = answerDoctorIds.split("\\|");
						if(!NullUtil.isNull(answerDoctorId)&&answerDoctorId.length > 0){
							for (int i = 0; i < answerDoctorId.length; i++) {
								requestMap.clear();
								requestMap.put("seq", i+"");
								requestMap.put("gcId", doctorGroup.getId()+"");
								requestMap.put("doctorId", answerDoctorId[i]);
								doctorService.saveDoctorGroupConsulationR(requestMap);
							}
						}
					}
					resultMap.put("code", Constants.SUCCESS);
					resultMap.put("msg", Constants.SUCCESS_MSG);
				}
			}else{
				Map doctorGroupConsulationR = doctorService.queryDoctorGroupConsulationRDetail(requestMap);
				doctorGroupConsulationR.put("state", "1");
				doctorGroupConsulationR.put("id", doctorGroupConsulationR.get("gcId"));
				doctorService.modifyDoctorGroupConsulation(doctorGroupConsulationR);
				doctorService.modifyDoctorGroupConsulationR(requestMap);
				resultMap.put("code", Constants.SUCCESS);
				resultMap.put("msg", Constants.SUCCESS_MSG);
			}
			
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/anon/queryDoctorGroupList" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String querySelfDoctorGroupConsulationList(HttpServletRequest request) throws Exception {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			String showCount = !NullUtil.isNull(requestMap.get("showCount"))?requestMap.get("showCount"):"10";
			Map _paramter = new HashMap();
			_paramter.put("start", Integer.parseInt(requestMap.get("pageId"))*Integer.parseInt(showCount));
			_paramter.put("limit", Integer.parseInt(showCount));
			_paramter.put("type", requestMap.get("type"));
			_paramter.put("doctorId", requestMap.get("doctorId"));
			
			String type = requestMap.get("type");
			if(NullUtil.isNull(type)){
				resultMap.put("code", Constants.FAIL);
				resultMap.put("msg", Constants.FAIL_MSG);
				return JsonUtils.obj2Json(resultMap);
			}
			
			List<Map> doctoryList = null;
			if("0".equals(type)){//查询发送邀请者的会诊信息；
				doctoryList = this.doctorService.querySelfDoctorGroupConsulationList(_paramter);
				if(!NullUtil.isNull(doctoryList)){
					for (Iterator iterator = doctoryList.iterator(); iterator.hasNext();) {
						Map map = (Map) iterator.next();
						Map paramter = new HashMap();
						paramter.put("gcId", map.get("id"));
						map.put("answerDoctorList", this.doctorService.queryOtherDoctorGroupConsulationList(paramter));
						map.put("hospitalList", this.recordService.queryRecordList(map));
					}
				}
			}else{
				doctoryList = this.doctorService.queryOtherDoctorGroupConsulationList(_paramter);
				if(!NullUtil.isNull(doctoryList)){
					for (Iterator iterator = doctoryList.iterator(); iterator.hasNext();) {
						Map map = (Map) iterator.next();
						List<Map> list = this.doctorService.querySelfDoctorGroupConsulationList(map);
						map.put("askDoctorInfo",!NullUtil.isNull(list)?list.get(0):null);
						map.put("patientID",!NullUtil.isNull(list)?list.get(0).get("patientID"):null);
						map.put("hospitalList", this.recordService.queryRecordList(map));
					}
				}
			}
			resultMap.put("doctoryList", doctoryList);
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
	 * 登录获取用户基本信息；
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/anon/doctorLogin" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String login(HttpServletRequest request) throws Exception {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("登录获取用户基本信息，参数：" + requestMap.toString());
			}
			
			String loginName = requestMap.get("doctorName");
			String password = requestMap.get("doctorPassword");
			if(NullUtil.isNull(loginName)||NullUtil.isNull(password)){
				resultMap.put("code", Constants.FAIL);
				resultMap.put("msg", Constants.FAIL_MSG);
				return JsonUtils.obj2Json(resultMap);
			}
			if(!NullUtil.isNull(password)&&password.length()<32){
				requestMap.put("doctorPassword", Md5.md5Digest(password));
			}
			Map user = doctorService.queryDoctorLogin(requestMap);
			if(!NullUtil.isNull(user)){
				user.put("feeList", DoctorDC.getInstance().querydoctorFeeMap(StringUtil.getString(user.get("doctorID"))));
				user.put("avatar", ConfigLoadUtil.loadConfig().getPropertie("accessAddress") + user.get("avatar"));
			}
			
			if(!NullUtil.isNull(user)){
				resultMap.put("code", Constants.SUCCESS);
				resultMap.put("msg", Constants.SUCCESS_MSG);
				resultMap.put("userInfo", user);
				resultMap.put("subAccount", createSubAccount(requestMap,user));
			}else{
				resultMap.put("code", Constants.FAIL);
				resultMap.put("msg", Constants.FAIL_MSG);
			}
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 登录获取用户基本信息；
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/anon/doctorDetail" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String doctorDetail(HttpServletRequest request) throws Exception {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("登录获取用户基本信息，参数：" + requestMap.toString());
			}
			
			String doctorID = requestMap.get("doctorID");
			if(NullUtil.isNull(doctorID)){
				resultMap.put("code", Constants.FAIL);
				resultMap.put("msg", Constants.FAIL_MSG);
				return JsonUtils.obj2Json(resultMap);
			}
			
			Map user = doctorService.queryDoctorDetail(requestMap);
			Float betterRate = evaluateService.queryBetterRate(requestMap);
			if(!NullUtil.isNull(user)){
				user.put("betterRate", betterRate);
				user.put("feeList", DoctorDC.getInstance().querydoctorFeeMap(StringUtil.getString(user.get("doctorID"))));
				user.put("avatar", ConfigLoadUtil.loadConfig().getPropertie("accessAddress") + user.get("avatar"));
				resultMap.put("code", Constants.SUCCESS);
				resultMap.put("msg", Constants.SUCCESS_MSG);
				resultMap.put("userInfo", user);
				resultMap.put("subAccount", createSubAccount(requestMap,user));
			}else{
				resultMap.put("code", Constants.FAIL_NULL_RESULT);
				resultMap.put("msg", Constants.FAIL_NULL_RESULT_MSG);
			}
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	
	/**
	 * 登录获取用户基本信息；
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/anon/queryReport" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryReport(HttpServletRequest request) throws Exception {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			String days = requestMap.get("days");
			if(NullUtil.isNull(days)){
				resultMap.put("code", Constants.FAIL_NULL_1);
				resultMap.put("msg", Constants.FAIL_NULL_MSG);
				return JsonUtils.obj2Json(resultMap);
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("todaySubscribe", this.subscribeService.queryTodaySubscribe(requestMap));
			resultMap.put("recentConsult", this.consultationService.queryTodayConsultation(requestMap));
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	@SuppressWarnings("rawtypes")
	private Map createSubAccount(Map<String,String> requestMap,Map user) throws Exception{
		requestMap.put("userId", StringUtil.getString(user.get("doctorID")));
		requestMap.put("userType", "1");
		return this.createAccount(requestMap);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/anon/createCallAccount" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String createCallAccount(HttpServletRequest request) throws Exception {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		return JsonUtils.obj2Json(this.createAccount(requestMap));
	}
	
	public Map createAccount(Map<String,String> requestMap) throws Exception {
		Map multMedia = multiMediaService.queryMultMedia(requestMap);
		if(NullUtil.isNull(multMedia)){
			CCPRestSDK restAPI = new CCPRestSDK();
			restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
			restAPI.setAccount(SID, TOKEN);// 初始化主帐号名称和主帐号令牌
			restAPI.setAppId(APPID);// 初始化应用ID
			Map<String,Object> result = restAPI.createSubAccount(StringTools.getBillno());
			Map responseMap = this.constructCreateSubAccountParamter(requestMap, result);
			Integer count = multiMediaService.modifyMultMedia(responseMap);
			if(count <= 0){
				multiMediaService.saveMultMediaInfo(responseMap);
			}
			return responseMap;
		}else{
			return multMedia;
		}
	}
	
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	private Map constructCreateSubAccountParamter(Map<String,String> requestMap,Map<String,Object> resultMap){
		if(NullUtil.isNull(resultMap) || NullUtil.isNull(requestMap)){
			return null;
		}
		
		Map data = (Map)resultMap.get("data");
		Map SubAccount = (Map)data.get("SubAccount");
		SubAccount.put("userId", StringUtil.getString(requestMap.get("userId")));
		SubAccount.put("userType", StringUtil.getString(requestMap.get("userType")));
		SubAccount.put("status", "0");
		return SubAccount;
	}
}

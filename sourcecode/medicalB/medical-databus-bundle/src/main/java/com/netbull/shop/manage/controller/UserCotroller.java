package com.netbull.shop.manage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.DateUtil;
import com.netbull.shop.common.util.Md5;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.common.util.XMLParser;
import com.netbull.shop.common.vo.Patient;
import com.netbull.shop.common.vo.UserAcceptInfoVo;
import com.netbull.shop.common.vo.UserVo;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.common.util.AreaDC;
import com.netbull.shop.manage.common.util.HttpXmlUtil;
import com.netbull.shop.manage.weixin.service.AcceptInfoService;
import com.netbull.shop.manage.weixin.service.UserBaseInfoService;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;
import com.netbull.shop.util.UploadFileUtil;


@Controller
public class UserCotroller {

	private static final Logger logger = Logger.getLogger("controlerLog");
	@Autowired
	private UploadFileUtil uploadFileUtil;
	@Resource
	private AcceptInfoService acceptInfoService;
	@Resource
	private UserBaseInfoService userBaseInfoService;
	
	
	/**
	 * 修改/新增用户基本信息；
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/anon/addOrModifyPatientInfo" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String modifyUserInfo(Patient patient,HttpServletRequest request) throws Exception {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("修改/新增用户基本信息，参数：" + requestMap.toString());
			}
			String loginName = patient.getNickName();
			String password = patient.getPatientPassword();
			
			if(NullUtil.isNull(loginName)){
				resultMap.put("code", Constants.FAIL);
				resultMap.put("msg", Constants.FAIL_MSG);
				return JsonUtils.obj2Json(resultMap);
			}
			if(!NullUtil.isNull(password)&&password.length()<32){
				patient.setPatientPassword(Md5.md5Digest(password));
			}
			
			int count = userBaseInfoService.modifyUserBaseInfo(patient);
			if(count <= 0){
				userBaseInfoService.saveUserBaseInfo(patient);
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("userId", patient.getPatientID());
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
	@RequestMapping(value = "/anon/patientRegister" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String patientRegister(Patient patient,HttpServletRequest request) throws Exception {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("修改/新增用户基本信息，参数：" + requestMap.toString());
			}
			String loginName = patient.getPatientName();
			String password = patient.getPatientPassword();
			
			if(NullUtil.isNull(loginName)){
				resultMap.put("code", Constants.FAIL);
				resultMap.put("msg", Constants.FAIL_MSG);
				return JsonUtils.obj2Json(resultMap);
			}
			if(!NullUtil.isNull(password)&&password.length()<32){
				patient.setPatientPassword(Md5.md5Digest(password));
			}
			
			Patient user = userBaseInfoService.queryUserBaseInfo(requestMap);
			
			if(NullUtil.isNull(user)){
				userBaseInfoService.saveUserBaseInfo(patient);
			}else{
				resultMap.put("code", Constants.FAIL_3);
				resultMap.put("msg", Constants.FAIL_MSG_9);
				resultMap.put("userId", patient.getPatientID());
				return JsonUtils.obj2Json(resultMap);
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("userId", patient.getPatientID());
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
	@RequestMapping(value = "/anon/modifyPassword" , produces="application/json;charset=utf-8")
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
			Integer count = userBaseInfoService.modifyPassword(requestMap);
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
	
	/**
	 * 登录获取用户基本信息；
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/anon/patientLogin" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String login(HttpServletRequest request) throws Exception {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("登录获取用户基本信息，参数：" + requestMap.toString());
			}
			
			String loginName = requestMap.get("patientName");
			String password = requestMap.get("patientPassword");
			if(NullUtil.isNull(loginName)||NullUtil.isNull(password)){
				resultMap.put("code", Constants.FAIL);
				resultMap.put("msg", Constants.FAIL_MSG);
				return JsonUtils.obj2Json(resultMap);
			}
			if(!NullUtil.isNull(password)&&password.length()<32){
				requestMap.put("patientPassword", Md5.md5Digest(password));
			}
			Patient user = userBaseInfoService.queryUserBaseInfo(requestMap);
			if(!NullUtil.isNull(user)){
				resultMap.put("code", Constants.SUCCESS);
				resultMap.put("msg", Constants.SUCCESS_MSG);
				resultMap.put("userInfo", user);
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
	 * 文件上传接口，包括上传头像、文件等；
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/anon/upload",produces="application/json;charset=utf-8")
	@ResponseBody
	public String upload(Patient patient,HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("上传文件，参数：" + requestMap.toString());
			}
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
			CommonsMultipartFile file = null;
			if(!NullUtil.isNull(multipartRequest.getFile("upload"))){
				file = (CommonsMultipartFile) multipartRequest.getFile("upload");
			}
			
			String trueFileName = null;
			if(!NullUtil.isNull(file)){
				trueFileName = uploadFileUtil.createFile(file,StringTools.getBillno());
			}
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("fileName", trueFileName);
			resultMap.put("iconUrl", ConfigLoadUtil.loadConfig().getPropertie("accessAddress") + trueFileName);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 短信发送操作；
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/anon/sendSMS" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String sendSMS(HttpServletRequest request) throws IOException {
		Map<String,String> paramterMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String,Object> numberSet = new HashMap<String,Object>();
		numberSet.put("NumberSet", paramterMap);
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("短信发送操作；参数：" + paramterMap.toString());
			}
			String sendSmsUrl = ConfigLoadUtil.loadConfig().getPropertie("sendSmsUrl");
			String smsAccount = ConfigLoadUtil.loadConfig().getPropertie("smsAccount");
			String smsPassword = ConfigLoadUtil.loadConfig().getPropertie("smsPassword");
			
			Map<String,Object> requestMap = new HashMap<String,Object>();
			String isrepeat = StringUtil.getString(paramterMap.get("isrepeat"));
			if(!NullUtil.isNull(paramterMap)&&"1".equals(isrepeat)){
				requestMap.put("loginName", paramterMap.get("phone"));
				/*UserVo user = this.userBaseInfoService.queryUserBaseInfo(requestMap);
				if(!NullUtil.isNull(user)){
					resultMap.put("code", Constants.SUCCESS);
					resultMap.put("isexit", "true");
					resultMap.put("msg", Constants.SUCCESS_MSG);
					return JsonUtils.obj2Json(resultMap);
				}*/
			}
			
			requestMap.put("userCode", smsAccount);
			requestMap.put("password", smsPassword);
			requestMap.put("serviceCount", "1");
			requestMap.put("content", StringUtil.getString(paramterMap.get("content")));
			requestMap.put("extend", "");
			requestMap.put("time", DateUtil.getDateDayFormat());
			requestMap.put("dataSign", "");
			requestMap.put("NumberSets", numberSet);
			requestMap.put("batchId", StringTools.getBillno());
			
			String resp = HttpXmlUtil.post(sendSmsUrl, JsonUtils.obj2Json(requestMap),"");
			Map respMap = XMLParser.string2Map(resp);
			String resultCode = !NullUtil.isNull(respMap)?StringUtil.getString(respMap.get("resultCode")):null;
			if(!NullUtil.isNull(resultCode)&&Constants.SUCCESS.equals(resultCode)){
				resultMap.put("code", Constants.SUCCESS);
				resultMap.put("msg", Constants.SUCCESS_MSG);
				resultMap.put("isexit", "false");
			}else{
				resultMap.put("code", Constants.FAIL);
				resultMap.put("msg", Constants.FAIL_MSG);
				resultMap.put("isexit", "false");
			}
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 省市区列表查询；
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/anon/queryAreaInfoList" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryAreaInfoList(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("省市区列表查询；参数：" + requestMap.toString());
			}
			String parentid = requestMap.get("parentid");
			String type = requestMap.get("type");
			
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("areaList", AreaDC.getInstance().queryAreaList(parentid, type));
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 收货地址列表查询；
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/anon/queryAcceptInfoList" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryAcceptInfoList(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("收货地址列表查询；参数：" + requestMap.toString());
			}
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("acceptList", acceptInfoService.queryAcceptInfoList(requestMap));
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	@RequestMapping(value = "/anon/queryAcceptInfo" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryAcceptInfo(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("收货地址列表查询；参数：" + requestMap.toString());
			}
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("acceptObj", acceptInfoService.queryAcceptInfo(requestMap));
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 更新收货地址信息，如果更新失败就添加收货地址；并返回新的收货信息；
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/anon/modifyAcceptInfo" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String modifyAcceptInfo(UserAcceptInfoVo userAcceptInfoVo,HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("修改收货地址信息，参数：" + requestMap.toString());
			}
			Integer count = acceptInfoService.modifyAcceptInfo(userAcceptInfoVo);
			if(count <= 0){
				acceptInfoService.saveAcceptInfo(userAcceptInfoVo);
			}
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
			resultMap.put("acceptId", userAcceptInfoVo.getId()+"");
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
	
	
	/**
	 * 收货地址列表查询；
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/anon/deleteAcceptInfo" , produces="application/json;charset=utf-8")
	@ResponseBody
	public String deleteAcceptInfo(UserAcceptInfoVo userAcceptInfoVo,HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("收货地址列表查询；参数：" + requestMap.toString());
			}
			userAcceptInfoVo.setStatus("-1");
			acceptInfoService.modifyAcceptInfo(userAcceptInfoVo);
			resultMap.put("code", Constants.SUCCESS);
			resultMap.put("msg", Constants.SUCCESS_MSG);
		} catch (Exception e) {
			logger.error("操作失败，原因："+e.getMessage());
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
		}
		return JsonUtils.obj2Json(resultMap);
	}
}

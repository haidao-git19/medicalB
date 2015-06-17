package com.netbull.shop.manage.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.page.Page;
import com.netbull.shop.common.task.Const;
import com.netbull.shop.common.util.ConvertUtils;
import com.netbull.shop.common.util.CookiesUtils;
import com.netbull.shop.common.util.DesEncrypt;
import com.netbull.shop.common.util.Md5;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.PutDuUtil;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.common.util.TokenDC;
import com.netbull.shop.common.vo.UserVo;
import com.netbull.shop.manage.common.util.HttpXmlUtil;
import com.netbull.shop.manage.common.util.RequestUtils;
import com.netbull.shop.manage.common.util.UploadPicUtil;
import com.netbull.shop.manage.common.util.ValiCodeUtil;
import com.netbull.shop.manage.weixin.dao.UserDao;
import com.netbull.shop.manage.weixin.service.UserService;
import com.netbull.shop.util.DataTableUtils;
import com.netbull.shop.util.JsonUtils;

@Controller
public class UserController {

	private static final Logger logger = Logger.getLogger("controlerLog");
	private static final String MYBATIS_PREFIX=UserDao.class.getName();
	@Autowired
	private UserService userService;
	
	/********************************后台管理***************************************/
	/**
	 * 用户管理
	 * @return
	 */
	@RequestMapping("/user/userList")
	public String userList(){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class userList method.");
		}
		return "user/userList";
	}
	
	/**
	 * 分页查询用户信息
	 * @param sEcho
	 * @param iColumns
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/user/queryUserList")
	@ResponseBody
	public  Map<String, Object> queryUserList(Integer sEcho, Integer iColumns, Integer iDisplayStart, Integer iDisplayLength,HttpServletRequest request) throws UnsupportedEncodingException {
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryUserList method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		List<List<?>> aaData = new ArrayList<List<?>>();
		
		Page page = userService.queryUserPage(iDisplayStart, iDisplayLength, requestMap);	//得到分页数据
		Map<String, Object> resultMap = DataTableUtils.initResultMap(sEcho , page.getTotal());
		
		aaData = ConvertUtils.convertClazz((List)page.getResult(),"id","userId","loginName","nickName","age","sex","phone","createTime","updateTime","");
		resultMap.put("aaData", aaData);
		return resultMap;
	}
	
	/**
	 * 用户信息详情
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/user/userDetail")
	public String userDetail(ModelMap model,Integer id){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class userDetail method.");
		}
		String realPath=ConfigLoadUtil.loadConfig().getPropertie("accessAddress");
		UserVo userVo=new UserVo();
		if(id!=null&&id!=0){
			userVo=userService.get(MYBATIS_PREFIX, id);
		}
		userVo.setIcon(realPath+userVo.getIcon());
		model.addAttribute("userVo", userVo);
		return "user/userDetail";
	}
	
	/********************************微信***************************************/
	
	/**
	 * 用户注册
	 * @return
	 */
	@RequestMapping(value="/anon/register",produces="text/plain;charset=utf-8")
	public String register(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class register method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String randPref=requestMap.get("randPref");
		if(!StringUtil.isEmpty(randPref)){
			randPref=StringUtil.getString(new Random().nextInt(100000));
		}
		
		String code=PutDuUtil.getLoginCode(request);
		String openId=PutDuUtil.handleDispatchOpenId(request, response, code);
		//String openId="oMrqKjiwq-D8BLJVHLdVbCjKk080";
		if (logger.isDebugEnabled()) {
			logger.debug("注册获取微信用户openId--->>> " + openId + " <<<");
		}
		
		HttpSession session=request.getSession(true);
		session.setAttribute("session_"+randPref, openId);
		
		model.addAttribute("randPref", randPref);
		return "login/register_f";
	}
	
	/**
	 * 注册检查用户是否已存在
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/anon/checkAccount")
	@ResponseBody
	public Map<String,String> checkAccount(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class checkAccount method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		HttpSession session=request.getSession(true);
		String openId=(String) session.getAttribute("session_"+requestMap.get("randPref"));
		if (logger.isDebugEnabled()) {
			logger.debug("注册时验证用户是否已注册时获取微信用户openId--->>> " + openId + " <<<");
		}
		
		UserVo userVo=userService.queryUserByParams(requestMap);
		Map<String,String> map=new HashMap<String, String>();
		if(!NullUtil.isNull(userVo)){
			map.put("existInfo", "该用户已存在!");
		}
		return map;
	}
	
	/**
	 * 找回密码检查用户是否存在
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/anon/checkAccount2")
	@ResponseBody
	public Map<String,String> checkAccount2(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class checkAccount2 method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		
		UserVo userVo=userService.queryUserByParams(requestMap);
		Map<String,String> map=new HashMap<String, String>();
		if(NullUtil.isNull(userVo)){
			map.put("notExistInfo", "该号码尚未注册,请先注册!");
		}
		return map;
	}
	
	/**
	 * 发送验证码
	 * @param request
	 */
	@RequestMapping(value="/anon/sendValiCode")
	@ResponseBody
	public Map<String,String> sendValiCode(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class sendValiCode method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String loginName=requestMap.get("loginName");
		String valiCode=ValiCodeUtil.createValiCode();
		//String valiCode="666666";
		if (logger.isDebugEnabled()) {
			logger.debug(">>>>>>>>>验证码为>>>>>>> " + valiCode + " <<<<<<<<<<<");
		}
		
		Map<String,String> paramsMap=new HashMap<String, String>();
		paramsMap.put("phone", loginName);
		paramsMap.put("content", "尊敬的用户,您的网牛注册验证码为："+valiCode+"。(若不是本人操作,请忽略。)");
		
		String sendSMSUrl = ConfigLoadUtil.loadConfig().getPropertie("sendSMSUrl");
		String json=HttpXmlUtil.doPost(sendSMSUrl, paramsMap);
		
		Map<String,Object> resultMap=JsonUtils.json2Map(json);
		String code=(String) resultMap.get("code");
		
		Map<String,String> map=new HashMap<String, String>();
		if(StringTools.equals(code, "0")){
			HttpSession session=request.getSession();
			session.setMaxInactiveInterval(60);
			session.setAttribute(loginName+"_session", valiCode);
			map.put("info", "SEND_SUCCESS");
		}
		return map;
	}
	
	/**
	 * 检查验证码正确性
	 * @param request
	 */
	@RequestMapping(value="/anon/checkValiCode")
	@ResponseBody
	public Map<String,String> checkValiCode(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class checkValiCode method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String loginName=requestMap.get("loginName");
		String code=requestMap.get("valiCode");
		
		HttpSession session=request.getSession();
		String valiCode=(String) session.getAttribute(loginName+"_session");
		
		Map<String,String> resultMap=new HashMap<String, String>();
		if(StringTools.equals(code, valiCode)){
			resultMap.put("info", "SUCCESS");
			session.removeAttribute(loginName+"_session");
		}
		return resultMap;
	}
	
	/**
	 * 用户进一步注册
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/anon/registerNext",produces="text/plain;charset=utf-8")
	public String registerNext(ModelMap model,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class registerNext method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		
		Map paramterMap = TokenDC.getInstance().getWechatVo(Const.NETBULL_LOGIN_CODE);
		String appid = StringUtil.getString(paramterMap.get("appId"));
		String jsapi_ticket =  StringUtil.getString(paramterMap.get("jsapi_ticket"));
		String url = ConfigLoadUtil.loadConfig().getPropertie("domainName")+"/anon/registerNext";
		
		Map<String, String> ret = StringTools.sign(jsapi_ticket, url);
		model.addAttribute("appId",appid);
		model.addAttribute("timestamp",ret.get("timestamp"));
		model.addAttribute("nonceStr",ret.get("nonceStr"));
		model.addAttribute("signature",ret.get("signature"));
		
		model.addAttribute("loginName", requestMap.get("loginName"));
		model.addAttribute("randPref", requestMap.get("randPref"));
		return "login/register_s";
	}
	
	/**
	 * 上传头像
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/anon/uploadIcon",produces="application/json;charset=utf-8")
	@ResponseBody
	public Map<String,String> uploadIcon(HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class uploadIcon method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");
		Map<String,String> map=UploadPicUtil.uploadPic(requestMap,file);
		return map;
	}
	
	/**
	 * 保存或更新用户信息
	 * @param request
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/anon/userSave",produces="application/json;charset=utf-8")
	@ResponseBody
	public Map<String,String> userSave(HttpServletRequest request,HttpServletResponse response) throws Exception{
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class userSave method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		Map<String,String> resultMap=new HashMap<String, String>();
		HttpSession session=request.getSession(true);
		String openId=(String) session.getAttribute("session_"+requestMap.get("randPref"));
		requestMap.put("openId", openId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("注册保存或更新用户信息获取微信用户openId--->>> " + openId + " <<<");
		}
		
		UserVo user=userService.queryUserByParams(requestMap);
		if(!NullUtil.isNull(user)){
			requestMap.put("userId", user.getUserId());
		}
		
		Map<String,String> param=new HashMap<String, String>();
		param.put("openId", openId);
		UserVo _userVo=userService.queryUserByParams(param);
		if(!NullUtil.isNull(_userVo)){
			param.put("loginName", _userVo.getLoginName());
			param.put("openId", "none");
			userService.updateUser(param);
		}
		
		String registerUrl = ConfigLoadUtil.loadConfig().getPropertie("registerUrl");
		String json_reg=HttpXmlUtil.doPost(registerUrl, requestMap);
		
		Map<String,Object> resultMap_reg=JsonUtils.json2Map(json_reg);
		String code=(String) resultMap_reg.get("code");
		
		if(StringTools.equals(code, "0")){
				
				/*注册成功则调用一次登录接口获取用户信息*/
				Map<String,String> loginMap=new HashMap<String, String>();
				loginMap.put("loginName", requestMap.get("loginName"));
				loginMap.put("password", requestMap.get("password"));
				
				String loginUrl = ConfigLoadUtil.loadConfig().getPropertie("loginUrl");
				String json_log=HttpXmlUtil.doPost(loginUrl, loginMap);
				Map<String,Object> resultMap_log=JsonUtils.json2Map(json_log);
				Map<String,String> userInfo=(Map<String, String>) resultMap_log.get("userInfo");
				
				CookiesUtils.setCookie(response, "userId", DesEncrypt.getEncryptString((String)userInfo.get("userId")),7*24*3600);
				
				resultMap.put("info", "保存成功!");
		}else{
			resultMap.put("error", "注册失败!");
		}
		return resultMap;
	}
	
	/**
	 * 用户登录
	 * @return
	 */
	@RequestMapping(value="/anon/login",produces="text/plain;charset=utf-8")
	public String login(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class login method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		
		String urlParam=request.getQueryString();
		
		if(StringUtil.isEmpty(urlParam)){
			int returnUrlIndex=urlParam.indexOf("returnUrl");
			if(returnUrlIndex!=-1){
				String returnUrl=urlParam.substring(returnUrlIndex+10);
				model.addAttribute("returnUrl", returnUrl);
			}
		}
		
		String randPref=requestMap.get("randPref");
		if(!StringUtil.isEmpty(randPref)){
			randPref=StringUtil.getString(new Random().nextInt(100000));
		}
		
		String code=PutDuUtil.getLoginCode(request);
		String openId=PutDuUtil.handleDispatchOpenId(request, response, code);
		//String openId="oMrqKjiwq-D8BLJVHLdVbCjKk080";
		if (logger.isDebugEnabled()) {
			logger.debug("登录获取微信用户openId--->>> " + openId + " <<<");
		}
		
		HttpSession session=request.getSession(true);
		session.setAttribute("session_"+randPref, openId);
		model.addAttribute("randPref", randPref);
		return "login/login";
	}
	
	/**
	 * 登录验证
	 * @param request
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/anon/loginValidate",produces="application/json;charset=utf-8")
	@ResponseBody
	public Map<String,String> loginValidate(HttpServletRequest request,HttpServletResponse response) throws Exception{
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class loginValidate method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String loginName=requestMap.get("loginName");
		String password=Md5.md5Digest(requestMap.get("password"));
		
		HttpSession session=request.getSession(true);
		String randPref=requestMap.get("randPref");
		String openId=(String) session.getAttribute("session_"+randPref);
		requestMap.put("openId", openId);
		if (logger.isDebugEnabled()) {
			logger.debug("登录验证获取openId >>>>>>>>> " + openId + " .");
		}
		
		Map<String,String> paramsMap=new HashMap<String,String>();
		paramsMap.put("loginName", loginName);
		paramsMap.put("password", password);
		
		String loginUrl = ConfigLoadUtil.loadConfig().getPropertie("loginUrl");
		String json=HttpXmlUtil.doPost(loginUrl, paramsMap);
		
		Map<String,Object> resultMap=JsonUtils.json2Map(json);
		String code=(String) resultMap.get("code");
		Map userInfoMap= (Map) resultMap.get("userInfo");
		if(!NullUtil.isNull(userInfoMap)){
			CookiesUtils.setCookie(response, "userId", DesEncrypt.getEncryptString((String)userInfoMap.get("userId")), 7*24*3600);
		}
//		map.put("success", "登录成功!");
		Map<String,String> map=new HashMap<String,String>();
		if(StringTools.equals(code, "0")){
			
			if(!StringUtil.isEmpty(openId)){
				map.put("error", "系统异常，登录失败!");
				return map;
			}
			
			Map<String,String> param=new HashMap<String, String>();
			param.put("openId", openId);
			logger.debug("param >>>>>>>>> " + param.toString() + " .");
			UserVo _userVo=userService.queryUserByParams(param);
			if(!NullUtil.isNull(_userVo)){
				param.put("loginName", _userVo.getLoginName());
				param.put("openId", "none");
				userService.updateUser(param);
			}
			paramsMap=new HashMap<String,String>();
			paramsMap.put("loginName", loginName);
			paramsMap.put("openId", openId);
			userService.updateUser(paramsMap);
			
			CookiesUtils.setCookie(response, "userId", DesEncrypt.getEncryptString((String)userInfoMap.get("userId")), 7*24*3600);
			map.put("success", "登录成功!");
		}else{
			map.put("fail", "登录失败,用户名或密码错误!");
		}
		return map;
	}
	
	/**
	 * 找回密码页面
	 * @return
	 */
	@RequestMapping(value="/anon/findPwd",produces="text/plain;charset=utf-8")
	public String findPwd_f(ModelMap model,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class findPwd_f method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		model.addAttribute("randPref", requestMap.get("randPref"));
		return "login/findPwd_f";
	}
	
	/**
	 * 设置新密码页面
	 * @return
	 */
	@RequestMapping(value="/anon/findPwdNext",produces="text/plain;charset=utf-8")
	public String findPwd_s(ModelMap model,HttpServletRequest request){
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class findPwd_s method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		model.addAttribute("loginName", requestMap.get("loginName"));
		model.addAttribute("randPref", requestMap.get("randPref"));
		return "login/findPwd_s";
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @throws Exception 
	 */
	@RequestMapping(value="/anon/confirmNewPwd")
	@ResponseBody
	public Map<String,String> confirmNewPwd(HttpServletRequest request) throws Exception{
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class confirmNewPwd method.");
		}
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);
		String loginName=requestMap.get("loginName");
		String password=Md5.md5Digest(requestMap.get("password"));
		
		Map<String,String> paramsMap=new HashMap<String, String>();
		paramsMap.put("loginName", loginName);
		paramsMap.put("password", password);
		
		String modifyPwdUrl = ConfigLoadUtil.loadConfig().getPropertie("modifyPwdUrl");
		String json=HttpXmlUtil.doPost(modifyPwdUrl, paramsMap);
		
		Map<String,Object> resultMap=JsonUtils.json2Map(json);
		String code=(String) resultMap.get("code");
		
		Map<String,String> map=new HashMap<String, String>();
		if(StringTools.equals(code, "0")){
			map.put("info", "修改成功!");
		}else{
			map.put("error", "修改失败!");
		}
		return map;
	}
	
	@RequestMapping("/anon/queryUserId")
	@ResponseBody
	public String queryUserId(ModelMap model,HttpServletRequest request) throws Exception{
		if (logger.isDebugEnabled()) {
			logger.debug("enter the " + this.getClass().getName() + " class queryUserId method.");
		}
		Cookie cookie = CookiesUtils.getCookie(request, "userId");
		if(NullUtil.isNull(cookie)){
			this.logger.error("获取cookie失败");
			return null;
		}
		String userId = DesEncrypt.getDecryptString(cookie.getValue());
		return  JsonUtils.obj2Json(userId);
	}
}

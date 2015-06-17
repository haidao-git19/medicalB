package com.netbull.shop.manage.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.netbull.shop.common.util.Base64Coder;
import com.netbull.shop.common.util.DateUtil;
import com.netbull.shop.common.util.Md5;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.common.util.XMLParser;
import com.netbull.shop.manage.common.constant.Const;
import com.netbull.shop.manage.common.constant.Constants;
import com.netbull.shop.manage.common.util.HttpXmlUtil;
import com.netbull.shop.manage.weixin.service.MultiMediaService;
import com.netbull.shop.util.JsonUtils;
import com.netbull.shop.util.RequestUtils;


@SuppressWarnings({ "unchecked", "rawtypes" })
@Controller
public class MultiMediaCotroller extends AbstractController {

	private static final Logger logger = Logger.getLogger("controlerLog");

	@Resource
	private MultiMediaService multiMediaService;
	
	/**
	 * 通过HTTPS POST方式提交请求，云通讯平台收到请求后，返回子账户信息。
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/anon/createSubAccounts", produces="application/json;charset=utf-8")
	@ResponseBody
	public String createSubAccounts(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);;
		Map<String,Object> responseMap = new HashMap<String,Object>();
		
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(SID, TOKEN);// 初始化主帐号名称和主帐号令牌
		restAPI.setAppId(APPID);// 初始化应用ID
		Map<String,Object> resultMap = restAPI.createSubAccount(StringTools.getBillno());
		
		String code = !NullUtil.isNull(resultMap.get("statusCode"))?StringUtil.getString(resultMap.get("statusCode")):null;
		if(!Const.RONGLIAN_ACCOUNT_SUCCESS.equals(code)){
			logger.error("调用创建子账户接口失败。");
			resultMap.clear();
			resultMap.put("code", Constants.FAIL);
			resultMap.put("msg", Constants.FAIL_MSG);
			return JsonUtils.obj2Json(resultMap);
		}
		
		responseMap = this.constructCreateSubAccountParamter(requestMap, resultMap);
		Integer count = multiMediaService.modifyMultMedia(responseMap);
		if(count <= 0){
			multiMediaService.saveMultMediaInfo(responseMap);
		}
		resultMap.clear();
		resultMap.put("code", Constants.SUCCESS);
		resultMap.put("msg", Constants.SUCCESS_MSG);
		resultMap.put("subAccount", responseMap);
		return JsonUtils.obj2Json(resultMap);
	}
	
	/**
	 * 通过HTTPS POST方式提交请求，云通讯平台收到请求后，返回关闭子账户的结果。
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/anon/CloseSubAccount", produces="application/json;charset=utf-8")
	@ResponseBody
	public String CloseSubAccount(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = new HashMap<String,String>();
		Map<String,Object> responseMap = new HashMap<String,Object>();
		
		String time = DateUtil.fotmatDate14(new Date());
		String sig = Md5.MD5(SID+TOKEN+time).toUpperCase();
		String authorization = Base64Coder.encodeString(SID+":"+ time);
		String url = Rest_URL + "/2013-12-26/Accounts/" + SID +"/CloseSubAccount?sig="+sig;

		Map result = multiMediaService.queryMultMedia(requestMap);
		requestMap.put("subAccountSid", StringUtil.getString(result.get("subAccountSid")));
		String json=HttpXmlUtil.post(url, JsonUtils.obj2Json(requestMap),authorization);
		Map<String,Object> resultMap=JsonUtils.json2Map(json);
		
		String code = NullUtil.isNull(resultMap.get("statusCode"))?StringUtil.getString(resultMap.get("statusCode")):null;
		if(!Const.RONGLIAN_ACCOUNT_SUCCESS.equals(code)){
			logger.error("调用关闭子账户接口失败。");
			responseMap.put("code", Constants.FAIL);
			responseMap.put("msg", Constants.FAIL_MSG);
			return JsonUtils.obj2Json(responseMap);
		}
		
		multiMediaService.modifyMultMedia(constructCloseSubAccountParamter(result));
		
		responseMap.put("code", Constants.SUCCESS);
		responseMap.put("msg", Constants.SUCCESS_MSG);
		return JsonUtils.obj2Json(responseMap);
	}
	
	
	/**
	 * 通过HTTPS POST方式提交请求，云通讯平台收到请求后，返回关闭子账户的结果。
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/anon/queryMultMedia", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryMultMedia(HttpServletRequest request) throws IOException {
		Map<String,String> requestMap = RequestUtils.parameterToMap(request);;
		Map<String,Object> responseMap = new HashMap<String,Object>();
		
		Map result = multiMediaService.queryMultMedia(requestMap);
		
		responseMap.put("code", Constants.SUCCESS);
		responseMap.put("msg", Constants.SUCCESS_MSG);
		responseMap.put("result", result);
		return JsonUtils.obj2Json(responseMap);
	}
	
	/**
	 * 此接口用于云通讯平台向第三方应用服务器做呼叫鉴权。
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/anon/callAuth", produces="application/json;charset=utf-8")
	public void callAuth(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String,Object> responseMap = new HashMap<String,Object>();
		String responseBody = StringTools.inputStream2String(request.getInputStream());
		Map<String,Object> requestMap = XMLParser.string2Map(responseBody);
		String body = "";
		
		if(NullUtil.isNull(requestMap) || NullUtil.isNull(requestMap.get("action"))){
			body = parseError(responseMap);
		}else{
			String action = StringUtil.getString(requestMap.get("action"));
			if (action.equals("CallAuth")) {
				// 解析呼叫鉴权
				body = parseCallAuth(responseMap);
			} else if (action.equals("CallEstablish")) {
				// 解析摘机请求
				body = parseCallEstablish(responseMap);
			} else if (action.equals("Hangup")) {
				// 解析挂断请求
				body = parseHangup(responseMap);
			}
			multiMediaService.saveCallAuthInfo(requestMap);
		}

		// 设置返回header
		response.setHeader("Status-Code", "HTTP/1.1 200 OK");
		response.setHeader("statusmsg", new Date() + "");
		response.setHeader("Content-Length", body.length() + "");
		try {
			OutputStream opt = response.getOutputStream();
			OutputStreamWriter out = new OutputStreamWriter(opt);
			out.write(body);
			out.flush();
		} catch (IOException e) {
			logger.error("调用呼叫鉴权接口失败。"+e.getMessage());
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
	
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	private Map constructCloseSubAccountParamter(Map<String,Object> resultMap){
		if(NullUtil.isNull(resultMap)){
			return null;
		}
		Map paramter = new HashMap();
		paramter.put("subAccountSid", StringUtil.getString(resultMap.get("subAccountSid")));
		paramter.put("status", "2");
		return paramter;
	}
	
	/**
	 * 解析呼叫鉴权
	 * @param e Element
	 * @return result
	 */
	private String parseCallAuth(Map<String,Object> responseMap) {
		Map respBody = new HashMap();
		respBody.put("statuscode", Const.AUTH_ACCOUNT_SUCCESS);
		respBody.put("statusmsg", Const.AUTH_ACCOUNT_SUCCESS_MSG);
		respBody.put("record", Const.AUTH_ACCOUNT_IS_RECORD);
		responseMap.put("Response", respBody);
		
		return XMLParser.getStringOfMap(responseMap, TAB_String);
	}
	
	/**
	 * 解析摘机请求
	 * @param e Element
	 * @return result
	 */
	private String parseCallEstablish(Map<String,Object> responseMap) {
		Map respBody = new HashMap();
		respBody.put("statuscode", Const.AUTH_ACCOUNT_SUCCESS);
		respBody.put("statusmsg", Const.AUTH_ACCOUNT_SUCCESS_MSG);
		respBody.put("billdata", Const.AUTH_BILLDATA_SUCCESS);
		respBody.put("sessiontime", Const.AUTH_SESSION_TIME);
		responseMap.put("Response", respBody);
		
		return XMLParser.getStringOfMap(responseMap, TAB_String);
	}
	
	/**
	 * 解析挂断请求
	 * @param e Element
	 * @return result
	 */
	private String parseHangup(Map<String,Object> responseMap) {
		Map respBody = new HashMap();
		respBody.put("statuscode", Const.AUTH_ACCOUNT_SUCCESS);
		respBody.put("statusmsg", Const.AUTH_ACCOUNT_SUCCESS_MSG);
		respBody.put("totalfee", Const.AUTH_BILLDATA_SUCCESS);
		responseMap.put("Response", respBody);
		
		return XMLParser.getStringOfMap(responseMap, TAB_String);
	}
	
	/**
	 * 解析错误响应
	 * @param e Element
	 * @return result
	 */
	private String parseError(Map<String,Object> responseMap) {
		Map respBody = new HashMap();
		respBody.put("statuscode", Const.AUTH_ACCOUNT_ERROR);
		respBody.put("statusmsg", Const.AUTH_ACCOUNT_ERROR_MSG);
		responseMap.put("Response", respBody);
		
		return XMLParser.getStringOfMap(responseMap, TAB_String);
	}
}

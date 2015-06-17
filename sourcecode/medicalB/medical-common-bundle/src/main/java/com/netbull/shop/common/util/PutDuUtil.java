package com.netbull.shop.common.util;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import com.alibaba.fastjson.JSONObject;
import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.task.Const;
import com.netbull.shop.common.vo.Constant;
/**
 * 
 * @author 
 * time :2014-07-16 15:24
 */
public class PutDuUtil {
	 private static final Logger logger = Logger.getLogger("utilLog");
	 private static String WEIXIN_OPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";

	/**
	 * 获取微信用户openid
	 * @param request
	 * @param appid 微信公众平台 在开发者菜单中获取
	 * @param secret 微信公众平台 在开发者菜单中获取
	 * @return
	 */
	public static String handleWeixinOpenId(HttpServletRequest request, HttpServletResponse response,String appid,String secret){
		
		//优先从session取，未取到调用微信接口获取openid
		Cookie  cookie = CookiesUtils.getCookie(request, Constant.USERTRACK_OPENID);
		String openId = !NullUtil.isNull(cookie)?cookie.getValue():null;
		logger.info("从cookie中获取的openid="+openId);
		if(NullUtil.isNull(openId)){
			/******此处供在开发和测试环境使用**********/
			String devMode = ConfigLoadUtil.loadConfig().getPropertie("devMode"); 
			boolean mode = !NullUtil.isNull(devMode)?Boolean.parseBoolean(devMode):false;
			if(mode){
				openId = ConfigLoadUtil.loadConfig().getPropertie("defaultOpenId"); 
			}
		}
//		HttpSession session=request.getSession(true);
//		String openId=!NullUtil.isNull(session.getAttribute("session_openId"))?(String) session.getAttribute("session_openId"):null;
		logger.info("@@优先从session取openid="+openId);
		String json="";
		if(NullUtil.isNull(openId)){
			String code = request.getParameter("code");
			String bodyContent="appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
			logger.info("调用微信公众平台Auth2.0接口获取OpenId请求参数："+bodyContent);
			try {
				json=HttpClientUtil.httpPost(WEIXIN_OPENID_URL,bodyContent);
				logger.info("获取json信息："+json);
			} catch (IOException e) {
				logger.error("微信平台报错",e);
				return openId;
			}
			if(NullUtil.isNull(json)){
				logger.info("是否执行了这个地方"+json);
				return openId;
			}
			JSONObject accessTokenVo=JSONObject.parseObject(json);
			openId=accessTokenVo.getString("openid");
			saveCookie(request,response,openId);
		}
		return openId;
	}
	
	private static void saveSession(HttpServletRequest request,HttpServletResponse response,String openId){
		HttpSession session=request.getSession(true);
		session.setAttribute("session_openId", openId);
	}
	
	private static void saveCookie(HttpServletRequest request,HttpServletResponse response,String openId){
		// 将用户信息放入cookie，行为轨迹需要使用
		CookiesUtils.setCookie(response, Constant.USERTRACK_SESSIONID,request.getSession().getId(), -1);
		CookiesUtils.setCookie(response, Constant.USERTRACK_OPENID,openId, -1);
	}
	/**
	 * 获取微信用户openid
	 * @param request
	 * @param appid 微信公众平台 在开发者菜单中获取
	 * @param secret 微信公众平台 在开发者菜单中获取
	 * @return
	 */
	public static String handleDispatchOpenId(HttpServletRequest request,HttpServletResponse response,String code){
		logger.info("获取code信息："+code);
		Map paramterMap = TokenDC.getInstance().getWechatVo(code);
		String appid = StringUtil.getString(paramterMap.get("appId"));
		String secret = StringUtil.getString(paramterMap.get("appSecret"));
		return handleWeixinOpenId(request,response,appid,secret);
	}
	
	/**
	 * 获取微信用户loginCode
	 * @param request
	 * @return
	 */
	public static String getLoginCode(HttpServletRequest request){
		String code = null;
		Cookie cookie = CookiesUtils.getCookie(request, Constant.USERTRACK_LOGINCODE);
		if(!NullUtil.isNull(cookie)){
			code = cookie.getValue();
		}
		if(!StringUtil.isEmpty(code)){
			code = request.getParameter("loginCode");
			if(!StringUtil.isEmpty(code)){
				code = String.valueOf(request.getSession().getAttribute("loginCode"));
			}
		}
		if(NullUtil.isNull(code)){
			code = Const.NETBULL_LOGIN_CODE;
		}
		logger.info("获取loginCode信息："+code);
		return code;
	}
}

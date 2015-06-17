package com.netbull.shop.common.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.task.Const;
import com.netbull.shop.common.util.CookiesUtils;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.RequestUtil;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.common.util.TokenDC;
import com.netbull.shop.common.vo.Constant;

public class Oauth2Filter implements Filter {

	private static final Logger logger = Logger.getLogger("oauth2Log");
	private static final String oauthUrl = "https://open.weixin.qq.com/connect/oauth2/authorize";
	private String callbackUrl = "http://www.wzt360.com";
	private String[] oauth2 = null;

	private static String[] usualExtention = new String[] { ".jsp", ".class",
		".jpeg", ".css", ".swf", ".js", ".gif", ".jpg",
		".png", ".db", ".MF", ".xml", ".flv", ".wsdd", ".tld", ".log ",
		".jar", ".bak", ".properties", ".mex", ".class",
		".wsdl ", ".jmx", ".ico" };

	private static Set<String> usualExtentionSet = new HashSet<String>();
	{
		CollectionUtils.addAll(usualExtentionSet, usualExtention);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		try{
			HttpServletRequest hrequest = (HttpServletRequest) request;
			HttpServletResponse hresponse = (HttpServletResponse) response;
			String uri = hrequest.getRequestURI();
			String ctx = hrequest.getContextPath();
			
			String loginCode = hrequest.getParameter("loginCode");
			String isOauth = hrequest.getParameter("isOauth");
			// 下一个过滤器
			if(!StringUtil.isEmpty(loginCode)){
				loginCode = ConfigLoadUtil.loadConfig().getPropertie("loginCode"); 
			}
			CookiesUtils.setCookie(hresponse, Constant.USERTRACK_LOGINCODE,loginCode, -1);
			
			String urlAftssh = uri.contains(ctx) ? uri.substring(ctx.length()+1) : uri.substring(1);
			if (urlAftssh.contains(".")) {
				int pos = urlAftssh.lastIndexOf(".");
				String ext = urlAftssh.substring(pos);
				if (usualExtentionSet.contains(ext)) {
					chain.doFilter(request, response);
					return;
				}
			}
			logger.debug("请求路径地址：uri"+uri);
			Cookie  cookie = CookiesUtils.getCookie(hrequest, Constant.USERTRACK_OPENID);
			String openId = !NullUtil.isNull(cookie)?cookie.getValue():null;
			
			if(!NullUtil.isNull(openId)){
				logger.debug("获取请求参数openId:"+openId+",不请求微信服务...");
				chain.doFilter(request, response);
				return ;
			}else{
				/******此处供在开发和测试环境使用**********/
				String devMode = ConfigLoadUtil.loadConfig().getPropertie("devMode"); 
				boolean mode = !NullUtil.isNull(devMode)?Boolean.parseBoolean(devMode):false;
				if(mode){
					openId = ConfigLoadUtil.loadConfig().getPropertie("defaultOpenId"); 
				}
			}
			String oauth2Filter = ConfigLoadUtil.loadConfig().getPropertie("oauth2Filter"); 
			oauth2 = !NullUtil.isNull(oauth2Filter)?oauth2Filter.split("\\|"):null;
			if(logger.isDebugEnabled()){
				logger.debug("获取请求参数loginCode="+loginCode);
				logger.debug("获取请求参数isOauth="+isOauth);
				logger.debug("获取请求参数urlAftssh="+urlAftssh);
				logger.debug("获取请求参数oauth2="+oauth2);
			}
			if(!NullUtil.isNull(loginCode) && !NullUtil.isNull(isOauth) && !Const.AUTH2_OPEN.equals(isOauth) && isContainOauth2Key(urlAftssh)){
				Map<String,Object> requestMap = new HashMap<String,Object>();
				Map paramterMap = TokenDC.getInstance().getWechatVo(loginCode);
				callbackUrl = ConfigLoadUtil.loadConfig().getPropertie("domainName");
				Map temp = RequestUtil.getUrlParams(hrequest.getQueryString());
				temp.remove("isOauth");
				String appid = StringUtil.getString(paramterMap.get("appId"));
				requestMap.put("appid", appid);
				requestMap.put("redirect_uri", callbackUrl+"/"+urlAftssh+RequestUtil.getUrlParamsByMap(temp));
				requestMap.put("response_type", "code");
				requestMap.put("scope", "snsapi_base");
				requestMap.put("state", "123");
				requestMap.put("isAuth2", "0");
				if(logger.isDebugEnabled()){
					logger.debug("通过appid构造url获取微信回传code值的请求URL："+oauthUrl+RequestUtil.getUrlParamsByMap(requestMap)+"#wechat_redirect");
				}
				hresponse.sendRedirect(oauthUrl+RequestUtil.getUrlParamsByMap(requestMap)+"#wechat_redirect");
				return;
			}
			chain.doFilter(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private boolean isContainOauth2Key(String key){
		if(oauth2!=null){
			for (int i = 0; i < oauth2.length; i++) {
				if(oauth2[i].equals(key)){
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

}

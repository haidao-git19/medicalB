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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.CookiesUtils;
import com.netbull.shop.common.util.Md5;
import com.netbull.shop.common.util.NullUtil;
import com.netbull.shop.common.util.RequestUtil;
import com.netbull.shop.common.util.StringTools;
import com.netbull.shop.common.util.StringUtil;
import com.netbull.shop.common.util.TokenDC;
import com.netbull.shop.common.vo.Constant;

public class RedirectFilter implements Filter {

	private static final Logger logger = Logger.getLogger("oauth2Log");

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

		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpSession session = hrequest.getSession();
		
		String uri = hrequest.getRequestURI();
		String ctx = hrequest.getContextPath();
		
		String urlAftssh = uri.contains(ctx) ? uri.substring(ctx.length()+1) : uri.substring(1);
		if (urlAftssh.contains(".")) {
			int pos = urlAftssh.lastIndexOf(".");
			String ext = urlAftssh.substring(pos);
			if (usualExtentionSet.contains(ext)) {
				chain.doFilter(request, response);
				return;
			}
		}
		
		String callbackUrl = ConfigLoadUtil.loadConfig().getPropertie("domainName");
		String queryString = hrequest.getQueryString();
		if(!NullUtil.isNull(queryString) && queryString.indexOf("returnUrl")<0){
			Map temp = RequestUtil.getUrlParams(hrequest.getQueryString());
			callbackUrl = callbackUrl+"/"+urlAftssh+RequestUtil.getUrlParamsByMap(temp);
			session.setAttribute("callbackUrl", callbackUrl);
			try {
				session.setAttribute("token", StringTools.create_nonce_str());
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

}

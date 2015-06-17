package com.netbull.shop.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.netbull.shop.common.log.LoggerFactory;

public class KeyWordsFilter implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(KeyWordsFilter.class);

	/** 异常数据后跳转指定页面路径 */
	private static String errorForwardUrl = "/jsp/common/tipInfo1.jsp";

	/** 关键字过滤处理类*/
	private KeyWordsHelper kwh = KeyWordsHelper.getKeyWordsHelper();
	
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		if(kwh.scan((HttpServletRequest)req)){
			if(logger.isDebugEnabled()){
				logger.debug("KeyWordsfilter拦截请求：" +((HttpServletRequest)req).getRequestURI());
			}
			chain.doFilter(req, res);
		}else{
			if(logger.isDebugEnabled()){
				logger.debug("KeyWordsfilter拦截请求包含配置关键字，不允许正常访问：" +((HttpServletRequest)req).getRequestURI() + ((HttpServletRequest)req).getQueryString());
			}
			HttpServletResponse response = (HttpServletResponse)res;
			response.sendRedirect(errorForwardUrl);
		}
	}

	/**
	 * 初始化
	 */
	public void init(FilterConfig config) throws ServletException {
		String refreshTime = config.getInitParameter("refreshTime");
		/** 启动刷新线程 同时加载配置项*/
		kwh.startRefreshThread(Long.valueOf(refreshTime));
		/** 加载配置项*/
//		kwh.refConf();
	}

}

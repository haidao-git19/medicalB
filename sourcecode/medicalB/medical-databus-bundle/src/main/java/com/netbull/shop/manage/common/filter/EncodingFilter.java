package com.netbull.shop.manage.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EncodingFilter implements Filter {

	/** 字符编码 */
	private String encoding;

	@Override
	public void init(FilterConfig chain) throws ServletException {

		// 获取web.xml中该过滤器的参数
		this.encoding = chain.getInitParameter("encoding");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest hrequest = (HttpServletRequest) request;
		hrequest.setCharacterEncoding(encoding);
		// 下一个过滤器
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {}

}

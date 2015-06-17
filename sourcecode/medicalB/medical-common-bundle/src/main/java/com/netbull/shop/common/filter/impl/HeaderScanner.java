package com.netbull.shop.common.filter.impl;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.netbull.shop.common.filter.Scanner;
import com.netbull.shop.common.log.LoggerFactory;


/**
 * 扫描请求报头信息 
 */
public class HeaderScanner extends Scanner {
	
	private static final Logger logger = LoggerFactory.getLogger(HeaderScanner.class);

	@SuppressWarnings("unchecked")
	@Override
	public boolean scan(HttpServletRequest request) {
		Enumeration headerNames = request.getHeaderNames();
		if(headerNames.hasMoreElements()){
			/** 每一个报头*/
			String headerName = (String)headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
			if(this.findKeyWords(headerValue)){
				if(logger.isDebugEnabled()){
					logger.debug("拦截到非法关键字：" + headerValue);
				}
				return Boolean.FALSE;
			}
		}
		
		return Boolean.TRUE;
	}

}

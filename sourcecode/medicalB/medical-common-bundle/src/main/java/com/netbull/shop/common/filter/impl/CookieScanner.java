package com.netbull.shop.common.filter.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.netbull.shop.common.filter.Scanner;
import com.netbull.shop.common.log.LoggerFactory;

/***
 * cookie过滤 
 *
 */
public class CookieScanner extends Scanner {
	
	private static final Logger logger = LoggerFactory.getLogger(CookieScanner.class);

	@Override
	public boolean scan(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies == null){
			return Boolean.TRUE;
		}
		for(int i = 0; i< cookies.length;i++){
			Cookie cook = cookies[i];
			if(this.findKeyWords(cook.getValue())){
				if(logger.isDebugEnabled()){
					logger.debug("拦截到非法关键字：" + cook.getValue());
				}
				return Boolean.FALSE;
			}
		}
		
		return Boolean.TRUE;
	}

}

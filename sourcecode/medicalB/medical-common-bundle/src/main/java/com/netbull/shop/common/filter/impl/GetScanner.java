package com.netbull.shop.common.filter.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.netbull.shop.common.filter.Scanner;
import com.netbull.shop.common.log.LoggerFactory;


/*******************************************************************************
 * 对get请求进行关键字过滤验证
 * 
 */
public class GetScanner extends Scanner {

	private static final Logger logger = LoggerFactory.getLogger(GetScanner.class);
	
	@Override
	public boolean scan(HttpServletRequest request) {

		String qst = request.getQueryString();
		if(StringUtils.isEmpty(qst)){
			return Boolean.TRUE;
		}
		String[] arr = qst.split("&");
		if (arr != null && arr.length > 0) {
			for (int i = 0; i < arr.length; i++) {
				String[] s = arr[i].split("=");
				if (s != null && s.length == 2) {
					if (findKeyWords(s[1])) {
						if(logger.isDebugEnabled()){
							logger.debug("拦截到非法关键字：" + s[1]);
						}
						return Boolean.FALSE;
					}
				}
			}
		}

		return Boolean.TRUE;
	}

}

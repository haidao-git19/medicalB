package com.netbull.shop.common.filter.impl;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.netbull.shop.common.filter.Scanner;
import com.netbull.shop.common.log.LoggerFactory;


/***
 *  对post请求进行关键字过滤验证
 *
 */
public class PostScanner extends Scanner {

	private static final Logger logger = LoggerFactory.getLogger(PostScanner.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean scan(HttpServletRequest request) {
		Map requestParams = request.getParameterMap();

		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			String[] value = (String[]) requestParams.get(key);
			int len = value != null ? value.length : 0;
			for (int i = 0; i < len; i++) {
				if (findKeyWords(value[i])) {
					if(logger.isDebugEnabled()){
						logger.debug("拦截到非法关键字：" + value[i]);
					}
					return Boolean.FALSE;
				}
			}
		}

		return Boolean.TRUE;
	}

}

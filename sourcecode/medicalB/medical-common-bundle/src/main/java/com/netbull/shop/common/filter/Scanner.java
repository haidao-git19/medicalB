package com.netbull.shop.common.filter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public abstract class Scanner {
	
	private static final Logger logger = Logger.getLogger(Scanner.class);
	
	private static final String REGEX = "{Regex}";

	private boolean isFlag;
	
	/** 配置文件信息*/
	private String seed;
	
	/**
	 * 扫描request信息，判断是否有敏感信息
	 * 
	 * @param request
	 * @return
	 */
	public abstract boolean scan(HttpServletRequest request);
	
	/***
	 * 判断传入的参数是否匹配关键字
	 * 
	 * @param value
	 *      被匹配的字符
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public boolean findKeyWords(String value) {
		if(seed.startsWith(REGEX)){
			return findByRegex(value);
		}else{
			return findByInput(value);
		}
	}
	
	/***
	 * 通过正则表达式来匹配
	 * 
	 * @param value
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private boolean findByRegex(String value) {
		String temp = seed.replace(REGEX, "");
		return false;
	}
	

	/**
	 * 通过规则配置关键字来匹配，关键字配置规则为 “”|“”
	 * 
	 * @param value
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private boolean findByInput(String value) {
		String v = "";
		try {
			v = URLDecoder.decode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("findByRegex ulrDecoder error!",e);
		}
		String[] seds = seed.split("\\|");
		for(int i = 0;i < seds.length;i++){
			if(v != null && v.trim().equals(seds[i])){
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
	

	public boolean isFlag() {
		return isFlag;
	}

	public void setFlag(boolean isFlag) {
		this.isFlag = isFlag;
	}

	public String getSeed() {
		return seed;
	}

	public void setSeed(String seed) {
		this.seed = seed;
	}


	
	
}

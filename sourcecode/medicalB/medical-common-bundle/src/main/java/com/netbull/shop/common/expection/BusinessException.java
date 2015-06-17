package com.netbull.shop.common.expection;

import org.apache.log4j.Logger;

public class BusinessException extends Exception{	
	
	private static final Logger logger = Logger.getLogger("exceptionLog");
	
	String url;
	
	public BusinessException(){
		super("对不起,系统繁忙,请稍候再试!");
		logger.error("对不起,系统繁忙,请稍候再试!");
	}
	
	public BusinessException(String msg){
		super(msg);
		logger.error(msg);
	}
	
	public BusinessException(String msg,Exception ex){
		super(msg,ex);
		logger.error(msg,ex);
	}
	
	public BusinessException(String msg,String url){
		super(msg);
		this.url = url;
		logger.error(msg);
	}
	
	public BusinessException(String msg,Exception ex,String url){
		super(msg,ex);
		this.url = url;
		logger.error(msg,ex);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
}

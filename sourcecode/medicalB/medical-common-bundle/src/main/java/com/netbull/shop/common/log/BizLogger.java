package com.netbull.shop.common.log;


/**
 * 业务日志输出接口
 * @author yangfeng
 *
 */
public class BizLogger extends JLogger {

	public BizLogger(String logclazz) {
		super(logclazz);
	}

	/**
	 * override parent info method to lift one level
	 * 确保业务日志的输�?
	 * @param msg  the business message
	 */
	public void info(Object message) {
		super.warn(message);
	}


}

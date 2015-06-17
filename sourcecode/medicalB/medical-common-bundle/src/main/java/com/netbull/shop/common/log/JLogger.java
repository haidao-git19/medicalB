package com.netbull.shop.common.log;

import org.apache.log4j.Logger;

/**
 * 日志输出接口，可以根据需要对日志输出的内容进行修�?
 * @author yangfeng
 *
 */
public class JLogger {
	private Logger log = null;

	protected JLogger(String logname) {
		this.log = Logger.getLogger(logname);
	}

	protected JLogger(Class<?> logclazz) {
		this.log = Logger.getLogger(logclazz);
	}

	public void debug(Object msg) {
		log.debug(msg);
	}

	public void debug(Throwable t) {
		log.debug("", t);
	}

	public void debug(Object msg, Throwable t) {
		log.debug(msg, t);
	}

	public void info(Object message) {
		log.info(message);
	}

	public void info(Object msg, Throwable t) {
		log.info(msg, t);
	}

	public void warn(Object msg) {
		log.warn(msg);
	}

	public void warn(Object msg, Throwable t) {
		log.warn(msg, t);
	}

	public void error(Object msg) {
		log.error(msg);
	}

	public void error(Throwable t) {
		log.error("", t);
	}

	public void error(Object msg, Throwable t) {
		log.error(msg, t);
	}

	public void fatal(Object msg) {
		log.fatal(msg);
	}

	public void fatal(Object msg, Throwable t) {
		log.fatal(msg, t);
	}

	public boolean isDebugEnabled() {
		return log.isDebugEnabled();
	}

	protected Logger getInternalLog() {
		return log;
	}
}

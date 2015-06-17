package com.netbull.shop.common.datacache;

import java.util.HashSet;

import org.apache.log4j.Logger;

import com.netbull.shop.common.log.LoggerFactory;

/**
 * 数据刷新后的通知线程
 *
 */
public class DataRefreshNotifyThread implements Runnable {

	private static Logger logger = LoggerFactory
			.getLogger(DataRefreshNotifyThread.class);

	DataRefreshListener listener = null;
	@SuppressWarnings("unchecked")
	HashSet hsTableNames = null;

	/**
	 * 通知指定listener有数据更新
	 * 
	 * @param listener
	 *            需要通知的listener
	 * @param hsTableNames
	 *            有更新的表名
	 */
	@SuppressWarnings("unchecked")
	public void doNotify(DataRefreshListener listener, HashSet hsTableNames) {
		this.listener = listener;
		this.hsTableNames = hsTableNames;
		start();
	}

	public void run() {
		if (logger.isDebugEnabled()) {
			logger.debug("Begin to Notify DCDataRefreshListener: "
					+ listener.getClass());
		}
		listener.doDCDataRefresh(hsTableNames);
		if (logger.isDebugEnabled()) {
			logger.debug("End to Notify DCDataRefreshListener: "
					+ listener.getClass());
		}
	}

	private void start() {
		new Thread(this, "DCDataRefreshNotifyThread").start();
	}

}

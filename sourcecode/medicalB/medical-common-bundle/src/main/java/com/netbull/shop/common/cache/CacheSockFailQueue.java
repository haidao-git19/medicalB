package com.netbull.shop.common.cache;

import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.netbull.shop.common.log.JLogger;
import com.netbull.shop.common.log.LoggerFactory;
import com.netbull.shop.common.util.NullUtil;

/**
 * <code>CacheSockFailQueue</code>
 * @author elvis
 * @see CacheSockFailQueue
 */
public class CacheSockFailQueue extends TimerTask{

	@SuppressWarnings("rawtypes")
	private ConcurrentMap  failQueue = new ConcurrentHashMap();
	
	private static CacheSockFailQueue instance = new CacheSockFailQueue();
	
	private static JLogger logger = LoggerFactory.getLogger("biz.wss.cache");

	private CacheSockFailQueue() {

	}

	public static  CacheSockFailQueue getInstance() {
		return instance;
	}

	@SuppressWarnings("unchecked")
	public void addSockFailToQueue(String server) {

		if (logger.isDebugEnabled()) {
			logger.debug("进入CacheSockFailQueue类addSockFailToQueue方法");
		}
		
		if (NullUtil.isNull(server)) {
			return;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("添加失败的连接对象节点到队列中");
		}
		
		/**
		 * 添加失败的连接对象节点到队列中；
		 */
		failQueue.putIfAbsent(server, new Date().getTime());
	}
	
	@SuppressWarnings("unchecked")
	public void removeSockFailToQueue(String server) {

		if (logger.isDebugEnabled()) {
			logger.debug("进入CacheSockFailQueue类removeSockFailToQueue方法");
		}
		
		if (server == null || "".equals(server) || failQueue.isEmpty()) {
			return;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("添加失败的连接对象节点到队列中");
		}
		
		/**
		 * 添加失败的连接对象节点到队列中；
		 */
		failQueue.remove(server);
	}


	public ConcurrentMap getFailQueue() {
		return failQueue;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void run() {
		
		if(failQueue.isEmpty()){
			if (logger.isDebugEnabled()) {
				logger.debug("缓存节点失败队列为空！ ");
			}
			return ;
		}
		
		Iterator iterator = failQueue.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry oo = (Entry)iterator.next();
			String key = oo.getKey()!=null?oo.getKey().toString():null;
			Long val = oo.getValue()!=null?Long.parseLong(oo.getValue().toString()):0L;
			
			//如果队列中服务节点长时间没被移除，说明该缓存节点服务可能挂了，发送提醒；
			if(new Date().getTime() - val > 1000 * 60){
				logger.error("请注意，有一个缓存服务节点异常，连接不上，请检查。");
				logger.error("节点服务为："+key);
			}
		}
	}
	
}

package com.netbull.shop.common.activeload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.netbull.shop.common.log.JLogger;
import com.netbull.shop.common.log.LoggerFactory;

import com.danga.MemCached.SockIOPool;



/**
 * 可自动加载属性变化的属性类;
 * @author elvis
 */
public class CachePropertiesEx {

	/**  
     *   
     */
	private static final long serialVersionUID = -6708397622206255544L;
	private static JLogger logger = LoggerFactory.getLogger("biz.wss.cache");
	
	private MonitorThread monitor;
	private Properties p;

	/**
	 * 
	 * @param intervalSecond
	 * 监听变化间隔
	 */
	public CachePropertiesEx(long intervalSecond) {
		monitor = MonitorThread.getInstance(intervalSecond);
	}

	/**
	 * 默认更新间隔为2分钟；
	 */
	public CachePropertiesEx() {
		this(10);
	}

	/**
	 * 加载配置文件
	 * 
	 * @param f
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void load(File f) throws FileNotFoundException, IOException {
		p = new Properties();
		p.load(new FileInputStream(f));
		
		MonitorEvent event = new FileChangeMonitorEvent(f);
		ReloadPropertiesListener listener = new ReloadPropertiesListener();
		listener.refreshMemcacheNode();
		event.addListener(listener);
		monitor.addEvent(event);
		if (!monitor.isMonitor()) {
			monitor.start();
		}

	}

	public String getProperty(String key) {
		return p.getProperty(key);
	}

	public Properties getProperties() {
		return p;
	}

	/**
	 * 当发生属性文件改变时重新加载属性文件<br>
	 * listener为内部类，为了访问包含类中p成员变量和静止外部访问该类
	 * 
	 */
	private class ReloadPropertiesListener implements IMonitorListener {

		public void update(MonitorEvent event) {
			FileChangeMonitorEvent fcmEvent = (FileChangeMonitorEvent) event;
			try {
				if (logger.isDebugEnabled()) {
					logger.debug("检查到属性文件发生改变，开始重新加载属性文件！");
				}
				p.load(new FileInputStream(fcmEvent.getF()));
				refreshMemcacheNode();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * 如果缓存文件修改了，就重新加载配置，并重新生成节点；
		 */
		@SuppressWarnings("unused")
		public void refreshMemcacheNode() {
			
			if (logger.isDebugEnabled()) {
				logger.debug("缓存属性文件加载完毕，重新生成缓存节点！");
			}
			
			boolean isActive = Boolean.parseBoolean(p
					.getProperty("wss.cache.isActive"));
			
			if (!isActive) {
				logger.warn("缓存服务未启用！！！");
				return;
			}

			String[] servers = getServers(p.getProperty("wss.cache.list"));
			Integer[] weights = getWeights(p.getProperty("wss.cache.list"));
			int initConn = Integer.parseInt(p.getProperty("wss.cache.initConn"));
			int minConn = Integer.parseInt(p.getProperty("wss.cache.minConn"));
			int maxConn = Integer.parseInt(p.getProperty("wss.cache.maxConn"));
			boolean isAliveCheck = Boolean.parseBoolean(p.getProperty("wss.cache.isAliveCheck"));
			int maintSleep = Integer.parseInt(p.getProperty("wss.cache.maintSleep"));
			boolean nagle = Boolean.parseBoolean(p.getProperty("wss.cache.nagl"));
			int socketTO = Integer.parseInt(p.getProperty("wss.cache.socketTO"));
			int socketConnectTO = Integer.parseInt(p.getProperty("wss.cache.socketConnectTO"));
			int idelTime = Integer.parseInt(p.getProperty("wss.cache.idelTime"));
			int busyTime = Integer.parseInt(p.getProperty("wss.cache.busyTime"));
			boolean failover = Boolean.parseBoolean(p.getProperty("wss.cache.failover"));
			boolean failback = Boolean.parseBoolean(p.getProperty("wss.cache.failback"));
			int hashAlg = Integer.parseInt(p.getProperty("wss.cache.hashAlg"));

			SockIOPool pool = SockIOPool.getInstance();
			/**
			 * 先关闭缓存连接；
			 */
			if(pool.isInitialized()){
				if (logger.isDebugEnabled()) {
					logger.debug("先关闭Memcache缓存节点的连接池！");
				}
				pool.shutDown();
			}
			
			pool.setServers(servers);
			pool.setWeights(weights);
			pool.setInitConn(initConn);
			pool.setMinConn(minConn);
			pool.setMaxConn(maxConn);
			pool.setAliveCheck(isAliveCheck);
			pool.setMaintSleep(maintSleep);
			pool.setNagle(nagle);
			pool.setSocketTO(socketTO);
			pool.setSocketConnectTO(socketConnectTO);
			pool.setMaxIdle(idelTime);
			pool.setMaxBusyTime(busyTime);
			pool.setFailover(failover);
			pool.setFailback(failback);
			pool.setHashingAlg(hashAlg);
			
			if(!pool.isInitialized()){
				if (logger.isDebugEnabled()) {
					logger.debug("重新初始化缓存节点的连接池！");
				}
				pool.initialize();
			}
			
			
		}

		/**
		 * 获取所有的节点服务；
		 * 
		 * @param s
		 * @return
		 */
		private String[] getServers(String s) {
			String[] str = s.split(";");
			String[] sa = new String[str.length];
			for (int i = 0; i < str.length; i++) {
				sa[i] = str[i].split(",")[0];
				if (logger.isDebugEnabled()) {
					 logger.debug("Memcache 缓存节点IP地址和权重:" + str[i]);
				}
			}

			return sa;
		}

		/**
		 * 获取节点的权重；
		 * 
		 * @param s
		 * @return
		 */
		private Integer[] getWeights(String s) {
			String[] str = s.split(";");
			Integer[] sa = new Integer[str.length];
			for (int i = 0; i < str.length; i++) {
				sa[i] = Integer.parseInt(str[i].split(",")[1]);
				if (logger.isDebugEnabled()) {
					logger.debug("Memcache 缓存节点IP地址:" + str[i] + " 节点权重是 :" + sa[i]);
				}
			}

			return sa;
		}
	}
}

package com.netbull.shop.common.cache;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.danga.MemCached.SockIOPool;

import com.netbull.shop.common.log.JLogger;
import com.netbull.shop.common.log.LoggerFactory;
import com.netbull.shop.common.util.IOUtil;

public class CacheConfigUtil {
	
	private static JLogger log = LoggerFactory.getLogger("biz.wss.cache");
	
	private static boolean isRefresh = false;
	/** 缓存开关，false关闭，true开启*/
	public static boolean isActive = false;
	private static long refreshInterval = 10*60*1000;//10分钟
	
	public static void init(final String filePath){
		Properties p = new Properties();
		InputStream in = null;
		try {
			in = IOUtil.getInputStream(filePath);
			p.load(in);
			
			isActive = Boolean.parseBoolean(p.getProperty("wss.cache.isActive"));
			if(!isActive){
				log.warn("缓存服务未启用！！！");
				return ;
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
		    	pool.initialize();
		    }
		    
		    isRefresh = Boolean.parseBoolean(p.getProperty("wss.cache.isRefresh"));
		    refreshInterval = Long.parseLong(p.getProperty("wss.cache.refresh.interval"));
		} catch (IOException e) {
			log.error("读取配置文件错误!", e);
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			in = null;
		}
		
		if(isRefresh){
		    new Thread(){
		    	public void run(){
		    		try {
						Thread.sleep(refreshInterval);
						log.debug("动态刷新缓存配置文件...");
						init(filePath);
						
					} catch (InterruptedException e) {
						log.error("刷新缓存配置文件错误",e);
					}
		    	}
		    }.start();
	    }
		
	}
	
	private static String[] getServers(String s){
		String[] str = s.split(";");
		String[] sa = new String[str.length];
		for(int i=0;i<str.length;i++){
			sa[i] = str[i].split(",")[0];
			if(log.isDebugEnabled()){
				log.debug("str:"+str[i]+" sa:"+sa[i]);
			}
		}
		
		return sa;
	}
	
	private static Integer[] getWeights(String s){
		String[] str = s.split(";");
		Integer[] sa = new Integer[str.length];
		for(int i=0;i<str.length;i++){
			sa[i] = Integer.parseInt(str[i].split(",")[1]);
			if(log.isDebugEnabled()){
				log.debug("str:"+str[i]+" sa:"+sa[i]);
			}
		}
		
		return sa;
	} 
	
}

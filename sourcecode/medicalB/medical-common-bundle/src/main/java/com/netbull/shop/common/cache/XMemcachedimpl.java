package com.netbull.shop.common.cache;

import java.util.Date;

import org.slf4j.LoggerFactory;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class XMemcachedimpl implements CacheService {
	final static org.slf4j.Logger logger = LoggerFactory.getLogger(XMemcachedimpl.class);

	private MemCachedClient memcachedClient;
	private MemCachedClientEnhance memcachedClientEnhance;
	private int expireTime;
	private boolean is_updataExp = Boolean.FALSE;
	final static String POINT_QUOTE = ".";

	public void destroy() {
		try {
			SockIOPool.getInstance().shutDown();
		} catch (Exception e) {
			logger.error("shutdown memcached client error:", e);
			e.printStackTrace();
		}
	}

	public Object getAndUpdateCache(String cacheName, String cacheKey) {
		throw new RuntimeException("no implement");
	}

	public Object getCache(String cacheName, String cacheKey) {
		try {
			return memcachedClientEnhance.get(cacheName + POINT_QUOTE + cacheKey);
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error("get memcached cache error:", e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T putCache(String cacheName, String cacheKey, Object value) {
		try {
			// 获取配置的缓存时间
			return (T) Boolean.valueOf(memcachedClientEnhance.set(cacheName + POINT_QUOTE + cacheKey, value, new Date(expireTime)));
		} catch (Throwable e) {
			logger.error("put memcached cache error:", e);
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T remove(String cacheName, String cacheKey) {
		try {
			return (T) Boolean.valueOf(memcachedClient.delete(cacheName
					+ POINT_QUOTE + cacheKey));
		} catch (Throwable e) {
			logger.error("remove memcached cache error:", e);
			throw new RuntimeException(e);
		}
	}

	public void init() {

	}

	public void flush(String cacheName) {

	}

	public MemCachedClient getMemcachedClient() {
		return memcachedClient;
	}

	public void setMemcachedClient(MemCachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}
	
	public MemCachedClientEnhance getMemcachedClientEnhance() {
		return memcachedClientEnhance;
	}

	public void setMemcachedClientEnhance(MemCachedClientEnhance memcachedClientEnhance) {
		this.memcachedClientEnhance = memcachedClientEnhance;
	}

	public int getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(int expireTime) {
		this.expireTime = expireTime;
	}

	public boolean isIs_updataExp() {
		return is_updataExp;
	}

	public void setIs_updataExp(boolean is_updataExp) {
		this.is_updataExp = is_updataExp;
	}

}

package com.netbull.shop.common.cache;

/**
 * 
 * @Copyrights: Apr 28, 2008��SHENZHEN TIANYUAN DIC INFORMATION TECHNOLOGY
 *              CO.,LTD.<br>
 *              Common<br>
 *              All rights reserved.<br>
 * @Filename: CacheService.java
 * @Description��
 * 
 * @Version�� V1.0<br>
 * @Author: yuhui
 * @Created�� 2010-6-15<br>
 * @History: <br>
 *           [ Author Date Version Content ]<br>
 */
public interface CacheService {

	void init();
	void destroy();
	<T> T putCache(String cacheName, String cacheKey, Object value);
	<T> T remove(String cacheName, String cacheKey);
	Object getCache(String cacheName, String cacheKey);
	Object getAndUpdateCache(String cacheName, String cacheKey);
	void flush(String cacheName);
}

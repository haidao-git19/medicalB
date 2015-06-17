package com.netbull.shop.util;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 * 数据缓存
 * @author 
 *
 */
public class DataCache {
    private static CacheManager  cache = CacheManager.create();
    
    /**
     * 初始化关注消息
     */
    public static void setCache(String cacheName , String key , Object value){
        Ehcache cache_msg = cache.getCache(cacheName);
        cache_msg.put(new Element(key, value));
    }
    /**
     * 初始化关注消息
     */
    public static Object getCache(String cacheName , String key){
        Ehcache cache_msg = cache.getCache(cacheName);
        if(cache_msg.get(key) == null){
        	return null;
        }else{
            return cache_msg.get(key).getValue();
        }
    }
}

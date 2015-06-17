package com.netbull.shop.common.util;

import java.util.ArrayList;   
import java.util.Collection;   
import java.util.Iterator;
import java.util.LinkedHashMap;   
import java.util.Set;
import java.util.concurrent.locks.Lock;   
import java.util.concurrent.locks.ReentrantLock;   
import java.util.Map; 

/**  
* 类说明：利用LinkedHashMap实现简单的缓存， 必须实现removeEldestEntry方法，具体参见JDK文档  
 *   
 * @author elvis  
 *   
 * @param <K>  
 * @param <V>  
 */  
public class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {   
    private final int maxCapacity;   
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;   
    private final Lock lock = new ReentrantLock();   
    
    public LRULinkedHashMap(int maxCapacity) {   
        super(maxCapacity, DEFAULT_LOAD_FACTOR, true);   
        this.maxCapacity = maxCapacity;   
    }   
    
    @Override  
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {   
        return size() > maxCapacity;   
    }   
    @Override  
    public boolean containsKey(Object key) {   
        try {   
            lock.lock();   
            return super.containsKey(key);   
        } finally {   
            lock.unlock();   
        }   
    }   
       
    @Override  
    public V get(Object key) {   
        try {   
            lock.lock();   
            return super.get(key);   
        } finally {   
            lock.unlock();   
        }   
    }   
    @Override  
    public V put(K key, V value) {   
        try {   
            lock.lock();   
            return super.put(key, value);   
        } finally {   
            lock.unlock();   
        }   
    }   
    public int size() {   
        try {   
            lock.lock();   
            return super.size();   
        } finally {   
            lock.unlock();   
        }   
    }   
    public void clear() {   
        try {   
            lock.lock();   
            super.clear();   
        } finally {   
            lock.unlock();   
        }   
    }   
    public Collection<Map.Entry<K, V>> getAll() {   
        try {   
            lock.lock();   
            return new ArrayList<Map.Entry<K, V>>(super.entrySet());   
        } finally {   
            lock.unlock();   
        }   
    }  
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
    	LRULinkedHashMap  lh = new LRULinkedHashMap(100);
    	lh.put(1, 1);
    	lh.put(2, 2);
    	lh.put(3, 3);
    	lh.put(4, 4);
    	lh.put(5, 5);
    	lh.put(6, 6);
    	lh.put(7, 7);
    	lh.put(8, 8);
    	lh.put(9, 9);
    	lh.put(10, 10);
    	lh.put(11, 11);
    	
    	System.out.println("=====================================");
    	Collection list = lh.getAll();
    	for(Iterator it = list.iterator(); it.hasNext();){
    		System.out.println(it.next());
    		
    	}
    	
    	System.out.println("=====================================");
    	Set key = lh.keySet();
    	Set val = lh.entrySet();
    	System.out.println(lh.get(key.toArray()[0]));;
    	
    	System.out.println("=====================================");
    	Collection list2 = lh.getAll();
    	for(Iterator it = list2.iterator(); it.hasNext();){
    		System.out.println(it.next());
    		
    	}
    	System.out.println(val.toArray()[0]);;
    	
    	
	}
}  
package com.netbull.shop.common.filter;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

public class KeyWordsConfig {
	
	private static final Logger logger = Logger.getLogger(KeyWordsConfig.class);
	
	private static final String IS_FLAG = "scan.flag";
	private static final String SEED = "scan.seed";
	private static final String SCANNER = "scanner";


	private static Properties p;
	
	public static Properties getKeyWords() {
		return p;
	}

	public static void setKeyWords(Properties keyWords) {
		KeyWordsConfig.p = p;
	}

	public static void load(String path){
		BufferedInputStream bf = null;
		try {
			bf = new BufferedInputStream(new FileInputStream(path));
			p = new Properties();
			p.load(bf);
		} catch (FileNotFoundException e) {
			logger.error("file not found!",e);
		} catch (IOException e) {
			logger.error("load keywords.properties error!",e);
		}finally{
			if(bf!=null){
				try {
					bf.close();
				} catch (IOException e) {
					logger.error("close keywords.properties error!", e);
				}
				bf = null;
			}
		}
	}
	

	
	/***
	 * 获取配置的扫描器有几种
	 * 
	 * @return
	 */
	public static Set<String> getClassString(){
		Set<String> cSet = new HashSet<String>();
		if(p == null){
			return cSet;
		}
		Set<Object> set = p.keySet();
		for(Object obj : set){
			String s = (String)obj;
			String[] cs = s.split("\\.");
			if(cs.length <= 3){
				logger.error("key words config error!");
				continue;
			}
			String keyhead = cs[0] + "." + cs[1] + "." + cs[2];
			cSet.add(keyhead);
		}
		return cSet;
	}
	
	/**
	 * 判断指定扫描类型的开关
	 * 
	 * @param head key值的前面公共部分 例如：Wss.kw.get
	 * @return
	 */
	public static boolean isFlag(String head){
		String flag = head + "." + IS_FLAG;
		String b = p.getProperty(flag);
		return Boolean.valueOf(b);
	}
	
	/**
	 * 获取关键字组合
	 * 
	 * @param head
	 * @return
	 */
	public static String getSeed(String head){
		String seed = head + "." + SEED;
		return (String)p.getProperty(seed);
	}
	
	/**
	 * 根据配置文件构造Scan类
	 *  
	 * @param head key值的前面公共部分 例如：Wss.kw.get
	 * @return
	 */
	public static Scanner buildScanner(String head){
		String key = head + "." + SCANNER;
		String className = p.getProperty(key);
		Scanner scanner = null;
		if(isFlag(head)){
			try {
				scanner = (Scanner)Class.forName(className).newInstance();
			} catch (ClassNotFoundException e) {
				logger.error("creat class error",e);
			} catch (InstantiationException e) {
				logger.error("creat class error",e);
			} catch (IllegalAccessException e) {
				logger.error("creat class error",e);
			}
		}
		
		return scanner;
	}
	
}

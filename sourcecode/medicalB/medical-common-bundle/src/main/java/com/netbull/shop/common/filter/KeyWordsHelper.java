package com.netbull.shop.common.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;


public class KeyWordsHelper {
	
	private static final Logger logger = Logger.getLogger(KeyWordsHelper.class);
	
	private static KeyWordsHelper keyWordsHelper = new KeyWordsHelper();
	
	/** 单例模式*/
	private KeyWordsHelper(){
		
	}
	
	public static KeyWordsHelper getKeyWordsHelper(){
		return keyWordsHelper;
	}

	/** 扫描的渠道类型*/
	private List<Scanner> scanner;
	
	/**
	 * 初始化配置数据
	 */
	public void refConf(){
		scanner = new ArrayList<Scanner>();
		Set<String> keySet = KeyWordsConfig.getClassString();
		for(String st : keySet){
			Scanner scan = KeyWordsConfig.buildScanner(st);
			if(scan != null){
				scan.setSeed(KeyWordsConfig.getSeed(st));
				scan.setFlag(KeyWordsConfig.isFlag(st));
				scanner.add(scan);
			}
		}
	}
	
	/**
	 * 判断是否有敏感关键字
	 * 
	 * @param request
	 * @return
	 */
	public boolean scan(HttpServletRequest request){
		if(scanner == null || scanner.size() == 0){
			refConf();
		}
		/** 遍历各种维度的关键字过滤*/
		for(Scanner sca : scanner){
			/** 有非法关键字，则表示非法*/
			if(!sca.scan(request)){
				return Boolean.FALSE;
			}
		}
		
		return Boolean.TRUE;
	}

	public List<Scanner> getScanner() {
		return scanner;
	}

	public void setScanner(List<Scanner> scanner) {
		this.scanner = scanner;
	}
	
	/***
	 * 启动刷新线程
	 * 
	 * @param refreshTime 刷新时间
	 */
	public void startRefreshThread(long refreshTime){
		InRefreshThread refreshThread = new InRefreshThread(refreshTime);
		Thread t = new Thread(refreshThread);
		t.start();
	}
	
	/**
	 * 刷新线程
	 */
	private class InRefreshThread implements Runnable{
		
		/** 刷新时间*/
		private long refreshTime;
		
		public InRefreshThread(long refreshTime){
			this.refreshTime = refreshTime;
		}
		
		public void run() {
			while(true){
				refConf();
				try {
					Thread.sleep(refreshTime*1000*1000);
				} catch (InterruptedException e) {
					logger.error("Thread sleep error!",e);
				}
			}
		}
		
	}
	
	
}

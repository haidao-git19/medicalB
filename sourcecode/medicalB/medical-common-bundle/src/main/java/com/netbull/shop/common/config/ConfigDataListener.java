package com.netbull.shop.common.config;

public interface ConfigDataListener {
	
	public void doConfigRefresh();
	
	//配置重新加载
	public void reLoarder();
	
	//每个监听器提供一个单独的不能重复的名
	public String getListenerName();
}

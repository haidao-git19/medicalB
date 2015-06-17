package com.netbull.shop.common.vo;

import java.io.Serializable;

public class HealthTestPoint implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1530767591086917418L;
	private String userId;
	private String tizhong;
	private String shentizhiliang;
	private String zhifang;
	private String yaotunbi;
	private String daixie;
	private String createTime;
	private String updateTime;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getTizhong() {
		return tizhong;
	}
	public void setTizhong(String tizhong) {
		this.tizhong = tizhong;
	}
	public String getShentizhiliang() {
		return shentizhiliang;
	}
	public void setShentizhiliang(String shentizhiliang) {
		this.shentizhiliang = shentizhiliang;
	}
	public String getZhifang() {
		return zhifang;
	}
	public void setZhifang(String zhifang) {
		this.zhifang = zhifang;
	}
	public String getYaotunbi() {
		return yaotunbi;
	}
	public void setYaotunbi(String yaotunbi) {
		this.yaotunbi = yaotunbi;
	}
	public String getDaixie() {
		return daixie;
	}
	public void setDaixie(String daixie) {
		this.daixie = daixie;
	}
	
	
}

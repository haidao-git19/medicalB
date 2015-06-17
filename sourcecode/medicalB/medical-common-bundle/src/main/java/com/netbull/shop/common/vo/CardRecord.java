package com.netbull.shop.common.vo;

import java.io.Serializable;

public class CardRecord implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9177110567815911300L;
	private Integer id;
	/*卡片标题*/
	private String cardTitle;
	/*开卡数量*/
	private Integer cardTotal;
	/*卡片类型*/
	private String cardType;
	/*有效期起始日期*/
	private String startTime;
	/*有效期终止日期*/
	private String endTime;
	/*开卡记录创建时间*/
	private String createTime;
	/*开卡记录更新时间*/
	private String updateTime;
	/*卡片状态*/
	private String status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCardTitle() {
		return cardTitle;
	}
	public void setCardTitle(String cardTitle) {
		this.cardTitle = cardTitle;
	}
	public Integer getCardTotal() {
		return cardTotal;
	}
	public void setCardTotal(Integer cardTotal) {
		this.cardTotal = cardTotal;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
}

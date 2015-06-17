package com.netbull.shop.common.vo;

import java.io.Serializable;

public class PointVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6563002759867685512L;
	private Integer id;
	private String userId;
	private String openId;
	/*积分规则id*/
	private Integer ruleId;
	/*可用积分*/
	private Integer availablePoint;
	/*总积分*/
	private Integer totalPoint;
	/*消费积分*/
	private Integer consumePoint;
	
	private String createTime;
	private String updateTime;
	/*积分消费时间*/
	private String consumeTime;
	private String state;
	private String remark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Integer getRuleId() {
		return ruleId;
	}
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}
	public Integer getAvailablePoint() {
		return availablePoint;
	}
	public void setAvailablePoint(Integer availablePoint) {
		this.availablePoint = availablePoint;
	}
	public Integer getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(Integer totalPoint) {
		this.totalPoint = totalPoint;
	}
	public Integer getConsumePoint() {
		return consumePoint;
	}
	public void setConsumePoint(Integer consumePoint) {
		this.consumePoint = consumePoint;
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
	public String getConsumeTime() {
		return consumeTime;
	}
	public void setConsumeTime(String consumeTime) {
		this.consumeTime = consumeTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}

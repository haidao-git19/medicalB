package com.netbull.shop.common.vo;

import java.io.Serializable;

public class SignInVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1011617722811906943L;
	private Integer id;
	private String openId;
	private String userId;
	/*获得积分*/
	private Integer receivePoint;
	/*创建时间*/
	private String createTime;
	/*签到时间*/
	private String signInTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSignInTime() {
		return signInTime;
	}
	public void setSignInTime(String signInTime) {
		this.signInTime = signInTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getReceivePoint() {
		return receivePoint;
	}
	public void setReceivePoint(Integer receivePoint) {
		this.receivePoint = receivePoint;
	}
	
}

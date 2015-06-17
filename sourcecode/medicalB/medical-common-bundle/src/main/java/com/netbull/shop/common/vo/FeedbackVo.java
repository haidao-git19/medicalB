package com.netbull.shop.common.vo;

public class FeedbackVo {

	private Integer id;
	/****** 用户编号 ******/
	private String userId;
	/****** 意见反馈 ******/
	private String mobileModel;
	/****** 意见内容 ******/
	private String content;
	/****** 创建时间 ******/
	private String creatTime;
	/****** 意见回复内容 ******/
	private String replyContent;
	/****** 意见回复时间 ******/
	private String replyTime;
	/****** 意见回复人id******/
	private String replyUser;
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}
	public String getReplyUser() {
		return replyUser;
	}
	public void setReplyUser(String replyUser) {
		this.replyUser = replyUser;
	}
	public String getMobileModel() {
		return mobileModel;
	}
	public void setMobileModel(String mobileModel) {
		this.mobileModel = mobileModel;
	}
	
}

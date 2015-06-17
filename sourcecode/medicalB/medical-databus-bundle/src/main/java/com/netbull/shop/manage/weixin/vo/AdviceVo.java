package com.netbull.shop.manage.weixin.vo;

import java.io.Serializable;

public class AdviceVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3236191793542607756L;
	private Integer adviceID;
	private Integer objectID;
	private String objectName;
	private String adviceTitle;
	private String note;
	private Integer adviceType;
	private Integer sectionID;
	private String sectionName;
	private String userID;
	public Integer getAdviceID() {
		return adviceID;
	}
	public void setAdviceID(Integer adviceID) {
		this.adviceID = adviceID;
	}
	public Integer getObjectID() {
		return objectID;
	}
	public void setObjectID(Integer objectID) {
		this.objectID = objectID;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getAdviceTitle() {
		return adviceTitle;
	}
	public void setAdviceTitle(String adviceTitle) {
		this.adviceTitle = adviceTitle;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getAdviceType() {
		return adviceType;
	}
	public void setAdviceType(Integer adviceType) {
		this.adviceType = adviceType;
	}
	public Integer getSectionID() {
		return sectionID;
	}
	public void setSectionID(Integer sectionID) {
		this.sectionID = sectionID;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
}

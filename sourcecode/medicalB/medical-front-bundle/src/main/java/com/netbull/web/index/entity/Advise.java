package com.netbull.web.index.entity;


public class Advise {

	private int adviceID;
	private int objectID;
	private String objectName;
	private String adviceTitle;
	private String note;
	private int adviceType;
	private int sectionID;
	private String sectionName;
	private String userID;
	public int getAdviceID() {
		return adviceID;
	}
	public void setAdviceID(int adviceID) {
		this.adviceID = adviceID;
	}
	public int getObjectID() {
		return objectID;
	}
	public void setObjectID(int objectID) {
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
	public int getAdviceType() {
		return adviceType;
	}
	public void setAdviceType(int adviceType) {
		this.adviceType = adviceType;
	}
	public int getSectionID() {
		return sectionID;
	}
	public void setSectionID(int sectionID) {
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

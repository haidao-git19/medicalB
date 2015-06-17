package com.netbull.shop.nursing.entity;

import java.io.Serializable;

public class Recovery implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2330349375113316987L;
	private Integer id;
	private Integer parentSectionID;
	private Integer childSectionID;
	private Integer diseaseID;
	private String nursingName;
	private String publisher;
	private String title;
	private String content;
	private String url;
	private Integer type;
	private Integer isShow;
	private String createTime;
	private String updateTime;
	private String userID;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentSectionID() {
		return parentSectionID;
	}
	public void setParentSectionID(Integer parentSectionID) {
		this.parentSectionID = parentSectionID;
	}
	public Integer getChildSectionID() {
		return childSectionID;
	}
	public void setChildSectionID(Integer childSectionID) {
		this.childSectionID = childSectionID;
	}
	public Integer getDiseaseID() {
		return diseaseID;
	}
	public void setDiseaseID(Integer diseaseID) {
		this.diseaseID = diseaseID;
	}
	public String getNursingName() {
		return nursingName;
	}
	public void setNursingName(String nursingName) {
		this.nursingName = nursingName;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIsShow() {
		return isShow;
	}
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
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
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
}

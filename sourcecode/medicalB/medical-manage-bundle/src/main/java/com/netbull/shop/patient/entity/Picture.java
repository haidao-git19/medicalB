package com.netbull.shop.patient.entity;

public class Picture {

	private int id;
	private int objectID;
	private String objectType;
	private String picUrl;
	private String picDesc;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getObjectID() {
		return objectID;
	}
	public void setObjectID(int objectID) {
		this.objectID = objectID;
	}
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getPicDesc() {
		return picDesc;
	}
	public void setPicDesc(String picDesc) {
		this.picDesc = picDesc;
	}
	
}

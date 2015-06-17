package com.netbull.shop.category.entity;

import java.io.Serializable;

public class CategoryAttrClass implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7678727661516334072L;
	private Integer id;
	private String categoryCode;
	private String attrClassName;
	private String createPerson;
	private String createTime;
	private String updatePerson;
	private String updateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getAttrClassName() {
		return attrClassName;
	}
	public void setAttrClassName(String attrClassName) {
		this.attrClassName = attrClassName;
	}
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdatePerson() {
		return updatePerson;
	}
	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
}

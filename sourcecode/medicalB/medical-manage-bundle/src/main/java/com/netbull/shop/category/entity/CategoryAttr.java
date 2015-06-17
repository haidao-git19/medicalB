package com.netbull.shop.category.entity;

import java.io.Serializable;

public class CategoryAttr implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8471751801087580499L;
	private Integer id;
	private Integer attrClassId;
	private String categoryCode;
	private Integer sortNum;
	private String attrName;
	private String attrCode;
	private Integer attrEnterType;
	private Integer attrType;
	private String description;
	private String createTime;
	private String createPerson;
	private String updateTime;
	private String updatePerson;
	
	private String attrClassName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAttrClassId() {
		return attrClassId;
	}
	public void setAttrClassId(Integer attrClassId) {
		this.attrClassId = attrClassId;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public String getAttrCode() {
		return attrCode;
	}
	public void setAttrCode(String attrCode) {
		this.attrCode = attrCode;
	}
	public Integer getAttrEnterType() {
		return attrEnterType;
	}
	public void setAttrEnterType(Integer attrEnterType) {
		this.attrEnterType = attrEnterType;
	}
	public Integer getAttrType() {
		return attrType;
	}
	public void setAttrType(Integer attrType) {
		this.attrType = attrType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public String getUpdatePerson() {
		return updatePerson;
	}
	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}
	public String getAttrClassName() {
		return attrClassName;
	}
	public void setAttrClassName(String attrClassName) {
		this.attrClassName = attrClassName;
	}
	
}

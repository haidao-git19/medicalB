package com.netbull.shop.common.vo;

import java.io.Serializable;

public class CatalogVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2341166367808838829L;
	private Integer id;
	/*类目名称*/
	private String catalogName;
	/*类目编码*/
	private String catalogCode;
	/*属性名称*/
	private String attrName;
	/*属性值*/
	private String attrValue;
	private String createTime;
	private String updateTime;
	/*排列*/
	private String seq;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public String getCatalogCode() {
		return catalogCode;
	}
	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public String getAttrValue() {
		return attrValue;
	}
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
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
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	
}

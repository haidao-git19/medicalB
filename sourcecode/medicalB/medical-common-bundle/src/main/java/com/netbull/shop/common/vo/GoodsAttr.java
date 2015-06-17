package com.netbull.shop.common.vo;

import java.io.Serializable;

public class GoodsAttr implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6898723221615202384L;
	private Integer id;
	private String goodsCode;
	private String attrCode;
	private String attrValue;
	private String createTime;
	private String updateTime;
	private String attrName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getAttrCode() {
		return attrCode;
	}
	public void setAttrCode(String attrCode) {
		this.attrCode = attrCode;
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
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	
	
}

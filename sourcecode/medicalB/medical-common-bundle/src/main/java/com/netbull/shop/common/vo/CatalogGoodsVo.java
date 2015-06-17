package com.netbull.shop.common.vo;

import java.io.Serializable;

public class CatalogGoodsVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -418396455907226426L;
	private Integer id;
	/*类目编码*/
	private String catalogCode;
	/*商品编码*/
	private String goodsCode;
	/*商品版本*/
	private String goodsVersion;
	/*封面图*/
	private String coverImage;
	/*排序*/
	private String order;
	/*类目名称*/
	private String catalogName;
	/*属性名称*/
	private String attrName;
	/*属性值*/
	private String attrValue;
	/*商品名称*/
	private String goodsName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCatalogCode() {
		return catalogCode;
	}
	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsVersion() {
		return goodsVersion;
	}
	public void setGoodsVersion(String goodsVersion) {
		this.goodsVersion = goodsVersion;
	}
	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
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
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	
}

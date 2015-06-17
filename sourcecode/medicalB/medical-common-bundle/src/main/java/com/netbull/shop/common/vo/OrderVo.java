package com.netbull.shop.common.vo;

public class OrderVo {

	private Integer id;
	private String userId;
	private String orderCode;
	private String acceptId;
	private String goodsCode;
	private String goodsVersion;
	private String localCode;
	private Float totalFee;
	private String style;
	private String sellTime;
	private Integer productNum;
	private String status;
	private String ispay;
	private String type;
	
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
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
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
	public String getLocalCode() {
		return localCode;
	}
	public void setLocalCode(String localCode) {
		this.localCode = localCode;
	}
	public Float getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Float totalFee) {
		this.totalFee = totalFee;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getSellTime() {
		return sellTime;
	}
	public void setSellTime(String sellTime) {
		this.sellTime = sellTime;
	}
	public Integer getProductNum() {
		return productNum;
	}
	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIspay() {
		return ispay;
	}
	public void setIspay(String ispay) {
		this.ispay = ispay;
	}
	public String getAcceptId() {
		return acceptId;
	}
	public void setAcceptId(String acceptId) {
		this.acceptId = acceptId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}

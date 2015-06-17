package com.netbull.shop.common.vo;

import java.io.Serializable;

public class SellRecordVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6647226288041771506L;
	private Integer id;
	/*用户id*/
	private String userId;
	/*订单编号*/
	private String orderCode;
	/*商品编号*/
	private String goodsCode;
	/*地区*/
	private String localCode;
	/*订单金额*/
	private float totalFee;
	/*购买记录类型*/
	private String style;
	/*购买时间*/
	private String sellTime;
	/*产品数量*/
	private Integer productNum;
	/*状态*/
	private String status; 
	/*是否支付*/
	private String ispay;
	
	/*收货人姓名*/
	private String acceptName;
	/*收货人手机*/
	private String phone;
	/*收货人固话*/
	private String telephone;
	/*快递编号*/
	private String expressCode;
	/*快递单号*/
	private String mailNo;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getLocalCode() {
		return localCode;
	}
	public void setLocalCode(String localCode) {
		this.localCode = localCode;
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
	public String getAcceptName() {
		return acceptName;
	}
	public void setAcceptName(String acceptName) {
		this.acceptName = acceptName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public float getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(float totalFee) {
		this.totalFee = totalFee;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getExpressCode() {
		return expressCode;
	}
	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}
	public String getMailNo() {
		return mailNo;
	}
	public void setMailNo(String mailNo) {
		this.mailNo = mailNo;
	}
	
}

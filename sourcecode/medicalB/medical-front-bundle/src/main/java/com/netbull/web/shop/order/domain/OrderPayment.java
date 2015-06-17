package com.netbull.web.shop.order.domain;

import java.util.Date;

public class OrderPayment {
	/** 未支付 */
	public static final int UN_PAY = 0;
	/** 已支付 */
	public static final int ALREADY_PAY = 1;
	
	private long id;
	private String orderNumber;
	private int payPrice;
	private int payStatus;
	private int payMode; //1在线支付  2货到付款
	private String payPlatformCode;
	private Date payTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(int payPrice) {
		this.payPrice = payPrice;
	}

	public int getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}

	public int getPayMode() {
		return payMode;
	}

	public void setPayMode(int payMode) {
		this.payMode = payMode;
	}

	public String getPayPlatformCode() {
		return payPlatformCode;
	}

	public void setPayPlatformCode(String payPlatformCode) {
		this.payPlatformCode = payPlatformCode;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

}

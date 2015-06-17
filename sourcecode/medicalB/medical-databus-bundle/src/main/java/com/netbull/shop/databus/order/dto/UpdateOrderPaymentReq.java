package com.netbull.shop.databus.order.dto;

public class UpdateOrderPaymentReq {

	private String orderNum;
	private String sign;

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}

package com.netbull.shop.databus.order.dto;

import com.netbull.shop.manage.common.http.Resp;

public class PutServiceOrderResp extends Resp {

	private String orderNum;
	private String tradeNum;

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getTradeNum() {
		return tradeNum;
	}

	public void setTradeNum(String tradeNum) {
		this.tradeNum = tradeNum;
	}

}

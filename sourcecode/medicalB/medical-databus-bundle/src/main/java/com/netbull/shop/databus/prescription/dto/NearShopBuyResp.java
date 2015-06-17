package com.netbull.shop.databus.prescription.dto;

import java.util.List;

import com.netbull.shop.databus.prescription.model.OneShopBuy;

public class NearShopBuyResp {
	private String code;
	private String msg;
	private List<OneShopBuy> shops;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<OneShopBuy> getShops() {
		return shops;
	}

	public void setShops(List<OneShopBuy> shops) {
		this.shops = shops;
	}

}

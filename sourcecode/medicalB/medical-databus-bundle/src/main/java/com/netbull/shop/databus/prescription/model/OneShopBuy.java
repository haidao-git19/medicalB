package com.netbull.shop.databus.prescription.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 同店购买
 */
public class OneShopBuy {
	@JSONField(name="shopID")
	private long shopId;
	private String shopName;
	private String address;
	private String logo;
	@JSONField(name="distance1")
	private double distance;
	private String phone;
	private String description;
	@JSONField(name="lng")
	private String locationX;
	@JSONField(name="lat")
	private String locationY;
	private int canBuyAll; // 能买全1 不能买全0
	private double totalPrice; // 买全后，总价格

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocationX() {
		return locationX;
	}

	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}

	public String getLocationY() {
		return locationY;
	}

	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}

	public int getCanBuyAll() {
		return canBuyAll;
	}

	public void setCanBuyAll(int canBuyAll) {
		this.canBuyAll = canBuyAll;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

}

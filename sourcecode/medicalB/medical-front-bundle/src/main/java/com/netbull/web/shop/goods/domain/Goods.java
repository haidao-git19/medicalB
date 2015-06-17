package com.netbull.web.shop.goods.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class Goods {

	private String goodsCode;
	private String brandName;
	private String goodsName;
	private String spec;
	private String sellingPoints;
	private String details;
	private double marketPrice;
	private double shopPrice;
	private int sellCharacter;
	private String weight;
	private String license;
	private String producer;
	private String imgPath;
	private long shopId;

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	@JSONField(serialize = false)
	public String getSellingPoints() {
		return sellingPoints;
	}

	public void setSellingPoints(String sellingPoints) {
		this.sellingPoints = sellingPoints;
	}

	public double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public double getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(double shopPrice) {
		this.shopPrice = shopPrice;
	}

	public int getSellCharacter() {
		return sellCharacter;
	}

	public void setSellCharacter(int sellCharacter) {
		this.sellCharacter = sellCharacter;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	@JSONField(serialize = false)
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

}

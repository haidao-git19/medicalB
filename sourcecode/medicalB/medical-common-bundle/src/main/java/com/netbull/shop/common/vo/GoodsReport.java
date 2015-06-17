package com.netbull.shop.common.vo;

import java.io.Serializable;

public class GoodsReport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/****商品编号*****/
	private String goodsCode;
	/****库存数*****/
	private String goodsStock;
	/****评论数*****/
	private String remarkNum;
	/****购买数*****/
	private String buyNum;
	/****实际购买数*****/
	private String realBuyNum;
	/****点赞数*****/
	private String praiseNum;
	/****收藏数*****/
	private String collectNum;
	/****咨询数*****/
	private String consultNum;
	/****分数*****/
	private String score;
	/****月销量*****/
	private String monthSales;
	
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsStock() {
		return goodsStock;
	}
	public void setGoodsStock(String goodsStock) {
		this.goodsStock = goodsStock;
	}
	public String getRemarkNum() {
		return remarkNum;
	}
	public void setRemarkNum(String remarkNum) {
		this.remarkNum = remarkNum;
	}
	public String getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(String buyNum) {
		this.buyNum = buyNum;
	}
	public String getRealBuyNum() {
		return realBuyNum;
	}
	public void setRealBuyNum(String realBuyNum) {
		this.realBuyNum = realBuyNum;
	}
	public String getCollectNum() {
		return collectNum;
	}
	public void setCollectNum(String collectNum) {
		this.collectNum = collectNum;
	}
	public String getConsultNum() {
		return consultNum;
	}
	public void setConsultNum(String consultNum) {
		this.consultNum = consultNum;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getMonthSales() {
		return monthSales;
	}
	public void setMonthSales(String monthSales) {
		this.monthSales = monthSales;
	}
	public String getPraiseNum() {
		return praiseNum;
	}
	public void setPraiseNum(String praiseNum) {
		this.praiseNum = praiseNum;
	}

}

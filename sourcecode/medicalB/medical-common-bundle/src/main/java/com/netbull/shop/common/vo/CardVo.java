package com.netbull.shop.common.vo;

import java.io.Serializable;

public class CardVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3291059748349208794L;
	private Integer id;
	/*开卡记录ID*/
	private Integer cardRecordId;
	/*卡片序列号*/
	private String cardNo;
	/*卡号*/
	private String cardCode;
	/*卡类型*/
	private String cardType;
	/*卡使用状态*/
	private String cardStatus;
	/*卡生效时间*/
	private String cardStartDate;
	/*卡失效时间*/
	private String cardEndDate;
	/*卡密*/
	private String cardPass;
	/*可办理数*/
	private Integer validNum;
	/*备注*/
	private String remark;
	/*卡主题*/
	private String cardTitle;
	/*状态参数*/
	private String statusParam;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	public String getCardStartDate() {
		return cardStartDate;
	}

	public void setCardStartDate(String cardStartDate) {
		this.cardStartDate = cardStartDate;
	}

	public String getCardEndDate() {
		return cardEndDate;
	}

	public void setCardEndDate(String cardEndDate) {
		this.cardEndDate = cardEndDate;
	}

	public String getCardPass() {
		return cardPass;
	}

	public void setCardPass(String cardPass) {
		this.cardPass = cardPass;
	}

	public Integer getValidNum() {
		return validNum;
	}

	public void setValidNum(Integer validNum) {
		this.validNum = validNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCardRecordId() {
		return cardRecordId;
	}

	public void setCardRecordId(Integer cardRecordId) {
		this.cardRecordId = cardRecordId;
	}

	public String getCardTitle() {
		return cardTitle;
	}

	public void setCardTitle(String cardTitle) {
		this.cardTitle = cardTitle;
	}

	public String getStatusParam() {
		return statusParam;
	}

	public void setStatusParam(String statusParam) {
		this.statusParam = statusParam;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
}

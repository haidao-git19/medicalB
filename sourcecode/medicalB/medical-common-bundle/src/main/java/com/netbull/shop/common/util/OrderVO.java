package com.netbull.shop.common.util;

import java.io.Serializable;

/**
 * 订单VO对象�?
 * @author 
 * WSS2.6
 * September 23, 2009  09:06:53 AM
 */
public class OrderVO  implements Serializable {

	private static final long serialVersionUID = 6459762931646363002L;
	//网厅流水�?
	private String ID ;
	//各业务系统订单号，如办理CRM附属�?��品时，CRM返回的CRM客户订单�?
	private String orderId ;
	//客户编号，即CRM中的cust_id
	private String custId ;
	//客户名称
	private String custName ;
	//接入号码，如手机号码，固话号码，宽带账号�?
	private String serviceNbr ;
	//CRM中的�?��品编号，或�?ISMP的service_id
	private String oferId ;
	//CRM中的策略组编号，或�?ISMP的product_id
	private String groupId ;
	//订够发生时间，YYYYMMDDHH24MMSS
	private String operTime ;
	//操作类型�?，开通；2，变更；3，关�?
	private String operation ;
	//受理渠道�?，WEB�?，SMS�?，WAP
	private String chanel ;
	//业务系统�?，CRM�?，ISMP�?�?0000
	private String bussSys ;
	//联系电话
	private String contactPhone ;
	//新装地址
	private String address ;
	//电子邮件
	private String email ;
	//证件类型
	private String idType ;
	//证件号码
	private String idNum ;
	//本地网编�?
	private String latnId ;
	//订单状�?
	private String orderState ;
	//回复内容
	private String content ;
	//回复时间，YYYYMMDDHH24MMSS
	private String endTime;
	
	private String contactName;
	
	
	
	
	public String getID() {
		return ID;
	}
	public void setID(String id) {
		ID = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getServiceNbr() {
		return serviceNbr;
	}
	public void setServiceNbr(String serviceNbr) {
		this.serviceNbr = serviceNbr;
	}
	public String getOferId() {
		return oferId;
	}
	public void setOferId(String oferId) {
		this.oferId = oferId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getOperTime() {
		return operTime;
	}
	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getChanel() {
		return chanel;
	}
	public void setChanel(String chanel) {
		this.chanel = chanel;
	}
	public String getBussSys() {
		return bussSys;
	}
	public void setBussSys(String bussSys) {
		this.bussSys = bussSys;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getLatnId() {
		return latnId;
	}
	public void setLatnId(String latnId) {
		this.latnId = latnId;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}


	
}

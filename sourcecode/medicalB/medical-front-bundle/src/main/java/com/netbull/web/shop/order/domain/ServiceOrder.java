package com.netbull.web.shop.order.domain;

public class ServiceOrder {
	private long id;
	private String orderNumber;
	private long patientId;
	private long doctorId;
	private int serviceType;
	private String serviceNumber;
	private String unionTradeNumber;
	private String unionTradeMsg;
	private String unionTradeState;

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

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	public int getServiceType() {
		return serviceType;
	}

	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceNumber() {
		return serviceNumber;
	}

	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}

	public String getUnionTradeNumber() {
		return unionTradeNumber;
	}

	public void setUnionTradeNumber(String unionTradeNumber) {
		this.unionTradeNumber = unionTradeNumber;
	}

	public String getUnionTradeMsg() {
		return unionTradeMsg;
	}

	public void setUnionTradeMsg(String unionTradeMsg) {
		this.unionTradeMsg = unionTradeMsg;
	}

	public String getUnionTradeState() {
		return unionTradeState;
	}

	public void setUnionTradeState(String unionTradeState) {
		this.unionTradeState = unionTradeState;
	}

}

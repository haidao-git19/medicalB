package com.netbull.shop.databus.order.dto;

public class PutServiceOrderReq {
	private long patientId;
	private long doctorId;
	private int serviceType;
	private String serviceNumber;
	private long shopId;
	private long prescriptionId;
	private int fee;
	private String sign;
	private int accpay;
	private int accpayfee;

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

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public long getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(long prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public int getAccpay() {
		return accpay;
	}

	public void setAccpay(int accpay) {
		this.accpay = accpay;
	}

	public int getAccpayfee() {
		return accpayfee;
	}

	public void setAccpayfee(int accpayfee) {
		this.accpayfee = accpayfee;
	}

}

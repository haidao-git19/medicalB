package com.netbull.shop.databus.prescription.dto;

public class PatientDetailResp {
	private String code;
	private String msg;
	private PatientPrescriptionDto data;

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

	public PatientPrescriptionDto getData() {
		return data;
	}

	public void setData(PatientPrescriptionDto data) {
		this.data = data;
	}

}

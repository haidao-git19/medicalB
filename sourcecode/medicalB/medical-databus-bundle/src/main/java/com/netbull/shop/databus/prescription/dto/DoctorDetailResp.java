package com.netbull.shop.databus.prescription.dto;

public class DoctorDetailResp {
	private String code;
	private String msg;
	private PrescriptionDto data;

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

	public PrescriptionDto getData() {
		return data;
	}

	public void setData(PrescriptionDto data) {
		this.data = data;
	}

}

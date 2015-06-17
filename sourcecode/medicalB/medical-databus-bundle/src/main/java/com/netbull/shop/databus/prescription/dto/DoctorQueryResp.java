package com.netbull.shop.databus.prescription.dto;

import java.util.Collections;
import java.util.List;

import com.netbull.shop.databus.prescription.model.Prescription;

public class DoctorQueryResp {

	private String code;
	private String msg;
	private long totalCount;
	public List<Prescription> prescriptions = Collections.emptyList();

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

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

}

package com.netbull.shop.databus.questionnaire.dto;

import java.util.Date;

public class ExceptionResult {

	private long rId;
	private String patientName;
	private String gender;
	private String hospitalName;
	private Date commitTime;

	public long getRId() {
		return rId;
	}

	public void setRId(long rId) {
		this.rId = rId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public Date getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(Date commitTime) {
		this.commitTime = commitTime;
	}

}

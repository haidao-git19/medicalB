package com.netbull.shop.manage.weixin.vo;

public class MonthService {
	private Long msid;
	private Long patientId;
	private Long doctorId;
	private Long fee;
	private String payState;
	private String createDate;
	private String beginDate;
	private String endDate;

	public Long getMsid() {
		return msid;
	}

	public void setMsid(Long msid) {
		this.msid = msid;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getFee() {
		return fee;
	}

	public void setFee(Long fee) {
		this.fee = fee;
	}

	public String getPayState() {
		return payState;
	}

	public void setPayState(String payState) {
		this.payState = payState;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "MonthService [msid=" + msid + ", patientId=" + patientId
				+ ", doctorId=" + doctorId + ", fee=" + fee + ", payState="
				+ payState + ", createDate=" + createDate + ", beginDate="
				+ beginDate + ", endDate=" + endDate + "]";
	}

}

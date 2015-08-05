package com.netbull.shop.databus.questionnaire.model;

import java.util.Date;

/**
 * 问卷患者绑定
 * 
 * @author Ade
 */
public class PatientBind {
	private long id;
	private long consultationId;
	private long qnId;
	private long patientId;
	private long doctorId;
	private Date bindTime;
	private int state;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getQnId() {
		return qnId;
	}

	public void setQnId(long qnId) {
		this.qnId = qnId;
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

	public Date getBindTime() {
		return bindTime;
	}

	public void setBindTime(Date bindTime) {
		this.bindTime = bindTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public long getConsultationId() {
		return consultationId;
	}

	public void setConsultationId(long consultationId) {
		this.consultationId = consultationId;
	}

}

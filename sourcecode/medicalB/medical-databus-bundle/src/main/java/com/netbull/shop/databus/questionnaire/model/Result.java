package com.netbull.shop.databus.questionnaire.model;

import java.util.Date;
import java.util.List;

/**
 * 问卷结果
 * 
 * @author Ade
 */
public class Result {
	private long id;
	private long qnId;
	private long doctorId;
	private long patientId;
	private Date commitTime;
	private int state;
	private long deputeDoctor;

	// --
	private List<ResultDetail> details;

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

	public long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public Date getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(Date commitTime) {
		this.commitTime = commitTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public List<ResultDetail> getDetails() {
		return details;
	}

	public void setDetails(List<ResultDetail> details) {
		this.details = details;
	}

	public long getDeputeDoctor() {
		return deputeDoctor;
	}

	public void setDeputeDoctor(long deputeDoctor) {
		this.deputeDoctor = deputeDoctor;
	}

}

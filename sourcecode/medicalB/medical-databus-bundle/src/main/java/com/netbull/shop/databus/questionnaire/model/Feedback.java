package com.netbull.shop.databus.questionnaire.model;

import java.util.Date;

/**
 * 问卷反馈
 * 
 * @author Ade
 */
public class Feedback {
	private long id;
	private long rId;
	private long doctorId;
	private String feedback;
	private Date createTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRId() {
		return rId;
	}

	public void setRId(long rId) {
		this.rId = rId;
	}

	public long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}

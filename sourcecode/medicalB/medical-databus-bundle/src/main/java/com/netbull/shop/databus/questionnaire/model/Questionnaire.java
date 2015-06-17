package com.netbull.shop.databus.questionnaire.model;

import java.util.Date;
import java.util.List;

/**
 * 问卷
 * 
 * @author Ade
 */
public class Questionnaire {
	private long id;
	private long doctorId;
	private String name;
	private String note;
	private Date createTime;

	// --
	private List<CaseClass> caseClasses;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<CaseClass> getCaseClasses() {
		return caseClasses;
	}

	public void setCaseClasses(List<CaseClass> caseClasses) {
		this.caseClasses = caseClasses;
	}

}
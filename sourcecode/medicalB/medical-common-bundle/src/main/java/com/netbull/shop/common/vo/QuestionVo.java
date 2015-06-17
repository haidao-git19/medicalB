package com.netbull.shop.common.vo;

import java.io.Serializable;

public class QuestionVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8113564345228485253L;
	private Integer questionID;
	private Integer patientID;
	private String title;
	private String disease;
	private String description;
	private String everTreat;
	private String wantHelp;
	private Integer status;
	private String createTime;
	private String updateTime;
	public Integer getQuestionID() {
		return questionID;
	}
	public void setQuestionID(Integer questionID) {
		this.questionID = questionID;
	}
	public Integer getPatientID() {
		return patientID;
	}
	public void setPatientID(Integer patientID) {
		this.patientID = patientID;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEverTreat() {
		return everTreat;
	}
	public void setEverTreat(String everTreat) {
		this.everTreat = everTreat;
	}
	public String getWantHelp() {
		return wantHelp;
	}
	public void setWantHelp(String wantHelp) {
		this.wantHelp = wantHelp;
	}
	
}

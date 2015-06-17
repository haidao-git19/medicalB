package com.netbull.web.index.entity;

import java.util.Date;

public class Consult {

	private int consultationID;
	private int userID;
	private Date consultationDate;
	private Date answerDate;
	private String question;
	private String answer;
	private int level;
	private String evaluation;
	private int isRepeat;
	private int sourceID;
	private int isFree;
	private String type;
	private String doctorName;
	private String datediff;
	private String attrname;
	private String doctorLevel;
	private String hospitalName;
	public int getConsultationID() {
		return consultationID;
	}
	public void setConsultationID(int consultationID) {
		this.consultationID = consultationID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public Date getConsultationDate() {
		return consultationDate;
	}
	public void setConsultationDate(Date consultationDate) {
		this.consultationDate = consultationDate;
	}
	public Date getAnswerDate() {
		return answerDate;
	}
	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
	public int getIsRepeat() {
		return isRepeat;
	}
	public void setIsRepeat(int isRepeat) {
		this.isRepeat = isRepeat;
	}
	public int getSourceID() {
		return sourceID;
	}
	public void setSourceID(int sourceID) {
		this.sourceID = sourceID;
	}
	public int getIsFree() {
		return isFree;
	}
	public void setIsFree(int isFree) {
		this.isFree = isFree;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDatediff() {
		return datediff;
	}
	public void setDatediff(String datediff) {
		this.datediff = datediff;
	}
	public String getAttrname() {
		return attrname;
	}
	public void setAttrname(String attrname) {
		this.attrname = attrname;
	}
	public String getDoctorLevel() {
		return doctorLevel;
	}
	public void setDoctorLevel(String doctorLevel) {
		this.doctorLevel = doctorLevel;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	
}

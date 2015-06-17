package com.netbull.shop.manage.weixin.vo;

public class ConsultationVo {
	
	private Long consultationID;
	private Long userID;
	private String title;
	private Long patientID;
	private Long doctorID;
	private String consultationDate;
	private String answerDate;
	private String question;
	private String answer;
	private Integer level;
	private String evaluation;
	private Integer isRepeat;
	private Long sourceID;
	private Integer isFree;
	private Integer isPrescribe;
	private Float fee;
	private String type;
	
	public Long getConsultationID() {
		return consultationID;
	}
	public void setConsultationID(Long consultationID) {
		this.consultationID = consultationID;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public Long getPatientID() {
		return patientID;
	}
	public void setPatientID(Long patientID) {
		this.patientID = patientID;
	}
	public String getConsultationDate() {
		return consultationDate;
	}
	public void setConsultationDate(String consultationDate) {
		this.consultationDate = consultationDate;
	}
	public String getAnswerDate() {
		return answerDate;
	}
	public void setAnswerDate(String answerDate) {
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
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
	public Integer getIsRepeat() {
		return isRepeat;
	}
	public void setIsRepeat(Integer isRepeat) {
		this.isRepeat = isRepeat;
	}
	public Long getSourceID() {
		return sourceID;
	}
	public void setSourceID(Long sourceID) {
		this.sourceID = sourceID;
	}
	public Integer getIsFree() {
		return isFree;
	}
	public void setIsFree(Integer isFree) {
		this.isFree = isFree;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(Long doctorID) {
		this.doctorID = doctorID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Float getFee() {
		return fee;
	}
	public void setFee(Float fee) {
		this.fee = fee;
	}
	public Integer getIsPrescribe() {
		return isPrescribe;
	}
	public void setIsPrescribe(Integer isPrescribe) {
		this.isPrescribe = isPrescribe;
	}
	
}

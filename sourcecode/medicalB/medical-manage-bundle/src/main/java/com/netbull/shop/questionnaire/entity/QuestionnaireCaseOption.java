package com.netbull.shop.questionnaire.entity;

public class QuestionnaireCaseOption {

	private Integer id;
	private String option;
	private Integer qId;
	private String note;
	private Integer exceptionFlag;
	private Integer level;
	private String index;
	private Double rangeFrom;
	private Double rangeTo;
	private Double exactlyVal;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public Integer getqId() {
		return qId;
	}
	public void setqId(Integer qId) {
		this.qId = qId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getExceptionFlag() {
		return exceptionFlag;
	}
	public void setExceptionFlag(Integer exceptionFlag) {
		this.exceptionFlag = exceptionFlag;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public Double getRangeFrom() {
		return rangeFrom;
	}
	public void setRangeFrom(Double rangeFrom) {
		this.rangeFrom = rangeFrom;
	}
	public Double getRangeTo() {
		return rangeTo;
	}
	public void setRangeTo(Double rangeTo) {
		this.rangeTo = rangeTo;
	}
	public Double getExactlyVal() {
		return exactlyVal;
	}
	public void setExactlyVal(Double exactlyVal) {
		this.exactlyVal = exactlyVal;
	}
	
	
}

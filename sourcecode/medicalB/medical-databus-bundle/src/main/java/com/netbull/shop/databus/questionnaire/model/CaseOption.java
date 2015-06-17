package com.netbull.shop.databus.questionnaire.model;

public class CaseOption {
	private long id;
	private long qId; // 题目ID
	private String option;
	private String note;
	private int exceptionFlag; // 异常选项标识： 1异常 0正常
	private int level; // 选项级别： 1正常 2轻度 3中度 4严重
	private String index; // 选项索引：例, A, B, C, D 例1, 2, 3, 4
	private double exactlyVal; // 精确值
	private double rangeFrom; // 区间上限
	private double rangeTo;// 区间下限

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public long getqId() {
		return qId;
	}

	public void setqId(long qId) {
		this.qId = qId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getExceptionFlag() {
		return exceptionFlag;
	}

	public void setExceptionFlag(int exceptionFlag) {
		this.exceptionFlag = exceptionFlag;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public double getExactlyVal() {
		return exactlyVal;
	}

	public void setExactlyVal(double exactlyVal) {
		this.exactlyVal = exactlyVal;
	}

	public double getRangeFrom() {
		return rangeFrom;
	}

	public void setRangeFrom(double rangeFrom) {
		this.rangeFrom = rangeFrom;
	}

	public double getRangeTo() {
		return rangeTo;
	}

	public void setRangeTo(double rangeTo) {
		this.rangeTo = rangeTo;
	}
}

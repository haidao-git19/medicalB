package com.netbull.shop.databus.questionnaire.model;

/**
 * 结果明细
 * 
 * @author Ade
 */
public class ResultDetail {
	private long id;
	private long rId;
	private long caseId;
	private double caseVal;
	private long caseOptionId;
	private int exceptionFlag;
	private int level;

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

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	public double getCaseVal() {
		return caseVal;
	}

	public void setCaseVal(double caseVal) {
		this.caseVal = caseVal;
	}

	public long getCaseOptionId() {
		return caseOptionId;
	}

	public void setCaseOptionId(long caseOptionId) {
		this.caseOptionId = caseOptionId;
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

}

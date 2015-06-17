package com.netbull.shop.databus.questionnaire.model;

import java.util.Date;
import java.util.List;

/**
 * 问卷题目
 * 
 * @author Ade
 */
public class Case {
	public static final int SINGLE_SELECT = 2;
	public static final int MULTI_SELECT = 3;

	private long id;
	private long qnId; // 问卷ID
	private long classId; // 问题分类ID
	private int type; // 问题类型 1 填空题 2单选 3多选
	private String title;
	private String unit; // 单位
	private int state; // 状态: 1有效 0无效
	private Date createTime;
	private int sort;

	// --
	private List<CaseOption> caseOptions;

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

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public List<CaseOption> getCaseOptions() {
		return caseOptions;
	}

	public void setCaseOptions(List<CaseOption> caseOptions) {
		this.caseOptions = caseOptions;
	}

}

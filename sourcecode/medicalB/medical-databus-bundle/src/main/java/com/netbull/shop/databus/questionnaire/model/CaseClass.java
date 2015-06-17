package com.netbull.shop.databus.questionnaire.model;

import java.util.List;

/**
 * 题目分类
 * 
 * @author Ade
 */
public class CaseClass {
	private long id;
	private long qnId; // 问卷ID
	private String name;
	private int sort;

	// --
	private List<Case> cases;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getQnId() {
		return qnId;
	}

	public void setQnId(long qnId) {
		this.qnId = qnId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public List<Case> getCases() {
		return cases;
	}

	public void setCases(List<Case> cases) {
		this.cases = cases;
	}

}

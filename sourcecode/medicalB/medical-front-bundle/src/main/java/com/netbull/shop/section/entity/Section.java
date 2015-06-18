package com.netbull.shop.section.entity;

import java.util.List;

public class Section {

	private long id;
	private String attrname;
	private String attrvalue;
	private long parentid;
	private int level;
	private int sort;
    private List<ChildSection> childlist;
    
    private String introduction;
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAttrname() {
		return attrname;
	}

	public void setAttrname(String attrname) {
		this.attrname = attrname;
	}

	public String getAttrvalue() {
		return attrvalue;
	}

	public void setAttrvalue(String attrvalue) {
		this.attrvalue = attrvalue;
	}

	public long getParentid() {
		return parentid;
	}

	public void setParentid(long parentid) {
		this.parentid = parentid;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public List<ChildSection> getChildlist() {
		return childlist;
	}

	public void setChildlist(List<ChildSection> childlist) {
		this.childlist = childlist;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

}


package com.netbull.web.index.entity;

import java.util.List;

public class SectionFilter {



    private String id;
	private String level;
	private int sort;
	private String doctorCount;
	private List<Section> list;
	private int parentid;
	private String attrvalue;
	private String attrname;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getDoctorCount() {
		return doctorCount;
	}
	public void setDoctorCount(String doctorCount) {
		this.doctorCount = doctorCount;
	}
	public List<Section> getList() {
		return list;
	}
	public void setList(List<Section> list) {
		this.list = list;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public String getAttrvalue() {
		return attrvalue;
	}
	public void setAttrvalue(String attrvalue) {
		this.attrvalue = attrvalue;
	}
	public String getAttrname() {
		return attrname;
	}
	public void setAttrname(String attrname) {
		this.attrname = attrname;
	}
	
	
}

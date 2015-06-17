
package com.netbull.web.hospital.entity;

import java.util.List;

import com.netbull.shop.section.entity.Section;

public class UnitSection {

	private int id;
	private int sort;
	private String level;
	private List<Section> list;
	
	private int parentid;
	private String attrvalue;
	private String attrname;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
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

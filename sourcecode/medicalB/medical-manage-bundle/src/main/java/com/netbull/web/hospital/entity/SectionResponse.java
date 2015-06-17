package com.netbull.web.hospital.entity;

import java.util.List;

public class SectionResponse {

	private String code;
	private String msg;
	private List<UnitSection> sectionList;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<UnitSection> getSectionList() {
		return sectionList;
	}
	public void setSectionList(List<UnitSection> sectionList) {
		this.sectionList = sectionList;
	}
	
}

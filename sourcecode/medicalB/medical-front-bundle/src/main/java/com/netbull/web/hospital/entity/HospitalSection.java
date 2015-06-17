package com.netbull.web.hospital.entity;

public class HospitalSection {
	
	private long hospitalID;
	private long sectionID;
	private String sectionName;
	private long sectionParentid;
	private String sectionParentname;
	public long getHospitalID() {
		return hospitalID;
	}
	public void setHospitalID(long hospitalID) {
		this.hospitalID = hospitalID;
	}
	public long getSectionID() {
		return sectionID;
	}
	public void setSectionID(long sectionID) {
		this.sectionID = sectionID;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public long getSectionParentid() {
		return sectionParentid;
	}
	public void setSectionParentid(long sectionParentid) {
		this.sectionParentid = sectionParentid;
	}
	public String getSectionParentname() {
		return sectionParentname;
	}
	public void setSectionParentname(String sectionParentname) {
		this.sectionParentname = sectionParentname;
	}
	
	

}

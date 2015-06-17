package com.netbull.shop.hospital.entity;

import java.util.List;

public class Hospital {

	private int hospitalID;
	private String hospitalName;
	private String linkMan;
	private String linkPhone;
	private int areaID;
	private String hospitalLevel;
	private String address;
	private double lng;
	private double lat;
	private Integer ctNum;
	private Integer cgNum;
	private String skill;
	private String userID;
	private String isCooperation;
	private String physicsAddress;
	private int doctorCount;
	private String type;
	private String areaName;
	private String summary;
	private Integer recomFlag;
	
	private List<Integer> sectionList; 
	
	public int getHospitalID() {
		return hospitalID;
	}
	public void setHospitalID(int hospitalID) {
		this.hospitalID = hospitalID;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getLinkPhone() {
		return linkPhone;
	}
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	public int getAreaID() {
		return areaID;
	}
	public void setAreaID(int areaID) {
		this.areaID = areaID;
	}
	public String getHospitalLevel() {
		return hospitalLevel;
	}
	public void setHospitalLevel(String hospitalLevel) {
		this.hospitalLevel = hospitalLevel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getIsCooperation() {
		return isCooperation;
	}
	public void setIsCooperation(String isCooperation) {
		this.isCooperation = isCooperation;
	}
	public String getPhysicsAddress() {
		return physicsAddress;
	}
	public void setPhysicsAddress(String physicsAddress) {
		this.physicsAddress = physicsAddress;
	}
	public int getDoctorCount() {
		return doctorCount;
	}
	public void setDoctorCount(int doctorCount) {
		this.doctorCount = doctorCount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public List<Integer> getSectionList() {
		return sectionList;
	}
	public void setSectionList(List<Integer> sectionList) {
		this.sectionList = sectionList;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Integer getRecomFlag() {
		return recomFlag;
	}
	public void setRecomFlag(Integer recomFlag) {
		this.recomFlag = recomFlag;
	}
	public Integer getCtNum() {
		return ctNum;
	}
	public void setCtNum(Integer ctNum) {
		this.ctNum = ctNum;
	}
	public Integer getCgNum() {
		return cgNum;
	}
	public void setCgNum(Integer cgNum) {
		this.cgNum = cgNum;
	}
}

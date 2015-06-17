package com.netbull.shop.area.entity;

import java.util.List;

import com.netbull.shop.hospital.entity.Hospital;

public class Area {

	private int areaID;
	private String areaName;
	private int parentID;
	private int index;
	private List<Hospital> hospitalList;
	private int counthospital;
	public int getAreaID() {
		return areaID;
	}
	public void setAreaID(int areaID) {
		this.areaID = areaID;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public int getParentID() {
		return parentID;
	}
	public void setParentID(int parentID) {
		this.parentID = parentID;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public List<Hospital> getHospitalList() {
		return hospitalList;
	}
	public void setHospitalList(List<Hospital> hospitalList) {
		this.hospitalList = hospitalList;
	}
	public int getCounthospital() {
		return counthospital;
	}
	public void setCounthospital(int counthospital) {
		this.counthospital = counthospital;
	}
	
}

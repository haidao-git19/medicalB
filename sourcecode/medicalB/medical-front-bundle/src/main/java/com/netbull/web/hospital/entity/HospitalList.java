
package com.netbull.web.hospital.entity;

import java.util.List;


public class HospitalList {

	private List<UnitList> hospitalList;
	
	private String msg;
	private String code;
	public List<UnitList> getHospitalList() {
		return hospitalList;
	}
	public void setHospitalList(List<UnitList> hospitalList) {
		this.hospitalList = hospitalList;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}

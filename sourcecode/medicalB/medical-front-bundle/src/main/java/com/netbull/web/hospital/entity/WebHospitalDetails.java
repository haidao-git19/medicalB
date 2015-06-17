
package com.netbull.web.hospital.entity;

import com.netbull.shop.hospital.entity.Hospital;

public class WebHospitalDetails {

	private String code ;
	private String msg;
	private Hospital hospitalDetail;
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
	public Hospital getHospitalDetail() {
		return hospitalDetail;
	}
	public void setHospitalDetail(Hospital hospitalDetail) {
		this.hospitalDetail = hospitalDetail;
	}
	
}

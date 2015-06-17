package com.netbull.shop.common.vo;

import java.io.Serializable;

public class PlusVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1312912466781828084L;
	private Integer plusID;
	private Integer doctorID;
	private Integer patientID;
	private Integer status;
	private double price;
	private Integer isPay;
	private String plusTime;
	private String createTime;
	private String updateTime;
	public Integer getPlusID() {
		return plusID;
	}
	public void setPlusID(Integer plusID) {
		this.plusID = plusID;
	}
	public Integer getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(Integer doctorID) {
		this.doctorID = doctorID;
	}
	public Integer getPatientID() {
		return patientID;
	}
	public void setPatientID(Integer patientID) {
		this.patientID = patientID;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getIsPay() {
		return isPay;
	}
	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}
	public String getPlusTime() {
		return plusTime;
	}
	public void setPlusTime(String plusTime) {
		this.plusTime = plusTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
}

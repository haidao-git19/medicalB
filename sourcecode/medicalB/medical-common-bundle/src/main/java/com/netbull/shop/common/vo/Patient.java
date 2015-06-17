package com.netbull.shop.common.vo;

import java.util.Date;

public class Patient {

	private int patientID;
	private String patientName;
	private String nickName;
	private String patientSex;
	private String patientAge;
	private String patientCard;
	private String patientPassword;
	private double balance;
	private int patientLevel;
	private Date createTime;
	private String contactPhone;
	private String img;
	private String address;
	private String isComp;
	
	public int getPatientID() {
		return patientID;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPatientSex() {
		return patientSex;
	}
	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}
	public String getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}
	public String getPatientCard() {
		return patientCard;
	}
	public void setPatientCard(String patientCard) {
		this.patientCard = patientCard;
	}
	public String getPatientPassword() {
		return patientPassword;
	}
	public void setPatientPassword(String patientPassword) {
		this.patientPassword = patientPassword;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getPatientLevel() {
		return patientLevel;
	}
	public void setPatientLevel(int patientLevel) {
		this.patientLevel = patientLevel;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getIsComp() {
		return isComp;
	}
	public void setIsComp(String isComp) {
		this.isComp = isComp;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
}
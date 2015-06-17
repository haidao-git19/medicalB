package com.netbull.shop.manage.weixin.vo;

public class Doctor {

	private long doctorID; // 医生ID
	private String doctorName; // 医生姓名
	private String doctorPassword; // 医生密码
	private long hospitalID; // 所属医院
	private String hospitalName; // 所属医院
	private long sectionID; // 所属科室
	private String sectionName; // 所属科室
	private String phone; // 医生电话
	private String doctorLevel; // 职称
	private String skill; // 特长擅长
	private String experience; // 就医经验
	private String ctArea; // 咨询范围
	private String netctArea; // 网络咨询范围
	private String practice; // 执业经历
	private int isZZ;// 是否支持转诊
	private int isNetCT;// 是否支持网络咨询
	private int isVideoCT;// 是否支持视频咨询
	private int isAudioCT;// 是否支持语音咨询
	private double balance; // 账户余额
	private double ctPrice;// 咨询资费
	private double netctPrice;// 网络咨询资费
	private double videoctPrice;// 视频咨询资费
	private double audioPrice;// 语音咨询资费
	private String videoAccount; // 视频账号

	public long getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(long doctorID) {
		this.doctorID = doctorID;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorPassword() {
		return doctorPassword;
	}

	public void setDoctorPassword(String doctorPassword) {
		this.doctorPassword = doctorPassword;
	}

	public long getHospitalID() {
		return hospitalID;
	}

	public void setHospitalID(long hospitalID) {
		this.hospitalID = hospitalID;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public long getSectionID() {
		return sectionID;
	}

	public void setSectionID(long sectionID) {
		this.sectionID = sectionID;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDoctorLevel() {
		return doctorLevel;
	}

	public void setDoctorLevel(String doctorLevel) {
		this.doctorLevel = doctorLevel;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getCtArea() {
		return ctArea;
	}

	public void setCtArea(String ctArea) {
		this.ctArea = ctArea;
	}

	public String getNetctArea() {
		return netctArea;
	}

	public void setNetctArea(String netctArea) {
		this.netctArea = netctArea;
	}

	public String getPractice() {
		return practice;
	}

	public void setPractice(String practice) {
		this.practice = practice;
	}

	public int getIsZZ() {
		return isZZ;
	}

	public void setIsZZ(int isZZ) {
		this.isZZ = isZZ;
	}

	public int getIsNetCT() {
		return isNetCT;
	}

	public void setIsNetCT(int isNetCT) {
		this.isNetCT = isNetCT;
	}

	public int getIsVideoCT() {
		return isVideoCT;
	}

	public void setIsVideoCT(int isVideoCT) {
		this.isVideoCT = isVideoCT;
	}

	public int getIsAudioCT() {
		return isAudioCT;
	}

	public void setIsAudioCT(int isAudioCT) {
		this.isAudioCT = isAudioCT;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getCtPrice() {
		return ctPrice;
	}

	public void setCtPrice(double ctPrice) {
		this.ctPrice = ctPrice;
	}

	public double getNetctPrice() {
		return netctPrice;
	}

	public void setNetctPrice(double netctPrice) {
		this.netctPrice = netctPrice;
	}

	public double getVideoctPrice() {
		return videoctPrice;
	}

	public void setVideoctPrice(double videoctPrice) {
		this.videoctPrice = videoctPrice;
	}

	public double getAudioPrice() {
		return audioPrice;
	}

	public void setAudioPrice(double audioPrice) {
		this.audioPrice = audioPrice;
	}

	public String getVideoAccount() {
		return videoAccount;
	}

	public void setVideoAccount(String videoAccount) {
		this.videoAccount = videoAccount;
	}

}

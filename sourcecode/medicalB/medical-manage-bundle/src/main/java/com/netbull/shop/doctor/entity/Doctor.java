package com.netbull.shop.doctor.entity;

import java.util.Date;

public class Doctor {

	private long doctorID; // 医生ID
	private String doctorName;//医生姓名
	private String doctorPassword;//登录密码
	private long hospitalID; // 所属医院
	private String hospitalName; // 所属医院
	private long sectionID; // 所属科室
	private String sectionName; // 所属科室
	private String doctorLevel; // 职称

	private double balance; // 账户余额
	private long creator;// 创建人

	private Integer isRecommend; // 是否推荐
	private Integer isZZ;// 是否支持转诊
	private Integer isNetCT;// 是否支持网络咨询
	private Integer isVideoCT;// 是否支持视频咨询
	private Integer isAudioCT;// 是否支持语音咨询
	private Integer isVisit;//是否支持上门服务

	private String skill; // 特长擅长
	private String experience; // 就医经验
	private String ctArea; // 咨询范围
	private String netctArea; // 网络咨询范围
	private String practice; // 执业经历

	private Date createTime;
	private Date updateTime;

	private long loginID;// 关联user_info表主键
	private String loginAccount; // 登录账号 user_info表 login_name
	private String realName; // 医生姓名 user_info表 true_name
	private String loginPwd; // 登录密码 user_info表 password
	private String phone; // 医生电话 user_info表 phone
	
	private String avatar; //头像路径
	private int status; //0表示删除  1表示正常
	
	private String remind;//门诊提示
	private String remindTime;//提示发布时间
	private int specialist;//0表示非顶级专家，1表示顶级专家

	public long getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(long doctorID) {
		this.doctorID = doctorID;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
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


	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public long getCreator() {
		return creator;
	}

	public void setCreator(long creator) {
		this.creator = creator;
	}

	public long getLoginID() {
		return loginID;
	}

	public void setLoginID(long loginID) {
		this.loginID = loginID;
	}

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public Integer getIsZZ() {
		return isZZ;
	}

	public void setIsZZ(Integer isZZ) {
		this.isZZ = isZZ;
	}

	public Integer getIsNetCT() {
		return isNetCT;
	}

	public void setIsNetCT(Integer isNetCT) {
		this.isNetCT = isNetCT;
	}

	public Integer getIsVideoCT() {
		return isVideoCT;
	}

	public void setIsVideoCT(Integer isVideoCT) {
		this.isVideoCT = isVideoCT;
	}

	public Integer getIsAudioCT() {
		return isAudioCT;
	}

	public void setIsAudioCT(Integer isAudioCT) {
		this.isAudioCT = isAudioCT;
	}

	public Integer getIsVisit() {
		return isVisit;
	}

	public void setIsVisit(Integer isVisit) {
		this.isVisit = isVisit;
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

	public String getRemind() {
		return remind;
	}

	public void setRemind(String remind) {
		this.remind = remind;
	}

	public String getRemindTime() {
		return remindTime;
	}

	public void setRemindTime(String remindTime) {
		this.remindTime = remindTime;
	}

	public int getSpecialist() {
		return specialist;
	}

	public void setSpecialist(int specialist) {
		this.specialist = specialist;
	}
	
}

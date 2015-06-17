package com.netbull.shop.doctor.entity;


public class Duty {
	private long id;
	private long doctorID;
	public String doctorName;
	private int weekFlag;
	private int weekNum;
	private int dayFlag;
	private String startTime;
	private String endTime;
	private int type;
	private int isLater;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public int getWeekFlag() {
		return weekFlag;
	}

	public void setWeekFlag(int weekFlag) {
		this.weekFlag = weekFlag;
	}

	public int getWeekNum() {
		return weekNum;
	}

	public void setWeekNum(int weekNum) {
		this.weekNum = weekNum;
	}

	public int getDayFlag() {
		return dayFlag;
	}

	public void setDayFlag(int dayFlag) {
		this.dayFlag = dayFlag;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIsLater() {
		return isLater;
	}

	public void setIsLater(int isLater) {
		this.isLater = isLater;
	}

}

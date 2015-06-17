package com.netbull.shop.doctor.entity;

public class DoctorFee {

	private long id;
	private long doctorID;
	private int type;
	private int level;
	private String levelTitle;
	private double fee;

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getLevelTitle() {
		return levelTitle;
	}

	public void setLevelTitle(String levelTitle) {
		this.levelTitle = levelTitle;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

}

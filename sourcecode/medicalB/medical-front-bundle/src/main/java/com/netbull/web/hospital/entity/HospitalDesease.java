package com.netbull.web.hospital.entity;

public class HospitalDesease {

	private long doctorID;
	private long diseaseID;
	private String diseaseName;
	private String doctorName;
	public long getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(long doctorID) {
		this.doctorID = doctorID;
	}
	public long getDiseaseID() {
		return diseaseID;
	}
	public void setDiseaseID(long diseaseID) {
		this.diseaseID = diseaseID;
	}
	public String getDiseaseName() {
		return diseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	
}

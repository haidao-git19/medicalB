package com.netbull.shop.disease.entity;

public class Disease {

	private long diseaseID;
	private long sectionID;
	private String diseaseName;
	private String introduction;

	public long getDiseaseID() {
		return diseaseID;
	}

	public void setDiseaseID(long diseaseID) {
		this.diseaseID = diseaseID;
	}

	public long getSectionID() {
		return sectionID;
	}

	public void setSectionID(long sectionID) {
		this.sectionID = sectionID;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

}

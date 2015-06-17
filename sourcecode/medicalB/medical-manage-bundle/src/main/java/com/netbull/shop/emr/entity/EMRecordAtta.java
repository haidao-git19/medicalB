package com.netbull.shop.emr.entity;

public class EMRecordAtta {

	private long attaID;
	private String attaName;
	private String attaURL;
	private long recordID;

	public long getAttaID() {
		return attaID;
	}

	public void setAttaID(long attaID) {
		this.attaID = attaID;
	}

	public String getAttaName() {
		return attaName;
	}

	public void setAttaName(String attaName) {
		this.attaName = attaName;
	}

	public String getAttaURL() {
		return attaURL;
	}

	public void setAttaURL(String attaURL) {
		this.attaURL = attaURL;
	}

	public long getRecordID() {
		return recordID;
	}

	public void setRecordID(long recordID) {
		this.recordID = recordID;
	}

}

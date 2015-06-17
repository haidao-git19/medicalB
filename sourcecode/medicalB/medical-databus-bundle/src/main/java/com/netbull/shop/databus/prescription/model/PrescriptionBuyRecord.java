package com.netbull.shop.databus.prescription.model;

import java.util.Date;

/**
 * @author Ade
 */
public class PrescriptionBuyRecord {
	private long id;
	private long patientIdPrescriptionId;
	private long drugShopId;
	private Date createTime;// 关联绑定时间

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPatientIdPrescriptionId() {
		return patientIdPrescriptionId;
	}

	public void setPatientIdPrescriptionId(long patientIdPrescriptionId) {
		this.patientIdPrescriptionId = patientIdPrescriptionId;
	}

	public long getDrugShopId() {
		return drugShopId;
	}

	public void setDrugShopId(long drugShopId) {
		this.drugShopId = drugShopId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}

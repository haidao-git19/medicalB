package com.netbull.shop.databus.prescription.model;

import java.util.Date;

/**
 * 处方关系
 * 
 * 1. 医生开处方(或者从自己的处方库选择一个处方) 2. 选择要关联的患者
 * 
 * @author Ade
 */
public class PrescriptionRelation {
	private long id;
	private long prescriptionId;// 处方ID
	private long patientId; // 患者ID
	private long creator;// 关联创建人 - 通常为doctorID
	private Date createTime;// 关联绑定时间

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(long prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public long getCreator() {
		return creator;
	}

	public void setCreator(long creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}

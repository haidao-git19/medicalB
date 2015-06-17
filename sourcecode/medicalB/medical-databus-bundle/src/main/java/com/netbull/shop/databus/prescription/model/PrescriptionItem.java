package com.netbull.shop.databus.prescription.model;

/**
 * 处方清单
 * 
 * @author Ade
 */
public class PrescriptionItem {

	private long id;
	private long prescriptionId; // 处方ID
	private String drugName; // 药名名称
	private int drugType;// 药品分类: 处方药，临床药等
	private String drugSpec; // 药品规格
	private String producer; // 生产厂家
	private int amount;// 数量
	private String useage;// 药品使用方法

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

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public int getDrugType() {
		return drugType;
	}

	public void setDrugType(int drugType) {
		this.drugType = drugType;
	}

	public String getDrugSpec() {
		return drugSpec;
	}

	public void setDrugSpec(String drugSpec) {
		this.drugSpec = drugSpec;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getUseage() {
		return useage;
	}

	public void setUseage(String useage) {
		this.useage = useage;
	}

}

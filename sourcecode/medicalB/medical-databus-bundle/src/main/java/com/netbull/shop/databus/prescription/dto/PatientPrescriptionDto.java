package com.netbull.shop.databus.prescription.dto;

import java.util.List;

import com.netbull.shop.databus.prescription.model.PrescriptionItem;

/**
 * 处方
 * 
 * @author Ade
 */
public class PatientPrescriptionDto {

	private long id;
	private String title; // 处方名字
	private String application; // 适用症
	private String remark; // 备注
	private String qrCodePath;// 处方二维码图片路径
	private long binder;// 病人处方绑定人
	private String bindTime; // 病人处方绑定时间
	private Integer payStatus;
	private Integer fetchStatus;
	private List<PrescriptionItem> items; // 处方清单

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getQrCodePath() {
		return qrCodePath;
	}

	public void setQrCodePath(String qrCodePath) {
		this.qrCodePath = qrCodePath;
	}

	public long getBinder() {
		return binder;
	}

	public void setBinder(long binder) {
		this.binder = binder;
	}

	public String getBindTime() {
		return bindTime;
	}

	public void setBindTime(String bindTime) {
		this.bindTime = bindTime;
	}

	public List<PrescriptionItem> getItems() {
		return items;
	}

	public void setItems(List<PrescriptionItem> items) {
		this.items = items;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getFetchStatus() {
		return fetchStatus;
	}

	public void setFetchStatus(Integer fetchStatus) {
		this.fetchStatus = fetchStatus;
	}

}

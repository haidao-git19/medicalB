package com.netbull.shop.databus.prescription.dto;

import java.util.Date;
import java.util.List;

import com.netbull.shop.databus.prescription.model.PrescriptionItem;

/**
 * 处方
 * 
 * @author Ade
 */
public class PrescriptionDto {

	private long id;
	private long doctorId;// 开处方的医生ID
	private String title; // 处方名字
	private String application; // 适用症
	private String remark; // 备注
	private Date createTime;// 创建时间
	private int status;// 状态
	private int isShow;//
	private String qrCodePath;// 处方二维码图片路径

	private List<PrescriptionItem> items; // 处方清单

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIsShow() {
		return isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}

	public String getQrCodePath() {
		return qrCodePath;
	}

	public void setQrCodePath(String qrCodePath) {
		this.qrCodePath = qrCodePath;
	}

	public List<PrescriptionItem> getItems() {
		return items;
	}

	public void setItems(List<PrescriptionItem> items) {
		this.items = items;
	}

}

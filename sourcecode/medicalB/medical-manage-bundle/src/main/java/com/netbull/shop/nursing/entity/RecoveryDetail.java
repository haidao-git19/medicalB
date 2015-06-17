package com.netbull.shop.nursing.entity;

import java.io.Serializable;

public class RecoveryDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8805134484008734662L;
	private Integer detailID;
	private Integer nursingID;
	private String title;
	private String content;
	private Integer sort;
	private Integer isShow;
	public Integer getDetailID() {
		return detailID;
	}
	public void setDetailID(Integer detailID) {
		this.detailID = detailID;
	}
	public Integer getNursingID() {
		return nursingID;
	}
	public void setNursingID(Integer nursingID) {
		this.nursingID = nursingID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getIsShow() {
		return isShow;
	}
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	
	
}

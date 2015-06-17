package com.netbull.shop.databus.order.dto;

import java.util.List;

import com.netbull.shop.manage.common.http.Resp;

public class PatientOrderListResp extends Resp {

	private int pageNum;
	private int pageSize;
	private int totalCount;
	private List<PatientOrder> list;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<PatientOrder> getList() {
		return list;
	}

	public void setList(List<PatientOrder> list) {
		this.list = list;
	}

}

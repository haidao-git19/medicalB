package com.netbull.shop.databus.order.dto;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.netbull.shop.manage.common.http.Resp;

public class DoctorRefundListResp extends Resp {
	private int pageNum;
	private int pageSize;
	private int totalCount;
	private List<Map<String, Object>> list;

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

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}

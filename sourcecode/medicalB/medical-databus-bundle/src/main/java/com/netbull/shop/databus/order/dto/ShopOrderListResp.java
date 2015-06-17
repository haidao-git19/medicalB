package com.netbull.shop.databus.order.dto;

import java.util.List;

import com.netbull.shop.manage.common.http.Resp;

public class ShopOrderListResp extends Resp {
	private int pageNum;
	private int pageSize;
	private int totalCount;
	private int recentMonthIncome;// 单位: 分
	private int totalIncome;// 单位: 分
	private List<ShopOrder> list;

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

	public double getRecentMonthIncome() {
		return recentMonthIncome;
	}

	public void setRecentMonthIncome(int recentMonthIncome) {
		this.recentMonthIncome = recentMonthIncome;
	}

	public int getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(int totalIncome) {
		this.totalIncome = totalIncome;
	}

	public List<ShopOrder> getList() {
		return list;
	}

	public void setList(List<ShopOrder> list) {
		this.list = list;
	}

}

package com.netbull.web.shop.goods.vo;

import java.util.ArrayList;
import java.util.List;

public class CataNavigation {
	private long id;
	private long pid;
	private int depth;
	private String name;
	private int sort;

	private List<CataNavigation> childs = new ArrayList<CataNavigation>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public List<CataNavigation> getChilds() {
		return childs;
	}

	public void setChilds(List<CataNavigation> childs) {
		this.childs = childs;
	}

}

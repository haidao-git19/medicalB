package com.netbull.web.shop.index.entity;

import java.util.List;

public class Floor {

	private int floor;
	private String title;
	private List<FloorItem> items;

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<FloorItem> getItems() {
		return items;
	}

	public void setItems(List<FloorItem> items) {
		this.items = items;
	}

}

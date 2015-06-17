
package com.netbull.web.index.entity;

import java.util.List;

import com.netbull.shop.hospital.entity.Hospital;

public class UnitList {

	private List<Hospital> list;
	private String key;
	private String latnName;
	public List<Hospital> getList() {
		return list;
	}
	public void setList(List<Hospital> list) {
		this.list = list;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getLatnName() {
		return latnName;
	}
	public void setLatnName(String latnName) {
		this.latnName = latnName;
	}
	
	
}

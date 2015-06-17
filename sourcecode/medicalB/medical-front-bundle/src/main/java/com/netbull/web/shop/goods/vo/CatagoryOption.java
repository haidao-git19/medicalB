package com.netbull.web.shop.goods.vo;


public class CatagoryOption {

	private long catId;
	private String optionClsName;
	private long scId;
	private long id;
	private String optionName;
	private int sort;

	public long getCatId() {
		return catId;
	}

	public void setCatId(long catId) {
		this.catId = catId;
	}

	public String getOptionClsName() {
		return optionClsName;
	}

	public void setOptionClsName(String optionClsName) {
		this.optionClsName = optionClsName;
	}

	public long getScId() {
		return scId;
	}

	public void setScId(long scId) {
		this.scId = scId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}

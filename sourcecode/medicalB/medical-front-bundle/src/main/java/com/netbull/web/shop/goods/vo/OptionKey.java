package com.netbull.web.shop.goods.vo;


public class OptionKey implements Comparable {
	private long catId;
	private String optionClsName;
	private long scId;

	public OptionKey(long catId, String optionClsName, long scId) {
		this.catId = catId;
		this.optionClsName = optionClsName;
		this.scId = scId;
	}

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

	public int compareTo(Object o) {
		OptionKey tmp = (OptionKey)o;
		return tmp.getScId() == this.scId ? 0 : (tmp.getScId() > this.scId ? -1 : 1);
	}

}

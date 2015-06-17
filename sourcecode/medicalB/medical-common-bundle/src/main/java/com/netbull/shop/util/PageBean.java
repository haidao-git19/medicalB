package com.netbull.shop.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 分页实体
 * @author <a href=mailto:wu.peng@starit.com.cn>wu.peng</a> 2012-6-12
 *
 * @param <T>
 */
public class PageBean<T> {

	private int start;
	private int limit;
	private long count;
	private Map<String,T> paramterMap;
	private List<T> data = new ArrayList<T>();

	public PageBean() {

	}

	public PageBean(int start, int limit) {
		this.start = start;
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public String toString() {
		return "PageBean [start=" + start + ", limit=" + limit + ", count=" + count + ", data=" + data + "]";
	}

	public Map<String, T> getParamterMap() {
		return paramterMap;
	}

	public void setParamterMap(Map<String, T> paramterMap) {
		this.paramterMap = paramterMap;
	}


}

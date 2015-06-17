
package com.netbull.web.index.entity;

import java.util.List;

public class SectionFilterResponse {

	private String code;
	private String msg;
	private List<SectionFilter> filter;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<SectionFilter> getFilter() {
		return filter;
	}
	public void setFilter(List<SectionFilter> filter) {
		this.filter = filter;
	}
	
}

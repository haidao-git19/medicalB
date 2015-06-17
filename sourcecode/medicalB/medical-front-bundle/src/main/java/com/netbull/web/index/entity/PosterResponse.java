
package com.netbull.web.index.entity;

import java.util.List;

public class PosterResponse {

	private String code;
	private String msg;
	private List<Poster> posterList;
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
	public List<Poster> getPosterList() {
		return posterList;
	}
	public void setPosterList(List<Poster> posterList) {
		this.posterList = posterList;
	}
	
	
}

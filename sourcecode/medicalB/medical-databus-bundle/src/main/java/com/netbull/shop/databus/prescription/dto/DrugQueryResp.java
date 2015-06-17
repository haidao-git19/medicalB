package com.netbull.shop.databus.prescription.dto;

import java.util.Collections;
import java.util.List;

import com.netbull.shop.databus.prescription.model.Drug;

public class DrugQueryResp {

	private String code;
	private String msg;
	private long totalCount;
	private List<Drug> drugs = Collections.emptyList();

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

	public List<Drug> getDrugs() {
		return drugs;
	}

	public void setDrugs(List<Drug> drugs) {
		this.drugs = drugs;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

}

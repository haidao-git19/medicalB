package com.netbull.shop.databus.questionnaire.dto;

import java.util.List;

import com.netbull.shop.manage.common.http.Resp;

public class ExceptionResultResp extends Resp {
	private List<ExceptionResult> list;

	public List<ExceptionResult> getList() {
		return list;
	}

	public void setList(List<ExceptionResult> list) {
		this.list = list;
	}

}

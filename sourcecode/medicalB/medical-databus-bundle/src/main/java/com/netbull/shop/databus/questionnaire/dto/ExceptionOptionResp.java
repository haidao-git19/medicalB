package com.netbull.shop.databus.questionnaire.dto;

import java.util.List;

import com.netbull.shop.databus.questionnaire.model.ExceptionOption;
import com.netbull.shop.manage.common.http.Resp;

public class ExceptionOptionResp extends Resp {
	private List<ExceptionOption> options;

	public List<ExceptionOption> getOptions() {
		return options;
	}

	public void setOptions(List<ExceptionOption> options) {
		this.options = options;
	}

}

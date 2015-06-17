package com.netbull.shop.databus.questionnaire.dto;

import com.netbull.shop.databus.questionnaire.model.Questionnaire;
import com.netbull.shop.manage.common.http.Resp;

public class QuestionnaireResp extends Resp {

	private Questionnaire questionnaire;

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

}
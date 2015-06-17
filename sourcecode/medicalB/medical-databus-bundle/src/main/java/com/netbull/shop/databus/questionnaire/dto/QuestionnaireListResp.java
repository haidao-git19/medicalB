package com.netbull.shop.databus.questionnaire.dto;

import java.util.List;

import com.netbull.shop.databus.questionnaire.model.Questionnaire;
import com.netbull.shop.manage.common.http.Resp;

public class QuestionnaireListResp extends Resp {

	private List<Questionnaire> questionnaires;

	public List<Questionnaire> getQuestionnaires() {
		return questionnaires;
	}

	public void setQuestionnaires(List<Questionnaire> questionnaires) {
		this.questionnaires = questionnaires;
	}

}
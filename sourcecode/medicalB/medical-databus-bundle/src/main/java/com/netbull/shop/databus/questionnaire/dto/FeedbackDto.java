package com.netbull.shop.databus.questionnaire.dto;

import com.netbull.shop.databus.questionnaire.model.Feedback;
import com.netbull.shop.manage.common.http.Resp;

public class FeedbackDto extends Resp {

	private Feedback feedback;

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}
	
	
}

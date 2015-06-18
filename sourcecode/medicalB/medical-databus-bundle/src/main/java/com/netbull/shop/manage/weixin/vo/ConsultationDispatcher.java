package com.netbull.shop.manage.weixin.vo;

public class ConsultationDispatcher {
	private Long dispatcherId;
	private Long consultationID;
	private int dispatcherType;
	private Long targetId;

	public Long getDispatcherId() {
		return dispatcherId;
	}

	public void setDispatcherId(Long dispatcherId) {
		this.dispatcherId = dispatcherId;
	}

	public Long getConsultationID() {
		return consultationID;
	}

	public void setConsultationID(Long consultationID) {
		this.consultationID = consultationID;
	}

	public int getDispatcherType() {
		return dispatcherType;
	}

	public void setDispatcherType(int dispatcherType) {
		this.dispatcherType = dispatcherType;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

}

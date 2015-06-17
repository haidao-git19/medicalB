package com.netbull.shop.common.vo;

public class OperLogVO extends PagesVo {

	private long id;
	
	private Long userId;
	
	private String loginCode;
	
	private String ip;
	
	private String operationType;
	
	private String operationDesc;
	
	private String mDateTime;
	

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getOperationDesc() {
		return operationDesc;
	}

	public void setOperationDesc(String operationDesc) {
		this.operationDesc = operationDesc;
	}

	public String getmDateTime() {
		return mDateTime;
	}

	public void setmDateTime(String mDateTime) {
		this.mDateTime = mDateTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}

 package com.netbull.shop.common.util; 

import java.io.Serializable;
  
 public class SysParamVo implements Serializable{
	 
	private static final long serialVersionUID = 3775672301166994603L;

	 private int paramId;
	 
	 private String paramType;
	 
	 private String paramDesc;
	 
	 private String paramKey;
	 
	 private String paramValue;


	public int getParamId() {
		return paramId;
	}

	public void setParamId(int paramId) {
		this.paramId = paramId;
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public String getParamDesc() {
		return paramDesc;
	}

	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}

	public String getParamKey() {
		return paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

}

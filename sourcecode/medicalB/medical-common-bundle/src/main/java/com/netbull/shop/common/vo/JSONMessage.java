package com.netbull.shop.common.vo;

import net.sf.json.JSONSerializer;

public class JSONMessage {
	public static interface Flag{
		String SUCCESS = "0";
		String FAIL = "1";
		String TIMEOUT = "2";
	}
	
	/** 0-成功 其它-失败 */
	private String flag = Flag.FAIL;
	private String msg = "系统异常";
	private Object data;
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return JSONSerializer.toJSON(this).toString();
	}
}

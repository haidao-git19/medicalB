
package com.netbull.shop.common.dbutil;

import java.io.Serializable;
/**
 * 系统参数对象；
 * @author elvis
 *
 */
public class EntParamVo implements Serializable {

	/**
	 * 序列化对象；
	 */
	private static final long serialVersionUID = 3775672301166994603L;

	// 系统ID
	private int entId;

	// 系统编号
	private String entCode;

	// 参数描述
	private String entDesc;

	// 流量限制
	private String flowLimit;

	// 优先级
	private String priority;
	
	//删除标志
	private String delFlag;

	public int getEntId() {
		return entId;
	}

	public void setEntId(int entId) {
		this.entId = entId;
	}

	public String getEntCode() {
		return entCode;
	}

	public void setEntCode(String entCode) {
		this.entCode = entCode;
	}

	public String getEntDesc() {
		return entDesc;
	}

	public void setEntDesc(String entDesc) {
		this.entDesc = entDesc;
	}

	public String getFlowLimit() {
		return flowLimit;
	}

	public void setFlowLimit(String flowLimit) {
		this.flowLimit = flowLimit;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
}

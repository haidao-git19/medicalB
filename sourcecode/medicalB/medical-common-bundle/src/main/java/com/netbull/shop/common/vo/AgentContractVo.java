package com.netbull.shop.common.vo;

import java.io.Serializable;

public class AgentContractVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3102870813019195093L;
	private Integer id;
	/*合同编号*/
	private String contractCode;
	/*合同名称*/
	private String contractName;
	/*代理商名称*/
	private String agentCode;
	/*酬金规则*/
	private String feeRule;
	/*开始日期*/
	private String startDate;
	/*结束日期*/
	private String endDate;
	/*押金*/
	private Float deposit;
	/*发出特权卡数量*/
	private Integer outCardCount;
	/*退回特权卡数量*/
	private Integer inCardCount;
	/*签订日期*/
	private String createDate;
	/*更新日期*/
	private String updateDate;
	/*是否显示*/
	private String isShow;
	/*备注*/
	private String remark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Float getDeposit() {
		return deposit;
	}
	public void setDeposit(Float deposit) {
		this.deposit = deposit;
	}
	public Integer getOutCardCount() {
		return outCardCount;
	}
	public void setOutCardCount(Integer outCardCount) {
		this.outCardCount = outCardCount;
	}
	public Integer getInCardCount() {
		return inCardCount;
	}
	public void setInCardCount(Integer inCardCount) {
		this.inCardCount = inCardCount;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getFeeRule() {
		return feeRule;
	}
	public void setFeeRule(String feeRule) {
		this.feeRule = feeRule;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	
}

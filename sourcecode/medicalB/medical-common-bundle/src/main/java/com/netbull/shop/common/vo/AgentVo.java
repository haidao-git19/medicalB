package com.netbull.shop.common.vo;

import java.io.Serializable;

public class AgentVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 222603348213430722L;
	private Integer id;
	/*代理商编码*/
	private String agentCode;
	/*代理商名称*/
	private String agentName;
	/*联系人*/
	private String linkman;
	/*代理商性别*/
	private String gender;
	/*代理商年龄*/
	private Integer age;
	/*手机号*/
	private String phone;
	/*身份证号*/
	private String identityNumber;
	/*住址*/
	private String address;
	/*创建时间*/
	private String createTime;
	/*更新时间*/
	private String updateTime;
	/*状态*/
	private String status;
	/*代理商总发展人数*/
	private String totalCount;
	/*代理商总酬金*/
	private String totalFee;
	/*代理商已结算费用*/
	private String settleFee;
	/*已结算数*/
	private String settleCount;
	/*未结算数*/
	private String unsettleCount;
	/*押金*/
	private String deposit;
	/*合同名称*/
	private String contractName;
	/*合同号*/
	private String contractCode;
	/*费用规则*/
	private String feeRule;
	/*开始时间*/
	private String startDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdentityNumber() {
		return identityNumber;
	}
	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getSettleCount() {
		return settleCount;
	}
	public void setSettleCount(String settleCount) {
		this.settleCount = settleCount;
	}
	public String getUnsettleCount() {
		return unsettleCount;
	}
	public void setUnsettleCount(String unsettleCount) {
		this.unsettleCount = unsettleCount;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getFeeRule() {
		return feeRule;
	}
	public void setFeeRule(String feeRule) {
		this.feeRule = feeRule;
	}
	public String getSettleFee() {
		return settleFee;
	}
	public void setSettleFee(String settleFee) {
		this.settleFee = settleFee;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
}

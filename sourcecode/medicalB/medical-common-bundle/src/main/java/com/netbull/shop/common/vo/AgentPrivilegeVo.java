package com.netbull.shop.common.vo;

import java.io.Serializable;

public class AgentPrivilegeVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -181408812797292542L;
	private Integer id;
	/*合同编号*/
	private String contractCode;
	/*卡序*/
	private String cardNo;
	/*卡号*/
	private String cardCode;
	/*创建时间*/
	private String createDate;
	/*更新时间*/
	private String updateDate;
	/*状态*/
	private String status;
	/*合同名称*/
	private String contractName;
	/*代理商名称*/
	private String agentName;
	/*代理商编码*/
	private String agentCode;
	
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
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
}

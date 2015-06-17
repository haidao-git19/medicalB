package com.netbull.shop.common.vo;

import java.io.Serializable;

public class AgentPurchaseVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3226386165170080963L;
	private Integer id;
	/*代理商名称*/
	private String agentName;
	/*合同编号*/
	private String contractCode;
	/*合同名称*/
	private String contractName;
	/*商品名称*/
	private String goodsName;
	/*商品编码*/
	private String goodsCode;
	/*商品版本*/
	private String goodsVersion;
	/*发出商品数量*/
	private Integer outGoodsCount;
	/*退回商品数量*/
	private Integer inGoodsCount;
	/*进货剩余量*/
	private Integer outGoodsLeft;
	/*创建日期*/
	private String createDate;
	/*更新日期*/
	private String updateDate;
	/*是否显示*/
	private Integer isVisible;
	/*货物状态*/
	private String status;
	/*备注*/
	private String remark;
	/*代理商编号*/
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
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsVersion() {
		return goodsVersion;
	}
	public void setGoodsVersion(String goodsVersion) {
		this.goodsVersion = goodsVersion;
	}
	public Integer getOutGoodsCount() {
		return outGoodsCount;
	}
	public void setOutGoodsCount(Integer outGoodsCount) {
		this.outGoodsCount = outGoodsCount;
	}
	public Integer getInGoodsCount() {
		return inGoodsCount;
	}
	public void setInGoodsCount(Integer inGoodsCount) {
		this.inGoodsCount = inGoodsCount;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Integer getIsVisible() {
		return isVisible;
	}
	public void setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public Integer getOutGoodsLeft() {
		return outGoodsLeft;
	}
	public void setOutGoodsLeft(Integer outGoodsLeft) {
		this.outGoodsLeft = outGoodsLeft;
	}
}

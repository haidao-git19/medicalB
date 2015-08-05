package com.netbull.shop.manage.weixin.vo;

public class AccountLog {

	private Long acc_log_id;// 资金动态ID
	private Long accId;// 账户ID
	private String createDate;// 记录生成日期
	private String orderNumber;// 订单编号
	private int fee;// 涉及金额
	private Integer serviceType;// 业务类型对应ServiceType
	private Integer tradeType;// 交易类型 1.退回 2.充值,3.银联支付,4.支付，5.提现
	private String serviceNumber;// 服务ID
	private Integer logstate;// 状态，0待审核 1已完成
	private String tradebank;// 交易银行简称
	private String bankACC;// 银行账号
	private String bankUserName;// 银行用户姓名
	private String branchBank;// 支行信息
	private String refundNo;//退费订单号

	public static interface TradeType {
		int REFUND = 1;
		int RECHARGE = 2;
		int UNION_PAY = 3;
		int ACC_PAY = 4;
		int GET_CASH = 5;
	}

	public Long getAcc_log_id() {
		return acc_log_id;
	}

	public void setAcc_log_id(Long acc_log_id) {
		this.acc_log_id = acc_log_id;
	}

	public Long getAccId() {
		return accId;
	}

	public void setAccId(Long accId) {
		this.accId = accId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	public String getServiceNumber() {
		return serviceNumber;
	}

	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}

	public Integer getLogstate() {
		return logstate;
	}

	public void setLogstate(Integer logstate) {
		this.logstate = logstate;
	}

	public String getTradebank() {
		return tradebank;
	}

	public void setTradebank(String tradebank) {
		this.tradebank = tradebank;
	}

	public String getBankACC() {
		return bankACC;
	}

	public void setBankACC(String bankACC) {
		this.bankACC = bankACC;
	}

	public String getBankUserName() {
		return bankUserName;
	}

	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}

	public String getBranchBank() {
		return branchBank;
	}

	public void setBranchBank(String branchBank) {
		this.branchBank = branchBank;
	}

	public String getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}

}

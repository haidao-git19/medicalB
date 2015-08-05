package com.netbull.shop.manage.weixin.vo;

import com.netbull.shop.common.util.DesEncrypt;

public class Account {
	// 加密盐值
	public static final String ACC_SALT_KEY = "mdbs123";
	private Long accId;// 账户ID
	private Long patientId;// 患者ID
	private Long balance;// 总余额
	private Long availdbalance;// 可用余额
	private String accounttext;// 加密验证DesEncrypt[ACC_SALT_KEY-balance-availdbalance]
	private String lastmodifyDate;// 最后更新日期

	private Integer accstate;// 账户状态 0可用，1不可用

	public Long getAccId() {
		return accId;
	}

	public void setAccId(Long accId) {
		this.accId = accId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}


	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Long getAvaildbalance() {
		return availdbalance;
	}

	public void setAvaildbalance(Long availdbalance) {
		this.availdbalance = availdbalance;
	}

	public String getAccounttext() {
		return accounttext;
	}

	public void setAccounttext(String accounttext) {
		this.accounttext = accounttext;
	}

	public String getLastmodifyDate() {
		return lastmodifyDate;
	}

	public void setLastmodifyDate(String lastmodifyDate) {
		this.lastmodifyDate = lastmodifyDate;
	}

	public Integer getAccstate() {
		return accstate;
	}

	public void setAccstate(Integer accstate) {
		this.accstate = accstate;
	}

	/**
	 * 验证账户可用不可用
	 * 
	 * @createTime: 2015年6月26日 下午10:57:58
	 * @history:
	 * @return boolean
	 */
	public boolean validAcc() {
		if (accstate == 1) {
			return false;
		}
		String text = this.getAccounttext();
		String realText = DesEncrypt.getEncryptString(ACC_SALT_KEY + "-"
				+ this.getBalance() + "-" + this.getAvaildbalance());
		return text.equals(realText);
	}

	public static void main(String[] args) {
		String realText;
		realText = DesEncrypt.getEncryptString("110325977706010000123456");
		//s4P3Wzivp4qt92t//httFHeNsSTaKPe2
		System.out.println(realText);
		String test="s4P3Wzivp4qt92t//httFHeNsSTaKPe2";
		realText = DesEncrypt.getDesString(test);
		
		System.out.println(realText);		
	}
}

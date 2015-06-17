package com.netbull.shop.auth.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.netbull.shop.entity.BaseEntity;

@Entity
@Table(name = "opt_log_info")
public class OptLogInfo extends BaseEntity {

	private String optName;
	private Date optTime;
	private String loginName;

	@Column(name = "opt_name", length = 512)
	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	@Column(name = "opt_time")
	public Date getOptTime() {
		return optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

	@Column(name = "login_name", length = 32)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

}

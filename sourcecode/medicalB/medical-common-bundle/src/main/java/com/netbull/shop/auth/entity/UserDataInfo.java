package com.netbull.shop.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import com.netbull.shop.entity.BaseEntity;

@Entity
@Table(name = "user_data_info")
public class UserDataInfo extends BaseEntity {
	
	public static final String TYPE_PRODUCT = "product";
	public static final String TYPE_PLATFORM = "platform";
	
	public static final String VALUE_ALL = "_all_";

	private UserInfo user;
	private String dataType;
	private String dataValue;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@ForeignKey(name = "none")
	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	@Column(name = "data_type", length = 128)
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Column(name = "data_value", length = 512)
	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

}

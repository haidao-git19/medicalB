package com.netbull.shop.auth.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import com.netbull.shop.entity.BaseEntity;

@Entity
@Table(name = "role_user_info")
public class RoleUserInfo extends BaseEntity {

	private RoleInfo role;
	private UserInfo user;

	@ManyToOne
	@JoinColumn(name = "role_id")
	@ForeignKey(name="none")
	public RoleInfo getRole() {
		return role;
	}

	public void setRole(RoleInfo role) {
		this.role = role;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	@ForeignKey(name="none")
	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

}

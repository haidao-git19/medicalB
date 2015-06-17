package com.netbull.shop.auth.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import com.netbull.shop.entity.BaseEntity;

@Entity
@Table(name = "role_menu_info")
public class RoleMenuInfo extends BaseEntity {

	private RoleInfo role;
	private MenuInfo menu;

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
	@JoinColumn(name = "menu_id")
	@ForeignKey(name="none")
	public MenuInfo getMenu() {
		return menu;
	}

	public void setMenu(MenuInfo menu) {
		this.menu = menu;
	}

}

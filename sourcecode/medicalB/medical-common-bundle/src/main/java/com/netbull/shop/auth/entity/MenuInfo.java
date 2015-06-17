package com.netbull.shop.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import com.netbull.shop.entity.BaseEntity;

@Entity
@Table(name = "menu_info")
public class MenuInfo extends BaseEntity {
    /**目录*/
	public static final int MENU_TYPE_FOLDER = 1;
	/**页面*/
	public static final int MENU_TYPE_PAGE = 2;
	/**操作*/
	public static final int MENU_TYPE_ACTION = 3;

	private String name;
	private String link;
	private Integer menuType;
	private Integer menuOrder;
	private MenuInfo parent;
	private Boolean visible;
	private String iconUrl;
	private String creator;
	private String description;

	@Column(length = 128)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 512)
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Column(name = "menu_type")
	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	@Column(name = "menu_order")
	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	@ManyToOne
	@JoinColumn(name = "parent_id")
	@ForeignKey(name = "none")
	public MenuInfo getParent() {
		return parent;
	}

	public void setParent(MenuInfo parent) {
		this.parent = parent;
	}

	@Column(name = "icon_url", length = 512)
	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	@Column(name = "creator", length = 32)
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Column(name = "description", length = 255)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int compareTo(Object o) {
		if (o instanceof MenuInfo) {
			MenuInfo mi = (MenuInfo) o;
			int l1 = 0, l2 = 0;
			MenuInfo cursor = this;
			while (cursor != null) {
				cursor = cursor.parent;
				l1++;
			}
			cursor = mi;
			while (cursor != null) {
				cursor = cursor.parent;
				l2++;
			}
			int levelRet = Integer.valueOf(l1).compareTo(Integer.valueOf(l2));
			if (levelRet != 0) {
				return levelRet;
			}
			if ((this.getParent() == null && mi.getParent() == null)
					|| (this.getParent().equals(mi.getParent()))) {
				int orderRet = 0;
				if (this.menuOrder != null && mi.getMenuOrder() != null) {
					orderRet = this.menuOrder.compareTo(mi.getMenuOrder());
				}
				if (orderRet != 0) {
					return orderRet;
				}
				return this.getId().compareTo(mi.getId());
			}
			return this.getParent().compareTo(mi.getParent());
		}
		return 0;
	}
}

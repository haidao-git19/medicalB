package com.netbull.shop.pharmacy.entity;

import java.io.Serializable;
import java.util.Date;

public class Pharmacy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6789761309910262243L;
	private Integer shopID;
	private String shopName;
	private String code;
	private String description;
	private String userID;
	private Date crtDate;
	private String latnId;
	private String address;
	private String physicsAddress;
	private Double lng;
	private Double lat;
	private String logo;
	private String phone;
	private Integer type;
	private Integer state;
	private Long creator;
	private String url;

	private Long loginID;// 关联user_info表主键
	private String loginAccount; // 登录账号 user_info表 login_name
	private String loginPwd; // 登录密码 user_info表 password
	private Integer companyId;// 医药公司ID

	public Integer getShopID() {
		return shopID;
	}

	public void setShopID(Integer shopID) {
		this.shopID = shopID;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getLatnId() {
		return latnId;
	}

	public void setLatnId(String latnId) {
		this.latnId = latnId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhysicsAddress() {
		return physicsAddress;
	}

	public void setPhysicsAddress(String physicsAddress) {
		this.physicsAddress = physicsAddress;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public Long getLoginID() {
		return loginID;
	}

	public void setLoginID(Long loginID) {
		this.loginID = loginID;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

}

package com.netbull.shop.common.vo;

public class UserVo {

	private Integer id;
	/****** 用户编号 ******/
	private String userId;
	/****** 微信对应的唯一标示id ******/
	private String openId;
	/****** 登录名称 ******/
	private String loginName;
	/****** 用户头像 ******/
	private String icon;
	/****** 签名 ******/
	private String sign;
	/****** 年龄 ******/
	private String age;
	/****** 昵称 ******/
	private String nickName;
	/****** 真实姓名******/
	private String realName;
	/****** 性别;1:男；2：女； ******/
	private String sex;
	private String sexCN;
	/****** 电话号码 ******/
	private String phone;
	/****** 电子邮箱 ******/
	private String email;
	/****** 状态；-1：失败；0：成功 ******/
	private String status;
	/****** 密码 ******/
	private String password;
	/****** 创建人 ******/
	private String creator;
	/****** 创建时间 ******/
	private String createTime;
	/****** 更新人 ******/
	private String updator;
	/****** 更新时间 ******/
	private String updateTime;
	private String exchangeCard;
	private String cardType;
	private String cardStatus;
	/****** 等级 ******/
	private String level;
	/****** 是否vip******/
	private String vip;
	/****** 特权数******/
	private String count;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSexCN() {
		return sexCN;
	}
	public void setSexCN(String sexCN) {
		this.sexCN = sexCN;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getVip() {
		return vip;
	}
	public void setVip(String vip) {
		this.vip = vip;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getExchangeCard() {
		return exchangeCard;
	}
	public void setExchangeCard(String exchangeCard) {
		this.exchangeCard = exchangeCard;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}
	
}

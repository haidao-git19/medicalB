package com.netbull.shop.common.vo;

import java.io.Serializable;

public class ContentVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7257125708435276555L;
	private Integer id;
	/*内容标题*/
	private String title;
	/*内容类型*/
	private Integer type;
	/*内容简介*/
	private String summary;
	/*内容富文本*/
	private String text;
	/*内容链接*/
	private String url;
	/*创建时间*/
	private String createTime;
	/*更新时间*/
	private String updateTime;
	/*内容状态*/
	private String state;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}

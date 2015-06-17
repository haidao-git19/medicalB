package com.netbull.shop.common.vo;

import java.io.Serializable;

public class GoodsClob implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6761411920976797664L;
	private Integer id;
	private String title;
	private String text;
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}

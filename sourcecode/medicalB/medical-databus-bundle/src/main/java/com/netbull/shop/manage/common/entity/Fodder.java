package com.netbull.shop.manage.common.entity;

/**
 * 素材
 * @author hkj
 *
 */
public class Fodder {
    /**主键*/
	private String id;
	/**标题*/
	private String title;
	/**描述*/
	private String description;
	/**素材类型*/
	private String fodder_type;
	/**正文*/
	private String content;
	/**图片链接*/
	private String pic_url;
	/**原文链接*/
	private String link_url;
	/**顺序*/
	private String sequence;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFodder_type() {
		return fodder_type;
	}

	public void setFodder_type(String fodder_type) {
		this.fodder_type = fodder_type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPic_url() {
		return pic_url;
	}

	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}

	public String getLink_url() {
		return link_url;
	}

	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}

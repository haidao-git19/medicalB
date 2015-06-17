package com.netbull.shop.manage.common.entity;

public class Keyword {
	/**主键*/
    private String id;
    /**内容*/
    private String content;
    /**名称*/
    private String name;
    /**是否完全匹配*/
    private Integer isFullMatchs;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIsFullMatchs() {
		return isFullMatchs;
	}
	public void setIsFullMatchs(Integer isFullMatchs) {
		this.isFullMatchs = isFullMatchs;
	}
    
    
}

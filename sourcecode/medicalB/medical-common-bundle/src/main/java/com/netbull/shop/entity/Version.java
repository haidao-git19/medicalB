package com.netbull.shop.entity;

import com.netbull.shop.common.config.ConfigLoadUtil;

public class Version {

	private String version;
	private String url;
	
	public String getVersion() {
		return ConfigLoadUtil.loadConfig().getPropertie("version");
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUrl() {
		return ConfigLoadUtil.loadConfig().getPropertie("domainName");
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}

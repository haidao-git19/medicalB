package com.netbull.shop.common.config;

import java.io.IOException;

import org.jdom.CDATA;
import org.jdom.Element;

public abstract class AbstractItem implements java.io.Serializable{

	private static final long serialVersionUID = 407798282558040339L;

	protected Element element = null;

	/**
	 * 得到此配置项的描�?
	 * @return 配置项描述字�?
	 */
	public String getDescription() {
		String desc = this.element.getChild(ConfigConstant.DESCRIPTION).getText();
		if (desc == null) {
			desc = "";
		}
		return desc;
	}

	/**
	 * 设置配置项描述，如果没有此标签，便增加一个�?
	 * @param value
	 */
	public void setDescription(String desc) {
		this.addElement(ConfigConstant.DESCRIPTION, desc);
	}

	/**
	 * 得到参数的名�?
	 * 
	 * @return
	 */
	public String getName() {
		return element.getChild(ConfigConstant.NAME).getText();
	}

	/**
	 * 设置属�?名称，如果没有此标签，便增加�?���?
	 * @param itemName
	 */
	public void setName(String itemName) {
		this.addElement(ConfigConstant.NAME, itemName);
	}

	/**
	 * 为当前的配置项增加一个子�?
	 * @param name 标签名称
	 * @param value 对应�?
	 *            
	 */
	protected void addElement(String name, String value) {
		this.element.removeChild(name);
		Element ele = new Element(name);
		if (value.indexOf("&") != -1 || value.indexOf("<") != -1
				|| value.indexOf(">") != -1) {
			CDATA cdata = new CDATA(value);
			ele.addContent(cdata);
		} else {
			ele.setText(value);
		}
		this.element.addContent(ele);
	}

	/**
	 * 更新配置项的xml内容
	 * @param ele  xml Element
	 */
	public void setElement(Element ele) {
		this.element = ele;
	}

	/**
	 * 返回本Item对应的JDOM Element
	 * @return
	 */
	protected Element getElement() {
		return this.element;
	}

	public String toString() {
		String xml = "";
		try {
			java.io.StringWriter sw = new java.io.StringWriter(1000);
			org.jdom.output.XMLOutputter out = new org.jdom.output.XMLOutputter();
			out.output(this.element, sw);
			xml = sw.toString();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return xml;
	}

}

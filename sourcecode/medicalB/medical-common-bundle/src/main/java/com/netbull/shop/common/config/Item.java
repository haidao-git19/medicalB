package com.netbull.shop.common.config;

import org.jdom.Element;

public class Item extends AbstractItem implements java.io.Serializable {

	private static final long serialVersionUID = -1270818045758491816L;

	public Item() {
		this.element = new Element(ConfigConstant.CONFIG_ITEM);
	}

	/**
	 * 用于从现有的配置子系统中得到�?��配置�?
	 * @param element jdom的Element
	 */
	public Item(Element element) {
		this.element = element;
	}


	/**
	 * 构�?�?��新的配置项目
	 * @param name  配置项目的名�?
	 */
	public Item(String name) {
		this();
		this.element.addContent(new Element(ConfigConstant.NAME));
		this.element.getChild(ConfigConstant.NAME).setText(name);
	}

	/**
	 * 构�?�?��新的配置项目
	 * @param name  配置项目的名�?
	 * @param description 配置项目的描�?
	 */
	public Item(String name, String description) {
		this(name);
		this.element.addContent(new Element(ConfigConstant.DESCRIPTION));
		this.element.getChild(ConfigConstant.DESCRIPTION).setText(description);
	}

	/**
	 * 获取配置项的�?
	 * @return 配置项�?
	 */
	public String getValue() {
		String value = element.getChild(ConfigConstant.VALUE).getText();
		if (value == null) {
			return "";
		}
		return value;
	}

	/**
	 * 设置配置项的值，如果没有此标签，便增加一个�?
	 * 
	 * @param value
	 */
	public void setValue(String value) {
		this.addElement(ConfigConstant.VALUE, value);
	}


	/**
	 * 修改当前的配置向内容
	 * @param item  Item 输入的新数据
	 *            
	 */
	public void modify(Item item) {
		this.setDescription(item.getDescription());
		this.setValue(item.getValue());
	}
}

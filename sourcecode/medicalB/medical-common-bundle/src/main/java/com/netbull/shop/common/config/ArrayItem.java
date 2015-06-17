package com.netbull.shop.common.config;

import java.util.Iterator;
import java.util.List;

import org.jdom.Element;

public class ArrayItem extends AbstractItem implements java.io.Serializable {

	private static final long serialVersionUID = -8161876370554053081L;
	
	private String sb;

	public ArrayItem() {
		this.element = new Element(ConfigConstant.ARRAY_CONFIG_ITEM);
	}

	/**
	 * 用于从现有的配置子系统中得到�?��多�?配置�?
	 * @param element jdom的Element
	 */
	public ArrayItem(Element element) {
		this.element = element;
	}

	/**
	 * 构�?�?��新的多�?配置项�?
	 * @param itemName  配置项名�?
	 */
	public ArrayItem(String name) {
		this();
		this.element.addContent(new Element(ConfigConstant.NAME));
		this.element.getChild(ConfigConstant.NAME).setText(name);
	}

	public ArrayItem(String name, String description) {
		this(name);
		this.element.addContent(new Element(ConfigConstant.DESCRIPTION));
		this.element.getChild(ConfigConstant.DESCRIPTION).setText(description);
	}
	
	/**
	 * 在多值列表中增加�?��新的�?
	 * @param value
	 */
	public void addValue(String value){
		Element temp = new Element(ConfigConstant.ARRAY_VALUE);
		temp.addContent(value);
		element.addContent(temp);
	}

	/**
	 * 返回本之列表配置项的�?���?
	 * 
	 * @return String数组
	 */
	@SuppressWarnings("unchecked")
	public String[] getValueList() {
		List list = this.element.getChildren(ConfigConstant.ARRAY_VALUE);
		String[] avs = new String[list.size()];
		Iterator itrts = list.iterator();
		int i = 0;
		while (itrts.hasNext()) {
			Element ele = (Element) itrts.next();
			avs[i++] = ele.getText();
		}
		return avs;
	}

	/**
	 * 修改当前的�?列表配置�?
	 * 
	 * @param aItem
	 *            ArrayItem 目标�?
	 * 
	 */
	public void modify(ArrayItem arrayItem) {
		this.setDescription(arrayItem.getDescription());
		//Remove all old array values
		this.element.removeChildren(ConfigConstant.ARRAY_VALUE);
		//Add new array values
		String[] valueList = arrayItem.getValueList();
		if (valueList != null && valueList.length > 0) {
			for (int i = 0; i < valueList.length; i++) {
				Element temp = new Element(ConfigConstant.ARRAY_VALUE);
				temp.setText(valueList[i]);
				this.element.addContent(temp);
			}
		}
	}

	public String getSb() {
		return sb;
	}

	public void setSb(String sb) {
		this.sb = sb;
	}
}

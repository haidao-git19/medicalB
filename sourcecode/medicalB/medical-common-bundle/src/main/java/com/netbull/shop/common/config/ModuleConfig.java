package com.netbull.shop.common.config;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.netbull.shop.common.log.LoggerFactory;


public class ModuleConfig {

	private static Logger logger = LoggerFactory.getLogger(ModuleConfig.class);

	private org.jdom.Element module = null;

	private String moduleName = null;

	private String moduleDesc = null;

	//private Item[] items = null;

	/**
	 * 默认构�?方法
	 */
	public ModuleConfig() {
		this.module = new Element(ConfigConstant.MODULE_CONFIG);
	}

	/**
	 * 创建�?��新的子系统配置项
	 * 
	 * @param moduleName
	 *            模块的名�?
	 */
	public ModuleConfig(String moduleName) {
		this();
		this.module
				.setAttribute(new Attribute(ConfigConstant.NAME, moduleName));
		this.moduleName = moduleName;
	}

	/**
	 * 创建�?��新的子系统配置项
	 * 
	 * @param moduleName
	 *            模块的名�?
	 * @param moduleDesc
	 *            模块的描�?
	 */
	public ModuleConfig(String moduleName, String moduleDesc) {
		this(moduleName);
		this.module.setAttribute(new Attribute(ConfigConstant.DESCRIPTION,
				moduleDesc));
		this.moduleDesc = moduleDesc;
	}

	/**
	 * 从配置文件构造一个子系统配置类�?
	 * 
	 * @param element
	 */
	public ModuleConfig(Element element) {
		this.module = element;
		this.moduleName = element.getAttribute(ConfigConstant.NAME).getValue();
		this.moduleDesc = element.getAttribute(ConfigConstant.DESCRIPTION)
				.getValue();
	}

	/**
	 * 返回当前配置模块的名�?
	 * @return 子系统名�?
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * 返回配置模块的描述信�?
	 * 
	 * @return
	 */
	public String getDescription() {
		return moduleDesc;
	}

	/**
	 * 设置子系统配置的描述信息
	 * 
	 * @param desc
	 */
	public void setDescription(String desc) {
		module.setAttribute(new Attribute(ConfigConstant.DESCRIPTION, desc));
	}

	/**
	 * 得到指定的配置项�?
	 * 
	 * @param itemName
	 *            配置项名�?
	 * @return 配置项名称对应的配置项类
	 */
	@SuppressWarnings("unchecked")
	public Item getItem(String itemName) {
		Item item = null;
		try {
			List list = module.getChildren(ConfigConstant.CONFIG_ITEM);
			Iterator itrts = list.iterator();
			while (itrts.hasNext()) {
				Element element = (Element) itrts.next();
				if ((element.getChild(ConfigConstant.NAME).getText())
						.equals(itemName)) {
					item = new Item(element);
					break;
				}
			}
		} catch (Exception e) {
			logger
					.error("ConfigMoudle=" + module + "ConfigItem=" + itemName,
							e);
		}
		return item;
	}

	/**
	 * 获取当前配置模块的所有单值配置项
	 * 
	 * @return 单�?配置项数�?
	 */
	@SuppressWarnings("unchecked")
	public Item[] getItems() {
		List list = module.getChildren(ConfigConstant.CONFIG_ITEM);
		Item[] items = new Item[list.size()];
		Iterator itrts = list.iterator();
		int i = 0;
		while (itrts.hasNext()) {
			Element element = (Element) itrts.next();
			Item item = new Item(element);
			items[i++] = item;
		}
		return items;
	}

	/**
	 * 获取指定的多值配置项�?
	 * 
	 * @param itemName
	 *            多�?配置项名�?
	 * 
	 * @return 配置项类
	 */
	@SuppressWarnings("unchecked")
	public ArrayItem getArrayItem(String itemName) {
		ArrayItem arrayItem = null;
		try {
			List list = module.getChildren(ConfigConstant.ARRAY_CONFIG_ITEM);
			Iterator itrts = list.iterator();
			while (itrts.hasNext()) {
				Element element = (Element) itrts.next();
				if (element.getChild(ConfigConstant.NAME).getText().equals(
						itemName)) {
					arrayItem = new ArrayItem(element);
					break;
				}
			}
		} catch (Exception ex) {
			logger.error("ConfigMoudle=" + module + "ArrayConfigItem="
					+ itemName, ex);
		}
		return arrayItem;
	}

	/**
	 * 得到本子系统的所有配置项
	 * 
	 * @return 配置项数�?
	 */
	@SuppressWarnings("unchecked")
	public ArrayItem[] getArrayItems() {
		List list = module.getChildren(ConfigConstant.ARRAY_CONFIG_ITEM);
		ArrayItem[] arrayItems = new ArrayItem[list.size()];
		Iterator itrts = list.iterator();
		int i = 0;
		while (itrts.hasNext()) {
			Element element = (Element) itrts.next();
			ArrayItem arrayItem = new ArrayItem(element);
			arrayItems[i++] = arrayItem;
		}
		return arrayItems;
	}

	/**
	 * 获取指定单�?配置项的�?
	 * 
	 * @param itemName
	 *            配置项名�?
	 * @return 配置�?
	 */
	public String getItemValue(String itemName) {
		Item item = getItem(itemName);
		if (item == null) {
			logger.error("Read ConfigItem Error,ConfigName=" + itemName);
			return null;
		}
		return item.getValue();
	}
	
	/**
	 * 获取制定单�?配置项的描述信息
	 * @param itemName 配置项名�?
	 * @return 描述信息
	 */
	public String getItemDescription(String itemName){
		Item item = getItem(itemName);
		if(item==null){
			logger.error("Read ConfigItem Error,ConfigName=" + itemName);
			return null;
		}
		return item.getDescription();
	}
	
	/**
	 * 获取制定多�?配置项的描述信息
	 * @param itemName 配置项名�?
	 * @return 描述信息
	 */
	public String getArrayItemDescription(String itemName){
		ArrayItem arrayItem = getArrayItem(itemName);
		if(arrayItem == null){
			logger.error("Read ArrayConfigItem Error,ArrayConfigName=" + itemName);
			return null;
		}
		return arrayItem.getDescription();
	}

	/**
	 * 获取制定多�?配置项的�?
	 * 
	 * @param itemName
	 *            配置项名�?
	 * @return 配置值数�?
	 */
	public String[] getItemValueList(String itemName) {
		ArrayItem arrayItem = this.getArrayItem(itemName);
		return arrayItem.getValueList();
	}

	/**
	 * 增加�?��单�?配置�?
	 * 
	 * @param item
	 *            配置项对�?
	 * @return boolean true 表示增加成功 false表示已经存在增加失败
	 */
	public boolean addItem(Item item) {
		// 如果该配置项目已经存在，返回false
		if (this.getItem(item.getName()) != null) {
			return false;
		}
		module.addContent(item.getElement());
		return true;
	}

	/**
	 * 增加�?��多�?配置�?
	 * 
	 * @param arrayItem
	 *            配置项对�?
	 * @return boolean true 表示增加成功 false表示已经存在增加失败
	 */
	public boolean addArrayItem(ArrayItem arrayItem) {
		if (this.getArrayItem(arrayItem.getName()) != null) {
			return false;
		}
		module.addContent(arrayItem.getElement());
		return true;
	}

	/**
	 * 删除�?��单�?配置�?
	 * 
	 * @param itemName
	 */
	public void removeItem(String itemName) {
		Item item = this.getItem(itemName);
		if (item == null) {
			return;
		}
		module.removeContent(item.getElement());
	}

	/**
	 * 删除�?��多�?配置�?
	 * 
	 * @param itemName
	 */
	public void removeArrayItem(String itemName) {
		ArrayItem arrayItem = this.getArrayItem(itemName);
		if (arrayItem == null) {
			return;
		}
		module.removeContent(arrayItem.getElement());
	}

	protected Element getElement() {
		return this.module;
	}

	public String toString() {
		String xml = "";
		try {
			java.io.StringWriter sw = new java.io.StringWriter(1000);
			org.jdom.output.XMLOutputter out = new org.jdom.output.XMLOutputter();
			out.output(this.module, sw);
			xml = sw.toString();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return xml;
	}

	public void showData() {
		try {
			XMLOutputter out = new XMLOutputter();
			out.output(this.module, System.out);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * 返回模块对象
	 */
	public Element getModuleElement() {
		return module;
	}
}

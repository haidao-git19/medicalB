package com.netbull.shop.common.config;

import org.apache.log4j.Logger;

import com.netbull.shop.common.util.StringTools;

public class CommonConfig {
	private Logger logger = null;
	private ModuleConfig moduleConfig = null;

	public CommonConfig(ModuleConfig moduleConfig, Logger logger) {
		this.moduleConfig = moduleConfig;
		this.logger = logger;
	}

	/**
	 * 获取值为整数的配置项的�?
	 * 
	 * @param configItemName
	 *            配置项的名称
	 * @param defaultValue
	 *            配置项的默认�?
	 * @return
	 */
	public int getIntValue(String configItemName, int defaultValue) {
		Item item = moduleConfig.getItem(configItemName);
		if (item != null) {
			String temp = item.getValue();
			try {
				return Integer.parseInt(temp);
			} catch (Exception e) {
				logger.error("Read " + configItemName + " ConfigItem Error!");
			}
		} else {
			logger.error(configItemName + " ConfigItem not Found!");
		}
		return defaultValue;
	}

	/**
	 * 获取值为字符串的配置项的�?
	 * 
	 * @param configItemName
	 *            配置项的名称
	 * @param defaultValue
	 *            配置项的默认�?
	 * @return
	 */
	public String getStringValue(String configItemName, String defaultValue) {
		Item item = moduleConfig.getItem(configItemName);
		if (item != null) {
			String temp = item.getValue();
			if (StringTools.isNotBlank(temp)) {
				return temp;
			}
		} else {
			logger.error(configItemName + " ConfigItem not Found!");
		}
		return defaultValue;
	}

	/**
	 * 获取值为字符串数组的配置项的值，不提供默认�?
	 * 
	 * @param configItemName
	 * @return
	 */
	public String[] getArrayValue(String configItemName) {
		ArrayItem arrayItem = moduleConfig.getArrayItem(configItemName);
		if (arrayItem != null) {
			return arrayItem.getValueList();
		} else {
			logger.error(configItemName + " ArrayConfigItem not Found!");
		}
		return null;
	}

	/**
	 * 获取值为字符串数组的配置项的值，不提供默认�?
	 * 
	 * @param configItemName
	 * @return
	 */
	public boolean getBooleanValue(String configItemName, boolean defaultValue) {
		Item item = moduleConfig.getItem(configItemName);
		if (item != null) {
			String temp = item.getValue();
			if ("true".equals(temp)) {
				return true;
			} else {
				return false;
			}

		} else {
			logger.error(configItemName + " ConfigItem not Found!");
		}
		return defaultValue;
	}

}

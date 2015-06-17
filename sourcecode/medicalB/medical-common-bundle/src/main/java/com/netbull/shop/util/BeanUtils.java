package com.netbull.shop.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 对apche BeanUtils 进行封装，将强制性捕获异常转化成运行时异常
 * 
 * 
 */
public class BeanUtils {

	private static void handleReflectionException(Exception e) {
		ExceptionUtils.unchecked(e);
	}

	/**
	 * 克隆对象
	 * 
	 * @param bean
	 * @return
	 */
	public static Object cloneBean(Object bean) {
		try {
			return org.apache.commons.beanutils.BeanUtils.cloneBean(bean);
		} catch (Exception e) {
			handleReflectionException(e);
			return null;
		}
	}

	/**
	 * 
	 * @param destClass
	 * @param orig
	 * @return
	 */
	public static Object copyProperties(Class destClass, Object orig) {
		try {
			Object target = destClass.newInstance();
			copyProperties((Object) target, orig);
			return target;
		} catch (Exception e) {
			handleReflectionException(e);
			return null;
		}
	}

	public static void copyProperties(Object dest, Object orig) {
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(dest.getClass(), orig);
		} catch (Exception e) {
			handleReflectionException(e);
		}
	}

	public static void copyProperty(Object bean, String name, Object value) {
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperty(bean, name, value);
		} catch (Exception e) {
			handleReflectionException(e);
		}
	}

	public static Map describe(Object bean) {
		try {
			return org.apache.commons.beanutils.BeanUtils.describe(bean);
		} catch (Exception e) {
			handleReflectionException(e);
			return null;
		}
	}

	public static String[] getArrayProperty(Object bean, String name) {
		try {
			return org.apache.commons.beanutils.BeanUtils.getArrayProperty(bean, name);
		} catch (Exception e) {
			handleReflectionException(e);
			return null;
		}
	}

	public static String getIndexedProperty(Object bean, String name, int index) {
		try {
			return org.apache.commons.beanutils.BeanUtils.getIndexedProperty(bean, name, index);
		} catch (Exception e) {
			handleReflectionException(e);
			return null;
		}
	}

	public static String getIndexedProperty(Object bean, String name) {
		try {
			return org.apache.commons.beanutils.BeanUtils.getIndexedProperty(bean, name);
		} catch (Exception e) {
			handleReflectionException(e);
			return null;
		}
	}

	public static String getMappedProperty(Object bean, String name, String key) {
		try {
			return org.apache.commons.beanutils.BeanUtils.getMappedProperty(bean, name, key);
		} catch (Exception e) {
			handleReflectionException(e);
			return null;
		}
	}

	public static String getMappedProperty(Object bean, String name) {
		try {
			return org.apache.commons.beanutils.BeanUtils.getMappedProperty(bean, name);
		} catch (Exception e) {
			handleReflectionException(e);
			return null;
		}
	}

	public static String getNestedProperty(Object bean, String name) {
		try {
			return org.apache.commons.beanutils.BeanUtils.getNestedProperty(bean, name);
		} catch (Exception e) {
			handleReflectionException(e);
			return null;
		}
	}

	public static String getProperty(Object bean, String name) {
		try {
			return org.apache.commons.beanutils.BeanUtils.getProperty(bean, name);
		} catch (Exception e) {
			handleReflectionException(e);
			return null;
		}
	}

	public static String getSimpleProperty(Object bean, String name) {
		try {
			return org.apache.commons.beanutils.BeanUtils.getSimpleProperty(bean, name);
		} catch (Exception e) {
			handleReflectionException(e);
			return null;
		}
	}

	public static void populate(Object bean, Map properties) {
		try {
			org.apache.commons.beanutils.BeanUtils.populate(bean, properties);
		} catch (Exception e) {
			handleReflectionException(e);
		}
	}

	public static void setProperty(Object bean, String name, Object value) {
		try {
			org.apache.commons.beanutils.BeanUtils.setProperty(bean, name, value);
		} catch (Exception e) {
			handleReflectionException(e);
		}
	}

	/**
	 * 将map转换成对应的javabean
	 * 
	 */
	public static Object convertMap(Class type, Map map) throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
		Object obj = type.newInstance(); // 创建 JavaBean 对象

		// 给 JavaBean 对象的属性赋值
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (map.containsKey(propertyName)) {
				// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
				Object value = map.get(propertyName);

				Object[] args = new Object[1];
				args[0] = value;

				descriptor.getWriteMethod().invoke(obj, args);
			}
		}
		return obj;
	}

	/**
	 * 将javabean转换成map
	 */
	public static Map convertBean(Object bean) throws Exception {
		Class type = bean.getClass();
		Map returnMap = new HashMap();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);

		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null) {
					returnMap.put(propertyName, result);
				} else {
					returnMap.put(propertyName, "");
				}
			}
		}
		return returnMap;
	}

	/**
	 * 循环遍历克隆Map对象 值对象为浅clone
	 * 
	 * @param src
	 * @return
	 */
	public static Map<String, Object> cloneMap(Map<String, Object> src) {
		Map<String, Object> des = new HashMap<String, Object>();
		for (Iterator<String> it = src.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			Object value = src.get(key);
			des.put(key, value);
		}
		return des;
	}
}

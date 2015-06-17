package com.netbull.shop.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.netbull.shop.common.log.LoggerFactory;

@SuppressWarnings({"rawtypes","unchecked","unused"})
public class PlaceHolderUtil {

	private static Logger logger = LoggerFactory.getLogger(PlaceHolderUtil.class);
	
	private final String PLACEHOLDER_START = "${";

	private  static Properties pro;

	private Map<String, Object> placeHolderHashMap = null;

	public PlaceHolderUtil(Map<String, Object> placeHolderHashMap) {
		this.placeHolderHashMap = placeHolderHashMap;
		try {
			if(pro == null){
				pro = buildProperty("log-config.properties");
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	/**
	 * 解析占位符
	 * 
	 * @param properties
	 */
	public String resolvePlaceHolderByKey(String propertiesKey) {
		final Object value = pro.getProperty(propertiesKey);
		
		if (value != null && String.class.isInstance(value)) {
			final String resolved = resolvePlaceHolder((String) value);
			if (resolved == null) {
				return "";
			} else {
				return resolved;
			}
		}
		return null;
	}
	
	/**
	 * 解析占位符
	 * 
	 * @param properties
	 */
	public void resolvePlaceHolders(Properties properties) {
		Iterator itr = properties.entrySet().iterator();
		while (itr.hasNext()) {
			final Map.Entry entry = (Map.Entry) itr.next();
			final Object value = entry.getValue();
			if (value != null && String.class.isInstance(value)) {
				final String resolved = resolvePlaceHolder((String) value);
				if (!value.equals(resolved)) {
					if (resolved == null) {
						itr.remove();
					} else {
						entry.setValue(resolved);
					}
				}
			}
		}
	}
	
	/**
	 * 解析占位符具体操作
	 * 
	 * @param property
	 * @return
	 */
	public String resolvePlaceHolder(String property) {
		if (property.indexOf(PLACEHOLDER_START) < 0) {
			return property;
		}
		StringBuffer buff = new StringBuffer();
		char[] chars = property.toCharArray();
		for (int pos = 0; pos < chars.length; pos++) {
			if (chars[pos] == '$') {
				if (chars[pos + 1] == '{') {
					String systemPropertyName = "";
					int x = pos + 2;
					for (; x < chars.length && chars[x] != '}'; x++) {
						systemPropertyName += chars[x];
						if (x == chars.length - 1) {
							throw new IllegalArgumentException(
									"unmatched placeholder start [" + property
											+ "]");
						}
					}
					String systemProperty = extractFromHashMap(this.placeHolderHashMap,systemPropertyName);
					buff.append(systemProperty == null ? "" : systemProperty);
					pos = x + 1;
					if (pos >= chars.length) {
						break;
					}
				}
			}
			buff.append(chars[pos]);
		}
		String rtn = buff.toString();
		return isEmpty(rtn) ? null : rtn;
	}

	/**
	 * 构造properties文件
	 * 
	 * @param path
	 * @throws IOException
	 */
	public Properties buildProperty(String path) throws IOException {
		InputStream is = getResourceAsStream(path);
		pro = new Properties();
		pro.load(is);
		return pro;
	}

	/**
	 * 构造properties文件的流
	 * 
	 * @param resource
	 * @return
	 */
	public InputStream getResourceAsStream(String resource) {
		String stripped = resource.startsWith("/") ? resource.substring(1)
				: resource;

		InputStream stream = null;
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		if (classLoader != null) {
			stream = classLoader.getResourceAsStream(stripped);
		}
		if (stream == null) {
			stream = PlaceHolderUtil.class.getResourceAsStream(resource);
		}
		if (stream == null) {
			stream = PlaceHolderUtil.class.getClassLoader()
					.getResourceAsStream(stripped);
		}
		if (stream == null) {
			throw new RuntimeException(resource + " not found");
		}
		return stream;
	}

	/**
	 * 获得系统属性 当然 你可以选择从别的地方获取值
	 * 
	 * @param systemPropertyName
	 * @return
	 */
	private String extractFromSystem(String systemPropertyName) {
		try {
			return System.getProperty(systemPropertyName);
		} catch (Throwable t) {
			return null;
		}
	}

	/**
	 * 从Map对象中获取值；
	 * 
	 * @param systemPropertyName
	 * @return
	 */
	private String extractFromHashMap(Map<String, Object> placeHolderHashMap ,String systemPropertyName) {
		try {
			String result = null;
			if (systemPropertyName == null || "".equals(systemPropertyName)) {
				return null;
			}

			String firstHolder = null;
			String lastHolder = null;
			if(systemPropertyName.indexOf(".") < 0){
				firstHolder = systemPropertyName;
				lastHolder = systemPropertyName;
			}else{
				firstHolder = systemPropertyName.substring(0,
						systemPropertyName.indexOf("."));
				lastHolder = systemPropertyName.substring(systemPropertyName
						.indexOf(".")+1);
			}

			Object obj = placeHolderHashMap.get(firstHolder);
			if(obj == null){
				result = "";
			}else if (obj instanceof HashMap) {
				return extractFromHashMap((HashMap)obj,lastHolder);
			} else {
				return result = obj.toString();
			}
			return result;
		} catch (Throwable t) {
			logger.error(t.getMessage());
			return null;
		}
	}

	/**
	 * 判断字符串的空(null或者.length=0)
	 * 
	 * @param string
	 * @return
	 */
	public boolean isEmpty(String string) {
		return string == null || string.length() == 0;
	}
	
	/**
	 * 
	 * @param string
	 * @return
	 */
	public void printProperties(Properties p) {
		Iterator it = p.entrySet().iterator();
		while (it.hasNext()) {
			Entry ent = (Entry) it.next();
			System.out.println(ent.getKey() + " : " + ent.getValue());
		}
	}

}

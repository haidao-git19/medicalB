package com.netbull.shop.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@SuppressWarnings({"serial","unchecked","rawtypes"})
public class MapClone {

	public static Map clone(Map paramter) {
		Map target = new HashMap();
		for (Iterator keyIt = paramter.keySet().iterator(); keyIt.hasNext();) {
			Object key = keyIt.next();
			target.put(key, paramter.get(key));
		}
		return target;
	}
}

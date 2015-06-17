/**
 * <p>包名: —— .</p>
 * <p>描述: —— 简要描述实现方式、使用注意事项等</p>
 * @since 2012-5-15 上午12:40:15
 */

package com.netbull.shop.common.util;

import java.util.List;

import net.sf.json.JSONArray;

/**
 * <p>标题: —— 要求能简洁地表达出类的功能和职责.</p>
 * <p>描述: —— 简要描述类的职责、实现方式、使用注意事项等</p>
 * <p>模块: 通用平台</p>
 * @version 1.0
 * @since 2012-5-15 上午12:40:15
 * <p>修订历史:（历次修订内容、修订人、修订时间等） </p>
 */

public class JSONUtil {

	
	/**
	  * <p>方法说明:将List转换为json.</p> 
	  * <p>创建时间:2012-5-15上午12:43:09</p>
	  * <p>作者: elvis</p>
	  * @param list
	  * @return String
	 */
	public static String ListToJSON(List<?> list) {
		String json = "";
		if (list != null) {
		JSONArray result = JSONArray.fromObject(list);
		json = result.toString();
		}
		return json;
	}
}

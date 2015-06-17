package com.netbull.shop.common.config;

import java.util.Map;

import com.netbull.shop.common.util.XMLParser;

import com.netbull.shop.common.wb.WBConstant;


/**
 * 完成Crm调用参数封装和返回结果解�?
 * 
 * @version 1.0
 */
public class CrmBizUtils {

	//xml的开始标�?
	private static final String XML_HEADER = "<?xml version='1.0' encoding='UTF-8'?>" + WBConstant.LINE_SEP +"<ESB>";

	//xml的结束标�?
	private static final String XML_END = "</ESB>";
	
	//构�?StringBuilder的初始化长度
	private static int reqXMLStrLength = 0;
	static{
		reqXMLStrLength = XML_HEADER.length() + XML_END.length();
	}
	

	// XML缩进
	private static final String TAB_String = "";

	/**
	 * 通过Map获取的请求XML片段
	 * @param crmBizMap
	 * @return 发到CRM的XML片段
	 * joe date:2009-11-02
	 */
	@SuppressWarnings("unchecked")
	public static String getCrmXMLStr(Map crmBizMap) {
		String paramXMLStr = XMLParser.getStringOfMap(crmBizMap, TAB_String);
		// 数据异常不做处理
		if (paramXMLStr == null) {
			return null;
		}
		//使用预定的长度来处理
		StringBuilder sb = new StringBuilder(paramXMLStr.length()+reqXMLStrLength);
		sb.append(XML_HEADER);
		sb.append(paramXMLStr);
		//sb.append(XMLParser.LINE_SEP);
		sb.append(XML_END);
		return sb.toString();
	}
	
	/**
	 * 解析CRM返回的XML文为MAP
	 * @param xml
	 * @return
	 * joe date:2009-11-02
	 */
	@SuppressWarnings("unchecked")
	public static Map getMapFromStr(String xmlStr) {
		if(xmlStr==null){
			return null;
		}
		return XMLParser.string2Map(xmlStr);
	}
	
	public static void main(String[] args)
	{
		
	}
	
	
}

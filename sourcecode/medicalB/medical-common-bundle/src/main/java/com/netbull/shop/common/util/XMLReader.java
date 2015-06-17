package com.netbull.shop.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/******************************************************

*Copyrights @ 2010，Tianyuan DIC Information Co., Ltd.
*         中国电信自助服务平台3.2
*All rights reserved.
*
*Filename： 
*            XMLReader.java
*Description：
*            XML读取
*Author:
*            吴柏平
*Finished：
*            2010年4月8日
********************************************************/

public class XMLReader {
	
	/**
	 * xml格式的字符串转换成Document对象
	 * @param XMLString xml格式的字符串
	 * @throws JDOMException IOException
	 * @return
	 */
	public static Document readXMLDocument(String XMLString) 
										throws JDOMException,IOException{

		Document document = null;
		ByteArrayInputStream bais = null;

		try {
			bais = new ByteArrayInputStream(XMLString.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
			throw uee;
		}

		if (bais != null) {
			try {
				SAXBuilder saxBuilder = new SAXBuilder();
				document = saxBuilder.build(bais);
			} catch (IOException ex) {
				ex.printStackTrace();
				throw ex;
			} catch (JDOMException ex) {
				ex.printStackTrace();
				throw ex;
			}
		}

		return document;
	} 
	
	public static String JDomToXmlStr(org.jdom.Document icDoc) throws IOException{
			StringWriter write = new StringWriter();
			new XMLOutputter().output(icDoc, write);
			
			String outXml = write.toString();
			write.close();
			return outXml;
		
	} 
}

package com.netbull.shop.common.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.netbull.shop.common.log.LoggerFactory;
import com.netbull.shop.common.wb.WBConstant;


/**
 * 用于与CRM进行时HashMap �?XML 互转
 * 
 * @version 1.0
 */
public class XMLParser {

	private static final Logger logger = LoggerFactory
			.getLogger(XMLParser.class);

	// 系统的换行符
	// static final String LINE_SEP = System.getProperty("line.separator");

	private static final int XML_INIT_LENGTH = 1000;

	// 请求包中的行缩进
	// private static final String TAB = "\t";

	// XML格式符号
	private static final String XML_START_LEFT = "<";
	private static final String XML_RIGHT = ">";
	private static final String XML_END_LEFT = "</";

	private XMLParser() {
	}

	/**
	 * 根据hashmap的模型转化为内容字符�?
	 * 
	 * @param map
	 *            HashMap 输入的Map
	 * @param tabs
	 *            String XML文件缩进空格
	 * @throws Exception
	 *             如果HashMap中无对应类型抛出异常
	 * @return String 返回的XML片段
	 * 将Map对象转换成xml文件
	 * joe date:2009-11-02
	 */
	@SuppressWarnings("unchecked")
	public static String getStringOfMap(Map map, String tabs) {
		StringBuilder sb = new StringBuilder(XML_INIT_LENGTH);
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry oo = (Entry)iterator.next();
			Object key = oo.getKey();
			Object val = oo.getValue();
			if (val == null) {// 没有�?
				sb.append(tabs);
				sb.append(XML_START_LEFT + key + XML_RIGHT);
				// sb.append("");
				sb.append(XML_END_LEFT + key + XML_RIGHT);
			} else {// 有�?
				if (val instanceof String) { // 包含int
					sb.append(tabs);
					sb.append(XML_START_LEFT + key + XML_RIGHT);
					sb.append(val);
					sb.append(XML_END_LEFT + key + XML_RIGHT);
				} else if (val instanceof HashMap) { // 如果还有子节点，则�?归调�?
					sb.append(tabs);
					sb.append(XML_START_LEFT + key + XML_RIGHT);
					sb.append(WBConstant.LINE_SEP);
					sb.append(getStringOfMap((HashMap) val, tabs
							+ WBConstant.TAB));
					sb.append(tabs);
					sb.append(XML_END_LEFT + key + XML_RIGHT);
				} else if (val instanceof HashMap[]) { // 如果还有并列的子节点数组，则递归调用
					if(val == null ){
						continue;
					}
					HashMap[] maps = (HashMap[]) val;
					sb.append(tabs);
					sb.append(XML_START_LEFT + key + XML_RIGHT);
					sb.append(WBConstant.LINE_SEP);
					for (int i = 0; i < maps.length; i++) {// 每个map�?��节点
						if(maps[i] == null){
							continue;
						}
						sb
								.append(getStringOfMap(maps[i], tabs
										+ WBConstant.TAB));
					}
					sb.append(tabs);
					sb.append(XML_END_LEFT + key + XML_RIGHT);
					sb.append(WBConstant.LINE_SEP);
				} else {
					// 出现异常记录日志,返回�?
					logger.error("Unkown Object in marshal map:" + val);
					return null;
				}
			}
			sb.append(WBConstant.LINE_SEP);
		}
		return sb.toString();
	}
	
	
	/**
	 * 根据hashmap的模型转化为内容字符�?
	 * 
	 * @param map
	 *            HashMap 输入的Map
	 * @param tabs
	 *            String XML文件缩进空格
	 * @throws Exception
	 *             如果HashMap中无对应类型抛出异常
	 * @return String 返回的XML片段
	 * 将Map对象转换成xml文件
	 * fwh date:2009-09-02
	 */
	@SuppressWarnings("unchecked")
	public static String getStringOfLinkedHashMap(Map map, String tabs) {
		StringBuilder sb = new StringBuilder(XML_INIT_LENGTH);			
		Iterator iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry oo = (Entry)iterator.next();
			Object key = oo.getKey();
			Object val = oo.getValue();
			if (val == null) {// 没有�?
				sb.append(tabs);
				sb.append(XML_START_LEFT + key + XML_RIGHT);
				// sb.append("");
				sb.append(XML_END_LEFT + key + XML_RIGHT);
			} else {// 有�?
				if (val instanceof String) { // 包含int
					sb.append(tabs);
					sb.append(XML_START_LEFT + key + XML_RIGHT);
					sb.append(val);
					sb.append(XML_END_LEFT + key + XML_RIGHT);
				} else if (val instanceof LinkedHashMap) { // 如果还有子节点，则�?归调�?
					sb.append(tabs);
					sb.append(XML_START_LEFT + key + XML_RIGHT);
					sb.append(getStringOfLinkedHashMap((LinkedHashMap) val, tabs));
					sb.append(tabs);
					sb.append(XML_END_LEFT + key + XML_RIGHT);
				} else if (val instanceof LinkedHashMap[]) { // 如果还有并列的子节点数组，则递归调用
					LinkedHashMap[] maps = (LinkedHashMap[]) val;
					for (int i = 0; i < maps.length; i++) {// 每个map�?��节点
						sb.append(tabs);
						sb.append(XML_START_LEFT + key + XML_RIGHT);
						sb
								.append(getStringOfLinkedHashMap(maps[i], tabs));
						sb.append(tabs);
						sb.append(XML_END_LEFT + key + XML_RIGHT);
					}
				} else {
					// 出现异常记录日志,返回�?
					logger.error("Unkown Object in marshal map:" + val);
					return null;
				}
			}
		}
		return sb.toString();
	}
	

	/**
	 * 
	 * @param xmlStr
	 * @return
	 * 将字符串转换为map对象
	 * joe date:2009-11-02
	 */
	@SuppressWarnings("unchecked")
	public static Map string2Map(String xmlStr) {
		if (xmlStr == null) {
			return null;
		}
		SAXBuilder builder = new SAXBuilder(false);
		Document doc = null;
		try {
			//加载xml文档
			doc = builder.build(new ByteArrayInputStream(xmlStr.getBytes()));
		} catch (Exception e) {
			logger.error("xml read error!!!", e);
			return null;
		}
		//获取xml文件的根节点 ESB节点
		Element root = doc.getRootElement();
		return (Map) marshal2Map(root, null, null);
	}

	/**
	 * 将xml文件转换为map对象
	 * @param xmlStr
	 * @return
	 * fwh date:2009-09-02
	 */
	@SuppressWarnings("unchecked")
	public static Map string2Map(File xmlStr) {
		if (xmlStr == null) {
			return null;
		}
		SAXBuilder builder = new SAXBuilder(false);
		Document doc = null;
		try {
			doc = builder.build(new FileInputStream(xmlStr));
		} catch (Exception e) {
			logger.error("xml read error!!!", e);
			return null;
		}
		Element root = doc.getRootElement();
		return (Map) marshal2Map(root, null, null);
	}
	
	@SuppressWarnings("unchecked")
	public static Map string2Map(String xmlStr,String encode) {
		if (xmlStr == null) {
			return null;
		}
		SAXBuilder builder = new SAXBuilder(false);
		Document doc = null;
		try {
			doc = builder.build(new ByteArrayInputStream(xmlStr.getBytes(encode)));
		} catch (Exception e) {
			logger.error("xml read error!!!", e);
			return null;
		}
		Element root = doc.getRootElement();
		return (Map) marshal2Map(root, null, null);
	}

	/**
	 * 把JDOM模型解析成map模型 本方法�?归调�?为了便于进行递归调用
	 * 
	 * @param ele
	 *            Element XML文件中的�?��根节�?
	 * @param superName
	 *            根节点对应上�?��的名�?
	 * @param supermap
	 *            根节点对应上�?��的map
	 * 
	 * @return HashMap
	 * joe date:2009-11-02
	 */
	@SuppressWarnings("unchecked")
	private static Object marshal2Map(Element ele, String superName,
			Map supermap) {
		Map map = new HashMap();
		
		//获取�?��的子节点
		List children = ele.getChildren();
		
		List slist = new ArrayList();
		for (int i = 0; i < children.size(); i++) {
			Element child = (Element) children.get(i);
			
			//子节点的名字
			String name = child.getName().trim();
			
			//获取子节点是否有下级节点
			int childSize = child.getChildren().size();
			if (childSize == 0) {// 无下�?��节点,
				//获取节点的�?
				String val = child.getText().trim();
				if (slist.size() > 0) {
					//如果有多个节点信息，将节点的map信息保存到list�?
					Map temp = new HashMap();
					temp.put(name, val);
					slist.add(temp);
				} else if (map.containsKey(name)) {
					Map temp = new HashMap();
					temp.put(name, val);
					slist.add(map);
					slist.add(temp);
				} else {
					map.put(name, val);
				}
			} else {// 还有�?��子节点，从根�?��是第3层了�?
				// 重名的子节点的情况只有复杂的节点才有，有下一级节点�?归调�?
				Object childMap = marshal2Map(child, name, map);
				if (childMap instanceof Map) {					
					if (map.containsKey(name)) {
						if (supermap!=null && supermap.get(superName) instanceof List) {
							List tempList = (List) supermap.get(superName);
							Map temp = new HashMap();
							temp.put(name, childMap);
							tempList.add(temp);							
						} else {
							//将list放到map�?
							List list = new ArrayList();
							list.add(map);
							Map temp = new HashMap();
							temp.put(name, childMap);
							list.add(temp);
							//supermap.remove(superName);
							supermap.put(superName, list);							
						}
					} else {
						map.put(name, childMap);
					}
				} else {
					map.put(name, childMap);
				}
			}
		}
		//当多个相同节点已经转化为List放入父MAP中时候需要将其作为返回对�?
		if(supermap!=null){
			Object obj =  supermap.get(superName);
			if(obj!=null && obj instanceof List){
				return obj;
			}
		}
		
		if (slist.size() != 0) {
			return slist;
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String path = "<esb><VODS><vod><id>10002219</id><liveId>2061</liveId><attrId>17</attrId><fileName>dianxinju.flv</fileName><owerType>4</owerType><owerId>30000521</owerId><fileSize>99405</fileSize><fileDesc>null</fileDesc><filePath>/home/bill/mboss-cluster/resources/broadcast/media/2061/video/</filePath><createDate>2013-12-25</createDate><state>VOD</state><stateDate>2014-01-07</stateDate><remark>null</remark></vod><vod><id>10002251</id><liveId>2061</liveId><attrId>17</attrId><fileName>电信获奖-2_0.flv</fileName><owerType>4</owerType><owerId>30000329</owerId><fileSize>99405</fileSize><fileDesc>null</fileDesc><filePath>/broadcast_fms/streams/2061/video/30000329/</filePath><createDate>2013-12-28</createDate><state>VOD</state><stateDate>2014-01-07</stateDate><remark>null</remark></vod></VODS></esb>";
//		 String path = "<Esb><content>要发送的内容</content><time>20111103014220</time><userName>elvis</userName><serviceCount>1</serviceCount><password>0192023A7BBD73250516F069DF18B500</password><NumberSets><NumberSet><content>你看着办6</content><phone>10659888</phone></NumberSet><NumberSet><content>你看着办8</content><phone>10659888</phone></NumberSet></NumberSets></Esb>";
		Map map = string2Map(path);
		System.out.println(map);
	}

}

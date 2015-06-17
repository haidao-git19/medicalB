package com.netbull.shop.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.Notification;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.subject.WebSubject;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.log.LoggerFactory;
import com.netbull.shop.common.task.Const;
import com.netbull.shop.shiro.ShiroUser;




/**
 * 
 * 字符串处理工具类
 * @author yangfeng
 */

public final class StringTools extends StringUtils{

	private static Logger logger = LoggerFactory.getLogger(StringTools.class);
	
	/**
	 * 时间戳长�?
	 */
	private static int timeStampLength = 4;

	/**
	 * 字符串替换方法，使用新的字符串替换所有旧的字符串
	 * 
	 * @param srcStr
	 * @param oldStr
	 * @param newStr
	 * @return
	 */
	public static String replaceAll(String srcStr, String oldStr, String newStr) {
		// srcStr 不能为空,oldStr，newStr不能为空，并且srcStr与oldStr长度不能�?
		if (srcStr == null || srcStr.length() == 0 || oldStr == null
				|| oldStr.length() == 0 || newStr == null) {
			return srcStr;
		}
		if (oldStr.equals(newStr)) {
			return srcStr;
		}
		StringBuffer sb = new StringBuffer(100);
		// 取第�?���?��被替换的字符串所在位�?
		int index = srcStr.indexOf(oldStr);
		if (index == -1) {
			return srcStr;
		}
		while (index >= 0) {
			sb.append(srcStr.substring(0, index) + newStr);
			// 取余下部分作为需要被替换的新字符�?
			srcStr = srcStr.substring(index + oldStr.length());
			index = srcStr.indexOf(oldStr);
			if (index == -1) {
				sb.append(srcStr);
			}
		}
		return sb.toString();
	}

	/**
	 * 判断字符串数组是否包含在字符
	 * @Title: isContainStr
	 * @Description: TODO(这里用一句话描述这个方法的作
	 * @param sl 字符串数
	 * @param str 判定的字符串
	 * @return Boolean 包含返回true 不包含返回false
	 * @throws
	 */
	public static Boolean isContainStr(String[] sl ,String str){
		for (String s : sl) {
			if(str.equals(s)) return Boolean.TRUE; else continue;
		}
		return Boolean.FALSE;
	}
	
	/**
	 * 数组转换为字符串
	 * @param arr
	 * @return
	 */
	public static String arrayToString(String[] arr) {
		StringBuffer bf = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			bf.append(arr[i]);
		}
		return bf.toString();
	}
	
	/**
	 * SHA1加密
	 * @param buffer
	 * @return
	 */
	public static String toHex(byte[] buffer) {
		StringBuffer buf = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			if (((int) buffer[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) buffer[i] & 0xff, 16));
		}
		//System.out.println(buf.toString().toLowerCase());
		return buf.toString().toLowerCase();
	}
	
	public static String inputStream2String(InputStream in) {
		StringBuffer buffer = new StringBuffer();
		String line = "";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
		} catch (IOException e) {
			logger.error("文件操作异常" + e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.error("数据流关闭异常::" + e);
				}
			}
		}
		return buffer.toString();
	}
	
	
	/**
	 * 字符串替换方法，本方法只替换第一个满足条件的字符�?
	 * 
	 * @param srcStr
	 *            String 源字符串
	 * @param oldStr
	 *            String �?��被替换的子字符串
	 * @param newStr
	 *            String 用去替换的新的字符串
	 * @return String 替换后返回的字符�?
	 */
	public static String replace(String srcStr, String oldStr, String newStr) {
		// srcStr 不能为空,oldStr，newStr不能为空，并且srcStr与oldStr长度不能�?
		if (srcStr == null || srcStr.length() == 0 || oldStr == null
				|| oldStr.length() == 0 || newStr == null) {
			return srcStr;
		}
		if (oldStr.equals(newStr)) {
			return srcStr;
		}

		String tmp = srcStr;
		int index = tmp.indexOf(oldStr);
		if (index != -1) {
			tmp = tmp.substring(0, index) + newStr
					+ tmp.substring(index + oldStr.length(), srcStr.length());
		}
		return tmp;
	}

	/**
	 * 从WML页面读取中文后需要�?过本函数进行转换
	 * 
	 * @param str
	 *            String �?��转换的字�?
	 * @return String 转化以后的字�?
	 */
	public static String convertWmlPageZhStr(String str) {
		try {
			// 按照ISO8859-1取得字符�?
			byte b1[] = str.getBytes("ISO8859-1");
			// 向UTF-8转换
			String utfs = new String(b1, "UTF-8");
			// 向GBK转化
			byte b2[] = utfs.getBytes("GBK");
			String GBs = new String(b2, "GBK");
			str = GBs;
		} catch (Exception ex2) {
			ex2.printStackTrace();
		}
		return str;
	}

	/**
	 * 获取系统时间�?返回字符�?
	 * 
	 * @return String
	 */
	public static String getTimeStamp() {
		String starttime = String.valueOf(System.currentTimeMillis());
		String timeStamp = starttime.substring(starttime.length()
				- timeStampLength);
		return timeStamp;
	}

	/**
	 * 从源串截取不超过指定字节长度的字符串
	 * 
	 * @param inputString
	 *            String
	 * @param byteLen
	 *            int
	 * @return String
	 * @throws Exception
	 */
	public static String cutString(String inputString, int byteLen)
			throws Exception {
		String tempString = "";
		String outputString = inputString;
		if (inputString.getBytes("GBK").length > byteLen) {
			for (int i = 0; i < inputString.length(); i++) {
				tempString = tempString + inputString.substring(i, i + 1);
				if (tempString.getBytes("GBK").length > byteLen) {
					outputString = inputString.substring(0, i);
					break;
				}
			}
		}

		return outputString;
	}
	
    
	/**
	 * 位数不够左补充，多余右结�?
	 * @param totalLength
	 * @param fillChar
	 * @param srcStr
	 * @param chineseFlag
	 * @return
	 */
	public static String leftFill(int totalLength, char fillChar,
			String srcStr, boolean chineseFlag) {
		int byteLength = (srcStr == null ? 0 : srcStr.length());
		if (chineseFlag && (byteLength > 0)) {
			try {
				byteLength = srcStr.getBytes("GBK").length;
			} catch (Exception e) {
				logger.error("leftFill() string.getBytes(GBK) exception-->", e);
				return leftFill(totalLength, fillChar, null, false);
			}
		}

		int length = totalLength - byteLength;
		if (length < 0) {
			return srcStr.substring(0, totalLength);
		} else if (length == 0) {
			return srcStr;
		} else {
			char[] arr = new char[length];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = fillChar;
			}
			String sFill = new String(arr);
			if (length == totalLength) {
				return sFill;
			} else {
				return sFill + srcStr;
			}
		}
	}

	

    /**
     * 位数不够右补充，多余右结�?
     * @param totalLength
     * @param fillChar
     * @param srcStr
     * @param chineseFlag
     * @return
     */
	public static String rightFill(int totalLength, char fillChar,
			String srcStr, boolean chineseFlag) {
		int byteLength = (srcStr == null ? 0 : srcStr.length());
		if (chineseFlag && (byteLength > 0)) {
			try {
				byteLength = srcStr.getBytes("GBK").length;
			} catch (Exception e) {
				logger
						.error("rightFill() string.getBytes(GBK) exception-->",
								e);
				return rightFill(totalLength, fillChar, null, false);
			}
		}

		int length = totalLength - byteLength;
		if (length < 0) {
			return srcStr.substring(0, totalLength);
		} else if (length == 0) {
			return srcStr;
		} else {
			char[] arr = new char[length];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = fillChar;
			}
			String sFill = new String(arr);
			if (length == totalLength) {
				return sFill;
			} else {
				return srcStr + sFill;
			}
		}
	}
	
	
	/**
	 * 在IP列表中查找某个特定的IP
	 * 
	 * @param remoteIP
	 *            �?��查找的IP
	 * @param list
	 *            IP列表
	 * @return
	 */
	public static boolean findIP(String remoteIP, String[] list) {
		String[] remoteIPList = remoteIP.split("\\.");
		if (remoteIPList == null || remoteIPList.length != 4) {
			return false;
		}

		if (list.length >= 1 && "ALL".equalsIgnoreCase(list[0])) {
			return true;
		}
		for (int i = 0; i < list.length; i++) {
			String ip = list[i];
			if (isEmpty(ip)) {
				continue;
			}
			String[] tempArray = ip.split("\\.");
			if (remoteIPList[0].equals(tempArray[0])) {
				if (tempArray[1].equals("*")) {
					return true;
				} else {
					if (remoteIPList[1].equals(tempArray[1])) {
						if (tempArray[2].equals("*")) {
							return true;
						} else {
							if (remoteIPList[2].equals(tempArray[2])) {
								if (tempArray[3].equals("*")
										|| remoteIPList[3].equals(tempArray[3])) {
									return true;
								}
								if (tempArray[3].indexOf("-") >= 0) {
									String[] temp = tempArray[3].split("-");
									if (temp.length != 2) {
										continue;
									}
									int start = Integer.parseInt(temp[0]);
									int end = Integer.parseInt(temp[1]);
									int remoteInt = Integer
											.parseInt(remoteIPList[3]);
									if (remoteInt >= start && remoteInt <= end
											|| remoteInt <= start
											&& remoteInt >= end) {
										return true;
									}
								}
							}
						}
					}
				}
			}

		}
		return false;
	}
	
	
	  /**
     * 将一�?Map 对象转化为一�?JavaBean
     * @param type 要转化的类型
     * @param map 包含属�?值的 map
     * @return 转化出来�?JavaBean 对象
     * @throws IntrospectionException
     *             如果分析类属性失�?
     * @throws IllegalAccessException
     *             如果实例�?JavaBean 失败
     * @throws InstantiationException
     *             如果实例�?JavaBean 失败
     * @throws InvocationTargetException
     *             如果调用属�?�?setter 方法失败
     */
    public static Object convertMap(Class type, Map map) {
        BeanInfo beanInfo;
        Object obj = new Object();
		try {
			beanInfo = Introspector.getBeanInfo(type);
				obj = type.newInstance();
        // �?JavaBean 对象的属性赋�?
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();

            if (map.containsKey(propertyName)) {
                // 下面�?��可以 try 起来，这样当�?��属�?赋�?失败的时候就不会影响其他属�?赋�?�?
                Object value = map.get(propertyName);

                Object[] args = new Object[1];
                args[0] = value;

					descriptor.getWriteMethod().invoke(obj, args);
            }
        }
		} catch (IntrospectionException e) {
			return null;
		}  catch (InstantiationException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		} catch (IllegalArgumentException e) {
			return null;
		} catch (InvocationTargetException e) {
			return null;
		}
        return obj;
    }
    
    /**
     * 微信公众平台回调验证消息真实性
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    public static boolean isValidateCheck(String signature,String timestamp,String nonce,String echostr){
    	if(signature == null || timestamp == null || nonce== null)
    		return false;
    	String[] arr=new String[] {signature,timestamp,nonce};  
    	//字典排序  
        Arrays.sort(arr);
        String bigStr=arr[0]+arr[1]+arr[2];  
        //加密  
        try {  
            //创建整理类对象，加密SHA-1  
            MessageDigest md=MessageDigest.getInstance("SHA-1");  
            //加密  
            byte[] digest=md.digest(bigStr.getBytes());  
            //将字节数组转换为十六进制字符串  
            String digestStr=byteToStr(digest);  
            //判断  
            if (digestStr.toLowerCase().equals(signature)) {  
                return true;  
            }  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
        return false;  
    }
    
    /** 
     * 将字节数组转换为十六进制字符串 
     *  
     * @param byteArray 
     * @return 
     */  
    private static String byteToStr(byte[] byteArray) {  
        String strDigest = "";  
        for (int i = 0; i < byteArray.length; i++) {  
            strDigest += byteToHexStr(byteArray[i]);  
        }  
        return strDigest;  
    }  
  
    /** 
     * 将字节转换为十六进制字符串 
     *  
     * @param mByte 
     * @return 
     */  
    private static String byteToHexStr(byte mByte) {  
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
        char[] tempArr = new char[2];  
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];  
        tempArr[1] = Digit[mByte & 0X0F];  
  
        String s = new String(tempArr);  
        return s;  
    }  
    
 // 创建单个文件
 		public static boolean createFile(String filePath) {
 			File file = new File(filePath);
 			if (file.exists()) {// 判断文件是否存在
 				System.out.println("目标文件已存在" + filePath);
 				return false;
 			}
 			if (filePath.endsWith(File.separator)) {// 判断文件是否为目录
 				System.out.println("目标文件不能为目录！");
 				return false;
 			}
 			if (!file.getParentFile().exists()) {// 判断目标文件所在的目录是否存在
 				// 如果目标文件所在的文件夹不存在，则创建父文件夹
 				System.out.println("目标文件所在目录不存在，准备创建它！");
 				if (!file.getParentFile().mkdirs()) {// 判断创建目录是否成功
 					System.out.println("创建目标文件所在的目录失败！");
 					return false;
 				}
 			}
 			try {
 				if (file.createNewFile()) {// 创建目标文件
 					System.out.println("创建文件成功:" + filePath);
 					return true;
 				} else {
 					System.out.println("创建文件失败！");
 					return false;
 				}
 			} catch (IOException e) {// 捕获异常
 				e.printStackTrace();
 				System.out.println("创建文件失败！" + e.getMessage());
 				return false;
 			}
 		}
 		
 		public static synchronized String getBillno() {
 			StringBuilder billno = new StringBuilder();

 			// 日期(格式:20080524)
 			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
 			billno.append(format.format(new Date()));

 			// 生成5位格林治毫秒数
 			String endStr = String.valueOf(new Date().getTime());
 			billno.append((endStr.substring(endStr.length() - 5, endStr.length())));

 			// 生成4位随机数
 			String str4 = "0123456789";
 			Random rand = new Random();
 			int length = 2;
 			for (int i = 0; i < length; i++) {
 				int randNum = rand.nextInt(9);
 				billno.append(str4.substring(randNum, randNum + 1));
 			}
 			
 			return billno.toString();
 		}
 		
 		public static synchronized String getBillnoFor10() {
 			StringBuilder billno = new StringBuilder();

 			// 日期(格式:20080524)
 			SimpleDateFormat format = new SimpleDateFormat("HHmmss");
 			billno.append(format.format(new Date()));
 			
 			// 生成2位格林治毫秒数
 			String endStr = String.valueOf(new Date().getTime());
 			billno.append((endStr.substring(endStr.length() - 2, endStr.length())));

 			// 生成2位随机数
 			String str4 = "0123456789";
 			Random rand = new Random();
 			int length = 2;
 			for (int i = 0; i < length; i++) {
 				int randNum = rand.nextInt(9);
 				billno.append(str4.substring(randNum, randNum + 1));
 			}
 			
 			return billno.toString();
 		}
 		
 		public static String createSHASign(SortedMap<String, String> packageParams) throws NoSuchAlgorithmException {
 			StringBuffer sb = new StringBuffer();
 			Set es = packageParams.entrySet();
 			Iterator it = es.iterator();
 			while (it.hasNext()) {
 				Map.Entry entry = (Map.Entry) it.next();
 				String k = (String) entry.getKey();
 				String v = (String) entry.getValue();
 				if (null != v && !"".equals(v) && !"sign".equals(k)
 						&& !"key".equals(k)) {
 					sb.append(k + "=" + v + "&");
 				}
 			}
 			String str = sb.toString();
 			str = str.substring(0,str.length()-1);
 			String signature = new SHA1().getDigestOfString(str.getBytes());
 			return signature.toLowerCase();

 		}
 		
 		 public static Map<String, String> sign(String jsapi_ticket, String url) {
 	        Map<String, String> ret = new HashMap<String, String>();
 	        String timestamp = create_timestamp();
 			String nonce_str = create_nonce_str();
 	        String string1;
 	        String signature = "";

 	        //注意这里参数名必须全部小写，且必须有序
 	        string1 = "jsapi_ticket=" + jsapi_ticket +
 	                  "&noncestr=" + nonce_str +
 	                  "&timestamp=" + timestamp +
 	                  "&url=" + url;
 	        try
 	        {
 	            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
 	            crypt.reset();
 	            crypt.update(string1.getBytes("UTF-8"));
 	            signature = byteToHex(crypt.digest());
 	        }
 	        catch (NoSuchAlgorithmException e)
 	        {
 	            e.printStackTrace();
 	        }
 	        catch (UnsupportedEncodingException e)
 	        {
 	            e.printStackTrace();
 	        }

 	        ret.put("url", url);
 	        ret.put("jsapi_ticket", jsapi_ticket);
 	        ret.put("nonceStr", nonce_str);
 	        ret.put("timestamp", timestamp);
 	        ret.put("signature", signature);
 	        return ret;
 	    }
 		 
 		public static Map<String, String> signForEditAddress(String accessToken,String appid, String url) {
 	        Map<String, String> ret = new HashMap<String, String>();
 	        String timestamp = create_timestamp();
 			String nonce_str = create_nonce_str();
 	        String string1;
 	        String signature = "";

 	        //注意这里参数名必须全部小写，且必须有序
 	        string1 = "accessToken=" + accessToken +
 	                  "&appid=" + appid +
 	                  "&noncestr=" + nonce_str +
 	                  "&timestamp=" + timestamp +
 	                  "&url=" + url;
 	        signature = new SHA1().getDigestOfString(string1.getBytes());
 	        ret.put("url", url);
 	        ret.put("nonceStr", nonce_str);
 	        ret.put("timestamp", timestamp);
 	        ret.put("signature", signature);
 	        return ret;
 	    }

 	    private static String byteToHex(final byte[] hash) {
 	        Formatter formatter = new Formatter();
 	        for (byte b : hash)
 	        {
 	            formatter.format("%02x", b);
 	        }
 	        String result = formatter.toString();
 	        formatter.close();
 	        return result;
 	    }

 	   public static String create_nonce_str() {
 	        return UUID.randomUUID().toString();
 	    }

 	   public static String create_timestamp() {
 	        return Long.toString(System.currentTimeMillis() / 1000);
 	    }
 	    
 		public static synchronized String getBillnoFor27() {
 			StringBuilder billno = new StringBuilder();
 			// 日期(格式:20080524)
 			SimpleDateFormat format = new SimpleDateFormat("HHmmss");
 			billno.append(format.format(new Date()));

 			// 生成4位格林治毫秒数
 			String endStr = String.valueOf(new Date().getTime());
 			billno.append((endStr.substring(endStr.length() - 3, endStr.length())));

 			// 生成2位随机数
 			String str4 = "0123456789";
 			Random rand = new Random();
 			int length = 2;
 			for (int i = 0; i < length; i++) {
 				int randNum = rand.nextInt(9);
 				billno.append(str4.substring(randNum, randNum + 1));
 			}
 			
 			return billno.toString();
 		}
 		
    public static String getOrderTypeName(String orderType){
		String typeName = "" ;
		if("1".equals(orderType)){
			typeName = "预约试驾";
		}else if("2".equals(orderType)){
			typeName = "维修预约";
		}else if("3".equals(orderType)){
			typeName = "购车申请";
		}else if("4".equals(orderType)){
			typeName = "活动申请";
		}else if("5".equals(orderType)){
			typeName = "保养预约";
		}else if("6".equals(orderType)){
			typeName = "二手车置换";
		}
		return typeName;
	}
    
    public static ShiroUser queryCurrentShiroUser() {
		try {
			WebSubject ws = (WebSubject) SecurityUtils.getSubject();
			return (ShiroUser) ws.getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
    
    /**
     * 查询访问微信的token值；
     * @return
     */
    public static String queryToken(){
    	ShiroUser su = queryCurrentShiroUser();
    	Map wechatMap = TokenDC.getInstance().getWechatVo(su.getLoginName());
    	String accessToken = wechatMap.get("accessToken")!=null?(String)wechatMap.get("accessToken"):null;
    	return accessToken;
    }
    
    /**
     * 查询微信账号的Id；
     * @return
     */
    public static Integer queryWechatId(){
    	ShiroUser su = queryCurrentShiroUser();
    	Map wechatMap = TokenDC.getInstance().getWechatVo(su.getLoginName());
    	Integer id = wechatMap.get("id")!=null?(Integer)wechatMap.get("id"):0;
    	return id;
    }
    
	public static String queryUserId(HttpServletRequest request){
		try {
			Cookie cookie = CookiesUtils.getCookie(request, "userId");
			if(NullUtil.isNull(cookie)){
				return null;
			}
			return DesEncrypt.getDecryptString(cookie.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    
    public static boolean verifiProductPrice(Map requestMap){
		try {
			if(NullUtil.isNull(requestMap)){
				return false;
			}
			String dataSign = StringUtil.getString(requestMap.get("dataSign"));
			String goodsCode = StringUtil.getString(requestMap.get("goodsCode"));
			String goodsVersion = StringUtil.getString(requestMap.get("goodsVersion"));
			String shopPrice = StringUtil.getString(requestMap.get("shopPrice"));
			
			StringBuffer sb = new StringBuffer();
			sb.append(Const.SHOP_PRODUCT_KEY);
			sb.append(goodsCode);
			sb.append(goodsVersion);
			sb.append(shopPrice);
			
			if(dataSign.equals(Md5.md5Digest(sb.toString()))){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
		}
		return false;
	}
    
	public static boolean isMobileNO(String mobiles){  
    	Pattern p = Pattern.compile("^((13[0-9])|(14[0,5-9])|(15[^4,\\D])|(17[0,5-9])|(18[0,5-9]))\\d{8}$");  
    	Matcher m = p.matcher(mobiles);  
    	return m.matches();  
    }
	
    public static Map<String, Object> parseJSON2Map(String jsonStr){
        Map<String, Object> map = new HashMap<String, Object>();
        //最外层解析
        JSONObject json = JSONObject.fromObject(jsonStr);
        for(Object k : json.keySet()){
            Object v = json.get(k); 
            //如果内层还是数组的话，继续解析
            if(v instanceof JSONArray){
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
                Iterator<JSONObject> it = ((JSONArray)v).iterator();
                while(it.hasNext()){
                    JSONObject json2 = it.next();
                    list.add(parseJSON2Map(json2.toString()));
                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }
        }
        return map;
    }
}

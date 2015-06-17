package com.netbull.shop.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.netbull.shop.common.vo.Constant;

public class RequestUtil {

	public static final String ACTION_SUFFIX_PATTERN = ".+[.]do$";// Action结尾

	/**
	 * 返回全路径url
	 * */
	public static String getFullURL(HttpServletRequest request) {
		String uri = getURI(request);
		String queryString = request.getQueryString();
		if (queryString == null)
			return uri;
		return uri + "?" + queryString;
	}

	/** 返回不带参数的URI */
	public static String getURI(HttpServletRequest request) {
		return request.getRequestURI();
	}

	public static boolean isAjax(HttpServletRequest request) {
		return request.getHeader("x-requested-with") != null
				&& request.getHeader("x-requested-with").equalsIgnoreCase( // ajax超时处理
						"XMLHttpRequest");
	}

	public static String getIp(HttpServletRequest request) {
		String ip = "";
		if (request.getHeader("x-forwarded-for") == null) {
			ip = request.getRemoteAddr();
		} else {
			ip = request.getHeader("x-forwarded-for");
		}
		return ip;
	}

	public static String subActionSuffix(String url) {
		if (url.matches(ACTION_SUFFIX_PATTERN)) {
			url = url.substring(0, url.indexOf(".do"));
		}
		return url;
	}

	/**
	 * 将url参数转换成map
	 * 
	 * @param param
	 *            aa=11&bb=22&cc=33
	 * @return
	 */
	public static Map<String, Object> getUrlParams(String param) {
		Map<String, Object> map = new HashMap<String, Object>(0);
		if (StringUtils.isBlank(param)) {
			return map;
		}
		String[] params = param.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] p = params[i].split("=");
			if (p.length == 2) {
				map.put(p[0], p[1]);
			}
		}
		return map;
	}

	/**
	 * 将map转换成url
	 * 
	 * @param map
	 * @return
	 */
	public static String getUrlParamsByMap(Map<String, Object> map) {
		if (map == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sb.append(entry.getKey() + "=" + entry.getValue());
			sb.append("&");
		}
		String s = sb.toString();
		if (s.endsWith("&")) {
			s = org.apache.commons.lang.StringUtils.substringBeforeLast(s, "&");
		}
		if(!NullUtil.isNull(s)){
			return "?"+s;
		}else{
			return s;
		}
	}

	public static String getLocalIp() {
		Enumeration<NetworkInterface> netInterfaces = null;
		try {
			netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					String localIp = ips.nextElement().getHostAddress();
					if (localIp.indexOf(Constant.LOCAL_SERVER_ADDRESS_PREFIX) >= 0) {
						return localIp;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getPostData(HttpServletRequest reqest) {
		try{
			InputStream input = reqest.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input, "utf-8"));
			StringBuilder buf = new StringBuilder();
			String line = null;
			while((line = reader.readLine()) != null) {
				buf.append(line);
			}
			reader.close();
			return buf.toString();
		}catch(Exception e) {
			return "";
		}
	}
}

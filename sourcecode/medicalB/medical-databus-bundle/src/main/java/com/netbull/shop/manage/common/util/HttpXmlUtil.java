package com.netbull.shop.manage.common.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

import com.netbull.shop.common.config.ConfigLoadUtil;
import com.netbull.shop.common.util.HttpClientFactory;
import com.netbull.shop.common.util.NullUtil;

/**
 * 此版本使用document 对象封装XML，解决发送短信内容包涵特殊字符而出现无法解析，如 短信为：“你好，<%&*&*&><<<>fds测试短信”
 * 
 * @author elvis
 */
public class HttpXmlUtil {

	protected static transient final Log logger = LogFactory.getLog(HttpXmlUtil.class);
	
	/**
	 * 执行一个HTTP GET请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param params
	 *            请求的查询参数,可以为null
	 * @return 返回请求响应的HTML
	 */
	public static String doGet(String url) throws Exception {
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		// 创建GET方法的实例
		GetMethod getMethod = new GetMethod(url);
		// 使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + getMethod.getStatusLine());
			}
			// 读取内容
			byte[] responseBody = getMethod.getResponseBody();
			// 处理内容
			System.out.println(new String(responseBody,"GBK"));
			return new String(responseBody,"GBK");
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			e.printStackTrace();
		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}
		return null;
	}
	
	/**
	 * 执行一个HTTP POST请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param params
	 *            请求的查询参数,可以为null
	 * @return 返回请求响应的HTML
	 */
	public static String doPost(String url, Map<String, String> params) {
		String response = null;
		HttpClient client = HttpClientFactory.getHttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		// 设置Post数据
		if (params!=null && !params.isEmpty()) {
			int i = 0;
			NameValuePair[] data = new NameValuePair[params.size()];
			for (Entry<String, String> entry : params.entrySet()) {
				data[i] = new NameValuePair(entry.getKey(), entry.getValue());
				i++;
			}
			postMethod.setRequestBody(data);
		}
		try {
			client.executeMethod(postMethod);
			if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
				response = postMethod.getResponseBodyAsString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return response;
	}

	/**
	 * 发送string字符串到指定的URL上，并返回响应信息；
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String post(String url, String paramString,String authorization) throws ClientProtocolException, IOException {

		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		post.setHeader("Accept", "application/json");
		post.setHeader("Content-Type", "application/json");
		if(!NullUtil.isNull(authorization)){
			post.setHeader("Authorization", authorization);
		}
		post.setEntity(new StringEntity(paramString, "UTF-8"));

		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String response = httpClient.execute(post, responseHandler);
		return new String(response.getBytes(),"UTF-8");
	}
	
	public static boolean sendNoticeMsg(String userId,String content){  
		String JPush_appKey = ConfigLoadUtil.loadConfig().getPropertie("JPush_appKey");
		String JPush_masterSecret = ConfigLoadUtil.loadConfig().getPropertie("JPush_masterSecret");
		JPushClient jpushClient = new JPushClient(JPush_masterSecret, JPush_appKey, 3);
	    PushPayload payload = buildPushObject_all_alias_alert(userId,content,0);
	    PushResult result;
		try {
			result = jpushClient.sendPush(payload);
			if(!result.isResultOK()){
				return false;
			}else{
				return true;
			}
		} catch (APIConnectionException e) {
			logger.error(e.getMessage());
		} catch (APIRequestException e) {
			logger.error(e.getMessage());
		}
		return false;
    }
	
	public static boolean sendNoticeMsg(String userId,String content,Integer type){  
		String JPush_appKey = ConfigLoadUtil.loadConfig().getPropertie("JPush_appKey");
		String JPush_masterSecret = ConfigLoadUtil.loadConfig().getPropertie("JPush_masterSecret");
		JPushClient jpushClient = new JPushClient(JPush_masterSecret, JPush_appKey, 3);
	    PushPayload payload = buildPushObject_all_alias_alert(userId,content,type);
	    PushResult result;
		try {
			result = jpushClient.sendPush(payload);
			if(!result.isResultOK()){
				return false;
			}else{
				return true;
			}
		} catch (APIConnectionException e) {
			logger.error(e.getMessage());
		} catch (APIRequestException e) {
			logger.error(e.getMessage());
		}
		return false;
    }
    
	public static boolean sendYSNoticeMsg(String userId,String content,Integer type){  
		String JPush_appKey = ConfigLoadUtil.loadConfig().getPropertie("YS_JPush_appKey");
		String JPush_masterSecret = ConfigLoadUtil.loadConfig().getPropertie("YS_JPush_masterSecret");
		JPushClient jpushClient = new JPushClient(JPush_masterSecret, JPush_appKey, 3);
	    PushPayload payload = buildPushObject_all_alias_alert(userId,content,type);
	    PushResult result;
		try {
			result = jpushClient.sendPush(payload);
			if(!result.isResultOK()){
				return false;
			}else{
				return true;
			}
		} catch (APIConnectionException e) {
			logger.error(e.getMessage());
		} catch (APIRequestException e) {
			logger.error(e.getMessage());
		}
		return false;
    }
	
	public static PushPayload buildPushObject_all_alias_alert(String aliases,String alert,Integer type) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(aliases))
                .setNotification(Notification.alert(alert))
                .setOptions(Options.sendno(type))
                .setOptions(Options.newBuilder()
                         .setApnsProduction(true)
                         .build())
                .build();
    }
	
	// MD5加密函数
		public static String MD5Encode(String sourceString) {
			String resultString = null;
			try {
				resultString = new String(sourceString);
				MessageDigest md = MessageDigest.getInstance("MD5");
				resultString = byte2hexString(md.digest(resultString.getBytes()));
			} catch (Exception ex) {
			}
			return resultString;
		}

		public static final String byte2hexString(byte[] bytes) {
			StringBuffer bf = new StringBuffer(bytes.length * 2);
			for (int i = 0; i < bytes.length; i++) {
				if ((bytes[i] & 0xff) < 0x10) {
					bf.append("0");
				}
				bf.append(Long.toString(bytes[i] & 0xff, 16));
			}
			return bf.toString();
		}
}

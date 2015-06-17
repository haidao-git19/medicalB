package com.netbull.shop.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * 
 * @author <a href="mailto:peng.wu@foxmail.com">wu.peng</a>
 * 
 */
public class HttpUtils {

	private static final Log log = LogFactory.getLog(HttpUtils.class);

	public static byte[] get(String url) {
		HttpClient hc = new HttpClient();
		GetMethod method = null;
		InputStream in = null;
		try {
			method = new GetMethod(url);
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());
			method.getParams().setParameter(HttpMethodParams.HTTP_URI_CHARSET,
					"utf-8");
			int code = hc.executeMethod(method);
			if (code == HttpStatus.SC_OK) {
				in = method.getResponseBodyAsStream();
				return com.netbull.shop.util.IOUtils.readByteArray(in);
			}
		} catch (Exception e) {
			log.error(e, e);
		} finally {
			if (method != null) {
				method.releaseConnection();
				method = null;
			}
			IOUtils.closeQuietly(in);
		}
		return null;
	}

	public static byte[] post(String url, Map<String, String> params) {
		HttpClient hc = new HttpClient();
		PostMethod method = null;
		InputStream in = null;
		try {
			method = new PostMethod(url);
			List<NameValuePair> _params = new ArrayList<NameValuePair>();
			if (params != null) {
				for (String key : params.keySet()) {
					_params.add(new NameValuePair(key, params.get(key)));
				}
			}
			method.setRequestBody(_params.toArray(new NameValuePair[] {}));
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());
			method.getParams().setParameter(HttpMethodParams.HTTP_URI_CHARSET,
					"utf-8");
			int code = hc.executeMethod(method);
			if (code == HttpStatus.SC_OK) {
				in = method.getResponseBodyAsStream();
				return com.netbull.shop.util.IOUtils.readByteArray(in);
			}
		} catch (Exception e) {
			log.error(e, e);
		} finally {
			if (method != null) {
				method.releaseConnection();
				method = null;
			}
			IOUtils.closeQuietly(in);
		}
		return new byte[] {};
	}

	/**
	 * 发送json字符串到指定的URL上，并返回响应信息；
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String postJson(String url, String jsonString) throws ClientProtocolException, IOException {

		DefaultHttpClient httpClient = new DefaultHttpClient();

		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", "application/json");
		post.setEntity(new StringEntity(jsonString, "UTF-8"));

		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String response = httpClient.execute(post, responseHandler);
		return response;
	}

	public static Map<String, String> getParams(ServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		if (request == null) {
			return params;
		}
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			String paramValue = request.getParameter(paramName);
			params.put(paramName, paramValue);
		}
		return params;
	}

}

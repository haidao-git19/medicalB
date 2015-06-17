package com.netbull.shop.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;


public class HttpConnectionManager {

	private static HttpParams httpParams;
	private static ClientConnectionManager connectionManager;

	/**
	 * 最大连接数
	 */
	public final static int MAX_TOTAL_CONNECTIONS = 800;
	/**
	 * 获取连接的最大等待时间
	 */
	public final static int WAIT_TIMEOUT = 60000;
	/**
	 * 每个路由最大连接数
	 */
	public final static int MAX_ROUTE_CONNECTIONS = 400;
	/**
	 * 连接超时时间
	 */
	public final static int CONNECT_TIMEOUT = 3000;
	/**
	 * 读取超时时间
	 */
	public final static int READ_TIMEOUT = 3000;

	static {
		httpParams = new BasicHttpParams();
		// 设置最大连接数
		ConnManagerParams.setMaxTotalConnections(httpParams,
				MAX_TOTAL_CONNECTIONS);
		// 设置获取连接的最大等待时间
		ConnManagerParams.setTimeout(httpParams, WAIT_TIMEOUT);
		// 设置每个路由最大连接数
		ConnPerRouteBean connPerRoute = new ConnPerRouteBean(
				MAX_ROUTE_CONNECTIONS);
		ConnManagerParams.setMaxConnectionsPerRoute(httpParams, connPerRoute);
		// 设置连接超时时间
		HttpConnectionParams.setConnectionTimeout(httpParams, CONNECT_TIMEOUT);
		// 设置读取超时时间
		HttpConnectionParams.setSoTimeout(httpParams, READ_TIMEOUT);

		SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		/*registry.register(new Scheme("https", SSLSocketFactory
				.getSocketFactory(), 443));*/

		connectionManager = new ThreadSafeClientConnManager(httpParams,
				registry);
	}

	public static HttpClient getHttpClient() {
		return new DefaultHttpClient(connectionManager, httpParams);
	}
	
	public static void main(String[] args) {
		HttpPost httpPost = new HttpPost("http://localhost:9091/fileUpload");
		ByteArrayEntity myEntity = new ByteArrayEntity("elvis".getBytes());
		httpPost.setEntity(myEntity);
		HttpHost httpHost = new HttpHost("localhost", 9091, null);
		try {
			HttpResponse hresp = getHttpClient().execute(httpHost, httpPost);
			byte[] json = EntityUtils.toByteArray(hresp.getEntity());
			GZIPInputStream gzip = null;
			gzip = new GZIPInputStream(new ByteArrayInputStream(json));
			System.out.println(gzip);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


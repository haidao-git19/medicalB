package com.netbull.shop.databus.order;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import com.netbull.shop.common.util.HttpClientUtil;


public class OrderTest {
	
	@Test
	@Ignore
	public void testPutServiceOrder() {
		String url = "http://localhost:8086/dbs/anon/put/service/order";
		String req = "{\"patientId\":2,\"doctorId\":1,\"serviceType\":4,\"serviceNumber\":\"1029374638\",\"fee\":100,\"sign\":\"3B369D85D566D143402B9F6751186932\"}";
		try {
			System.out.println(req);
			System.out.println(HttpClientUtil.httpPost(url, req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void testPutShopServiceOrder() {
		String url = "http://localhost:8086/dbs/anon/put/service/order";
		String req = "{\"patientId\":2,\"shopId\":101,\"serviceType\":4,\"prescriptionId\":\"12345678\",\"fee\":100,\"sign\":\"502A878376582E080BBB6BAF5D9CAF59\"}";
		try {
			System.out.println(req);
			System.out.println(HttpClientUtil.httpPost(url, req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	//@Ignore
	public void testUpdateOrderPayment() {
		String url = "http://localhost:8086/dbs/anon/update/order/payment";
		String req = "{\"orderNum\":\"20150517203725723716\", \"sign\": \"12372D43D7802BE47B4282C077C8F863\"}";
		try {
			System.out.println(req);
			System.out.println(HttpClientUtil.httpPost(url, req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void testPatientOrderList() {
		String url = "http://localhost:8086/dbs/anon/patient/order/list";
		String req = "{\"pageNum\":5, \"pageSize\": 10, \"serviceType\": \"4\", \"patientId\": 2}";
		try {
			System.out.println(req);
			System.out.println(HttpClientUtil.httpPost(url, req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void testShopOrderList() {
		String url = "http://localhost:8086/dbs/anon/shop/order/list";
		String req = "{\"pageNum\":1, \"pageSize\": 10, \"serviceType\": \"4\", \"shopId\": 100}";
		try {
			System.out.println(req);
			System.out.println(HttpClientUtil.httpPost(url, req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void testDoctorOrderList() {
		String url = "http://localhost:8086/dbs/anon/doctor/order/list";
		String req = "{\"pageNum\":1, \"pageSize\": 10, \"serviceType\": \"4, 5\", \"doctorId\": 24}";
		try {
			System.out.println(req);
			System.out.println(HttpClientUtil.httpPost(url, req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

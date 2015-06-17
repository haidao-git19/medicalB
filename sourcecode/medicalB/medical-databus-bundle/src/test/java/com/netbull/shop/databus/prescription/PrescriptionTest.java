package com.netbull.shop.databus.prescription;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import com.netbull.shop.common.util.HttpClientUtil;

public class PrescriptionTest {
	
	@Test
	@Ignore
	public void testDrugQuery() {
		String url = "http://localhost:8088/dbs/anon/prescription/drug/query";
		String req = "{\"shortCode\":\"l\",\"pageNum\":1,\"pageSize\":4}";
		try {
			System.out.println(req);
			System.out.println(HttpClientUtil.httpPost(url, req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void testDoctorPrescriptionList() {
		String url = "http://localhost:8088/dbs/anon/doctor/prescription/list";
		String req = "{\"doctorId\":12,\"pageNum\":1,\"pageSize\":4}";
		try {
			System.out.println(req);
			System.out.println(HttpClientUtil.httpPost(url, req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void testDoctorPrescriptionDel() {
		String url = "http://localhost:8088/dbs/anon/doctor/prescription/del";
		String req = "{\"id\":8}";
		try {
			System.out.println(req);
			System.out.println(HttpClientUtil.httpPost(url, req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void testDoctorPrescriptionDetail() {
		String url = "http://localhost:8086/dbs/anon/doctor/prescription/detail";
		String req = "{\"id\":23}";
		try {
			System.out.println(req);
			System.out.println(HttpClientUtil.httpPost(url, req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void testDoctorPrescriptionCreate() {
		String url = "http://localhost:8088/dbs/anon/doctor/prescription/create";
		String req = "{\"doctorId\":1,\"title\":\"咳嗽处方\",\"application\":\"适用于风寒咳咳\",\"remark\":\"禁烟酒\",\"isShow\":1,\"qrCodePath\":\"http://aaa/bbb/ccc\",\"prescriptionItems\":[{\"drugName\":\"太极止咳糖浆\",\"drugType\":1,\"drugSpec\":\"150ml*1瓶\",\"producer\":\"江西制药厂\",\"amount\":\"1\",\"useage\":\"一日三次，每次10ml\"},{\"drugName\":\"头孢氨卡胶囊\",\"drugType\":2,\"drugSpec\":\"10片*1盒\",\"producer\":\"云南制药厂\",\"amount\":\"2\",\"useage\":\"一日两次\"}]}";
		try {
			System.out.println(req);
			System.out.println(HttpClientUtil.httpPost(url, req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void testDoctorPrescriptionUpdate() {
		String url = "http://localhost:8088/dbs/anon/doctor/prescription/update";
		String req = "{\"id\":14,\"application\":\"适用发烧\",\"remark\":\"XXX\",\"title\":\"XXXX\",\"prescriptionItems\":[{\"amount\":\"6\",\"drugName\":\"药品A\",\"drugSpec\":\"10片*盒\",\"producer\":\"XX\",\"drugType\":8,\"id\":19,\"prescriptionId\":14,\"useage\":\"XX\"},{\"amount\":\"8\",\"drugName\":\"药品A\",\"drugSpec\":\"XX\",\"producer\":\"XX\",\"drugType\":8,\"id\":20,\"prescriptionId\":14,\"useage\":\"XX\"}]}";
		try {
			System.out.println(req);
			System.out.println(HttpClientUtil.httpPost(url, req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void testPrescriptionRelationCreate() {
		String url = "http://localhost:8088/dbs/anon/prescription/bind";
		String req = "{\"prescriptionId\":14,\"patientId\":8,\"creator\":1}";
		try {
			System.out.println(req);
			System.out.println(HttpClientUtil.httpPost(url, req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void testPatientPrescriptionList() {
		String url = "http://localhost:8086/dbs/anon/patient/prescription/list";
		String req = "{\"patientId\":8,\"pageNum\":1,\"pageSize\":4}";
		try {
			System.out.println(req);
			System.out.println(HttpClientUtil.httpPost(url, req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void testPatientPrescriptionDetail() {
		String url = "http://localhost:8086/dbs/anon/patient/prescription/detail";
		String req = "{\"id\":11}";
		try {
			System.out.println(req);
			System.out.println(HttpClientUtil.httpPost(url, req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void testBuyRecord() {
		String url = "http://localhost:8086/dbs/anon/patient/prescription/buy/record";
		String req = "{\"patientIdPrescriptionId\":11, \"drugShopId\":12}";
		try {
			System.out.println(req);
			System.out.println(HttpClientUtil.httpPost(url, req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

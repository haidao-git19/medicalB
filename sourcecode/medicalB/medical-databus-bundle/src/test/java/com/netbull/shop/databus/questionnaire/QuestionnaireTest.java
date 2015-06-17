package com.netbull.shop.databus.questionnaire;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import com.netbull.shop.common.util.HttpClientUtil;

public class QuestionnaireTest {
	
	@Test
	@Ignore
	public void testDoctorPrescriptionList() {
		String url = "http://localhost:8086/dbs/anon/doctor/questionnaire/list";
		String req = "{\"doctorId\":1}";
		try {
			System.out.println(req);
			System.out.println(HttpClientUtil.httpPost(url, req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	//@Ignore
	public void testDoctorPrescriptionDetail() {
		String url = "http://localhost:8086/dbs/anon/doctor/questionnaire/detail";
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
	public void testPatientBind() {
		String url = "http://localhost:8086/dbs/anon/questionnaire/patient/bind";
		String req = "{\"patientId\":12,\"doctorId\":100,\"qnId\":1}";
		try {
			System.out.println(req);
			System.out.println(HttpClientUtil.httpPost(url, req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void testPrescriptionCommit() {
		String url = "http://localhost:8086/dbs/anon/questionnaire/commit";
		String req = "{\"patientId\":12,\"doctorId\":100,\"qnId\":1,\"details\":[{\"caseId\":1,\"caseVal\":160,},{\"caseId\":2,\"caseVal\":45},{\"caseId\":3,\"caseVal\":10},{\"caseId\":4,\"caseVal\":150,\"exceptionFlag\":1,\"level\":1},{\"caseId\":5,\"caseVal\":80},{\"caseId\":6,\"caseOptionId\":1},{\"caseId\":7,\"caseOptionId\":7,\"exceptionFlag\":1,\"level\":3}]}";
		try {
			System.out.println(req);
			System.out.println(HttpClientUtil.httpPost(url, req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void testExceptionOption() {
		String url = "http://localhost:8086/dbs/anon/questionnaire/exceptionOption";
		String req = "{\"id\":2}";
		try {
			System.out.println(req);
			System.out.println(HttpClientUtil.httpPost(url, req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void testFeedbackCommit() {
		String url = "http://localhost:8086/dbs/anon/questionnaire/feedback/commit";
		String req = "{\"rId\":2,\"doctorId\":100,\"feedback\":\"多喝水，禁抽烟酗酒，多吃水果加运动\"}";
		try {
			System.out.println(req);
			System.out.println(HttpClientUtil.httpPost(url, req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void testFeedbackQuery() {
		String url = "http://localhost:8086/dbs/anon/questionnaire/feedback/query";
		String req = "{\"id\": 10}";
		try {
			System.out.println(req);
			System.out.println(HttpClientUtil.httpPost(url, req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void testexceptionList() {
		String url = "http://localhost:8086/dbs/anon/questionnaire/exception/list";
		String req = "{\"doctorId\": 100}";
		try {
			System.out.println(req);
			System.out.println(HttpClientUtil.httpPost(url, req));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

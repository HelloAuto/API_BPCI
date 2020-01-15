package com.bpci.practice.example;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bpci.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC001_Get_ActiveAdmissionCount extends TestBase{
	
	@BeforeClass
	void getActiveAdmissionCount() throws InterruptedException {
		
		logger.info("********started TC001_Get_ActiveAdmissionCount*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getActiveAdmissionCount");
		Thread.sleep(1000);
	}

	@Test
	void checkStatusCode() {
		
		logger.info("********Checking Status Code*********");
		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==> "+statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkAdmissionCount() {
		logger.info("********Checking Admision Count*********");
		//String responseBody = response.getBody().asString();  //To validate single data.Below one will help to validate each data of response string. 
		JsonPath jsonpath = response.jsonPath();
		int i = jsonpath.get("AdmissionCount");
		logger.info("Admission Count is ==> "+i);
		System.out.println(i);
	}
	
	@Test
	void checkStatusLine() {
		logger.info("********Checking Status Line*********");
		String statusLine = response.getStatusLine();
		logger.info("Status Line is ==> "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkResponseTime() {
		logger.info("********Checking Response Time*********");
		long responseTime = response.getTime();
		logger.info("Response Time is ==> "+responseTime);
		if(responseTime >2000) 
			logger.warn("Response Time is greater than 2000");
		//Assert.assertTrue(responseTime < 2000);
	}
	
	@Test
	void checkContentType() {
		logger.info("********Checking Content Type*********");
		String contentType = response.header("Content-Type");
		logger.info("Content Type is ==> "+contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}
	
	@Test
	void checkContentLength() {
		logger.info("********Checking Content Length*********");
		String contentLength = response.header("Content-Length");
		logger.info("Content Length is ==> "+contentLength);
		Assert.assertTrue(Integer.parseInt(contentLength) < 1500);
	}
	
	@Test
	void checkServerType() { 
		logger.info("********Checking Server Type*********");
		String serverType = response.header("Server");
		logger.info("Server Type is ==> "+serverType);
		Assert.assertEquals(serverType, "Microsoft-IIS/8.5");
	}
	
	@Test
	void checkActiveEpisode() {
		logger.info("********Checking Active Episode*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getActiveAdmissionCount?status=active");
		JsonPath jsonpath = response.jsonPath();
		int activeAdmissionCount = jsonpath.get("AdmissionCount");
		logger.info("Active Admission Count is : "+activeAdmissionCount);
		Assert.assertEquals(activeAdmissionCount, 13, "Not Matched");
	}
	
	@Test
	void checkInactiveEpisode() {
		logger.info("********Checking Inactive Episode*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getActiveAdmissionCount?status=inactive");
		JsonPath jsonpath = response.jsonPath();
		int inactiveAdmissionCount = jsonpath.get("AdmissionCount");
		logger.info("Inactive Admission Count is : "+inactiveAdmissionCount);
		Assert.assertEquals(inactiveAdmissionCount, 4, "Not Matched");
	}
	
	@Test
	void checkObservationEpisode() {
		logger.info("********Checking Observation Episode*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getActiveAdmissionCount?status=observation");
		JsonPath jsonpath = response.jsonPath();
		int observationAdmissionCount = jsonpath.get("AdmissionCount");
		logger.info("Inactive Admission Count is : "+observationAdmissionCount);
		Assert.assertEquals(observationAdmissionCount, 0, "Not Matched");
	}
	
	@Test
	void checkActiveEpisodeFilterByEpisodeId() {
		logger.info("********Checking Active Episode Filter By Episode Id*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getActiveAdmissionCount?episodeid=5");
		JsonPath jsonpath = response.jsonPath();
		int activeAdmissionCount = jsonpath.get("AdmissionCount");
		logger.info("Active Admission Count is : "+activeAdmissionCount);
		Assert.assertEquals(activeAdmissionCount, 1, "Not Matched");
	}
	
//	@Test // Need to check
//	void checkActiveEpisodeFilterByEpisodeType() {
//		logger.info("********Checking Active Episode Filter By Episode Type*********");
//		RestAssured.baseURI = uri;
//		httpRequest = RestAssured.given();
//		response = httpRequest.request(Method.GET,"/getActiveAdmissionCount?episodetype_fk=5");
//		JsonPath jsonpath = response.jsonPath();
//		int activeAdmissionCount = jsonpath.get("AdmissionCount");
//		logger.info("Active Admission Count is : "+activeAdmissionCount);
//		Assert.assertEquals(activeAdmissionCount, 13, "Not Matched");
//	}
	
	@Test
	void checkActiveEpisodeFilterByDischargeDate() {
		logger.info("********Checking Active Episode Filter By Discharge Date*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getActiveAdmissionCount?dischargeDate=2019-05-20 00:00:00.000");
		JsonPath jsonpath = response.jsonPath();
		int activeAdmissionCount = jsonpath.get("AdmissionCount");
		logger.info("Active Admission Count is : "+activeAdmissionCount);
		Assert.assertEquals(activeAdmissionCount, 1, "Not Matched");
	}
	
	@Test
	void checkActiveEpisodeFilterByLOS() {
		logger.info("********Checking Active Episode Filter By LOS*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getActiveAdmissionCount?LOS=10");
		JsonPath jsonpath = response.jsonPath();
		int activeAdmissionCount = jsonpath.get("AdmissionCount");
		logger.info("Active Admission Count is : "+activeAdmissionCount);
		Assert.assertEquals(activeAdmissionCount, 12, "Not Matched");
	}
	
	@Test
	void checkActiveEpisodeFilterByInitiatorName() {
		logger.info("********Checking Active Episode Filter By Initiator Name*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getActiveAdmissionCount?initiator=Dr.Steve");
		JsonPath jsonpath = response.jsonPath();
		int activeAdmissionCount = jsonpath.get("AdmissionCount");
		logger.info("Active Admission Count is : "+activeAdmissionCount);
		Assert.assertEquals(activeAdmissionCount, 3, "Not Matched");
	}
	
	@Test
	void checkActiveEpisodeFilterByDischargeDisposition() {
		logger.info("********Checking Active Episode Filter By Discharge Disposition*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getActiveAdmissionCount?dischargeDisposition=DD2");
		JsonPath jsonpath = response.jsonPath();
		int activeAdmissionCount = jsonpath.get("AdmissionCount");
		logger.info("Active Admission Count is : "+activeAdmissionCount);
		Assert.assertEquals(activeAdmissionCount, 2, "Not Matched");
	}
	
	@Test
	void checkActiveEpisodeFilterByFacilityName() {
		logger.info("********Checking Active Episode Filter By Facility Name*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getActiveAdmissionCount?facilityName=name5");
		JsonPath jsonpath = response.jsonPath();
		int activeAdmissionCount = jsonpath.get("AdmissionCount");
		logger.info("Active Admission Count is : "+activeAdmissionCount);
		Assert.assertEquals(activeAdmissionCount, 1, "Not Matched");
	}
	
//	@Test
//	void checkActiveEpisodeFilterByReadmission() {
//		logger.info("********Checking Active Episode Filter By Readmission*********");
//		RestAssured.baseURI = uri;
//		httpRequest = RestAssured.given();
//		response = httpRequest.request(Method.GET,"/getActiveAdmissionCount?readmission=yes");
//		JsonPath jsonpath = response.jsonPath();
//		int activeAdmissionCount = jsonpath.get("AdmissionCount");
//		logger.info("Active Admission Count is : "+activeAdmissionCount);
//		Assert.assertEquals(activeAdmissionCount, 13, "Not Matched");
//	}
	
	@Test
	void checkActiveEpisodeFilterByStartDate() {
		logger.info("********Checking Active Episode Filter By Start Date*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getActiveAdmissionCount?startDate=2019-07-01 00:00:00.000");
		JsonPath jsonpath = response.jsonPath();
		int activeAdmissionCount = jsonpath.get("AdmissionCount");
		logger.info("Active Admission Count is : "+activeAdmissionCount);
		Assert.assertEquals(activeAdmissionCount, 9, "Not Matched");
	}
	
	@Test
	void checkActiveEpisodeFilterByPatientName() {
		logger.info("********Checking Active Episode Filter By Patient Name*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getActiveAdmissionCount?patientName=Abhi Avi Singh");
		JsonPath jsonpath = response.jsonPath();
		int activeAdmissionCount = jsonpath.get("AdmissionCount");
		logger.info("Active Admission Count is : "+activeAdmissionCount);
		Assert.assertEquals(activeAdmissionCount, 7, "Not Matched");
	}
	
	@Test
	void checkActiveEpisodeFilterByCurrentState() {
		logger.info("********Checking Active Episode Filter By Current State*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getActiveAdmissionCount?state=state1");
		JsonPath jsonpath = response.jsonPath();
		int activeAdmissionCount = jsonpath.get("AdmissionCount");
		logger.info("Active Admission Count is : "+activeAdmissionCount);
		Assert.assertEquals(activeAdmissionCount, 3, "Not Matched");
	}
	
	@Test
	void checkActiveEpisodeFilterByOwnerName() {
		logger.info("********Checking Active Episode Filter By Owner Name*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getActiveAdmissionCount?owner=owner1");
		JsonPath jsonpath = response.jsonPath();
		int activeAdmissionCount = jsonpath.get("AdmissionCount");
		logger.info("Active Admission Count is : "+activeAdmissionCount);
		Assert.assertEquals(activeAdmissionCount, 10, "Not Matched");
	}
	
	@AfterClass 
	void tearDown() {
		logger.info("********Finished TC001_Get_ActiveAdmissionCount*********");
	}
}

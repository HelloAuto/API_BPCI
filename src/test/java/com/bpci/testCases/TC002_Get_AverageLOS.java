package com.bpci.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bpci.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC002_Get_AverageLOS extends TestBase{
	
	@BeforeClass
	void getActiveAdmissionCount() throws InterruptedException {
		
		logger.info("********started TC002_Get_AverageLOS*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getAverageLOS");
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
	void checkAverageLOS() {
		try {
			logger.info("********Checking Average LOS*********");
			//String responseBody = response.getBody().asString();  //To validate single data.Below one will help to validate each data of response string. 
			JsonPath jsonpath = response.jsonPath();
			float i = jsonpath.get("AvgLOS");
			logger.info("Average LOS is ==> "+i);
		}
		catch(NullPointerException e) {
			logger.warn("Projected Cost Per Year is NULL.");
		}
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
		Assert.assertTrue(responseTime < 2000);
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
	void checkAvgLosForActiveStatus() {
		logger.info("*******Checking checkAvgLosForActiveStatus********* ");
		RestAssured.baseURI = uri;
		
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getAverageLos?status=active");
	    JsonPath json=response.jsonPath();
	    float average=json.get("AvgLOS");
	    logger.info(average);
	    Assert.assertEquals(average, 10f);	
	}
	@Test
	void checkAvgLosForInactiveStatus() {
		logger.info("*******Checking checkAvgLosForInactiveStatus********* ");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getAverageLos?status=inactive");
	    JsonPath json=response.jsonPath();
	    float average=json.get("AvgLOS");
	    logger.info(average);
	    Assert.assertEquals(average, 9f);	
		
	}
	@Test
	void checkAvgLosForEpisodeIdFilter() {
		logger.info("*******Checking checkAvgLosForEpisodeIdFilter********* ");
		RestAssured.baseURI = "http://192.61.99.40:8088/api/values/getAverageLOS?episodeId=339";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"?episodeId=339");
	    JsonPath json=response.jsonPath();
	    float average=json.get("AvgLOS");
	    logger.info(average);
	    Assert.assertEquals(average, 3f);	
		
	}
	@Test
	void checkAvgLosForClinicalEpisodeFilter() {
		logger.info("*******Checking checkAvgLosForClinicalEpisodeFilter********* ");
		RestAssured.baseURI = "http://192.61.99.40:8089/api/values/getAverageLOS";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"?status=active&clinicalepisode=name5");
	    JsonPath json=response.jsonPath();
	    float average=json.get("AvgLOS");
	    logger.info(average);
	    Assert.assertEquals(average, 10f);	
		
	}
	@Test
	void checkAvgLosForStartDateFilter() {
		logger.info("*******Checking checkAvgLosForStartDateFilter********* ");
		RestAssured.baseURI = "http://192.61.99.40:8088/api/values/getAverageLos";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"?status=inactive&startDate=2002-06-14 00:00:00.000");
	    JsonPath json=response.jsonPath();
	    float average=json.get("AvgLOS");
	    logger.info(average);
	    Assert.assertEquals(average, 3f);		
	}
	@Test
	void checkAvgLosForDischargeDate() {
		logger.info("*******Checking checkAvgLosForStartDateFilter********* ");
		RestAssured.baseURI = "http://192.61.99.40:8088/api/values/getAverageLos";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"?dischargeDate=2019-06-17 00:00:00.000");
	    JsonPath json=response.jsonPath();
	    float average=json.get("AvgLOS");
	    logger.info(average);
	    Assert.assertEquals(average, 13f);	
		
	}
	@Test
	void checkAvgLosForPatientFilter() {
		logger.info("*******Checking checkAvgLocForPatientFilter********* ");
		RestAssured.baseURI = "http://192.61.99.40:8089/api/values/getAverageLos";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"?patientName=Abhi Avi Singh");
	    JsonPath json=response.jsonPath();
	    float average=json.get("AvgLOS");
	    logger.info(average);
	    Assert.assertEquals(average, 10f);	
		
	}
	@Test
	void checkAvgLosForInitiator() {
		logger.info("*******Checking checkAvgLosForInitiator********* ");
		RestAssured.baseURI = "http://192.61.99.40:8089/api/values/getAverageLos";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"?initiator =Dr.Kabir");
	    JsonPath json=response.jsonPath();
	    float average=json.get("AvgLOS");
	    logger.info(average);
	    Assert.assertEquals(average, 10f);	
		
	}
	@Test
	void checkAvgLosForDischargeDisposition() {
		logger.info("*******Checking checkAvgLosForDischargeDisposition********* ");
		RestAssured.baseURI = "http://192.61.99.40:8089/api/values/getAverageLos";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"?dischargeDisposition=DD1&status=active");
	    JsonPath json=response.jsonPath();
	    float average=json.get("AvgLOS");
	    logger.info(average);
	    Assert.assertEquals(average, 10f);	
		
	}
	@Test
	void checkAvgLosForOwnerFilter() {
		logger.info("*******Checking checkAvgLosForOwnerFilter********* ");
		RestAssured.baseURI = "http://192.61.99.40:8089/api/values/getAverageLos";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"?owner=owner1");
	    JsonPath json=response.jsonPath();
	    float average=json.get("AvgLOS");
	    logger.info(average);
	    Assert.assertEquals(average, 10f);	
		
	}
	@Test
	void checkAvgLosForFacilityId() {
		logger.info("*******Checking checkAvgLosForFacilityId********* ");
		RestAssured.baseURI = "http://192.61.99.40:8089/api/values/getAverageLos";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"?facilityid=20");
	    JsonPath json=response.jsonPath();
	    float average=json.get("AvgLOS");
	    logger.info(average);
	    Assert.assertEquals(average, 10f);	
	}
		
		
	
	@AfterClass 
	void tearDown() {
		logger.info("********Finished TC002_Get_AverageLOS*********");
	}

}

package com.bpci.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bpci.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC005_Get_PercentageDischargeToSNF extends TestBase{
	
	@BeforeClass
	void getActiveAdmissionCount() throws InterruptedException {
		
		logger.info("********started TC005_Get_PercentageDischargeToSNF*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getDischargeSNF");
		Thread.sleep(3000);
	}

	@Test
	void checkStatusCode() {
		
		logger.info("********Checking Status Code*********");
		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==> "+statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkPercentageDischargeToSNF() {
		try {
			logger.info("********Checking Percentage Discharge to SNF*********");
			//String responseBody = response.getBody().asString();  //To validate single data.Below one will help to validate each data of response string. 
			JsonPath jsonpath = response.jsonPath();
			int i = jsonpath.get("DischargeSNF");
			logger.info("Percentage Discharge to SNF is ==> "+i);
		}
		catch (NullPointerException e) {
			//e.printStackTrace();
			logger.warn("Percentage Discharge to SNF is NULL. API is Down.");
			
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
	
	@AfterClass 
	void tearDown() {
		logger.info("********Finished TC005_Get_PercentageDischargeToSNF*********");
	}

}

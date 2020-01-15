package com.bpci.practice.example;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.bpci.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC023_Post_sendPasswordRecoveryMail extends TestBase{
	
	@BeforeClass
	void getEpisodeFacilityDetails() throws InterruptedException {
		
		logger.info("********started TC023_Post_sendPasswordRecoveryMail*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.POST,"/sendPasswordRecoveryMail?emailAddress=snehakumari275@gmail.com");
		Thread.sleep(1000);
		//responseTest=httpRequest.request(Method.POST,"/sendPasswordRecoveryMail?emailAddress=xyz@gmail.com");
	}

	@Test
	void checkStatusCode() {
		
		logger.info("********Checking Status Code*********");
		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==> "+statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void mailSentVerification() {
		logger.info("********Verifying login with valid credentials*********");
		response = httpRequest.request(Method.POST,"/sendPasswordRecoveryMail?emailAddress=snehakumari275@gmail.com");
		JsonPath jsonpath = response.jsonPath();
		String message = jsonpath.get("status");
		System.out.println(message);
		Assert.assertEquals(message, "Mail sent Successfully");

	}
	
	@Test
	void mailNotSent()
	{
		logger.info("********Verifying login with invalid credentials*********");
		response = httpRequest.request(Method.POST,"/sendPasswordRecoveryMail?emailAddress=xyz@gmail.com");
		JsonPath jsonpath = response.jsonPath();
		String msg = jsonpath.get("status");
		System.out.println(msg);
		Assert.assertEquals(msg, "Email Id Doesn't Exists");	
	}
	
	@Test
	void checkStatusLine() {
		logger.info("********Checking Status Line*********");
		String statusLine = response.getStatusLine();
		logger.info("Status Line is ==> "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
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
		logger.info("********Finished TC023_Post_sendPasswordRecoveryMail********");
	}
}
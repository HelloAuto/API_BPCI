package com.bpci.practice.example;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bpci.base.TestBase;
import com.mongodb.diagnostics.logging.Logger;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC008_Get_ClinicalEpisodeType extends TestBase{
	
	@BeforeClass
	void getClinicalEpisodeType() throws InterruptedException {
		
		logger.info("********started TC008_Get_ClinicalEpisodeType*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getClinicalEpisodeType?drg=471");
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
	void checkClinicalEpisodeDetails() {
		logger.info("********Checking Clinical Episode Details*********"); 
		JsonPath jsonpath = response.jsonPath();
		Map<String, Object> clinicalEpisodeDetails = jsonpath.get();
 		logger.info(clinicalEpisodeDetails);
 		int clinicalEID = (Integer) clinicalEpisodeDetails.get("ClinicalEpisodeId");
 		Assert.assertEquals(clinicalEID, 75, "Not Matched");
 		logger.info("Clinical Episode Id is :"+clinicalEID);
 		
 		String episodeType=(String) clinicalEpisodeDetails.get("ClinicalEpisodeType");
 		Assert.assertEquals(episodeType, "CERVICAL SPINAL FUSION");
 		logger.info("clinical EpisodeDetails is:::"+episodeType);
 		
		
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
		logger.info("********Finished TC001_Get_ActiveAdmissionCount*********");
	}

}

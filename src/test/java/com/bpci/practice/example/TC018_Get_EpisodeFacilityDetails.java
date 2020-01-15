package com.bpci.practice.example;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bpci.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC018_Get_EpisodeFacilityDetails extends TestBase{
	
	@BeforeClass
	void getEpisodeFacilityDetails() throws InterruptedException {
		
		logger.info("********started TC018_Get_EpisodeFacilityDetails*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"getEpisodeFacilityDetails?episodeId=990");
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
	void checkValidEpisodeFacilityDetails() {
		logger.info("********Checking Episode Facility Details*********");
		//String responseBody = response.getBody().asString();  //To validate single data.Below one will help to validate each data of response string. 
		JsonPath jsonpath = response.jsonPath();
		Map<String, Object > EpisodeFacilityDetails = jsonpath.getMap("FacilityDetails");
		System.out.println(EpisodeFacilityDetails);
		
		//Verifying Facility Id
		int facilityId = (Integer) EpisodeFacilityDetails.get("id");
		logger.info("Facility id is ==> "+facilityId);
		Assert.assertEquals(facilityId, 2);
		
		
		//Verifying Facility name
		String facilityName = (String) EpisodeFacilityDetails.get("name");
		logger.info("Facility name is ==> "+facilityName);
		Assert.assertEquals(facilityName, "COCBR");
		
		//Verifying Facility city
//		String facilityCity = (String) EpisodeFacilityDetails.get("city");
//		logger.info("Facility city is ==> "+facilityCity);
//		Assert.assertNull(facilityCity);
		

		//Verifying attending Physician name
		String dischrgDisposition = (String) EpisodeFacilityDetails.get("dischargeDisposition");
		logger.info("Discharge disposition is ==> "+dischrgDisposition);
		Assert.assertEquals(dischrgDisposition, "HOM");
		

	}
	
//	@Test
//	void checkInValidEpisodeFacilityDetails() {
//		logger.info("********Checking Episode Facility Details for Invalid Episode*********");
//		//String responseBody = response.getBody().asString();  //To validate single data.Below one will help to validate each data of response string. 
//		response = httpRequest.request(Method.GET,"getEpisodeFacilityDetails?episodeId=000000");
//		JsonPath jsonpath = response.jsonPath();
//		Map<String, Object > EpisodeFacilityDetails = jsonpath.getMap("FacilityDetails");
//		System.out.println(EpisodeFacilityDetails);
//		
//		//Verifying Facility Id
//		int facilityId = (Integer) EpisodeFacilityDetails.get("id");
//		logger.info("Facility id is ==> "+facilityId);
//		Assert.assertEquals(facilityId, 0);
//		
//		//Verifying Facility name
//		String facilityName = (String) EpisodeFacilityDetails.get("name");
//		logger.info("Facility name is ==> "+facilityName);
//		Assert.assertEquals(facilityName, null);
//		
//		//Verifying Facility city
//		String facilityCity = (String) EpisodeFacilityDetails.get("city");
//		logger.info("Facility city is ==> "+facilityCity);
//		Assert.assertNull(facilityCity, null);
//		
//
//		//Verifying attending Physician name
//		String dischrgDisposition = (String) EpisodeFacilityDetails.get("dischargeDisposition");
//		logger.info("Discharge disposition is ==> "+dischrgDisposition);
//		Assert.assertEquals(dischrgDisposition, null);
//	}
	
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
		logger.info("********Finished TC018_Get_EpisodeFacilityDetails********");
	}
}



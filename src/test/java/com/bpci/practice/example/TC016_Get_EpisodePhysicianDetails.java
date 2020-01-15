package com.bpci.practice.example;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bpci.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC016_Get_EpisodePhysicianDetails extends TestBase{
	
	@BeforeClass
	void getEpisodePhysicianDetails() throws InterruptedException {
		
		logger.info("********started TC016_Get_EpisodePhysicianDetails*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getEpisodePhysicianDetails?episodeId=990");
		Thread.sleep(1000);
	}

	@Test
	void checkStatusCode() {
		
		logger.info("********Checking Status Code*********");
		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==> "+statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test (priority=1)
	void checkValidEpisodePhysicianDetails() {
		logger.info("********Checking Episode Physician Details*********");
		//String responseBody = response.getBody().asString();  //To validate single data.Below one will help to validate each data of response string. 
		JsonPath jsonpath = response.jsonPath();
		Map<String, Object > EpisodePhysicianDetails = jsonpath.getMap("PhysicianDetails");
		System.out.println(EpisodePhysicianDetails);
		
		//Verifying Physician Id
		
		int physicianId = (Integer) EpisodePhysicianDetails.get("id");
		logger.info("Physician id is ==> "+physicianId);
		Assert.assertEquals(physicianId, 432);
		
		
		//Verifying Physician name
		String physicianName = (String) EpisodePhysicianDetails.get("name");
		 logger.info("Physician name is ==> "+physicianName);
		Assert.assertEquals(physicianName, "PATEL NIRAV");
		
		//Verifying Physician age
//		String physnAge = (String) EpisodePhysicianDetails.get("age");
//		logger.info("Physician age is ==> "+physnAge);
//		Assert.assertNull(physnAge);
		

		//Verifying attending Physician name
		String attendingPhysn = (String) EpisodePhysicianDetails.get("attendingPhysician");
		logger.info("Attending Physician name is ==> "+attendingPhysn);
		Assert.assertEquals(attendingPhysn, "PATEL NIRAV");
		
		//Verifying consulting Physician name
		String consultingPhysn = (String) EpisodePhysicianDetails.get("consultingPhysician");
		logger.info("Consulting Physician name is ==> "+consultingPhysn);
		Assert.assertEquals(consultingPhysn, "GULLAPALLI SATYA");		
		
		//Verifying attending Physician name
		String referringPhysn = (String) EpisodePhysicianDetails.get("referringPhysician");
		logger.info("Reffering Physician name is ==> "+referringPhysn);
		Assert.assertEquals(referringPhysn, "REFERRED SELF");	
	}
	
//	@Test
//	void checkInvalidEpisodePhysicianDetails() {
//		logger.info("********Checking Episode Physician Details for Invalid EpisodeId*********");
//		//response = httpRequest.request(Method.GET,"getEpisodePhysicianDetails?episodeId=");
//		response = httpRequest.request(Method.GET,"/getEpisodePhysicianDetails?episodeId=00000");
//		JsonPath jsonpath = response.jsonPath();
//		Map<String, Object > InvalidEpisodePhysicianDetails = jsonpath.getMap("PhysicianDetails");
//		System.out.println(InvalidEpisodePhysicianDetails);
//		
//		//Verifying Physician Id
//		int invalidPhysicianId = (Integer) InvalidEpisodePhysicianDetails.get("id");
//		logger.info("Physician id is ==> "+invalidPhysicianId);
//		Assert.assertEquals(invalidPhysicianId, 0);
//		
//		
//		//Verifying Physician name
//		String invalidPhysicianName = (String) InvalidEpisodePhysicianDetails.get("name");
//		logger.info("Physician name is ==> "+invalidPhysicianName);
//		Assert.assertEquals(invalidPhysicianName, null);
//		
//		//Verifying Physician age
////		String physnAge = (String) EpisodePhysicianDetails.get("age");
////		logger.info("Physician age is ==> "+physnAge);
////		Assert.assertNull(physnAge);
//		
//
//		//Verifying attending Physician name
//		String invalidAttendingPhysn = (String) InvalidEpisodePhysicianDetails.get("attendingPhysician");
//		logger.info("Attending Physician name is ==> "+invalidAttendingPhysn);
//		Assert.assertEquals(invalidAttendingPhysn, null);
//		
//		//Verifying consulting Physician name
//		String invalidConsultingPhysn = (String) InvalidEpisodePhysicianDetails.get("consultingPhysician");
//		logger.info("Consulting Physician name is ==> "+invalidConsultingPhysn);
//		Assert.assertEquals(invalidConsultingPhysn, null);		
//		
//		//Verifying attending Physician name
//		String invalidReferringPhysn = (String) InvalidEpisodePhysicianDetails.get("referringPhysician");
//		logger.info("Reffering Physician name is ==> "+invalidReferringPhysn);
//		Assert.assertEquals(invalidReferringPhysn, null);	
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
		logger.info("********Finished TC016_Get_EpisodePhysicianDetails*********");
	}
}


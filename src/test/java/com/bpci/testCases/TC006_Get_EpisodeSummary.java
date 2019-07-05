package com.bpci.testCases;

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

public class TC006_Get_EpisodeSummary extends TestBase{
	@BeforeClass
	void getEpisodeSummary() throws InterruptedException {
		
		logger.info("********started TC006_Get_EpisodeSummary*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getEpisodeSummary");
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
	void checkEpisodeSummary() {
		try {
			logger.info("********Checking Episode Summary*********"); 
			JsonPath jsonpath = response.jsonPath();
			List<Map<String, Object>> responseBody = jsonpath.getList("listEpisodeSummary");
			
			for(int i=0; i<responseBody.size(); i++) {
				logger.info("Episode ID for Episode "+(i+1)+" is : "+responseBody.get(i).get("episodeId"));
			}
			
			for(int i=0; i<responseBody.size(); i++) {
				logger.info("Clinical Episode for Episode "+(i+1)+" is : "+responseBody.get(i).get("clinicalEpisode"));
			}
			
			for(int i=0; i<responseBody.size(); i++) {
				logger.info("Start Date for Episode "+(i+1)+" is : "+responseBody.get(i).get("startdate"));
			}
			
			for(int i=0; i<responseBody.size(); i++) {
				logger.info("Discharge Date for Episode "+(i+1)+" is : "+responseBody.get(i).get("dischargeDate"));
			}
			
			for(int i=0; i<responseBody.size(); i++) {
				logger.info("Admit Date for Episode "+(i+1)+" is : "+responseBody.get(i).get("admitDate"));
			}
			
			for(int i=0; i<responseBody.size(); i++) {
				logger.info("LOS for Episode "+(i+1)+" is : "+responseBody.get(i).get("los"));
			}
			
			for(int i=0; i<responseBody.size(); i++) {
				logger.info("Patient Name for Episode "+(i+1)+" is : "+responseBody.get(i).get("patientName"));
			}
			
			for(int i=0; i<responseBody.size(); i++) {
				logger.info("Discharge Disposition for Episode "+(i+1)+" is : "+responseBody.get(i).get("dischargeDisposition"));
			}
			
			for(int i=0; i<responseBody.size(); i++) {
				logger.info("State for Episode "+(i+1)+" is : "+responseBody.get(i).get("state"));
			}
			
			for(int i=0; i<responseBody.size(); i++) {
				logger.info("Owner for Episode "+(i+1)+" is : "+responseBody.get(i).get("owner"));
			}
			
			for(int i=0; i<responseBody.size(); i++) {
				logger.info("Initiator for Episode "+(i+1)+" is : "+responseBody.get(i).get("initiator"));
			}
			
			for(int i=0; i<responseBody.size(); i++) {
				logger.info("Number of Visits for Episode "+(i+1)+" is : "+responseBody.get(i).get("numberOfVisits"));
			}
			
			for(int i=0; i<responseBody.size(); i++) {
				logger.info("Readmission for Episode "+(i+1)+" is : "+responseBody.get(i).get("readmission"));
			}
			
			for(int i=0; i<responseBody.size(); i++) {
				logger.info("Facility Name for Episode "+(i+1)+" is : "+responseBody.get(i).get("facilityName"));
			}
			
			for(int i=0; i<responseBody.size(); i++) {
				logger.info("Severity for Episode "+(i+1)+" is : "+responseBody.get(i).get("severity"));
			}
			
			for(int i=0; i<responseBody.size(); i++) {
				logger.info("End Date for Episode "+(i+1)+" is : "+responseBody.get(i).get("endDate"));
			}
			
			for(int i=0; i<responseBody.size(); i++) {
				logger.info("Observation End Date for Episode "+(i+1)+" is : "+responseBody.get(i).get("observationEndDate"));
			}
			
			for(int i=0; i<responseBody.size(); i++) {
				logger.info("Total LOS for Episode "+(i+1)+" is : "+responseBody.get(i).get("totalLos"));
			}
			
			for(int i=0; i<responseBody.size(); i++) {
				logger.info("Status for Episode "+(i+1)+" is : "+responseBody.get(i).get("status"));
			}
			
		}
		catch (Exception e) {
			//e.printStackTrace();
			logger.warn(e);
			
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
		Assert.assertTrue(Integer.parseInt(contentLength) < 2500);
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
		logger.info("********Finished TC006_Get_Episode_Summary*********");
	}


}

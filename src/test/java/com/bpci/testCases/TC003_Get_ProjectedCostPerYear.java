package com.bpci.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bpci.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC003_Get_ProjectedCostPerYear extends TestBase{
	//private static final double NULL = 0;

	@BeforeClass
	void getActiveAdmissionCount() throws InterruptedException {
		
		logger.info("********started TC003_Get_ProjectedCostPerYear*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getProjectedCost");
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
	void checkProjectedCost() {
		try {
			logger.info("********Checking Projected Cost*********");
			//String responseBody = response.getBody().asString();  //To validate single data.Below one will help to validate each data of response string. 
			JsonPath jsonpath = response.jsonPath();
			float i = jsonpath.get("ProjectedCost");
			logger.info("Projected Cost Per Year is ==> "+i);
		}
		catch (NullPointerException e) {
			//e.printStackTrace();
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
	void getProjectCost() {
		logger.info("*******Checking getProjectCost********* ");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getProjectedCost");
	    JsonPath json=response.jsonPath();
	    float projectedCost=json.get("ProjectedCost");
	    logger.info(projectedCost);
	    Assert.assertEquals(projectedCost, 1531300f);	
	}
	@Test
	void getProjectCostwithEpisodeFilter() {
		logger.info("*******Checking getProjectCostwithEpisodeFilter********* ");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getProjectedCost?episodeid=330&status=inactive");
	    JsonPath json=response.jsonPath();
	    float projectedCost=json.get("ProjectedCost");
	    logger.info(projectedCost);
	    Assert.assertEquals(projectedCost, 400f);	
		
	}
	@Test
	void getProjectCostWithEpisodeTypeFilter() {
		logger.info("*******Checking getProjectCostWithEpisodeTypeFilter********* ");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getProjectedCost?status=inactive");
	    JsonPath json=response.jsonPath();
	    float projectedCost=json.get("ProjectedCost");
	    logger.info(projectedCost);
	    Assert.assertEquals(projectedCost, 16900f);	
		
	}
	@Test
	void getProjectCostWithDischargeDateFilter() {
		logger.info("*******Checking getProjectCostWithDischargeDateFilter********* ");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getProjectedCost?status=active&dischargeDate=2019-06-17 00:00:00.000");
	    JsonPath json=response.jsonPath();
	    float projectedCost=json.get("ProjectedCost");
	    logger.info(projectedCost);
	    Assert.assertEquals(projectedCost, 5200f);	
	}
	@Test
	void getProjectCostWithLoSFilter() {
		logger.info("*******Checking getProjectCostWithLoSFilter********* ");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getProjectedCost?status=active&LOS=10");
	    JsonPath json=response.jsonPath();
	    float projectedCost=json.get("ProjectedCost");
	    logger.info(projectedCost);
	    Assert.assertEquals(projectedCost, 22500f);	
	}
	@Test
	void getProjectCostWithInitiatorNameFilter() {
		logger.info("*******Checking getProjectCostWithInitiatorNameFilter********* ");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getProjectedCost?status=active&initiator=SIDHU SIMARJIT");
	    JsonPath json=response.jsonPath();
	    float projectedCost=json.get("ProjectedCost");
	    logger.info(projectedCost);
	    Assert.assertEquals(projectedCost, 30150f);	
		
	}
	@Test
	void getProjectCostWithDischargeDispositionFilter() {
		logger.info("*******Checking getProjectCostWithDischargeDispositionFilter********* ");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getProjectedCost?status=active&dischargeDisposition=HHS");
	    JsonPath json=response.jsonPath();
	    float projectedCost=json.get("ProjectedCost");
	    logger.info(projectedCost);
	    Assert.assertEquals(projectedCost, 62250f);	
	}
	@Test
	void getProjectCostWithFacilityName() {
		logger.info("*******Checking getProjectCostWithFacilityName********* ");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getProjectedCost?status=active&facilityName=COCBP");
	    JsonPath json=response.jsonPath();
	    float projectedCost=json.get("ProjectedCost");
	    logger.info(projectedCost);
	    Assert.assertEquals(projectedCost, 62250f);	
	}
	@Test
	void getProjectCostWithStartDateFilter() {
		logger.info("*******Checking getProjectCostWithStartDateFilter********* ");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getProjectedCost?status=inactive&startDate=2002-05-28 00:00:00.000");
	    JsonPath json=response.jsonPath();
	    float projectedCost=json.get("ProjectedCost");
	    logger.info(projectedCost);
	    Assert.assertEquals(projectedCost, 20000f);	
	}
	@Test
	void getProjectCostWithMemberFilter() {
		logger.info("*******Checking getProjectCostWithMemberFilter********* ");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getProjectedCost?status=inactive&patientName=PALUMBO  AGATHA");
	    JsonPath json=response.jsonPath();
	    float projectedCost=json.get("ProjectedCost");
	    logger.info(projectedCost);
	    Assert.assertEquals(projectedCost, 250f);	
	}
	@Test
	void getProjectCostWithOwnerFilter() {
		logger.info("*******Checking getProjectCostWithOwnerFilter********* ");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getProjectedCost?status=inactive&owner=user1");
	    JsonPath json=response.jsonPath();
	    float projectedCost=json.get("ProjectedCost");
	    logger.info(projectedCost);
	    Assert.assertEquals(projectedCost, 16900f);	
	}
	@Test
	void getProjectCoseWithDischargeDateFilter() {
		logger.info("*******Checking getProjectCoseWithDischargeDateFilter********* ");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"getProjectedCost?status=inactive&dischargedate=2019-06-17 00:00:00.000");
	    JsonPath json=response.jsonPath();
	    float projectedCost=json.get("ProjectedCost");
	    logger.info(projectedCost);
	    Assert.assertEquals(projectedCost, 4850f);	
	}
	@Test
	void getProjectCostWithEndDateFilter() {
		logger.info("*******Checking getProjectCostWithEndDateFilter********* ");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getProjectedCost?status=active&observationEndDate=2019-09-19 00:00:00.000");
	    JsonPath json=response.jsonPath();
	    float projectedCost=json.get("ProjectedCost");
	    logger.info(projectedCost);
	    Assert.assertEquals(projectedCost, 1250f);	
	}
	@Test
	void getProjectCostWithObservationEndDate() {
		logger.info("*******Checking getProjectCostWithObservationEndDate********* ");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getProjectedCost?status=active&observationEndDate=2019-09-19 00:00:00.000");
	    JsonPath json=response.jsonPath();
	    float projectedCost=json.get("ProjectedCost");
	    logger.info(projectedCost);
	    Assert.assertEquals(projectedCost, 4400f);	
	}
	@AfterClass 
	void tearDown() {
		logger.info("********Finished TC003_Get_ProjectedCostPerYear*********");
	}



}

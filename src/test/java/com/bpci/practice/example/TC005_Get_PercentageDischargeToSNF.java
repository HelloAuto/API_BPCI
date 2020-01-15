package com.bpci.practice.example;

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
			Assert.assertEquals(i, 67);
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
	
	@Test
	void validatePercentageDischargeToSNFFilterByEpisodeId() {
		logger.info("\"********Checking Percentage Discharge to SNF Filter By Episode ID*********\"");
		RestAssured.baseURI=uri;
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/getDischargeSNF?episodeId=14");
		JsonPath jsonresponse = response.jsonPath();
		int i = jsonresponse.get("DischargeSNF");
		logger.info("Discharge to SNF is : "+i);
		Assert.assertEquals(i, 100);
	}
	
	@Test
	void validatePercentageDischargeToSNFFilterByEpisodeType() {
		logger.info("\"********Checking Percentage Discharge to SNF Filter By Episode Type*********\"");
		RestAssured.baseURI=uri;
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/getDischargeSNF?episodeType_fk=86");
		JsonPath jsonresponse = response.jsonPath();
		int i = jsonresponse.get("DischargeSNF");
		logger.info("Discharge to SNF is : "+i);
		Assert.assertEquals(i, 67);
	}
	
	@Test
	void validatePercentageDischargeToSNFFilterByStartDate() {
		logger.info("\"********Checking Percentage Discharge to SNF Filter By Start Date*********\"");
		RestAssured.baseURI=uri;
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/getDischargeSNF?startdate=2019-04-01 00:00:00.000");
		JsonPath jsonresponse = response.jsonPath();
		int i = jsonresponse.get("DischargeSNF");
		logger.info("Discharge to SNF is : "+i);
		Assert.assertEquals(i, 100);
	}
	
	@Test
	void validatePercentageDischargeToSNFFilterByAdmitDate() {
		logger.info("\"********Checking Percentage Discharge to SNF Filter By Admit Date*********\"");
		RestAssured.baseURI=uri;
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/getDischargeSNF?admitDate=2019-02-10 00:00:00.000");
		JsonPath jsonresponse = response.jsonPath();
		int i = jsonresponse.get("DischargeSNF");
		logger.info("Discharge to SNF is : "+i);
		Assert.assertEquals(i, 50);
	}
	
	@Test
	void validatePercentageDischargeToSNFFilterByDischargeDate() {
		logger.info("\"********Checking Percentage Discharge to SNF Filter By Discharge Date*********\"");
		RestAssured.baseURI=uri;
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/getDischargeSNF?dischargeDate=2019-02-20 00:00:00.000");
		JsonPath jsonresponse = response.jsonPath();
		int i = jsonresponse.get("DischargeSNF");
		logger.info("Discharge to SNF is : "+i);
		Assert.assertEquals(i, 50);
	}
	
	@Test
	void validatePercentageDischargeToSNFFilterByLOS() {
		logger.info("\"********Checking Percentage Discharge to SNF Filter By LOS*********\"");
		RestAssured.baseURI=uri;
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/getDischargeSNF?los=10");
		JsonPath jsonresponse = response.jsonPath();
		int i = jsonresponse.get("DischargeSNF");
		logger.info("Discharge to SNF is : "+i);
		Assert.assertEquals(i, 67);
	}
	
	@Test
	void validatePercentageDischargeToSNFFilterByPatientName() {
		logger.info("\"********Checking Percentage Discharge to SNF Filter By Patient Name*********\"");
		RestAssured.baseURI=uri;
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/getDischargeSNF?patientName=fullname5");
		JsonPath jsonresponse = response.jsonPath();
		int i = jsonresponse.get("DischargeSNF");
		logger.info("Discharge to SNF is : "+i);
		Assert.assertEquals(i, 100);
	}
	
	@Test
	void validatePercentageDischargeToSNFFilterByDischargeDisposition() {
		logger.info("\"********Checking Percentage Discharge to SNF Filter By Discharge Disposition*********\"");
		RestAssured.baseURI=uri;
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/getDischargeSNF?dischargeDisposition=DD1");
		JsonPath jsonresponse = response.jsonPath();
		int i = jsonresponse.get("DischargeSNF");
		logger.info("Discharge to SNF is : "+i);
		Assert.assertEquals(i, 100);
	}
	
	@Test
	void validatePercentageDischargeToSNFFilterByOwnerName() {
		logger.info("\"********Checking Percentage Discharge to SNF Filter By Owner Name*********\"");
		RestAssured.baseURI=uri;
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/getDischargeSNF?owner=owner1");
		JsonPath jsonresponse = response.jsonPath();
		int i = jsonresponse.get("DischargeSNF");
		logger.info("Discharge to SNF is : "+i);
		Assert.assertEquals(i, 100);
	}
	
	@Test
	void validatePercentageDischargeToSNFFilterByFacilityName() {
		logger.info("\"********Checking Percentage Discharge to SNF Filter By Facility Name*********\"");
		RestAssured.baseURI=uri;
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/getDischargeSNF?facilityName=name2");
		JsonPath jsonresponse = response.jsonPath();
		int i = jsonresponse.get("DischargeSNF");
		logger.info("Discharge to SNF is : "+i);
		Assert.assertEquals(i, 50);
	}
	
	@Test
	void validatePercentageDischargeToSNFFilterByEndDate() {
		logger.info("\"********Checking Percentage Discharge to SNF Filter By End Date*********\"");
		RestAssured.baseURI=uri;
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/getDischargeSNF?endDate=2019-08-30 00:00:00.000");
		JsonPath jsonresponse = response.jsonPath();
		int i = jsonresponse.get("DischargeSNF");
		logger.info("Discharge to SNF is : "+i);
		Assert.assertEquals(i, 100);
	}
	
	@Test
	void validatePercentageDischargeToSNFFilterByObservationDate() {
		logger.info("\"********Checking Percentage Discharge to SNF Filter By Observation Date*********\"");
		RestAssured.baseURI=uri;
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/getDischargeSNF?observation_End=2019-10-29 00:00:00.000");
		JsonPath jsonresponse = response.jsonPath();
		int i = jsonresponse.get("DischargeSNF");
		logger.info("Discharge to SNF is : "+i);
		Assert.assertEquals(i, 67);
	}
	
	@Test
	void validatePercentageDischargeToSNFFilterByState() {
		logger.info("\"********Checking Percentage Discharge to SNF Filter By State*********\"");
		RestAssured.baseURI=uri;
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/getDischargeSNF?state=state1");
		JsonPath jsonresponse = response.jsonPath();
		int i = jsonresponse.get("DischargeSNF");
		logger.info("Discharge to SNF is : "+i);
		Assert.assertEquals(i, 67);
	}
	
	@Test
	void validatePercentageDischargeToSNFFilterByStatus() {
		logger.info("\"********Checking Percentage Discharge to SNF Filter By status*********\"");
		RestAssured.baseURI=uri;
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/getDischargeSNF?status=ACTIVE");
		JsonPath jsonresponse = response.jsonPath();
		int i = jsonresponse.get("DischargeSNF");
		logger.info("Discharge to SNF is : "+i);
		Assert.assertEquals(i, 67);
	}
	
	@Test
	void validatePercentageDischargeToSNFFilterByInitiator() {
		logger.info("\"********Checking Percentage Discharge to SNF Filter By Initiator*********\"");
		RestAssured.baseURI=uri;
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/getDischargeSNF?initiator_Fullname=Dr.V");
		JsonPath jsonresponse = response.jsonPath();
		int i = jsonresponse.get("DischargeSNF");
		logger.info("Discharge to SNF is : "+i);
		Assert.assertEquals(i, 67);
	}
	
	@Test
	void validatePercentageDischargeToSNFFilterByDefault() {
		logger.info("\"********Checking Percentage Discharge to SNF Filter By Default*********\"");
		RestAssured.baseURI=uri;
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/getDischargeSNF?status=active");
		JsonPath jsonresponse = response.jsonPath();
		int i = jsonresponse.get("DischargeSNF");
		response=httpRequest.request(Method.GET,"/getDischargeSNF");
		JsonPath jsonpathresp = response.jsonPath();
		int j =jsonpathresp.get("DischargeSNF");
		logger.info("Discharge to SNF is : "+i);
		logger.info("Default SNF is : "+j);
		//Assert.assert
		Assert.assertEquals(i, j);
		if(i==j) {
			logger.info("Pass");
		}
		else 
			logger.warn("Fail");
		
	}
	
	@Test
	void validatePercentageDischargeToSNFFilterByInactive() {
		logger.info("\"********Checking Percentage Discharge to SNF Filter By Inactive*********\"");
		RestAssured.baseURI=uri;
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/getDischargeSNF?status=inactive");
		JsonPath jsonresponse = response.jsonPath();
		int i = jsonresponse.get("DischargeSNF");
		logger.info("Discharge to SNF is : "+i);
		Assert.assertEquals(i, 80);
	}
	
	@AfterClass 
	void tearDown() {
		logger.info("********Finished TC005_Get_PercentageDischargeToSNF*********");
	}

}

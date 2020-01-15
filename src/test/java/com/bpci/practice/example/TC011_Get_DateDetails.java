package com.bpci.practice.example;

import java.util.Date;
import java.util.Locale;

import static org.testng.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bpci.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC011_Get_DateDetails extends TestBase{
	
	
	@BeforeClass
	void getActiveAdmissionCount() throws InterruptedException {
		
		logger.info("********started TC011_Get_DateDetails*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getDateDetails?admitdate=01-09-2019");
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
	void checkDateDetails() throws ParseException {
		
		logger.info("********Checking Admision Count*********");
		
		//To validate the response body
		JsonPath jsonpath = response.jsonPath();
		Map<String, Object > dateDetails = jsonpath.get();
		
		//Validating admit date
	    String admitDate=(String) dateDetails.get("AdmitDate");
	    Assert.assertEquals(admitDate, "01-09-2019");
	    
	    //Parsing admit date from String to date
	    SimpleDateFormat date = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
	    Date admDate = (Date) date.parse(admitDate);
		logger.info("Admit Date is:" +admDate);
		
		//Validating start date is same as admit date
		String startDate= (String) dateDetails.get("StartDate");
		Assert.assertEquals(startDate, "01-09-2019");
		
		//Parsing start date from String to date
		SimpleDateFormat date1 = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
	    Date strDate = (Date) date1.parse("01-09-2019");
	    Assert.assertEquals(admDate, strDate);
	    logger.info("Start date is:" +strDate);
	    
		//Validating end date is start date + 90
		String endDate=(String) dateDetails.get("EndDate");
		SimpleDateFormat date2 = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
	    Date enDate = (Date) date2.parse(endDate);
		logger.info("End Date is:" +enDate);
		long diffendTostart = Math.abs(enDate.getTime() - strDate.getTime());
		long diff = TimeUnit.DAYS.convert(diffendTostart, TimeUnit.MILLISECONDS);
		System.out.println("Difference between start date and end date is "+diff);
		assertEquals(diff, 90);

		
		//Validating observation date is start date + 120
		String observationDate=(String) dateDetails.get("ObservationDate");
		SimpleDateFormat date3 = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
	    Date obsDate = (Date) date3.parse(observationDate);
		logger.info("Observation Date is:" +obsDate);
		long diffobsTostart = Math.abs(obsDate.getTime() - strDate.getTime());
		long diff2 = TimeUnit.DAYS.convert(diffobsTostart, TimeUnit.MILLISECONDS);
		System.out.println("Difference between start date and observation date is "+diff2);
		assertEquals(diff2, 120);
		
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
		logger.info("********Finished TC011_Get_DateDetails*********");
	}
}

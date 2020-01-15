package com.bpci.practice.example;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bpci.base.TestBase;
import com.bpci.utilities.DBConnect;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC014_Get_Initiator_Name extends TestBase {
	@BeforeClass
	void getInitiatorName() throws InterruptedException {
		
		logger.info("********started TC014_Get_Initiator_Name*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getInitiatorNames");
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
	void checkEpisodeSummary() throws Exception {
		
			logger.info("********Checking Initiator details *********"); 
			JsonPath jsonpath = response.jsonPath();
			List<Map<String, Object>> responseBody = jsonpath.get("InitiatorDetails");
			
			//responseBody=jsonpath.get();
			for(int i=0; i<responseBody.size(); i++) {
				if((Integer)responseBody.get(i).get("Id")==2) {
					logger.info("Initiator ID is : "+responseBody.get(i).get("Id"));
					logger.info("Initiator Name is : "+responseBody.get(i).get("Name"));
					Assert.assertEquals(responseBody.get(i).get("Id"), 2, "Id not matched");
					//Assert.assertEquals(responseBody.get(i).get("Name"), "ZEIDAN FADY", "Name not matched");
				Assert.assertEquals(responseBody.get(i).get("Name"), DBConnect.connectDB("tbl_Initiator", "initiatorid", 2));
				System.out.println(DBConnect.connectDB("tbl_Initiator", "initiatorid", 2));
				}
				
		}
//		catch (Exception e) {
//			//e.printStackTrace();
//			logger.warn(e);
//			
//		}
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

	

		




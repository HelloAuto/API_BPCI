package com.bpci.testCases;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bpci.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;

public class TC015_Post_Insert_Episode_Details extends TestBase {
	@BeforeClass
	void getClinicalEpisodeType() throws InterruptedException {
		
		logger.info("********TC015_Post_Insert_Episode_Details*********");
		RestAssured.baseURI = uri;
		
		PreemptiveBasicAuthScheme authScheme=new PreemptiveBasicAuthScheme();
		authScheme.setUserName("User1");
		authScheme.setPassword("pass123");
		RestAssured.authentication=authScheme; 
		
		httpRequest = RestAssured.given();
		JSONObject requestParam=new JSONObject();
		requestParam.put("memberFirstName","Abhi");
		requestParam.put("memberLastName", "Singh") ;
		requestParam.put("memberMiddleName", "Avi");
		requestParam.put("memberFullName", "Abhi Avi Singh");
		requestParam.put("admittingPhyName", "4");
		requestParam.put("episodeCreatedBy", "Dev");
		requestParam.put("facilityName", "Appolo");
		requestParam.put("OwnerId", "1");
		requestParam.put("startDate", "2019-07-01 00:00:00.000");
		requestParam.put("endDate", "2019-09-29 00:00:00.000");
		requestParam.put("observationDate", "2019-10-29 00:00:00.000");
		requestParam.put("clinicalEpisodeId", "5");
		requestParam.put("referringPhyName", "Jake");
		requestParam.put("attendingPhyName", "Jill");
		requestParam.put("consultingPhyName", "Jackson");
		requestParam.put("admitDate", "2019-07-01 00:00:00.000");
		requestParam.put("diagnosisCode", "DC1");
		
		
		httpRequest.header("Content-Type","application/json");
	
		httpRequest.body(requestParam.toJSONString());
		
		response = httpRequest.request(Method.POST,"/InsertEpisodeDetails");
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
	void validateEpisodeDetails() {
		logger.info("********started TC015_Post_Insert_Episode_Details*********");
		RestAssured.baseURI = ("http://192.61.99.40:8089/api/values/getEpisodeSummary");

		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"?patientName=Abhi Avi Singh");
		//response.body().asString();
		JsonPath jsonpath = response.jsonPath();
		List<Map<String, Object>> reponseBody = jsonpath.getList("listEpisodeSummary");
		String memberName=(String) reponseBody.get(0).get("patientName");
	    logger.info("Member Name is:::"+memberName);
		
 		
		
			
				}
		
				
	

	}
	
	



package com.bpci.practice.example;

import org.testng.annotations.BeforeClass;

import com.bpci.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class GetEmailAddress extends TestBase {
	@BeforeClass
	void getActiveAdmissionCount() throws InterruptedException {
		
		logger.info("********started TC002_Get_AverageLOS*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/getAverageLOS");
		Thread.sleep(1000);
	}
}

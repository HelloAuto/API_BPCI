package com.bpci.testCases;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import org.apache.commons.io.IOUtils;

import org.testng.annotations.Test;


import io.restassured.RestAssured;


public class PostRequestUsingJsonFile {
	@Test
	public void postRequest() throws IOException {
		FileInputStream jsonFile=new FileInputStream(new File(".\\JsonFileInput\\jsonrequest1.json"));
		RestAssured.baseURI=" http://192.61.99.40:8088/account/Register";
		
		given()
		      .header("Content-Type","application/json")
		      .and()
		      .body(IOUtils.toString(jsonFile,"UTF-8"))
		
		.when()
		      .post("/posts")
		.then()
		      .statusCode(200)
		      .and()
		      .log().all();
		  
	}


	
}

package com.bpci.practice.example;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class TestPost {
	@Test
	public void postMethod() throws Exception {
		RestAssured.baseURI="http://192.61.99.40:8088/account/Login";
		Response response=given()
				.header("Content-Type","application/json")
				.and()
				.body("{\r\n" + 
						"\"EMail\":\"nehanavneet1@gmail.com\",\r\n" + 
						"\"Password\":\"Hineha@123\"\r\n" + 
						"}")
		.when()
		  .post("/posts")
		.then()
		  .statusCode(200)
		  .and()
		  .log().all()
		  .extract().response();
		  
	}

}

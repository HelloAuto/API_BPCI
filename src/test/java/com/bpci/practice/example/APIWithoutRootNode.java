package com.bpci.practice.example;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIWithoutRootNode {
	@Test
	void getWeatherDetails() {
		
		//Specify base URI
		RestAssured.baseURI="https://jsonplaceholder.typicode.com";
		
		//Request object
		RequestSpecification httprequest=RestAssured.given();
		
		//Response object
		
		Response response=httprequest.request(Method.GET,"/users");
		
		//print response in console
		 List<String> jsonResponse = response.jsonPath().getList("$");
		 // String responseBody=response.getBody().asString();
		 // JsonPath jsonpath = response.jsonPath();
		 // System.out.println("Response body json: "+jsonpath);
		 // String city = jsonpath.get("City");
		  //System.out.println(city);
		  System.out.println("Response body is "+jsonResponse);
		  
		
		  
	}


}

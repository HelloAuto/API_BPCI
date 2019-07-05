package com.bpci.base;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public String uri="http://192.61.99.40:8089/api/values";
	public static Logger logger;
	public int episodeid = 2;
	Properties prop;
	
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream fis  = new FileInputStream("C:\\Users\\91890\\eclipse-workspace\\RestAssuredAPITesting_BPCI\\src\\test\\java\\com\\bpci\\config\\config.properties");
			prop.load(fis);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@BeforeClass
	public void setup() {
		logger = Logger.getLogger("BPCI_Rest_API");
		PropertyConfigurator.configure("C:\\Users\\91890\\eclipse-workspace\\RestAssuredAPITesting_BPCI\\log4j.properties");
		logger.setLevel(Level.DEBUG);
	}
	
}

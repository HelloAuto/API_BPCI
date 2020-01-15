package com.bpci.base;

import java.io.FileInputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import com.bpci.practice.example.GetEpisodeDetails;
import com.bpci.utilities.*;

public class TestBase {

	public static RequestSpecification httpRequest;
	public static Response response;
	public String uri = "http://192.61.99.40:8088/api/values";

	public static Logger logger;

	protected static Properties prop;

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir")+"\\src\\test\\java\\com\\bpci\\config\\config.properties");
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void setup() {
		logger = Logger.getLogger("BPCI_Rest_API");
		PropertyConfigurator.configure("C:\\Users\\Neha\\eclipse-workspace\\BPCI_API\\API_BPCI\\log4j.properties");
		logger.setLevel(Level.DEBUG);
	}

	public void vailidateAPIResult(List<Map<String, String>> responseBody,
			ArrayList<HashMap<String, String>> queryResultList, String excelPath, int curRowNum) {
		// not doing any thing
	}

	public List<Map<String, String>> getJsonData1(String uri, Map<String, String> tempData) {
		return null;

	}

	public List<Map<String, String>> getJsonData(String uri, String jsonObjectName) {
		return null;

	}

	public ArrayList<HashMap<String, String>> parseResultSetObj(ResultSet rs) {

		return null;
	}

	public Map<String, String> getRequestParam(String requestParam,String jsonObjectName) {
		return null;
	}

	public String ValidatePostAPISuccessMsg(String msgBody, String excelPath, int curRowNum) {
		return null;
	}
	public boolean isResponseBodyValid(List<Map<String,String>> responseBody,String excelPath, int curRowNum,String query) throws Exception {
		return false;
	}

	
}

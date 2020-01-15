package com.bpci.testCases;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import com.bpci.base.TestBase;
import com.bpci.utilities.ExcelUtils;
import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class POstRequestMethodDeclare extends TestBase {
    

	public Map<String, String> getRequestParam(String requestParam,String jsonObjectName) {

		int count = 0;
		Map<String, String> tempData = new HashMap<String, String>();

		if (requestParam.contains(",")) {
			requestParam = requestParam.trim();
			requestParam = requestParam.replace("\n", "").replace("\r", "");
			requestParam = requestParam.replace("\"", "");

		//	System.out.println("requestparameter from excel"+requestParam);

			String[] parameters = requestParam.split(",");
			for (String w : parameters) {

				String[] postParam = w.split(":");
				tempData.put(postParam[0], postParam[1]);
				
			}
			System.out.println("keys==" + tempData.keySet());
			System.out.println("value==" + tempData.values());
		}
		 else if(requestParam.isEmpty()) {
			 
			 RequestSpecification httpRequest3 = RestAssured.given();
			 response = httpRequest3.post(uri);
			 httpRequest3.header("Content-Type","application/json");
			 Response response=httpRequest3.post(jsonObjectName);
			 System.out.println("Response body: " + response.body().asString());
			 

							
		 }

		else  {

			requestParam = requestParam.trim();
			requestParam = requestParam.replace("\n", "").replace("\r", "");
			requestParam = requestParam.replace("\"", "");

			//System.out.println(requestParam);

			String[] postParam1 = requestParam.split(":");
			tempData.put(postParam1[0], postParam1[1]);
		}
			
		
		count++;
		return tempData;

	}

	public List<Map<String, String>> getJsonData1(String uri, Map<String, String> tempData) {
		RequestSpecification httpRequest3 = RestAssured.given();
		JSONObject requestParameter = new JSONObject(tempData);

		System.out.println("After converting into JSON"+requestParameter.toJSONString());

		httpRequest3.header("Content-Type", "application/json");
		httpRequest3.body(requestParameter.toJSONString());

		response = httpRequest3.post(uri);

		System.out.println("Response body: " + response.asString());
		return null;
	}

	public String ValidatePostAPISuccessMsg(String msgBody, String excelPath, int curRowNum) {
		try {
			int count = 0;
			
			StringBuilder msg = new StringBuilder(msgBody);

			System.out.println(msg);
		
			if((msg.toString()).trim().equalsIgnoreCase(response.body().asString()))  {
				ExcelUtils.setCellData(excelPath, "Sheet7", curRowNum + 1, 5, "PASS");
				System.out.println("my api pass");

			} else {
				ExcelUtils.setCellData(excelPath, "Sheet7", curRowNum + 1, 5, "FAIL");
				System.out.println("fail");

			}

			count++;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msgBody;

	}
}

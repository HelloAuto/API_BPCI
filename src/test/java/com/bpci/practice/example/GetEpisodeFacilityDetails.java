package com.bpci.practice.example;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.bpci.base.TestBase;
import com.bpci.utilities.ExcelUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class GetEpisodeFacilityDetails extends TestBase{
public ArrayList<HashMap<String, String>> parseResultSetObj(ResultSet rs ){
		
		ArrayList<HashMap<String, String>> queryResultList = new ArrayList<HashMap<String, String>>();
		try {
		
			ResultSetMetaData mData = rs.getMetaData();
			System.out.println(mData.getColumnName(1));
			
			//int colCount = mData.getColumnCount();
			while (rs.next()) {
				HashMap<String, String> rsSet = new HashMap<String, String>();
				rsSet.put(mData.getColumnName(1), rs.getString(1));
				rsSet.put(mData.getColumnName(2), rs.getString(2));
				rsSet.put(mData.getColumnName(3), rs.getString(3));
			
				
				
				queryResultList.add( rsSet );
				System.out.println(rsSet);
			}
		
		}catch(Exception e) {
			logger.info("**** Exception caught in parseResultSetObj  ******" + e.getMessage());
		} 
		return queryResultList;
	}
	
	
	public   List<Map<String,String>> getJsonData(String uri, String jsonObjectName) {
	
		RequestSpecification httpRequest3 = RestAssured.given();
		response = httpRequest3.request(Method.GET,uri);
	
		
	
		JsonPath jsonpath = new JsonPath(response.body().asString());
		
		List<Map<String,String>> responseBody = jsonpath.getList(jsonObjectName); 
		
		return responseBody;
		
	}
	
	public void vailidateAPIResult(List<Map<String,String>> responseBody, ArrayList<HashMap<String, String>> queryResultList, String excelPath
			, int curRowNum) {
		
		try {
		int listIndex = 0;
		
		for(HashMap<String, String> rsSet : queryResultList) {
			for(String colName : rsSet.keySet()) {
				System.out.println("Cur ColName - " + colName);
				
				//Object curResponse = responseBody.get(listIndex).get(colName);
				String curResponse = responseBody.get(listIndex).get(colName);
				 if(rsSet.get(colName).toString().equalsIgnoreCase(curResponse.toString())){ 
					 ExcelUtils.setCellData(excelPath,"Sheet3", curRowNum+1, 5, "PASS");
				 
				  } else { ExcelUtils.setCellData(excelPath, "Sheet3", curRowNum+1, 5, "FAIL"); 
				  }
				Assert.assertEquals(rsSet.get(colName), responseBody.get(listIndex).get(colName));
				
			}
			listIndex++;
			
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	


}

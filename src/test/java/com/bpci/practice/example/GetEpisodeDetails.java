package com.bpci.practice.example;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bpci.base.TestBase;
import com.bpci.utilities.DBConnectExcel;
import com.bpci.utilities.ExcelUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class GetEpisodeDetails extends TestBase {
	
	

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
					rsSet.put(mData.getColumnName(4), rs.getString(4));
					rsSet.put(mData.getColumnName(5), rs.getString(5));
					//rsSet.put(mData.getColumnName(6), rs.getString(6));
					//rsSet.put(mData.getColumnName(7), rs.getString(7));
					
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
					
					Object curResponse = responseBody.get(listIndex).get(colName);
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

		
	
		
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		 * boolean finalResult = true; List<Map<String,String>> responseBody = null;
		 * String excelPath =""; try {
		 * 
		 * logger.info("********started Get Request validation*********"); //String uri
		 * = "https://reqres.in"; //used for testing RestAssured.baseURI = uri;
		 * 
		 * httpRequest = RestAssured.given();
		 * 
		 * 
		 * excelPath = prop.getProperty("excel"); int rowCount =
		 * ExcelUtils.getRowCount(excelPath, "Sheet2"); int colCount =
		 * ExcelUtils.getCellCount(excelPath, "Sheet2", 1); String data1[][] = new
		 * String[rowCount][colCount]; for(int i=1; i<=rowCount;i++) { for(int j=0;
		 * j<colCount; j++) { data1[i-1][j] = ExcelUtils.getCellData(excelPath,
		 * "Sheet2", i, j); } } for(int i=0;i<rowCount;i++) {
		 * System.out.println("Iteration " + data1[i][2]); RequestSpecification
		 * httpRequest2 = RestAssured.given(); response =
		 * httpRequest2.request(Method.GET,data1[i][2]);
		 * 
		 * // response = httpRequest2.request(Method.GET, "/api/unknown");
		 * logger.info(data1[i][2]); JsonPath jsonpath = new
		 * JsonPath(response.body().asString()); //JsonPath jsonpath =
		 * response.jsonPath(); // commented for testing
		 * System.out.println("Response body - " + response.body().asString());
		 * 
		 */
				//List<Map<String,Object>> responseBody = jsonpath.get(data1[i][3]);
				
			/*	responseBody = jsonpath.getList(data1[i][3]); 
				//List<Map<String,String>> responseBody = jsonpath.get("data");
				//List<Map<String,String>> responseBody = jsonpath.get(json, );
				//logger.info(responseBody.get(i).get("episodeId"));
				DBConnectExcel.connectDBExcel(i);
				
				ResultSet rs = DBConnectExcel.getResultSetObj();
				ArrayList<HashMap<String, Object>> queryResultList = parseResultSetObj(rs);
				
				int listIndex = 0;
				
				for(HashMap<String, Object> rsSet : queryResultList) {
					for(String colName : rsSet.keySet()) {
						System.out.println("Cur ColName - " + colName);
						
						Object curResponse = responseBody.get(i).get(colName);
						 if(rsSet.get(colName).toString().equalsIgnoreCase(curResponse.toString())){ 
							 ExcelUtils.setCellData(excelPath,"Sheet2", i+1, 5, "PASS");
						 
						  } else { ExcelUtils.setCellData(excelPath, "Sheet2", i+1, 5, "FAIL"); 
						  }
						Assert.assertEquals(rsSet.get(colName), responseBody.get(i).get(colName));
						
					}
					listIndex++;
					
				}
				*/
			
	/*
	 * } }catch (Exception e) { e.printStackTrace(); } finally {
	 * DBConnectExcel.closeConnection();
	 * 
	 * 
	 * 
	 * }
	 * 
	 * }
	 */
	
	
	/*private ArrayList<HashMap<String, Object>> parseResultSetObj(ResultSet rs ){
		
		ArrayList<HashMap<String, Object>> queryResultList = new ArrayList<HashMap<String, Object>>();
		try {
			ResultSetMetaData mData = rs.getMetaData();
			System.out.println(mData.getColumnName(1));
			
			//int colCount = mData.getColumnCount();
			while (rs.next()) {
				HashMap<String, Object> rsSet = new HashMap<String, Object>();
				rsSet.put(mData.getColumnName(1), rs.getInt(1));
				rsSet.put(mData.getColumnName(2), rs.getString(2));
				rsSet.put(mData.getColumnName(3), rs.getString(3));
				rsSet.put(mData.getColumnName(4), rs.getString(4));
				rsSet.put(mData.getColumnName(5), rs.getString(5));
				
		
				
				
				
				
				queryResultList.add( rsSet );
				System.out.println(rsSet);
				
			}
		
		}catch(Exception e) {
			logger.info("**** Exception caught in parseResultSetObj  ******" + e.getMessage());
		} 
		return queryResultList;
	}
	*/
	
}

package com.bpci.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.bpci.base.TestBase;
import com.bpci.utilities.DBConnectExcel;
import com.bpci.utilities.ExcelUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class GetRequestUsingExcel extends TestBase{
	
	public GetRequestUsingExcel() {
		super();
	}
	
	@Test//(dataProvider="dataProvider")
	void validateAllGetRequest() throws Exception {
		
		logger.info("********started Get Request validation*********");
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		
		//String excelPath = prop.getProperty("excel");
		String excelPath = prop.getProperty("excel");
		int rowCount = ExcelUtils.getRowCount(excelPath, "Sheet1");
		int colCount = ExcelUtils.getCellCount(excelPath, "Sheet1", 1);
		String data[][] = new String[rowCount][colCount];
		for(int i=1; i<=rowCount; i++) {
			for(int j=0; j<colCount; j++) {
				data[i-1][j] = ExcelUtils.getCellData(excelPath, "Sheet1", i, j);
				//System.out.println(data);
			}
		}
		for(int i=0;i<rowCount;i++) {
			response = httpRequest.request(Method.GET,data[i][2]);
			logger.info(data[i][2]);
			JsonPath jsonpath = response.jsonPath();
			int responseBody = jsonpath.getInt(data[i][3]);
			int queryResult = DBConnectExcel.connectDBExcel(i);
			if(responseBody==queryResult) {
				ExcelUtils.setCellData(excelPath, "Sheet1", i+1, 5, "PASS");
				}
				else {
					ExcelUtils.setCellData(excelPath, "Sheet1", i+1, 5, "FAIL");
				}
			int statusCode = response.getStatusCode();
			logger.info(statusCode);
			Assert.assertEquals(responseBody, queryResult);
		}
	}
}

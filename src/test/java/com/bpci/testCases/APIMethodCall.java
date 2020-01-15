package com.bpci.testCases;

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

//import com.bpci.base.DataPopulatorUtils;
import com.bpci.base.TestBase;
import com.bpci.utilities.DBConnectExcel;
import com.bpci.utilities.ExcelUtils;
import com.mongodb.diagnostics.logging.Logger;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
public class APIMethodCall extends TestBase {
	public APIMethodCall() {
		super();
	}

	@Test
	void dataPopulator() {

		List<Map<String, String>> responseBody = null;
		String excelPath = "";

		try {

			logger.info("********started Get GetEventDetailsBasedOnEpisode Request validation*********");
			RestAssured.baseURI = uri;

			excelPath = prop.getProperty("excel");
			int rowCount = ExcelUtils.getRowCount(excelPath, "Sheet1");
			System.out.println("rowcount" + rowCount);

			int colCount = ExcelUtils.getCellCount(excelPath, "Sheet1", 1);
			System.out.println("columncount" + colCount);

			String data1[][] = new String[rowCount][colCount];
			for (int i = 1; i <= rowCount; i++) {
				for (int j = 0; j < colCount; j++) {
					data1[i - 1][j] = ExcelUtils.getCellData(excelPath, "Sheet1", i, j);
				}
			}

			for (int i = 0; i < rowCount; i++) {

				String uri = data1[i][2];
                String query=data1[i][4];
				
				System.out.println("uri---- " + data1[i][2]);
				String jsonObjectName = data1[i][3];// data is showing
				logger.info("**********responseObjectNameInExcel******************" + jsonObjectName);

				TestBase obj = new APIMethodDeclare();               
				responseBody = obj.getJsonData(uri, jsonObjectName);
				 
				if(!obj.isResponseBodyValid(responseBody, excelPath, i,query)) {
					//DBConnectExcel.connectDBExcel(i);
				//	ResultSet rs = DBConnectExcel.getResultSetObj();
					//ArrayList<HashMap<String, String>> queryResultList = obj.parseResultSetObj(rs);
					//Assert.assertNull(null, "negative testing pass");
					
					System.out.println("hiiii");
					
					
				}else {
					logger.info("**************ResponseBody********" + responseBody);
	                System.out.println("response sizeee==="+responseBody.size());
					DBConnectExcel.connectDBExcel(i);
					ResultSet rs = DBConnectExcel.getResultSetObj();
					ArrayList<HashMap<String, String>> queryResultList = obj.parseResultSetObj(rs);
					
					obj.vailidateAPIResult(responseBody, queryResultList, excelPath, i);
					System.out.println("query result"+queryResultList);
				}
				
				

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectExcel.closeConnection();

		}

	}
}
 
package com.bpci.testCases;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.bpci.base.TestBase;
import com.bpci.utilities.DBConnectExcel;
import com.bpci.utilities.ExcelUtils;

import io.restassured.RestAssured;

public class PostRequestAPIMethodCall extends TestBase {
	public PostRequestAPIMethodCall() {
		super();
	}

	@Test
	void dataPopulator() {

		List<Map<String, String>> responseBody = null;

		String excelPath = "";
		Map<String, String> tempData = null;
		String msg = null;

		try {

			logger.info("********started  PostRequestAPIMethodCall*********");
			RestAssured.baseURI = uri;

			excelPath = prop.getProperty("excel");
			int rowCount = ExcelUtils.getRowCount(excelPath, "Sheet7");
			System.out.println("rowcount" + rowCount);

			int colCount = ExcelUtils.getCellCount(excelPath, "Sheet7", 1);
			System.out.println("columncount" + colCount);

			String data1[][] = new String[rowCount][colCount];
			for (int i = 1; i <= rowCount; i++) {
				for (int j = 0; j < colCount; j++) {
					data1[i - 1][j] = ExcelUtils.getCellData(excelPath, "Sheet7", i, j);
				}
			}

			for (int i = 0; i < rowCount; i++) {

				String uri = data1[i][2];
				String requestParam = data1[i][3];
				String msgBody = data1[i][4];
				String jsonObjectName = data1[i][6];
				System.out.println("uri---- " + data1[i][2]);

				TestBase obj = new POstRequestMethodDeclare();
				tempData = obj.getRequestParam(requestParam,jsonObjectName);
				responseBody = obj.getJsonData1(uri, tempData);
				msg = obj.ValidatePostAPISuccessMsg(msgBody, excelPath, i);
                
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

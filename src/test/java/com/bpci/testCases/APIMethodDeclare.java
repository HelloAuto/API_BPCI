package com.bpci.testCases;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;

import com.bpci.base.TestBase;
import com.bpci.utilities.ExcelUtils;
import com.google.common.base.Objects;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class APIMethodDeclare extends TestBase {
	
	public ArrayList<HashMap<String, String>> parseResultSetObj(ResultSet rs) {
		ArrayList<HashMap<String, String>> queryResultList = new ArrayList<HashMap<String, String>>();
		try {
			ResultSetMetaData mData = rs.getMetaData();
			int colCount = mData.getColumnCount();
			while (rs.next()) {
				HashMap<String, String> rsSet = new HashMap<String, String>();
				for (int i = 1; i <= colCount; i++) {
					rsSet.put(mData.getColumnName(i), rs.getString(i));
				}
				queryResultList.add(rsSet);
				System.out.println(rsSet);
			}
		} catch (Exception e) {
			logger.info("**** Exception caught in parseResultSetObj  ******" + e.getMessage());
		}
		return queryResultList;
	}
	 

	public List<Map<String, String>> getJsonData(String uri, String jsonObjectName) {
		RequestSpecification httpRequest3 = RestAssured.given();
		response = httpRequest3.request(Method.GET, uri);
		JsonPath jsonpath = new JsonPath(response.body().asString());
		List<Map<String, String>> responseBody = null;
		int count = jsonObjectName.length();
		System.out.println("json obj count" + count);
		if (!jsonObjectName.isEmpty()) {
			responseBody = jsonpath.getList(jsonObjectName);
		} else {
			responseBody = new ArrayList<Map<String, String>>();
			Map<String, String> tempMap = new HashMap<String, String>();
			// List<HashMap<String,String>> emptyJsonNode=response.jsonPath().getList("$");
			// responseBody = response.jsonPath().getList("$");
			Object obj = response.jsonPath().getObject("$", Object.class);
			if (obj instanceof Map) {
				tempMap = (Map<String, String>) obj;
				responseBody.add(tempMap);
				// System.out.println("resBody"+ response.body());
			} else if (obj instanceof List) {
				responseBody = response.jsonPath().getList("$");
			}
		}
		return responseBody;
	}

	
	public void vailidateAPIResult(List<Map<String, String>> responseBody,
			ArrayList<HashMap<String, String>> queryResultList, String excelPath, int curRowNum) {
		try {
			int listIndex = 0;
			int l = responseBody.size(); // String[] str = new String[l];
			Object obj = response.jsonPath().getObject("$", Object.class);
			for (HashMap<String, String> rsSet : queryResultList) {
				Set<String> keySet = rsSet.keySet();
				for (String colName : keySet) {
					System.out.println("Cur ColName - " + colName);
					
					for (String colValue : rsSet.values()) {
						System.out.println("column value" + colValue);
					}
					Object queryRst = rsSet.get(colName);
					Object str = responseBody.get(listIndex).get(colName);
					System.out.println(listIndex);

					if (rsSet.get(colName).toString().equalsIgnoreCase(str.toString())) {
						System.out.println("query size" + queryResultList.size());
						ExcelUtils.setCellData(excelPath, "Sheet1", curRowNum + 1, 5, "PASS");
					} else {
						ExcelUtils.setCellData(excelPath, "Sheet1", curRowNum + 1, 5, "FAIL");
					} 
					Assert.assertEquals(rsSet.get(colName), str.toString());
					System.out.println("1");
					System.out.println(rsSet.get(colName)); // sft + ctrl + i
				}
				listIndex++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean isResponseBodyValid(List<Map<String, String>> responseBody, String excelPath, int curRowNum,
			String query) throws Exception {
		try {
			for (Map<String, String> res : responseBody) {
				Set<String> keySet = res.keySet();
				System.out.println("keySet");
				if (res.containsValue(null)) {
					System.out.println("yes");
					Assert.assertNull(null, "negative testing pass");
					ExcelUtils.setCellData(excelPath, "Sheet1", curRowNum + 1, 5, "PASS");
					System.out.println("keyset sizeee" + keySet.size());
					return false;

				} else if (responseBody.size() == 1 && query.isEmpty()) {

					String val = res.values().toString();
					System.out.println("val=====" + val);
					Assert.assertEquals(val, "[[]]");
					ExcelUtils.setCellData(excelPath, "Sheet1", curRowNum + 1, 5, "PASS");
					System.out.println("empty");
					return false;
				} else
					ExcelUtils.setCellData(excelPath, "Sheet1", curRowNum + 1, 5, "FAIL");
			}

		} catch (NullPointerException e) {
		}
		return true;
	}

}

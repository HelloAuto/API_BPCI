package com.bpci.testCases;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;

import com.bpci.base.TestBase;

public class QueryTest extends TestBase {
public ArrayList<HashMap<String, String>> parseResultSetObj(ResultSet rs ){
		
		ArrayList<HashMap<String, String>> queryResultList = new ArrayList<HashMap<String, String>>();
		try {
		
			ResultSetMetaData mData = rs.getMetaData();
			System.out.println(mData.getColumnName(1));
			
			int colCount = mData.getColumnCount();
			
			
			while (rs.next()) {
				HashMap<String, String> rsSet = new HashMap<String, String>();
				/*
				 * rsSet.put(mData.getColumnName(1), rs.getString(1));
				 * rsSet.put(mData.getColumnName(2), rs.getString(2));
				 * rsSet.put(mData.getColumnName(3), rs.getString(3));
				 * rsSet.put(mData.getColumnName(4), rs.getString(4));
				 * rsSet.put(mData.getColumnName(5), rs.getString(5));
				 * rsSet.put(mData.getColumnName(6), rs.getString(6));
				 */
				//rsSet.put(mData.getColumnName(7), rs.getString(7));  cost
				for (int i = 1; i <= colCount; i++) {
					rsSet.put(mData.getColumnName(i), rs.getString(i));
	            }
				
				queryResultList.add( rsSet );
				System.out.println(rsSet);
				
				
				
				  
				 
				
				 
				 
			}
		
		}catch(Exception e) {
			logger.info("**** Exception caught in parseResultSetObj  ******" + e.getMessage());
		} 
		return queryResultList;
	}


	
}

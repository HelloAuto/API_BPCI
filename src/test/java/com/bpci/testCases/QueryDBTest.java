package com.bpci.testCases;


	//STEP 1. Import required packages
	import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
	public class QueryDBTest {

	
	   // JDBC driver name and database URL
	  // static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:sqlserver://192.61.99.40;databaseName=Bpci";

	   //  Database credentials
	   static final String USER = "bpci_admin";
	   static final String PASS = "bpci@123456";
	   
	   public static void main(String[] args) {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql;
	      sql = "	select * from [dbo].[tbl_Encounter] where encounterId=1337";
	      ResultSet rs = stmt.executeQuery(sql);

	      //STEP 5: Extract data from result set
	      ArrayList<HashMap<String, String>> queryResultList = new ArrayList<HashMap<String, String>>();
	      ResultSetMetaData mData = rs.getMetaData();
	      int colCount = mData.getColumnCount();
	      HashMap<String, String> rsSet = new HashMap<String, String>();
	      while(rs.next()){
	 
	         for (int i = 1; i <= colCount; i++) {
					rsSet.put(mData.getColumnName(i), rs.getString(i));
	            }
				
				queryResultList.add( rsSet );
				System.out.println(rsSet);
	      }
	      //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	}//end main
}//end FirstExample
	


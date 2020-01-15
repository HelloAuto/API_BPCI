package com.bpci.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import com.bpci.base.TestBase;

public class DBConnect extends TestBase{
	
		
	
	
	public static  String connectDB(String tableName, String condition, int id ) throws Exception {
		
		String dburl = "jdbc:sqlserver://192.61.99.40;databaseName=Bpci";
		String username = "bpci_admin";
		String password = "bpci@123456";
		String fullName =null;
		String query = "select cast(in1.initiatorId as varchar(max))as initiatorId,in1.initiator_Fullname as name \r\n" + 
				"from [dbo].[tbl_Initiator] in1\r\n" + 
				"join [dbo].[tbl_Episode] ep\r\n" + 
				"on ep.initiatorId_FK=in1.initiatorId\r\n" + 
				"where ep.episodeId=1815";
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // Loading Driver
		
		
		Connection con = DriverManager.getConnection(dburl,username,password);  //Create Connection
		
		Statement stmt = con.createStatement(); //Create Statement Object
		
		//stmt.executeQuery(queryCon);
		
		ResultSet rs= stmt.executeQuery(query);	
	/*	ResultSetMetaData rsMetaData=rs.getMetaData();
		int numberOfColumns=rsMetaData.getColumnCount();
		for(int i=1;i<numberOfColumns;i++) {
			String columnName=rsMetaData.getCatalogName(i);
			System.out.println(columnName);
			}
		}*/
		
		
		
		while(rs.next()) {
			logger.info(rs.getInt(1)+" "+rs.getString(3));
			fullName = rs.getString(3);
		}
		
		//String fullName = rs.getString(3);
		con.close();
		return fullName;
}}



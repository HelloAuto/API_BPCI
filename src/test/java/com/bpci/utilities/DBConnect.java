package com.bpci.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnect {
	
	public static String connectDB(String tableName, String condition, int id ) throws Exception {
		
		String dburl = "192.61.99.40";
		String username = "bpci_admin";
		String password = "bpci@123456";
		String queryCon = "use bcpitest";
		String query = "select * from "+tableName+"where "+condition+"="+id;
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // Loading Driver
		
		
		Connection con = DriverManager.getConnection(dburl,username,password);  //Create Connection
		
		Statement stmt = con.createStatement(); //Create Statement Object
		
		stmt.executeQuery(queryCon);
		
		ResultSet rs= stmt.executeQuery(query);	
		
		String fullName = rs.getString(3);
		
		return fullName;
	}

}

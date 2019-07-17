package com.bpci.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.bpci.base.TestBase;

public class DBConnect extends TestBase{
	
	public static String connectDB(String tableName, String condition, int id ) throws Exception {
		
		String dburl = "jdbc:sqlserver://192.61.99.40;databaseName=BpciTest";
		String username = "bpci_admin";
		String password = "bpci@123456";
		String fullName =null;
		String query = "select * from tbl_Initiator where initiatorid = 2";
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // Loading Driver
		
		
		Connection con = DriverManager.getConnection(dburl,username,password);  //Create Connection
		
		Statement stmt = con.createStatement(); //Create Statement Object
		
		//stmt.executeQuery(queryCon);
		
		ResultSet rs= stmt.executeQuery(query);	
		
		while(rs.next()) {
			logger.info(rs.getInt(1)+" "+rs.getString(3));
			fullName = rs.getString(3);
		}
		
		//String fullName = rs.getString(3);
		con.close();
		return fullName;
	}

}

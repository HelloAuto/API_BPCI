package com.bpci.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import com.bpci.base.TestBase;

public class DBConnectExcel extends TestBase{
	
	public static ResultSet rs = null;
	public static Connection con = null;
	public DBConnectExcel() {
		super();
	}

	public static void connectDBExcel(int rowNum) throws Exception {
		String dburl = "jdbc:sqlserver://192.61.99.40;databaseName=Bpci";
		String username = "bpci_admin";
		String password = "bpci@123456";
		String query = null;
		// String queryforGetEventDetails = null;
		String excelPath = prop.getProperty("excel");

		query = ExcelUtils.getCellData(excelPath, "Sheet1", rowNum + 1, 4);
		System.out.println(rowNum);
		logger.info(query);
	
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // Loading Driver

		con = DriverManager.getConnection(dburl, username, password); // Create Connection

		Statement stmt = con.createStatement(); // Create Statement Object

		rs = stmt.executeQuery(query);
		
	}

	public static void connectDBExcelPost(int rowNum) throws Exception {
		String dburl = "jdbc:sqlserver://192.61.99.40;databaseName=Bpci";
		String username = "bpci_admin";
		String password = "bpci@123456";
		String query = null;
		// String queryforGetEventDetails = null;
		String excelPath = prop.getProperty("excel");

		query = ExcelUtils.getCellData(excelPath, "Sheet1", rowNum + 1, 4);
		
		System.out.println(rowNum);
		logger.info(query);
	
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // Loading Driver

		con = DriverManager.getConnection(dburl, username, password); // Create Connection

		Statement stmt = con.createStatement(); // Create Statement Object

		rs = stmt.executeQuery(query);

	}

	public static void closeConnection() {
		try {
		if ( null != con)
			con.close();
		}catch(Exception e) {
			System.out.println("*********** Exception in closing connection - " + e.getMessage());
			e.printStackTrace();
		}
	}

	//methods return resultSet Object
	public static ResultSet getResultSetObj() {
		return rs;
	}
}
	
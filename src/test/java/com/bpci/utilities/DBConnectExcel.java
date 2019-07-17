package com.bpci.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.bpci.base.TestBase;

public class DBConnectExcel extends TestBase{
	
	public DBConnectExcel() {
		super();
	}
	public static int connectDBExcel(int value) throws Exception {
		String dburl = "jdbc:sqlserver://192.61.99.40;databaseName=BpciTest";
		String username = "bpci_admin";
		String password = "bpci@123456";
		String query = null;
		int activeCount=0;
		
		String excelPath = prop.getProperty("excel");
//		int rowCount = ExcelUtils.getRowCount(excelPath, "Sheet1");
//		//int colCount = ExcelUtils.getCellCount(excelPath, "Sheet1", 1);
//		//String data[][] = new String[rowCount][colCount];
//		for(int i=0; i<rowCount; i++) {
			query = ExcelUtils.getCellData(excelPath, "Sheet1", value+1, 4);
			System.out.println(value);
			logger.info(query);
		//}
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // Loading Driver
		
		
		Connection con = DriverManager.getConnection(dburl,username,password);  //Create Connection
		
		Statement stmt = con.createStatement(); //Create Statement Object
		
		//stmt.executeQuery(queryCon);
		
		ResultSet rs= stmt.executeQuery(query);	
		
		while(rs.next()) {
			logger.info(rs.getInt(1));
			activeCount = rs.getInt(1);
		}
		
		//String fullName = rs.getString(3);
		con.close();
		return activeCount;
	}
	
}
	
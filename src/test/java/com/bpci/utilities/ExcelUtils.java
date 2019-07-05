package com.bpci.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bpci.base.TestBase;

public class ExcelUtils extends TestBase{
	static FileInputStream fis;
	static XSSFWorkbook wb;
	static XSSFSheet ws;
	static XSSFRow row;
	static XSSFCell cell;
	static FileOutputStream fos;
	
	public static int getRowCount(String xlfile, String xlsheet) throws Exception {
		
		fis = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(xlsheet);
		int rowCount = ws.getLastRowNum();
		wb.close();
		fis.close();
		
		return rowCount;
	}
	
	public static int getCellCount(String xlfile, String xlsheet, int rowCount) throws Exception {
		
		fis = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rowCount);
		int cellCount = row.getLastCellNum();
		wb.close();
		fis.close();
		
		return cellCount;	
	}
	
	public static String getCellData(String xlfile, String xlsheet, int rowCount, int cellCount) throws Exception {
	
		fis = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rowCount);
		cell = row.getCell(cellCount);
		String data;
		try {
			DataFormatter formatter = new DataFormatter();
			data = formatter.formatCellValue(cell);
		}
		catch(Exception e) {
			data = "";
		}
		wb.close();
		fis.close();
		
		return data;
	}
	
	public static void setCellData(String xlfile, String xlsheet, int rowCount, int cellCount, String data) throws Exception {
		
		fis = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rowCount);
		cell = row.createCell(cellCount);
		cell.setCellValue(data);
		fos = new FileOutputStream(xlfile);
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();
	}

}

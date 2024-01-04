package com.fbms.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

public class TestDataFactory {

	//method defined for reading a cell  
	public static String ReadCellData(int vRow, int vColumn, int vSheet) {

		String value = null; // variable for storing the cell value
		Workbook wb = null; // initialize Workbook null

		try {
			//reading data from a file in the form of bytes  
			FileInputStream fis = new FileInputStream("src//test//resources//FOETestData.xlsx");
			//constructs an XSSFWorkbook object, by buffering the whole stream into the memory  
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Sheet sheet = wb.getSheetAt(vSheet); // getting the XSSFSheet object at given index
		DataFormatter formatter = new DataFormatter();

		//Row row = sheet.getRow(vRow); // returns the logical row
		//Cell cell = row.getCell(vColumn); // getting the cell representing the given column
		//value = cell.getStringCellValue(); // getting cell value
		value = formatter.formatCellValue(sheet.getRow(vRow).getCell(vColumn));

		return value; // returns the cell values
	}

	//method defined for writing into a cell  
	public static void writeCellData(int vRow, int vColumn, String value, int vSheet, String fileName) throws IOException {

		Workbook wb = null;
		File src = new File(System.getProperty("user.dir") + "\\downloads\\" + fileName);

		try {
			//reading data from a file in the form of bytes

			FileInputStream fis = new FileInputStream(src);

			//constructs an XSSFWorkbook object, by buffering the whole stream into the memory  

			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Sheet sheet = wb.getSheetAt(vSheet); // getting the XSSFSheet object at given index

		sheet.getRow(vRow).createCell(vColumn).setCellValue(value);

		FileOutputStream output_file = new FileOutputStream(src);
		wb.write(output_file);

		output_file.close();
	}

	//method defined for writing into a cell  
	public static void writeIntCellData(int vRow, int vColumn, int value, int vSheet, String fileName) throws IOException {

		Workbook wb = null;
		File src = new File(System.getProperty("user.dir") + "\\downloads\\" + fileName);

		try {
			//reading data from a file in the form of bytes

			FileInputStream fis = new FileInputStream(src);

			//constructs an XSSFWorkbook object, by buffering the whole stream into the memory  

			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Sheet sheet = wb.getSheetAt(vSheet); // getting the XSSFSheet object at given index

		sheet.getRow(vRow).createCell(vColumn).setCellValue(value);

		FileOutputStream output_file = new FileOutputStream(src);
		wb.write(output_file);

		output_file.close();
	}

	//method defined for writing into a cell  
	public static void writeFloatCellData(int vRow, int vColumn, Float value, int vSheet, String fileName) throws IOException {

		Workbook wb = null;
		File src = new File(System.getProperty("user.dir") + "\\downloads\\" + fileName);

		try {
			//reading data from a file in the form of bytes

			FileInputStream fis = new FileInputStream(src);

			//constructs an XSSFWorkbook object, by buffering the whole stream into the memory  

			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Sheet sheet = wb.getSheetAt(vSheet); // getting the XSSFSheet object at given index

		sheet.getRow(vRow).createCell(vColumn).setCellValue(value);

		FileOutputStream output_file = new FileOutputStream(src);
		wb.write(output_file);

		output_file.close();
	}
}
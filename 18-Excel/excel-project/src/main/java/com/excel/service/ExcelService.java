package com.excel.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.excel.entity.CourseEntity;
import com.excel.utils.Log;

@Service
public class ExcelService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ExcelService.class);
	
	public List<CourseEntity> courses = Arrays.asList(
			new CourseEntity(1, "JAVA", "excellent"),
			new CourseEntity(2, "JavaScript", "excellent"), 
			new CourseEntity(3, "ReactJS", "Very Good"),
			new CourseEntity(4, "PYTHON", "Good"));

	public void generateExcel() throws IOException {

		// (1) create workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// (2) create sheet
		XSSFSheet sheet = workbook.createSheet("Courses Info");

		// (3) create row - 0 refers as the first row
		XSSFRow row = sheet.createRow(0);

		// (4) create cells
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("Name");
		row.createCell(2).setCellValue("Level");

		// update cells with data
		int dataRowIndex = 1;

		for (CourseEntity courseEntity : courses) {
			// I create a new row in the next line dataRowIndex =1 now
			XSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(courseEntity.getId());
			dataRow.createCell(1).setCellValue(courseEntity.getName());
			dataRow.createCell(2).setCellValue(courseEntity.getSkill());
			// increment the row data
			dataRowIndex++;
		}

		// Finally,
		// let’s write the content to a “temp.xlsx” file in the current directory of the project
		// and close the workbook
		
		//		File currDir = new File(".");
		//		String path = currDir.getAbsolutePath();
		//		String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";

		
		// this line does the same 		
		FileOutputStream outputStream = new FileOutputStream(new File("temp.xlsx"));
		
		workbook.write(outputStream);
		workbook.close();
		
		Log.infoGreen(LOGGER, "created Excel");
	}

	
	
	public XSSFWorkbook generateExcelToDownload() throws IOException {

		// (1) create workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// (2) create sheet
		XSSFSheet sheet = workbook.createSheet("Courses Info");

		// (3) create row - 0 refers as the first row
		XSSFRow row = sheet.createRow(0);

		// (4) create cells
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("Name");
		row.createCell(2).setCellValue("Level");

		// update cells with data
		int dataRowIndex = 1;

		for (CourseEntity courseEntity : courses) {
			// I create a new row in the next line dataRowIndex =1 now
			XSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(courseEntity.getId());
			dataRow.createCell(1).setCellValue(courseEntity.getName());
			dataRow.createCell(2).setCellValue(courseEntity.getSkill());
			// increment the row data
			dataRowIndex++;
		}

		/**
		 * Since I want to download the file,
		 * Thus, I will close the workbook in the Controller
		 */

		return workbook;
	}
	
	
	public void readDataFromExcel() throws IOException {
		
		/**
		 * If I want a file to open a file inside src/main/resources , 
		 * Then use the following command:
		 * File file = ResourceUtils.getFile("classpath:temp.xlsx");
		 */
		
		/**
		 * This opens the Open Existing Workbook
		 */
		FileInputStream fileInputStream = new FileInputStream(new File("temp.xlsx"));
		
		// Get the workbook instance for XLSX file 
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        
        // It possible to get the sheet in 2 ways:
        // by its name  :  XSSFSheet sheet = workbook.getSheet("Courses Info");
        // by its index :  XSSFSheet sheet = workbook.getSheetAt(0) : this means get the first sheet         
        XSSFSheet sheet = workbook.getSheetAt(0);

        sheet.forEach(row -> {
        	row.forEach(cell -> {
        		Log.green(cell);
        	});
        });
        
        
        
        
        

        // (1) Get the workbook instance for XLSX file 
//        XSSFWorkbook workbook = new XSSFWorkbook(fIP);

//        if(file.isFile() && file.exists()) {
//           System.out.println("openworkbook.xlsx file open successfully.");
//        } else {
//           System.out.println("Error to open openworkbook.xlsx file.");
//        }
	}
	
}

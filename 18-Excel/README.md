###### \_

<img src="https://img.shields.io/badge/- File upload with Multipart File %20- blue" height=50px>

|     | Subject                                                               |
| :-: | :-------------------------------------------------------------------- |
|     | [Introduction](#Introduction)                                         |
|  1  | [write to excel file](#1_write_to_excel_file)                         |
|  2  | [download excel file from server](#2_download_excel_file_from_server) |
|     | [2.0.0. Steps to Upload/Download](#2_0_0_steps_to_upload_download)    |
|     | [2.0.1. Byte Array conversion](#2_0_1_byte_array_conversion)          |

---

###### Introduction

<img src="https://img.shields.io/badge/- Introduction  %20-blue" height=40px>

- In order to be JAVA to be able to work with Microsoft apps we use the [`Apache POI`](#-)
- JAVA doesn't have built-in support for working with this kind of files
- [`Apache POI`](#-) with it, we can read, write , modify excel spread sheets

Use this [`Apache POI quick guide link`](https://www.tutorialspoint.com/apache_poi/apache_poi_quick_guide.htm)

#### [Components of Apache POI](#-)

Apache POI contains classes and methods to work on all OLE2 Compound documents of MS Office. The list of components of this API is given below. </br>
`Note` − Older versions of POI support binary file formats such as doc, xls, ppt, etc. Version 3.5 onwards, POI supports OOXML file formats of MS-Office such as docx, xlsx, pptx, etc.

1. POIFS (Poor Obfuscation Implementation File System) − This component is the basic factor of all other POI elements. It is used to read different files explicitly.
2. HSSF (Horrible Spreadsheet Format) − It is used to read and write xls format of MS-Excel files.
3. XSSF (XML Spreadsheet Format) − It is used for xlsx file format of MS-Excel.
4. HPSF (Horrible Property Set Format) − It is used to extract property sets of the MS-Office files.
5. HWPF (Horrible Word Processor Format) − It is used to read and write doc extension files of MS-Word.
6. XWPF (XML Word Processor Format) − It is used to read and write docx extension files of MS-Word.
7. HSLF (Horrible Slide Layout Format) − It is used for read, create, and edit PowerPoint presentations.
8. HDGF (Horrible DiaGram Format) − It contains classes and methods for MS-Visio binary files.
9. HPBF (Horrible PuBlisher Format) − It is used to read and write MS-Publisher files.

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

---

---

---

###### 1_write_to_excel_file

<img src="https://img.shields.io/badge/- 1. write to excel file  %20-blue" height=40px>

With `excel` we have :

1. workbook - which is a new excel file
2. sheet - we can create multiple sheets in one workbook.
3. row (and column)
4. cell

- create Workbook -> create sheet -> create row -> store the data in a cell

Let's create a Spring boot app wih following dependencies and also add to it from MVN repo [`Apache POI`](#-)

- Both Dependencies are needed , see explanation in [StackOverflow ](https://stackoverflow.com/questions/60217698/what-is-the-difference-between-poi-and-poi-ooxml)

- See following link with example https://springjava.com/spring-boot/export-data-to-excel-file-in-spring-boot

```xml
<!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->
<dependency>
	<groupId>org.apache.poi</groupId>
	<artifactId>poi</artifactId>
	<version>5.2.0</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml -->
<dependency>
	<groupId>org.apache.poi</groupId>
	<artifactId>poi-ooxml</artifactId>
	<version>5.2.0</version>
</dependency>
```

Here is a simploe class exapmle , where I do the following:

1. create Workbook -> create sheet -> create row -> store the data in a cell
2. the I save the it in a n excel file , in my local computer

```java
package com.excel.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.excel.entity.CourseEntity;

@Service
public class ExcelService {

  // temp data for the example
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
		// let’s write the content to a “temp.xlsx” file in the current directory
		// and close the workbook
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";

		FileOutputStream outputStream = new FileOutputStream(fileLocation);
		workbook.write(outputStream);
		workbook.close();

	}
}
```

```java
@RestController
@RequestMapping("/excel")
public class ExcelController {

	@Autowired
	private ExcelService excelService;

	@GetMapping
	public void createExcel() throws IOException {
		excelService.generateExcel();
	}
}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

###### 2_download_excel_file_from_server

<img src="https://img.shields.io/badge/- 2. download_excel_file_from_server  %20-blue" height=40px>

Let's see how we can download excel file from server.

I created new method , which:

- only to create an Excel format with data, `but` w/o saving it to local folder
- I return it `workbook` bac to controller
- In Controller I config that it will be a daonload file

```java
package com.excel.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
		// let’s write the content to a “temp.xlsx” file in the current directory
		// and close the workbook
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";

		// this will write the file to local directory
		FileOutputStream outputStream = new FileOutputStream(fileLocation);
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
}
```

```java
package com.excel.controller;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excel.service.ExcelService;
import com.excel.utils.Log;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/excel")
public class ExcelController {

	private static Logger LOGGER = LoggerFactory.getLogger(ExcelController.class);

	@Autowired
	private ExcelService excelService;

	@GetMapping
	public void createExcel() throws IOException {
		excelService.generateExcel();
	}

	@GetMapping("/download")
	public void downloadExcel(HttpServletResponse response) throws IOException {

		// this, responsible to download the xlsx file
		/**
		 * In a regular HTTP response, the Content-Disposition response header is a
		 * header indicating if the content is expected to be displayed inline in the
		 * browser, that is, as a Web page or as part of a Web page, or as an
		 * attachment, that is downloaded and saved locally.
		 */
		response.setContentType("application/octet-stream");


		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=" + "WhatEverNameIWant" + ".xlsx";

		response.setHeader(headerKey, headerValue);

		XSSFWorkbook workbook = excelService.generateExcelToDownload();

		ServletOutputStream servletOutputStream = response.getOutputStream();
		workbook.write(servletOutputStream);

		// close workbook + servletOutputStream
		workbook.close();
		servletOutputStream.close();

		Log.infoYellow(LOGGER, "download excel");
	}

}
```

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

######

<img src="https://img.shields.io/badge/-   %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

######

<img src="https://img.shields.io/badge/-   %20-blue" height=40px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

######

<img src="https://img.shields.io/badge/- X  %20-yellow" height=40px>

<img src="https://img.shields.io/badge/- X %20- green" height=32px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

######

<img src="https://img.shields.io/badge/-X.   %20-yellow" height=35px>

[<img src="https://img.shields.io/badge/-Back to top%20-brown" height=22px>](#_)

---

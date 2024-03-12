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
	
	@GetMapping("/read")
	public void readDataFromExcel() throws IOException {
		excelService.readDataFromExcel();
	}

}

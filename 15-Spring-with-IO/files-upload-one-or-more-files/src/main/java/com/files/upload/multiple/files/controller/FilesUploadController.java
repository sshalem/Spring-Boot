package com.files.upload.multiple.files.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/generic/upload")
public class FilesUploadController {

	/**************************
	 * Single File Upload
	 *************************/
	@PostMapping("/singleFile")
	public void uploadSingleFile(@RequestParam("single") MultipartFile multipartFile)
			throws IllegalStateException, IOException {

		// clean path : removes any `/` or `.` from url
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		final String FOLDER_PATH = System.getProperty("user.dir");
		String path = FOLDER_PATH + "/src/main/resources/" + fileName;

		// this saves the file in the the filePath I declare
		// transferTo - a method from `MultipartFile` class
		multipartFile.transferTo(new File(path));
	}

	/****************************
	 * Multiple Files Upload
	 ****************************/
	@PostMapping("/multipleFiles")
	public void uploadMultipleFiles(@RequestParam("multiple") MultipartFile[] multipartFiles)
			throws IllegalStateException, IOException {

		List<String> fileNames = new ArrayList<>();

		for (int i = 0; i < multipartFiles.length; i++) {
			// clean path removes any `/` or `.` from url
			String fileName = StringUtils.cleanPath(multipartFiles[i].getOriginalFilename());
			fileNames.add(fileName);

			/****************************************
			 * File Location in src/main/resources
			 ****************************************/

			// Path Option (1) - To get "/src/main/resources"
			File currDir = new File(".");
			String absolutePath = currDir.getAbsolutePath();
			String pathOption_1 = absolutePath.substring(0, absolutePath.length() - 1) + "src/main/resources/" + fileName;

			System.out.println(absolutePath);
			System.out.println(pathOption_1);
			
			
			// Path Option (2) - To get "/src/main/resources"
			String FOLDER_PATH = System.getProperty("user.dir");
			String pathOption_2 = FOLDER_PATH + "/src/main/resources/" + fileName;
			

		
			
			System.out.println(pathOption_2);

			// this saves the file in the the filePath I declare
			// transferTo - a method from `MultipartFile` class
			multipartFiles[i].transferTo(new File(pathOption_2));
		}
	}
}

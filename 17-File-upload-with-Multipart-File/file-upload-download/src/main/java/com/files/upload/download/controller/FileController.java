package com.files.upload.download.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import com.files.upload.download.entity.DataBaseAttachmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.files.upload.download.entity.FileSystemAttachmentEntity;
import com.files.upload.download.model.ResponseData;
import com.files.upload.download.service.StorageService;

@RestController
@CrossOrigin("*")
public class FileController {

	@Autowired
	private StorageService storageService;

	/**********************************************************
	 * 
	 * Upload/Download using Data Base
	 * 
	 **********************************************************/

	@PostMapping(path = "/database/upload")
	public ResponseEntity<?> uploadAttachmentToDB(@RequestParam("attachment") MultipartFile multipartFile) throws Exception {

		DataBaseAttachmentEntity dataBaseAttachmentEntity = storageService.uploadAttachmentToDB(multipartFile);

		// Here I setup the download URL
		// Where FrontEnd will click the link
		// and will download the file
		String downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/database/download/") // this path need to same path of the @GetMapping
				.path(dataBaseAttachmentEntity.getId()) // concatenate the Id of the attachment to the url
				.toUriString();

		ResponseData responseData = new ResponseData(
				dataBaseAttachmentEntity.getId(),
				dataBaseAttachmentEntity.getFileName(),
				downloadURl,
				multipartFile.getContentType(),
				multipartFile.getSize());

		return ResponseEntity.status(HttpStatus.OK).body(responseData);
	}

	@GetMapping(path = "/database/download/{attachmentId}")
	public ResponseEntity<?> downloadAttachmentFromDB(@PathVariable String attachmentId) throws Exception {

		/**
		 * I return in the response byte[]. 
		 * What it means?
		 * This means , I will get the content of the file : 
		 * text content, (can display right away as the content of a tag)
		 * image content (display in img tag)
		 * pdf content (use library, or down load the file then open it)
		 * I can preview it : in Network tab at browser at the Preview 
		 * 
		 */
		
		// I must converts the byte[] Array , to String 
		// see the implementation inside Arrays.toString(x)
		// And let the FrontENd , convert the byteArray to an image so I can display it on the page
		
		DataBaseAttachmentEntity dataBaseAttachmentEntity = storageService.downloadAttachmentFromDB(attachmentId);
		String byteArrayAsString = Arrays.toString(dataBaseAttachmentEntity.getData());
				
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(dataBaseAttachmentEntity.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dataBaseAttachmentEntity.getFileName() + "\"")
				.body(byteArrayAsString);
	}

	/**********************************************************
	 * 
	 * Upload/Download using File System
	 * 
	 **********************************************************/

	@PostMapping("/fileSystem/upload")
	public ResponseEntity<?> uploadAttachmentToFileSystem(@RequestParam("attachment") MultipartFile multipartFile) throws IOException {

		FileSystemAttachmentEntity fileSystemAttachmentEntity = storageService.uploadAttachmentToFileSystem(multipartFile);

		// Here I setup the download URL
		// Where FrontEnd will click the link
		// and will download the file
		String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/fileSystem/download/") // this path need to same path of the @GetMapping
				.path(fileSystemAttachmentEntity.getFileName())
				.toUriString();

		ResponseData responseData = new ResponseData(
				fileSystemAttachmentEntity.getId(),
				fileSystemAttachmentEntity.getFileName(), 
				downloadUrl, 
				multipartFile.getContentType(), 
				multipartFile.getSize());

		return ResponseEntity.status(HttpStatus.OK).body(responseData);
	}

	@GetMapping("/fileSystem/download/{fileName}")
	public ResponseEntity<?> downloadAttachmentFromFileSystem(@PathVariable String fileName) throws IOException {
		FileSystemAttachmentEntity fileSystemAttachmentEntity = storageService.downloadAttachmentFromFileSystem(fileName);
		String filePath = fileSystemAttachmentEntity.getFilePath();
		byte[] data = Files.readAllBytes(new File(filePath).toPath());

		return ResponseEntity
				.ok()
				.contentType(MediaType.parseMediaType(fileSystemAttachmentEntity.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileSystemAttachmentEntity.getFileName() + "\"")
				.body(new ByteArrayResource(data));

	}
}

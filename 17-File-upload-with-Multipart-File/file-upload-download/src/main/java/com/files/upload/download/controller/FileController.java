package com.files.upload.download.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.files.upload.download.entity.FileSystemAttachmentEntity;
import com.files.upload.download.model.ResponseData;
import com.files.upload.download.service.StorageService;

public class FileController {

	@Autowired
	private StorageService storageService;

	/**********************************************************
	 * 
	 * Upload/Download using Data Base
	 * 
	 **********************************************************/

	@PostMapping
	public ResponseEntity<?> uploadAttachmentToDB(@RequestParam("attachment") MultipartFile file) throws IOException {
		String uploadImage = storageService.uploadAttachmentToDB(file);
		return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
	}

	@GetMapping("/{fileName}")
	public ResponseEntity<?> downloadAttachmentFromDB(@PathVariable String fileName) {
		byte[] imageData = storageService.downloadAttachmentFromDB(fileName);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);

	}

	/**********************************************************
	 * 
	 * Upload/Download using File System
	 * 
	 **********************************************************/

	@PostMapping("/fileSystem")
	public ResponseEntity<?> uploadAttachmentToFileSystem(@RequestParam("attachment") MultipartFile multipartFile)
			throws IOException {
		FileSystemAttachmentEntity fileSystemAttachmentEntity = storageService.uploadAttachmentToFileSystem(multipartFile);

		String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/download/") // this path need to same path of the @GetMapping
				.path(fileSystemAttachmentEntity.getFileName())
				.toUriString();

		ResponseData responseData = new ResponseData(
				fileSystemAttachmentEntity.getFileName(), 
				downloadUrl, 
				multipartFile.getContentType(), 
				multipartFile.getSize());

		return ResponseEntity.status(HttpStatus.OK).body(responseData);
	}

	@GetMapping("/fileSystem/{fileName}")
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

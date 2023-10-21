package com.filesystem.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.filesystem.entity.FileSystemAttachmentEntity;
import com.filesystem.model.ResponseData;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.filesystem.service.FileService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileController {

	@Autowired
	private FileService fileService;

	@PostMapping("/fileSystem/upload")
	public ResponseEntity<?> uploadToFileSystem(@RequestParam("file") MultipartFile file) throws IOException {

		FileSystemAttachmentEntity fileData = fileService.uploadToFileSystem(file);

		String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/fileSystem/download/") // this path need to same path of the @GetMapping
				.path(fileData.getName())
				.toUriString();

		ResponseData responseData = new ResponseData(fileData.getName(), downloadUrl, file.getContentType(), file.getSize());

		return ResponseEntity.status(HttpStatus.OK).body(responseData);
	}

	@GetMapping("/fileSystem/download/{fileName}")
	public ResponseEntity<?> downloadFromFileSystem(@PathVariable String fileName) throws IOException {

		FileSystemAttachmentEntity fileSystemAttachmentEntity = fileService.downloadFromFileSystem(fileName);
		String filePath = fileSystemAttachmentEntity.getFilePath();
		byte[] data = Files.readAllBytes(new File(filePath).toPath());

		return ResponseEntity
				.ok()
				.contentType(MediaType.parseMediaType(fileSystemAttachmentEntity.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileSystemAttachmentEntity.getName() + "\"")
				.body(new ByteArrayResource(data));
	}
	
	
	
	
}

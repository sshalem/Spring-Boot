package com.filesystem.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.filesystem.entity.FileSystemAttachmentEntity;
import com.filesystem.model.DataFile;
import com.filesystem.model.ResponseData;
import com.filesystem.service.FileService;
import com.filesystem.utils.SysoutToJsonFormat;

@RestController
@CrossOrigin("*")
public class FileController {

	@Autowired
	private FileService fileService;

	@PostMapping("/fileSystem/upload")
	public ResponseEntity<?> uploadAttachmentToFileSystem(@RequestBody DataFile dataFile) throws IOException {

//		SysoutToJsonFormat.jsonFormat(dataFile);
//		System.out.println(dataFile);
		
		
		
		FileSystemAttachmentEntity fileSystemAttachmentEntity = fileService.uploadToFileSystem(dataFile);

		System.out.println(fileSystemAttachmentEntity);
		
		/**
		 * Here I setup the download URL
		 * Where FrontEnd will click the link
		 * and will download the file
		 */
		String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/fileSystem/download/") // this path need to same path of the @GetMapping
				.path(fileSystemAttachmentEntity.getName())
				.toUriString();

		ResponseData responseData = new ResponseData(
				fileSystemAttachmentEntity.getId(),
				fileSystemAttachmentEntity.getName(), 
				downloadUrl, 
				fileSystemAttachmentEntity.getType(), 
				fileSystemAttachmentEntity.getSize());

		return ResponseEntity.status(HttpStatus.OK).body(responseData);
	}

	
	/**
	 * 
	 * This method is used for downloading the image from server
	 * the url MUST be same as the downloadURl I define in the POST method uploadAttachmentToFileSystem 
	 * 
	 */
	@GetMapping("/fileSystem/download/{fileName}")
	public ResponseEntity<?> downloadAttachmentFromFileSystem(@PathVariable String fileName) throws IOException {
		FileSystemAttachmentEntity fileSystemAttachmentEntity = fileService.downloadFromFileSystem(fileName);
		String filePath = fileSystemAttachmentEntity.getFilePath();
		byte[] data = Files.readAllBytes(new File(filePath).toPath());

		return ResponseEntity
				.ok()
				.contentType(MediaType.parseMediaType(fileSystemAttachmentEntity.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileSystemAttachmentEntity.getName() + "\"")
				.body(new ByteArrayResource(data));

	}
	
	/**
	 * 
	 * This method I use to load an image from server
	 * And display it in an <img> tag as Base64
	 * 
	 */
	@GetMapping(path = "/fileSystem/loadAttachment/{fileName}")
	public ResponseEntity<?> loadAttachmentFromFileSystem(@PathVariable String fileName) throws Exception {

		FileSystemAttachmentEntity fileSystemAttachmentEntity = fileService.downloadFromFileSystem(fileName);
		String filePath = fileSystemAttachmentEntity.getFilePath();
		byte[] data = Files.readAllBytes(new File(filePath).toPath());
		
		String base64String = Base64.getEncoder().encodeToString(data);
		
		return ResponseEntity.ok().body(base64String);
	}
		
}

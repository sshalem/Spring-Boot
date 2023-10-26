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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.filesystem.service.FileService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin("*")
public class FileController {

	@Autowired
	private FileService fileService;

	@PostMapping("/fileSystem/upload")
	public ResponseEntity<?> uploadToFileSystem(@RequestParam("attachment") MultipartFile file) throws IOException {
 
		/**
		 * the @RequestParam("attachment") comes from frontEnd code:
		 *  `formData.append('attachment', selectedFile);
		 */
		
		FileSystemAttachmentEntity fileSystemAttachmentEntity = fileService.uploadToFileSystem(file);
		
		// Here I setup the download URL
		// Where FrontEnd will click the link
		// and will download the file
		String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/fileSystem/download/") // this path need to same path of the @GetMapping
				.path(fileSystemAttachmentEntity.getName())
				.toUriString();

		ResponseData responseData = new ResponseData(
				fileSystemAttachmentEntity.getId(),
				fileSystemAttachmentEntity.getName(), 
				downloadUrl, 
				file.getContentType(), 
				file.getSize());

		return ResponseEntity.status(HttpStatus.OK).body(responseData);
	}

	@GetMapping("/fileSystem/download/{fileName}")
	public ResponseEntity<?> downloadFromFileSystem(@PathVariable String fileName) throws IOException {

		FileSystemAttachmentEntity fileSystemAttachmentEntity = fileService.downloadFromFileSystem(fileName);
		String filePath = fileSystemAttachmentEntity.getFilePath();
		byte[] data = Files.readAllBytes(new File(filePath).toPath());
		ByteArrayResource returnedValue = new ByteArrayResource(data);
		/**
		 * I return in the response ByteArrayResource which build of byte[].
		 * What it means?
		 * This means , I will get the content of the file : 
		 * text content, (can display right away as the content of a tag)
		 * image content (display in img tag)
		 * pdf content (use library, or down load the file then open it)
		 * I can preview it : in Network tab at browser at the Preview 
		 * 
		 */
		return ResponseEntity
				.ok()
				.contentType(MediaType.parseMediaType(fileSystemAttachmentEntity.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileSystemAttachmentEntity.getName() + "\"")
				.body(returnedValue);
	}
		
}

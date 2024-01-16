package com.database.upload.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.database.upload.entity.DataBaseAttachmentEntity;
import com.database.upload.model.ResponseData;
import com.database.upload.service.StorageService;

@RestController
@CrossOrigin("*")
public class FileController {

	@Autowired
	private StorageService storageService;


	@PostMapping(path = "/database/upload")
	public ResponseEntity<?> uploadAttachmentToDB(@RequestParam("attachment") MultipartFile multipartFile) throws Exception {

		/**
		 *  the @RequestParam("attachment") comes from frontEnd code:
		 *  `formData.append('attachment', selectedFile);
		 */
		DataBaseAttachmentEntity dataBaseAttachmentEntity = storageService.uploadAttachmentToDB(multipartFile);
		
		/**
		 * Here I setup the download URL
		 * Where FrontEnd will click the link
		 * and will download the file
		 */
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

	/**
	 * 
	 * This method is used for downloading the image from server
	 * the url MUST be same as the downloadURl I define in the POST method uploadAttachmentToDB 
	 * 
	 */
	@GetMapping(path = "/database/download/{attachmentId}")
	public ResponseEntity<?> downloadAttachmentFromDB(@PathVariable String attachmentId) throws Exception {

		DataBaseAttachmentEntity dataBaseAttachmentEntity = storageService.downloadAttachmentFromDB(attachmentId);
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(dataBaseAttachmentEntity.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dataBaseAttachmentEntity.getFileName() + "\"")
				.body(new ByteArrayResource(dataBaseAttachmentEntity.getData()));
	}
	
	
	/**
	 * 
	 * This method I use to load an image from server
	 * And display it in an <img> tag as Base64
	 * 
	 */
	@GetMapping(path = "/database/loadAttachment/{attachmentId}")
	public ResponseEntity<?> loadAttachmentFromDB(@PathVariable String attachmentId) throws Exception {

		// I must converts the byte[] Array , to String 
		// see the implementation inside Arrays.toString(x)
		// And let the FrontENd , convert the byteArray to an image so I can display it on the page
		
		DataBaseAttachmentEntity dataBaseAttachmentEntity = storageService.downloadAttachmentFromDB(attachmentId);

		// Option 1 Best Practice: 
		// convert Byte[] to Base64 String type	
		String base64String = Base64.getEncoder().encodeToString(dataBaseAttachmentEntity.getData());
		
		// Option 2: 
		// convert Byte[] to String		
		// String byteArrayAsString = Arrays.toString(dataBaseAttachmentEntity.getData());
		
		// return ResponseEntity.ok()
		//		.contentType(MediaType.parseMediaType(dataBaseAttachmentEntity.getFileType()))
		//		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dataBaseAttachmentEntity.getFileName() + "\"")
		//		.body(byteArrayAsString);
					
		return ResponseEntity.ok().body(base64String);
	}

}

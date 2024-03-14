package com.google.drive.api.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.drive.api.service.GoogleService;

@RestController
@RequestMapping("/")
public class GoogleDriveController {

	@Autowired
	private GoogleService googleService;

	@PostMapping("/uploadToGoogleDrive")
	public ResponseEntity<?> fileUpload(@RequestParam("image") MultipartFile multipartFile) {
		if (multipartFile.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		try {
			return ResponseEntity.status(HttpStatus.OK).body(googleService.uploadFileToDrive(multipartFile));
		} catch (GeneralSecurityException | IOException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@GetMapping("getAllFilesList")
	public ResponseEntity<?> getAllFilesList() throws IOException, GeneralSecurityException {
		return ResponseEntity.ok(googleService.getAllFilesList());
	}
	
	
}

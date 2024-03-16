package com.google.drive.api.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.drive.api.model.ResponseData;

@Service
public class GoogleService {

	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

	private static final String CREDENTIALS_FILE_KEY_PATH = getPathToGoogleCredentials();

	private Set<String> SCOPES = Collections.singleton(DriveScopes.DRIVE);

	/**************************************************************
	 * get the directory where the the credentials.json resides
	 **************************************************************/
	private static String getPathToGoogleCredentials() {
		String currentDirectory = System.getProperty("user.dir");
		Path filePath = Paths.get(currentDirectory, "credentials.json");
		return filePath.toString();
	}
	
	
	/*****************************************
	 * upload File To google Drive operation
	 *****************************************/
	public String uploadFileToDrive(MultipartFile multipartFile) throws GeneralSecurityException, IOException {

		try {
			/**
			 * this folderId is taken from google drive account where I created the
			 * "files_upload" folder.
			 * https://drive.google.com/drive/folders/1tjW0MfAqpG6aggXTuNSAyu7bZxCqkcy7 . I
			 * take only the id which is : 1tjW0MfAqpG6aggXTuNSAyu7bZxCqkcy7
			 */
			String folderId = "1tjW0MfAqpG6aggXTuNSAyu7bZxCqkcy7";

			Drive drive = createDriveInstance();

			/**
			 * File - used from from package of com.google.api.services.drive.model.File
			 * File - and not from Java.io package
			 */
			File googleFileMetaData = new File();

			googleFileMetaData.setName(multipartFile.getOriginalFilename());
			googleFileMetaData.setParents(Collections.singletonList(folderId));

			/**
			 * File - used from "com.google.api.services.drive.model.File"
			 * AbstractInputStreamContent - used from
			 * "com.google.api.client.http.AbstractInputStreamContent" create(File content,
			 * AbstractInputStreamContent mediaContent)
			 */
			String contentType = multipartFile.getContentType();

			// first get the bytes[] of the multipartFile and place them in a
			// ByteArrayInputStream
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(multipartFile.getBytes());

			InputStreamContent inputStreamContent = new InputStreamContent(contentType, byteArrayInputStream);

			/**
			 * File - used from "com.google.api.services.drive.model.File"
			 * AbstractInputStreamContent - used from "com.google.api.client.http.AbstractInputStreamContent" 
			 * create(File content, AbstractInputStreamContent mediaContent)
			 */
			// This saves the file in google drive
			File uploadedFile = drive
					.files()					
					.create(googleFileMetaData, inputStreamContent)					
					.setFields("id")					
					.execute();
			
			String imageUrl = "https://drive.google.com/uc?export=view&id=" + uploadedFile.getId();
			System.out.println("IMAGE URL: " + imageUrl);

			return imageUrl;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
	}


	/******************************************
	 * get List Of Files from google drive
	 ******************************************/
	public List<File> getListOfFiles() throws GeneralSecurityException, IOException {		
		Drive drive = createDriveInstance();
		
		// I can set the fields according the Google Drive API Rest overview
		// https://developers.google.com/drive/api/reference/rest/v3/files
		
		// (1) Since I don't use setFields() , thus,  This will return to FrontEnd a JSON with following fields : id, kind, mimeType, name
		List<File> files = drive.files().list().execute().getFiles();	
		
		// (2) This will return to FrontEnd a JSON with field : id
		// List<File> files = drive.files().list().setFields("files(id)").execute().getFiles();	
				
		// (3) This will return to FrontEnd a JSON with field : id, kind, name, mimeType, thumbnailLink, originalFilename		 	
		// List<File> files = drive.files().list().setFields("files(id,kind,name,mimeType,thumbnailLink,originalFilename)").execute().getFiles();
		
		files.forEach(file -> System.out.println(file));
		
		return files;
	}
	
	/******************************************
	 * Download file from google drive 
	 ******************************************/
	public ResponseData downloadFileFromGoogleDrive(String fileId) throws GeneralSecurityException, IOException {	
		
		Drive drive = createDriveInstance();		
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();		
		drive.files().get(fileId).executeMediaAndDownloadTo(byteArrayOutputStream);		
				
		// I use the filter in order to get the mimeType and the name of the file I want to download
		List<File> list = drive
				.files()
				.list()
				.execute()
				.getFiles()
				.stream().filter(file -> file.getId().equals(fileId)).collect(Collectors.toList());
		
		System.out.println(list);
		
		return ResponseData
				.builder()
				.setStream(byteArrayOutputStream)
				.setMimeType(list.get(0).getMimeType())
				.setFileName(list.get(0).getName())
				.build();
	}
	
	/******************************************
	 * Delete file from google drive operation
	 ******************************************/	
	public void deleteFile(String fileId) throws GeneralSecurityException, IOException {		
		Drive drive = createDriveInstance();	
		drive.files().delete(fileId).execute();		
	}
	
	
	private Drive createDriveInstance() throws GeneralSecurityException, IOException {

		FileInputStream fileInputStream = new FileInputStream(CREDENTIALS_FILE_KEY_PATH);
		GoogleCredentials googleCredentials = GoogleCredentials.fromStream(fileInputStream).createScoped(SCOPES);
		HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(googleCredentials);
		NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

		// setApplicationName - same as the name I gave in GCP console "spring-google-drive-upload"
		return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, requestInitializer).setApplicationName("spring-google-drive-upload").build();
	}
}

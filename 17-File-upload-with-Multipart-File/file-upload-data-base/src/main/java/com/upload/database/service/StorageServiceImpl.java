package com.upload.database.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.upload.database.entity.DataBaseAttachmentEntity;
import com.upload.database.entity.FileSystemAttachmentEntity;
import com.upload.database.repository.DataBaseRepository;
import com.upload.database.repository.FileSystemRepository;

@Service
public class StorageServiceImpl implements StorageService {

	private final String FOLDER_PATH = "c:/Localdata/";
	
	@Autowired
	private DataBaseRepository dataBaseRepository;
	
	@Autowired
	private FileSystemRepository fileSystemRepository;

	/**********************************************************
	 * 
	 * Upload/Download using Data Base
	 * 
	 **********************************************************/
	@Override
	public DataBaseAttachmentEntity uploadAttachmentToDB(MultipartFile multipartFile) throws Exception {

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

		try {
			if (fileName.contains("..")) {
				throw new Exception("Filename contains invalid path sequence " + fileName);
			}
			DataBaseAttachmentEntity dataBaseAttachmentEntity = new DataBaseAttachmentEntity(fileName, multipartFile.getContentType(), multipartFile.getBytes());
			return dataBaseRepository.save(dataBaseAttachmentEntity);
		} catch (Exception e) {
			throw new Exception("Could not save File: " + fileName);
		}
	}

	@Override
	public DataBaseAttachmentEntity downloadAttachmentFromDB(String attachmentId) throws Exception {
		return dataBaseRepository.findById(attachmentId).orElseThrow(() -> new Exception("File not found with Id: " + attachmentId));
	}

	/**********************************************************
	 *
	 * Upload/Download using File System
	 * 
	 **********************************************************/

	@Override
	public FileSystemAttachmentEntity uploadAttachmentToFileSystem(MultipartFile multipartFile) throws IllegalStateException, IOException {

		// clean path : removes any `/` or `.` from url
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		String path = FOLDER_PATH + fileName;

		FileSystemAttachmentEntity fileSystemAttachmentEntity = new FileSystemAttachmentEntity();
		fileSystemAttachmentEntity.setFileName(fileName);
		fileSystemAttachmentEntity.setFilePath(path);
		fileSystemAttachmentEntity.setFileType(multipartFile.getContentType());

		FileSystemAttachmentEntity returnedValue = fileSystemRepository.save(fileSystemAttachmentEntity);

		// this saves the file in the the filePath I declare
		// transferTo - a method from `MultipartFile` class
		multipartFile.transferTo(new File(path));

		return returnedValue;
	}

	@Override
	public FileSystemAttachmentEntity downloadAttachmentFromFileSystem(String attachmentName) {
		FileSystemAttachmentEntity returnedValue = fileSystemRepository.findByFileName(attachmentName).get();
		return returnedValue;
	}
}

package com.filesystem.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.filesystem.entity.FileSystemAttachmentEntity;
import com.filesystem.repository.FileDataRepository;

@Service
public class FileService {

	@Autowired
	private FileDataRepository fileDataRepository;
	
	private final String FOLDER_PATH = "c:/Localdata/";
	
	public FileSystemAttachmentEntity uploadToFileSystem(MultipartFile file) throws IOException {
		
		// clean path removes any `/` or `.` from url
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		String path = FOLDER_PATH + fileName;
		
		FileSystemAttachmentEntity fileSystemAttachmentEntity = new FileSystemAttachmentEntity();
		fileSystemAttachmentEntity.setName(fileName);
		fileSystemAttachmentEntity.setFilePath(path);
		fileSystemAttachmentEntity.setType(file.getContentType());

		FileSystemAttachmentEntity returnedFileDataEntity = fileDataRepository.save(fileSystemAttachmentEntity);

		// this saves the file in the the filePath I declare
		// transferTo - a method from `MultipartFile` class 
		file.transferTo(new File(path));

        return returnedFileDataEntity;
    }

	public FileSystemAttachmentEntity downloadFromFileSystem(String fileName) throws IOException {

		FileSystemAttachmentEntity fileDataEntity = fileDataRepository.findByName(fileName).get();
		return fileDataEntity;
	}
}

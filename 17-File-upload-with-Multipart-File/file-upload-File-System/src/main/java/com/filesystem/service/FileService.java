package com.filesystem.service;

import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.filesystem.entity.FileDataEntity;
import com.filesystem.repository.FileDataRepository;

@Service
public class FileService {

	@Autowired
	private FileDataRepository fileDataRepository;
	
	private final String FOLDER_PATH = "c:/Localdata/";
	
	public FileDataEntity uploadToFileSystem(MultipartFile file) throws IOException {
		
		String path = FOLDER_PATH + file.getOriginalFilename();
		
		FileDataEntity fileDataEntity = new FileDataEntity();
		fileDataEntity.setName(file.getOriginalFilename());
		fileDataEntity.setFilePath(path);
		fileDataEntity.setType(file.getContentType());

		FileDataEntity returnedFileDataEntity = fileDataRepository.save(fileDataEntity);

		// this saves the file in the the filePath I declare
		// transferTo - a method from `MultipartFile` class 
		file.transferTo(new File(path));

        return returnedFileDataEntity;
    }

	public FileDataEntity downloadFromFileSystem(String fileName) throws IOException {

		FileDataEntity fileDataEntity = fileDataRepository.findByName(fileName).get();
		return fileDataEntity;
	}
}

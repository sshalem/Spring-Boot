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
		
		String filePath = FOLDER_PATH + file.getOriginalFilename();
		
		FileDataEntity fileData = new FileDataEntity();
		fileData.setName(file.getOriginalFilename());
		fileData.setFilePath(filePath);
		fileData.setType(file.getContentType());

		FileDataEntity returnedFileData = fileDataRepository.save(fileData);

		file.transferTo(new File(filePath));

        return returnedFileData;
    }

	public FileDataEntity downloadFromFileSystem(String fileName) throws IOException {

		FileDataEntity fileData = fileDataRepository.findByName(fileName).get();
		return fileData;
	}
}

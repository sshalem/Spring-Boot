package com.filesystem.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.filesystem.entity.FileData;
import com.filesystem.repository.FileDataRepository;

@Service
public class FileService {

	@Autowired
	private FileDataRepository fileDataRepository;
	
	private final String FOLDER_PATH = "c:/Localdata";
	
	public String uploadImageToFileSystem(MultipartFile file) throws IOException {
		
		String filePath = FOLDER_PATH + file.getOriginalFilename();
		
		FileData fileData = new FileData();
		fileData.setName(file.getOriginalFilename());
		fileData.setFilePath(filePath);
		fileData.setType(file.getContentType());
		
		FileData _fileData = fileDataRepository.save(fileData);

		file.transferTo(new File(filePath));
		if (_fileData != null) {
			return "file uploaded successfully : " + filePath;
		}
		return null;
	}

	public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
		Optional<FileData> fileData = fileDataRepository.findByName(fileName);
		String filePath = fileData.get().getFilePath();
		byte[] images = Files.readAllBytes(new File(filePath).toPath());
		return images;
	}
}

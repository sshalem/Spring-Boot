package com.filesystem.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.filesystem.entity.FileSystemAttachmentEntity;
import com.filesystem.model.DataFile;
import com.filesystem.repository.FileDataRepository;

@Service
public class FileService {

	@Autowired
	private FileDataRepository fileDataRepository;
	
	private final String FOLDER_PATH = System.getProperty("user.dir");

	public FileSystemAttachmentEntity uploadToFileSystem(DataFile file) throws IOException {
		
		// cleanPath removes any `/` or `.` from url
		String fileName = StringUtils.cleanPath(file.getName());
						
		// String path = FOLDER_PATH + "/src/main/resources/" + fileName;
		String path = FOLDER_PATH + "/src/main/resources/" + fileName;
		
		FileSystemAttachmentEntity fileSystemAttachmentEntity = new FileSystemAttachmentEntity();
		fileSystemAttachmentEntity.setName(fileName);
		fileSystemAttachmentEntity.setFilePath(path);
		fileSystemAttachmentEntity.setType(file.getType());
		fileSystemAttachmentEntity.setSize(file.getSize());

		FileSystemAttachmentEntity returnedFileDataEntity = fileDataRepository.save(fileSystemAttachmentEntity);

		/**
		 * I convert the Bse64 file to a real Image 
		 */
		String base64Image = file.getImage().substring(22);
		byte[] decodeData = Base64.getDecoder().decode(base64Image);
		
		/**
		 * This code writes the image, to a file 
		 */
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(path);
			for (int i = 0; i < decodeData.length; i++) {
				out.write(decodeData[i]);
			}
		} finally {
			if (out != null)
				out.close();
		}

        return returnedFileDataEntity;
    }

	public FileSystemAttachmentEntity downloadFromFileSystem(String fileName) throws IOException {

		FileSystemAttachmentEntity fileDataEntity = fileDataRepository.findByName(fileName).get();
		return fileDataEntity;
	}
}

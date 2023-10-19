package com.database.upload.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.database.upload.entity.DataBaseAttachmentEntity;
import com.database.upload.repository.DataBaseRepository;

@Service
public class StorageServiceImpl implements StorageService {

	
	@Autowired
	private DataBaseRepository dataBaseRepository;


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

}

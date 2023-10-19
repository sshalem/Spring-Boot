package com.database.upload.service;

import org.springframework.web.multipart.MultipartFile;

import com.database.upload.entity.DataBaseAttachmentEntity;

public interface StorageService {

    DataBaseAttachmentEntity uploadAttachmentToDB(MultipartFile multipartFile) throws Exception;
    DataBaseAttachmentEntity downloadAttachmentFromDB(String attachmentId) throws Exception;
 
}

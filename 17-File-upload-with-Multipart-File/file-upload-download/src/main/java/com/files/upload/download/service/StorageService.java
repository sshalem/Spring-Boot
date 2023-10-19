package com.files.upload.download.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;
import com.files.upload.download.entity.DataBaseAttachmentEntity;
import com.files.upload.download.entity.FileSystemAttachmentEntity;

public interface StorageService {

    DataBaseAttachmentEntity uploadAttachmentToDB(MultipartFile multipartFile) throws Exception;
    DataBaseAttachmentEntity downloadAttachmentFromDB(String attachmentId) throws Exception;
    FileSystemAttachmentEntity uploadAttachmentToFileSystem(MultipartFile multipartFile) throws IllegalStateException, IOException;
    FileSystemAttachmentEntity downloadAttachmentFromFileSystem(String attachmentName);
}

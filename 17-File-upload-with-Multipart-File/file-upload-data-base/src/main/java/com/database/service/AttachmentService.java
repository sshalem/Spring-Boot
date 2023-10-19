package com.database.service;

import org.springframework.web.multipart.MultipartFile;
import com.database.entity.Attachment;

public interface AttachmentService {

	Attachment saveAttachment(MultipartFile multipartFile) throws Exception;

	Attachment getAttachment(String fileId) throws Exception;
}

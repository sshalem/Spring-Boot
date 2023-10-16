package com.file.service;

import com.file.entity.Attachment;
import com.file.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Override
    public Attachment saveAttachment(MultipartFile file) throws Exception {
        System.out.println(file);
        System.out.println(file.getOriginalFilename());
        byte[] bytes = file.getBytes();
        System.out.println(file.getContentType());
        System.out.println(file.getSize());

        String name = file.getName();
        System.out.println(name);

        Resource resource = file.getResource();
        System.out.println(file.getResource());


        System.out.println(file.getInputStream());

        System.out.println();

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new Exception("Filename contains invalid path sequence " + fileName);
            }
            Attachment attachment = new Attachment(fileName, file.getContentType(), file.getBytes());
            return attachmentRepository.save(attachment);
        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }
    }

    @Override
    public Attachment getAttachment(String fileId) throws Exception {
        return attachmentRepository.findById(fileId).orElseThrow(() -> new Exception("File not found with Id: " + fileId));
    }
}

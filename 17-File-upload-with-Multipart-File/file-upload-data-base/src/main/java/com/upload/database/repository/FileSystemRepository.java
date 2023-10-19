package com.upload.database.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upload.database.entity.FileSystemAttachmentEntity;

@Repository
public interface FileSystemRepository extends JpaRepository<FileSystemAttachmentEntity, String> {

	Optional<FileSystemAttachmentEntity> findByFileName(String fileName);
}

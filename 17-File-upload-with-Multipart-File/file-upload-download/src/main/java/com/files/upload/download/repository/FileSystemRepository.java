package com.files.upload.download.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.files.upload.download.entity.FileSystemAttachmentEntity;

@Repository
public interface FileSystemRepository extends JpaRepository<FileSystemAttachmentEntity, String> {

	Optional<FileSystemAttachmentEntity> findByName(String fileName);
}

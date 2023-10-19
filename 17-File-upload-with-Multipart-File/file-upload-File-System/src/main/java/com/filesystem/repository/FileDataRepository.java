package com.filesystem.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.filesystem.entity.FileSystemAttachmentEntity;

@Repository
public interface FileDataRepository extends JpaRepository<FileSystemAttachmentEntity, Long> {

	Optional<FileSystemAttachmentEntity> findByName(String fileName);
}

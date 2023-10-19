package com.files.upload.download.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.files.upload.download.entity.DataBaseAttachmentEntity;

@Repository
public interface DataBaseRepository extends JpaRepository<DataBaseAttachmentEntity, String> {

}

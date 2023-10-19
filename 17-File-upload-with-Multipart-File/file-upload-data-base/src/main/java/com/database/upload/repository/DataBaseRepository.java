package com.database.upload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.database.upload.entity.DataBaseAttachmentEntity;

@Repository
public interface DataBaseRepository extends JpaRepository<DataBaseAttachmentEntity, String> {

}

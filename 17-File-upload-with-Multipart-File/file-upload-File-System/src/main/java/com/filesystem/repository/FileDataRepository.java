package com.filesystem.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.filesystem.entity.FileDataEntity;

@Repository
public interface FileDataRepository extends JpaRepository<FileDataEntity, Long> {

	Optional<FileDataEntity> findByName(String fileName);
}

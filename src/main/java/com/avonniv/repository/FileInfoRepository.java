package com.avonniv.repository;

import com.avonniv.domain.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
    Optional<FileInfo> findOneByPath(String path);
}

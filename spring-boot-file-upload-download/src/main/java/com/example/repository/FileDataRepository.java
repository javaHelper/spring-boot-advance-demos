package com.example.repository;

import com.example.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.File;
import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileData, Integer> {
    Optional<FileData> findByName(String filename);
}
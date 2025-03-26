package com.example.service;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.MigrationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FlywayService {

    @Autowired
    private Flyway flyway;

    public Page<MigrationInfo> getMigrationsPage(Pageable pageable) {
        MigrationInfoService infoService = flyway.info();
        List<MigrationInfo> allMigrations = Arrays.asList(infoService.all());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), allMigrations.size());

        List<MigrationInfo> pageContent = allMigrations.subList(start, end);

        return new PageImpl<>(pageContent, pageable, allMigrations.size());
    }

    public List<MigrationInfo> getAllMigrations() {
        MigrationInfoService infoService = flyway.info();
        return Arrays.asList(infoService.all());
    }

    public MigrationInfo[] getPendingMigrations() {
        return flyway.info().pending();
    }

    public MigrationInfo[] getAppliedMigrations() {
        return flyway.info().applied();
    }
}
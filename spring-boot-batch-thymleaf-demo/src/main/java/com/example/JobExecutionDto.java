package com.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class JobExecutionDto {
    private Long executionId;
    private Long instanceId;
    private String jobName;
    private BatchStatus status;
    private ExitStatus exitStatus;
    private Date startTime;
    private Date endTime;
    private Date createTime;
    private Date lastUpdated;
    private String jobConfigurationName;
}
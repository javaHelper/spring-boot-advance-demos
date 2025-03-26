package com.example;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class BatchController {

    private final JobExplorer jobExplorer;
    private final JobRepository jobRepository;

    @GetMapping("/jobs")
    public String listJobs(Model model) {
        List<String> jobs = jobExplorer.getJobNames();
        model.addAttribute("jobs", jobs);
        return "jobs";
    }

    @GetMapping("/jobs/{jobName}")
    public String listJobInstances(@PathVariable String jobName, Model model) throws NoSuchJobException {
        List<JobInstance> jobInstances = jobExplorer.getJobInstances(jobName, 0, 100);
        
        model.addAttribute("jobName", jobName);
        model.addAttribute("jobInstances", jobInstances);
        return "jobInstances";
    }

    @GetMapping("/jobs/{jobName}/{instanceId}")
    public String listJobExecutions(@PathVariable String jobName, 
                                  @PathVariable Long instanceId, 
                                  Model model) {
        JobInstance jobInstance = jobExplorer.getJobInstance(instanceId);
        List<JobExecution> jobExecutions = jobExplorer.getJobExecutions(jobInstance);
        
        List<JobExecutionDto> jobExecutionDtos = jobExecutions.stream()
                .map(this::convertToDto)
                .sorted(Comparator.comparing(JobExecutionDto::getStartTime).reversed())
                .collect(Collectors.toList());
        
        model.addAttribute("jobName", jobName);
        model.addAttribute("instanceId", instanceId);
        model.addAttribute("jobExecutions", jobExecutionDtos);
        return "jobExecution";
    }

    @GetMapping("/execution/{executionId}")
    public String showJobExecution(@PathVariable Long executionId, Model model) 
            throws JobExecutionAlreadyRunningException, JobRestartException, 
                   JobInstanceAlreadyCompleteException, JobParametersInvalidException {
        
        // Get the JobExecution using JobExplorer instead of JobRepository
        JobExecution jobExecution = jobExplorer.getJobExecution(executionId);
        
        if (jobExecution == null) {
            throw new IllegalArgumentException("JobExecution not found for ID: " + executionId);
        }
        
        model.addAttribute("jobExecution", jobExecution);
        return "stepExecution";
    }

    private JobExecutionDto convertToDto(JobExecution jobExecution) {
        JobExecutionDto dto = new JobExecutionDto();
        dto.setExecutionId(jobExecution.getId());
        dto.setInstanceId(jobExecution.getJobInstance().getInstanceId());
        dto.setJobName(jobExecution.getJobInstance().getJobName());
        dto.setStatus(jobExecution.getStatus());
        dto.setExitStatus(jobExecution.getExitStatus());
        dto.setStartTime(jobExecution.getStartTime());
        dto.setEndTime(jobExecution.getEndTime());
        dto.setCreateTime(jobExecution.getCreateTime());
        dto.setLastUpdated(jobExecution.getLastUpdated());
        dto.setJobConfigurationName(jobExecution.getJobConfigurationName());
        return dto;
    }
}
package com.akazimour.JOB_MS.job.controller;


import com.akazimour.JOB_MS.job.dto.JobDto;
import com.akazimour.JOB_MS.job.mapper.JobMapper;
import com.akazimour.JOB_MS.job.model.Job;
import com.akazimour.JOB_MS.job.service.JobServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/jobs")
public class JobController {

    @Autowired
    JobMapper jobMapper;

    private final JobServiceImpl jobService;

    public JobController(JobServiceImpl jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    @Operation(summary = "Get all jobs", description = "Get all jobs")
    public List<JobDto> findAllJobs(){
        return jobMapper.JobListToJobDtoList(jobService.getAllJobs());
    }

    @PostMapping
    @Operation(summary = "Create new job", description = "Create new job")
    public JobDto createNewJob(@RequestBody Job job){
      return jobMapper.JobToJobDto(jobService.createJob(job));
    };

    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Update job", description = "Update job")
    public JobDto updateExistingJob(@RequestBody Job job, @PathVariable long id){
        return jobMapper.JobToJobDto(jobService.updateJob(job,id));
    };

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Delete job", description = "Delete job")
    public String removeJob(@PathVariable long id){
        return jobService.deleteJob(id);
    };

    @GetMapping(value = "/{id}")
    @Operation(summary = "Find job by id", description = "Find job by id")
    public JobDto findJobById(@PathVariable long id)  {
        return jobMapper.JobToJobDto(jobService.getJobById(id));
    };

    @GetMapping(value = "/byCompanyId/{id}")
    @Operation(summary = "List of jobs by company id", description = "List of jobs by company id")
    public List<JobDto> allJobsByCompanyId(@PathVariable long id){
        return jobMapper.JobListToJobDtoList(jobService.getJobsByCompId(id));
    }

}

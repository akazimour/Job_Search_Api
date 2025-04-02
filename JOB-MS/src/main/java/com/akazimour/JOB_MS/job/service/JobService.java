package com.akazimour.JOB_MS.job.service;



import com.akazimour.JOB_MS.job.model.Job;

import java.util.List;

public interface JobService {
 public List<Job> getAllJobs();
 public Job createJob(Job job);
 public Job updateJob(Job job, long id);
 public String deleteJob(long id);
 public Job getJobById(long id);
 public List<Job> getJobsByCompId(long id);

}

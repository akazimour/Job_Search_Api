package com.akazimour.JOB_MS.job.service;


import com.akazimour.JOB_MS.exception.IdCanNotBeNullException;
import com.akazimour.JOB_MS.exception.NoSuchModelException;
import com.akazimour.JOB_MS.job.clients.CompanyClient;
import com.akazimour.JOB_MS.job.dto.JobDto;
import com.akazimour.JOB_MS.job.model.Job;
import com.akazimour.JOB_MS.job.model.JobCategory;
import com.akazimour.JOB_MS.job.model.JobType;
import com.akazimour.JOB_MS.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    // RestTemplate restTemplate = new RestTemplate();

    @Autowired
    CompanyClient companyClient;
    @Autowired
    KafkaProducerServiceForJob kafkaProducerServiceForJob;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job createJob(Job job) {
        if (job == null) {
            return new Job(0, "Job is null", "Job is null", "Job is null", JobType.NONE, JobCategory.NONE);
        }
            Job savedJob = jobRepository.save(job);
            JobDto messageDto = new JobDto();
            messageDto.setCompanyId(savedJob.getCompanyId());
            messageDto.setId(savedJob.getId());
            messageDto.setTitle(savedJob.getTitle());
            messageDto.setDescription(savedJob.getDescription());
            messageDto.setJobCategory(savedJob.getJobCategory());
            messageDto.setJobType(savedJob.getJobType());
            messageDto.setLocation(savedJob.getLocation());
            kafkaProducerServiceForJob.sendMessage("job-topic", UUID.randomUUID(), messageDto);
        return savedJob;
    }

    @Override
    public Job updateJob(Job job, long id) {
        checkIfIdIsNull(id);
        Job jobResult = getJob(id);
        jobResult.setJobCategory(job.getJobCategory());
        jobResult.setJobType(job.getJobType());
        jobResult.setDescription(job.getDescription());
        jobResult.setLocation(job.getLocation());
        jobResult.setTitle(job.getTitle());
        jobRepository.save(jobResult);
        return jobResult;
    }

    @Override
    public String deleteJob(long id) {
        checkIfIdIsNull(id);
        if (getJobById(id) != null)
            jobRepository.deleteById(id);
        companyClient.deleteJobItem(id);
//        restTemplate.delete("http://localhost:8082/api/companies/remove/job/" + id, String.class);
        return "Job successfully deleted with id: " + id;
    }

    @Override
    public Job getJobById(long id) {
        checkIfIdIsNull(id);
        return getJob(id);
    }

    @Override
    public List<Job> getJobsByCompId(long id) {
        return jobRepository.findAllJobByCompanyId(id).orElseGet(ArrayList::new);
    }


    private Job getJob(long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new NoSuchModelException("No such Job with id: " + id));
    }

    private static void checkIfIdIsNull(long id) {
        if (id == 0) {
            throw new IdCanNotBeNullException("Id can not be 0!");
        }
    }
}

package com.akazimour.JOB_MS.job.initDB;

import com.akazimour.JOB_MS.job.model.Job;
import com.akazimour.JOB_MS.job.model.JobCategory;
import com.akazimour.JOB_MS.job.model.JobType;
import com.akazimour.JOB_MS.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitJobs {
    @Autowired
    JobRepository jobRepository;

   public void jobCreator(){

       Job tester = new Job();
       tester.setJobCategory(JobCategory.IT);
       tester.setLocation("Budapest");
       tester.setJobType(JobType.HYBRID);
       tester.setDescription("Tester description");
       tester.setTitle("Automated tester");
       jobRepository.save(tester);

       Job engineer = new Job();
       engineer.setJobCategory(JobCategory.ENGINEERING);
       engineer.setJobType(JobType.HYBRID);
       engineer.setLocation("Budapest");
       engineer.setDescription("Engineer description");
       engineer.setTitle("Product Engineer");
       jobRepository.save(engineer);

       Job developer = new Job();
       developer.setJobCategory(JobCategory.IT);
       developer.setJobType(JobType.REMOTE);
       developer.setLocation("Budapest");
       developer.setDescription("Developer description");
       developer.setTitle("Java Developer");
       jobRepository.save(developer);

       Job manager = new Job();
       manager.setJobCategory(JobCategory.ENGINEERING);
       manager.setJobType(JobType.HYBRID);
       manager.setLocation("Budapest");
       manager.setDescription("Product manager description");
       manager.setTitle("Product manager");
       jobRepository.save(manager);

       Job designer = new Job();
       designer.setJobCategory(JobCategory.ENGINEERING);
       designer.setJobType(JobType.HYBRID);
       designer.setLocation("Budapest");
       designer.setDescription("Designer description");
       designer.setTitle("Product designer");
       jobRepository.save(designer);

   }
}

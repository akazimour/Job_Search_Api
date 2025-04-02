package com.akazimour.JOB_MS;

import com.akazimour.JOB_MS.job.initDB.InitJobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class JobMsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JobMsApplication.class, args);
    }

    @Autowired
    InitJobs initJobs;

    @Override
    public void run(String... args) throws Exception {
       // initJobs.jobCreator();
    }
}

package com.akazimour.COMPANY_MS.company.clients;

import com.akazimour.COMPANY_MS.company.external.JobDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "JOB-MS",url = "${job-ms.url}")
public interface JobClient {

    @GetMapping(value = "/api/jobs/byCompanyId/{id}")
    List<JobDto> allJobsByCompanyId(@PathVariable long id);

    @GetMapping(value = "/api/jobs/{id}")
    JobDto findJobById(@PathVariable long id);

    @PostMapping(value = "/api/jobs")
    JobDto createNewJob(@RequestBody JobDto jobDto);

}

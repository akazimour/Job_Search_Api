package com.akazimour.JOB_MS.job.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANY-MS", url = "http://localhost:8082")
public interface CompanyClient {

    @DeleteMapping(value = "/api/companies/remove/job/{id}")
    public String deleteJobItem(@PathVariable long id);
}

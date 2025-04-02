package com.akazimour.REVIEW_MS.review.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANY-MS",url = "${company-ms.url}")
public interface CompanyClient {

    @DeleteMapping(value = "/api/companies/remove/review/{id}")
    public String deleteReviewItem(@PathVariable long id);
}

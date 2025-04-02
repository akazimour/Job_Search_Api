package com.akazimour.COMPANY_MS.company.clients;

import com.akazimour.COMPANY_MS.company.external.ReviewDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "REVIEW-MS",url = "${review-ms.url}")
public interface ReviewClient {

    @GetMapping(value = "/api/reviews/byCompanyId/{id}")
    List<ReviewDto> GetAllReviewBasedOnCompanyId(@PathVariable long id);

    @PostMapping(value = "/api/reviews")
    public ReviewDto createNewReview(@RequestBody ReviewDto reviewDto);
}

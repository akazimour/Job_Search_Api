package com.akazimour.COMPANY_MS.company.controller;

import com.akazimour.COMPANY_MS.company.dto.CompanyDto;
import com.akazimour.COMPANY_MS.company.dto.CompanyRating;
import com.akazimour.COMPANY_MS.company.dto.CompanyResponseWithDetails;
import com.akazimour.COMPANY_MS.company.external.ReviewDto;
import com.akazimour.COMPANY_MS.company.mapper.CompanyMapper;
import com.akazimour.COMPANY_MS.company.model.Company;
import com.akazimour.COMPANY_MS.company.service.CompanyServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/companies")
public class CompanyController {

    @Autowired
    CompanyMapper companyMapper;

    private final CompanyServiceImpl companyService;

    public CompanyController(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    @Operation(summary = "Get all companies", description = "Retrieve all companies with their jobs or reviews.")
    public List<CompanyResponseWithDetails> findAllCompanies(@RequestParam(value = "jobs", required = false) String jobs,
                                                             @RequestParam(value = "reviews", required = false) String reviews) {
        if (jobs == null && reviews == null) {
            return companyService.getAllCompaniesWithoutDetails();
        } else if (jobs == null) {
            return companyService.getAllCompaniesWithReviews();
        } else if (reviews == null) {
            return companyService.getAllCompaniesWithJobs();
        } else
            return companyService.getAllCompaniesWithoutDetails();
    }

    // COMPANY ENDPOINTS

    @PostMapping
    @Operation(summary = "Create a company", description = "Create a company")
    public CompanyDto createNewCompany(@RequestBody Company company) {
        return companyMapper.CompanyToCompanyDto(companyService.createCompany(company));
    }

    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Update a company by its id", description = "Update a company by its id")
    public CompanyDto updateExistingCompanyDetails(@RequestBody Company company, @PathVariable long id) {
        return companyMapper.CompanyToCompanyDto(companyService.updateCompanyDetails(company, id));
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Delete the company by its id", description = "Delete the company by its id")
    public String removeCompany(@PathVariable long id) {
        return companyService.deleteCompany(id);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Find company by id", description = "Find company by id")
    public CompanyDto findCompanyById(@PathVariable long id) {
        return companyMapper.CompanyToSummaryCompanyDto(companyService.getCompanyById(id));
    }

    @GetMapping(value = "/details/{id}")
    @Operation(summary = "Get one company by id and their details", description = "Find company by id and show its reviews or jobs")
    public CompanyResponseWithDetails findCompanyByIdWithDetails(@PathVariable long id, @RequestParam(value = "jobDetails", required = false) String jobDetails,
                                                                 @RequestParam(value = "reviewDetails", required = false) String reviewDetails) {
        if (jobDetails == null)
            return companyService.getCompanyByIdWithReviews(id);
        else if (reviewDetails == null) {
            return companyService.getCompanyByIdWithJobs(id);
        } else if (jobDetails == null && reviewDetails == null) {
            return companyService.getCompanySummary(id);
        } else
            return companyService.getCompanySummary(id);
    }

    @GetMapping(value = "/avgRate/{id}")
    @Operation(summary = "Get one company by id and its avg rate", description = "Get one company by id and its avg rate")
    public CompanyRating getCompanyWithAverageRating(@PathVariable long id) {
            return companyService.getCompanyWithAvgRating(id);
    }

    @PutMapping(value = "/add/{compId}/{jobId}")
    @Operation(summary = "Insert an existing job to the company using their id-s", description = "Insert an existing job to the company using their id-s")
    public String addNewJobToCompany(@PathVariable long compId, @PathVariable long jobId) {
        return companyService.insertJobToCompany(compId, jobId);
    }

    // REVIEW ENDPOINTS

    @PutMapping(value = "/add/review/{compId}")
    @Operation(summary = "Insert a review to an existing company", description = "Insert a review to an existing company")
    public String addReviewToCompany(@PathVariable long compId, @RequestBody ReviewDto reviewDto) {
        return companyService.insertReviewToCompany(compId, reviewDto);
    }

    @GetMapping(value = "/byReview/{id}")
    @Operation(summary = "Returns a company by review id", description = "Returns  a company by review id")
    public CompanyDto CompanyWithReviewByRevId(@PathVariable long id) {
        return companyMapper.CompanyToCompanyDto(companyService.findByReviewId(id));
    }

    @DeleteMapping(value = "/remove/review/{id}")
    @Operation(summary = "Delete a review from company by id", description = "Delete a review by id from company")
    public String deleteReviewItem(@PathVariable long id) {
        return companyService.findCompanyByReviewIdAndRemove(id);
    }

    // JOB ENDPOINT

    @DeleteMapping(value = "/remove/job/{id}")
    @Operation(summary = "Delete a job from company by id", description = "Delete a job by id from company")
    public String deleteJobItem(@PathVariable long id) {
        return companyService.findCompanyByJobIdAndRemove(id);
    }

    @GetMapping(value = "/feedback")
    @Operation(summary = "Testing the controller", description = "Testing the controller")
    public String testingResponse() {
        return "Hello from company controller";
    }

}

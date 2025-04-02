package com.akazimour.COMPANY_MS.company.service;
import com.akazimour.COMPANY_MS.company.dto.CompanyRating;
import com.akazimour.COMPANY_MS.company.dto.CompanyResponseWithDetails;
import com.akazimour.COMPANY_MS.company.external.ReviewDto;
import com.akazimour.COMPANY_MS.company.model.Company;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    List<Company> getAllCompanies();
    List<CompanyResponseWithDetails> getAllCompaniesWithReviews();
    List<CompanyResponseWithDetails> getAllCompaniesWithJobs();
    List<CompanyResponseWithDetails> getAllCompaniesWithoutDetails();
    Company createCompany(Company company);
    Company updateCompanyDetails(Company company, long id);
    String deleteCompany(long id);
    Company getCompanyById(long id);
    CompanyResponseWithDetails getCompanyByIdWithReviews(long id);
    CompanyResponseWithDetails getCompanyByIdWithJobs(long id);
    String insertJobToCompany(long companyId, long jobId);
    String insertReviewToCompany(long compId, ReviewDto reviewDto);
    CompanyRating getCompanyWithAvgRating(long compId);
    CompanyResponseWithDetails getCompanySummary(long id);
}

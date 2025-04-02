package com.akazimour.COMPANY_MS.company.service;

import com.akazimour.COMPANY_MS.company.clients.JobClient;
import com.akazimour.COMPANY_MS.company.clients.ReviewClient;
import com.akazimour.COMPANY_MS.company.dto.CompanyDto;
import com.akazimour.COMPANY_MS.company.dto.CompanyRating;
import com.akazimour.COMPANY_MS.company.dto.CompanyResponseWithDetails;
import com.akazimour.COMPANY_MS.company.dto.CompanySummary;
import com.akazimour.COMPANY_MS.company.external.JobDto;
import com.akazimour.COMPANY_MS.company.external.ReviewDto;
import com.akazimour.COMPANY_MS.company.mapper.CompanyMapper;
import com.akazimour.COMPANY_MS.company.model.Company;
import com.akazimour.COMPANY_MS.company.repository.CompanyRepository;
import com.akazimour.COMPANY_MS.exception.IdCanNotBeNullException;
import com.akazimour.COMPANY_MS.exception.NoSuchModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    RestTemplate restTemplate = new RestTemplate();
    private final CompanyMapper companyMapper;
    @Autowired
    JobClient jobClient;
    @Autowired
    ReviewClient reviewClient;


    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public List<CompanyResponseWithDetails> getAllCompaniesWithReviews() {
        List<CompanyResponseWithDetails> companyResponseWithReviews = new ArrayList<>();
        List<CompanyDto> companyDtos = companyMapper.CompanyListToCompanyDtoList(companyRepository.findAllCompanies());
        for (CompanyDto c : companyDtos) {
            List<ReviewDto> reviewDtoList = reviewClient.GetAllReviewBasedOnCompanyId(c.getId());
//            restTemplate.getForObject("http://localhost:8083/api/reviews/byCompanyId/" + c.getId(), List.class);
            CompanyResponseWithDetails response = new CompanyResponseWithDetails(
                    c.getId(),
                    c.getName(),
                    c.getDescription()
            );
            response.setReviewDtoList(reviewDtoList);
            companyResponseWithReviews.add(response);
        }
        return companyResponseWithReviews;
    }


    @Override
    public List<CompanyResponseWithDetails> getAllCompaniesWithJobs() {
        List<CompanyResponseWithDetails> companyResponseWithJobs = new ArrayList<>();
        List<CompanyDto> companyDtos = companyMapper.CompanyListToCompanyDtoList(companyRepository.findAllCompanies());
        for (CompanyDto c : companyDtos) {
            List<JobDto> jobDtoList = jobClient.allJobsByCompanyId(c.getId());
//            restTemplate.getForObject("http://localhost:8081/api/jobs/byCompanyId/" + c.getId(), List.class);
            CompanyResponseWithDetails response = new CompanyResponseWithDetails(
                    c.getId(),
                    c.getName(),
                    c.getDescription()
            );
            response.setJobDtoList(jobDtoList);
            companyResponseWithJobs.add(response);
        }
        return companyResponseWithJobs;
    }

    @Override
    public List<CompanyResponseWithDetails> getAllCompaniesWithoutDetails() {
        List<CompanyResponseWithDetails> sumCompanyList = new ArrayList<>();
        List<CompanyDto> companyDtos = companyMapper.CompanyListToSummaryCompanyDtoList(companyRepository.findAll());
        companyDtos.forEach(companyDto ->
                sumCompanyList.add(new CompanyResponseWithDetails(companyDto.getId(), companyDto.getName(), companyDto.getDescription())));
        return sumCompanyList;
    }

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company updateCompanyDetails(Company company, long id) {
        checkIfIdIsNull(id);
        Company companyResult = getCompany(id);
        companyResult.setDescription(company.getDescription());
        companyResult.setName(company.getName());
        companyRepository.save(companyResult);
        return companyResult;
    }

    @Override
    public String deleteCompany(long id) {
        checkIfIdIsNull(id);
        if (getCompany(id) != null)
            companyRepository.deleteById(id);
        return "Company with Id: " + id + " is deleted!";
    }

    @Override
    public Company getCompanyById(long id) {
        checkIfIdIsNull(id);
        return getCompany(id);
    }

    @Override
    public CompanyResponseWithDetails getCompanyByIdWithReviews(long id) {
        Company company = companyRepository.findCompanyById(id).orElseThrow(() -> new IdCanNotBeNullException("There is no review added to this company or company does not exist with id: " + id));
        List<ReviewDto> reviewDtoList = reviewClient.GetAllReviewBasedOnCompanyId(company.getId());
//        restTemplate.getForObject("http://localhost:8083/api/reviews/byCompanyId/" + company.getId(), List.class);
        CompanyResponseWithDetails compDetails = new CompanyResponseWithDetails();
        compDetails.setReviewDtoList(reviewDtoList);
        compDetails.setId(company.getId());
        compDetails.setDescription(company.getDescription());
        compDetails.setName(company.getName());
        return compDetails;
    }

    @Override
    public CompanyResponseWithDetails getCompanyByIdWithJobs(long id) {
        Company company = companyRepository.findCompanyById(id).orElseThrow(() -> new IdCanNotBeNullException("There is no job added to this company or company does not exist with id: " + id));
        List<JobDto> jobDtoList = jobClient.allJobsByCompanyId(company.getId());
//        restTemplate.getForObject("http://localhost:8081/api/jobs/byCompanyId/" + company.getId(), List.class);
        CompanyResponseWithDetails compDetails = new CompanyResponseWithDetails();
        compDetails.setJobDtoList(jobDtoList);
        compDetails.setId(company.getId());
        compDetails.setDescription(company.getDescription());
        compDetails.setName(company.getName());
        return compDetails;
    }

    @Override
    public String insertJobToCompany(long companyId, long jobId) {
        checkIfIdIsNull(companyId);
        checkIfIdIsNull(jobId);
        Company company = getCompany(companyId);
        JobDto jobDto = jobClient.findJobById(jobId);
//        restTemplate.getForObject("http://localhost:8081/api/jobs/" + jobId, JobDto.class);
        checkIfJobIsNull(jobDto);
        company.addJobToCompany(jobDto.getId());
        jobDto.setCompanyId(company.getId());
        jobClient.createNewJob(jobDto);
//        restTemplate.postForObject("http://localhost:8081/api/jobs", jobDto, JobDto.class);
        companyRepository.save(company);
        return "Job with id: " + jobId + " has been added to " + company.getName();
    }

    @Override
    public String insertReviewToCompany(long compId, ReviewDto reviewDto) {
        checkIfIdIsNull(compId);
        checkIfReviewIsNull(reviewDto);
        Company company = getCompany(compId);
        reviewDto.setCompanyId(company.getId());
        ReviewDto returnedReview = reviewClient.createNewReview(reviewDto);
//        ReviewDto returnedReview = restTemplate.postForObject("http://localhost:8083/api/reviews", reviewDto, ReviewDto.class);
        checkIfReviewIdListIsNull(company);
        checkIfReviewIsNull(returnedReview);
        company.getReviewIdList().add(returnedReview.getId());
        companyRepository.save(company);
        return "Review with id: " + returnedReview.getId() + " has been added to " + company.getName();
    }

    @Override
    public CompanyRating getCompanyWithAvgRating(long compId) {
        Company company = companyRepository.findCompanyById(compId).orElseThrow(() -> new IdCanNotBeNullException("There is no review added to this company or company does not exist with id: " + compId));
        List<ReviewDto> reviewDtoList = reviewClient.GetAllReviewBasedOnCompanyId(company.getId());
        double avgRate = reviewDtoList.stream().map(ReviewDto::getRating).mapToLong(Long::longValue).average().orElse(0.0);
        return new CompanyRating(company.getId(),company.getName(),company.getDescription(),(long) avgRate);
    }

    @Override
    public CompanyResponseWithDetails getCompanySummary(long id) {
        Company companyById = getCompanyById(id);
        return new CompanyResponseWithDetails(companyById.getId(), companyById.getName(), companyById.getDescription());
    }

    public String findCompanyByJobIdAndRemove(Long id) {
        Company company = companyRepository.findCompanyByJobId(id).orElseThrow(() -> new IdCanNotBeNullException("There is no such Job with JobId: " + id));
        company.getJobIdList().remove(id);
        companyRepository.save(company);
        return "Job with id :" + id + " has been deleted!";
    }

    public String findCompanyByReviewIdAndRemove(Long id) {
        Company company = companyRepository.findCompanyByReviewId(id).orElseThrow(() -> new IdCanNotBeNullException("There is no such Job with ReviewId: " + id));
        company.getReviewIdList().remove(id);
        companyRepository.save(company);
        return "Review with id :" + id + " has been deleted!";
    }

    private void checkIfReviewIsNull(ReviewDto reviewDto) {
        if (reviewDto == null) {
            throw new IdCanNotBeNullException("Review is null!");
        }
    }

    public Company findByReviewId(long id) {
        return companyRepository.findCompanyByReviewId(id).orElseThrow(() -> new IdCanNotBeNullException("There is no such Company with ReviewId: " + id));
    }

    private void checkIfJobIsNull(JobDto jobDto) {
        if (jobDto == null) {
            throw new IdCanNotBeNullException("Job is null!");
        }
    }

    private static void checkIfIdIsNull(long id) {
        if (id == 0) {
            throw new IdCanNotBeNullException("Id can not be 0!");
        }
    }

    private Company getCompany(long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new NoSuchModelException("No such Company with id: " + id));
    }

    private static void checkIfReviewIdListIsNull(Company company) {
        if (company.getReviewIdList() == null) {
            company.setReviewIdList(new ArrayList<>());
        }
    }


}

package com.akazimour.COMPANY_MS.company.repository;

import com.akazimour.COMPANY_MS.company.dto.CompanyDto;
import com.akazimour.COMPANY_MS.company.dto.CompanySummary;
import com.akazimour.COMPANY_MS.company.model.Company;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query(value = "SELECT c FROM Company c WHERE :id MEMBER OF c.reviewIdList")
    Optional<Company> findCompanyByReviewId(@Param("id") Long id);

    @Query(value = "SELECT c FROM Company c WHERE :id MEMBER OF c.jobIdList")
    Optional<Company> findCompanyByJobId(@Param("id") Long id);

    @Query("SELECT new Company(c.id, c.name, c.description) FROM Company c WHERE c.id = :id ")
    Optional<Company> findCompanyById(@Param("id") Long id);

    @EntityGraph(attributePaths = {"reviewIdList"})
    Optional<Company> findById(Long companyId);

    @EntityGraph(attributePaths = {"jobIdList"})
    Optional<Company> getCompanyById(Long companyId);

    @Query("SELECT new Company(c.id, c.name, c.description) FROM Company c")
    List<Company> findAllCompanies();

    //Projection solution

    //    @Query("SELECT DISTINCT c.id as id, c.name as name, c.description as description, c.reviewIdList as reviewIdList FROM Company c WHERE c.id = :id")
//    Optional<CompanySummary> findCompanyWithReviewsById(@Param("id") Long id);

    //    @Query("SELECT DISTINCT c.id as id, c.name as name, c.description as description, c.jobIdList as jobIdList FROM Company c WHERE c.id = :id")
//    Optional<CompanySummary> findCompanyWithJobsById(@Param("id") Long id);

}


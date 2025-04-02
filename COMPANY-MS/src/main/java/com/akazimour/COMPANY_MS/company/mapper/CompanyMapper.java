package com.akazimour.COMPANY_MS.company.mapper;


import com.akazimour.COMPANY_MS.company.dto.CompanyDto;
import com.akazimour.COMPANY_MS.company.model.Company;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    @Named(value = "jobSummary")
    CompanyDto CompanyToCompanyDto(Company company);

    @IterableMapping(qualifiedByName= "jobSummary")
    List<CompanyDto> CompanyListToCompanyDtoList (List<Company> companyList);

    @Named("purelyCompanies")
    @Mapping(target = "jobIdList",ignore = true)
    @Mapping(target = "reviewIdList",ignore = true)
    CompanyDto CompanyToSummaryCompanyDto(Company company);

    @IterableMapping(qualifiedByName= "purelyCompanies")
    List<CompanyDto> CompanyListToSummaryCompanyDtoList (List<Company> companyList);

    @Named("withReviews")
    @Mapping(target = "jobIdList",ignore = true)
    CompanyDto CompanywithReviewsToSummaryCompanyDto(Company company);

    @IterableMapping(qualifiedByName= "withReviews")
    List<CompanyDto> CompanyListWithReviewsToCompanyDtoList (List<Company> companyList);

    @Named("withJobs")
    @Mapping(target = "reviewIdList",ignore = true)
    CompanyDto CompanywithJobsToSummaryCompanyDto(Company company);

    @IterableMapping(qualifiedByName= "withJobs")
    List<CompanyDto> CompanyListWithJobsToCompanyDtoList (List<Company> companyList);



}

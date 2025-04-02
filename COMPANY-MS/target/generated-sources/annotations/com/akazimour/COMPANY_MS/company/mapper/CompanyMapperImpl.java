package com.akazimour.COMPANY_MS.company.mapper;

import com.akazimour.COMPANY_MS.company.dto.CompanyDto;
import com.akazimour.COMPANY_MS.company.model.Company;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-02T13:54:51+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class CompanyMapperImpl implements CompanyMapper {

    @Override
    public CompanyDto CompanyToCompanyDto(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyDto companyDto = new CompanyDto();

        companyDto.setId( company.getId() );
        companyDto.setName( company.getName() );
        companyDto.setDescription( company.getDescription() );
        List<Long> list = company.getJobIdList();
        if ( list != null ) {
            companyDto.setJobIdList( new ArrayList<Long>( list ) );
        }
        List<Long> list1 = company.getReviewIdList();
        if ( list1 != null ) {
            companyDto.setReviewIdList( new ArrayList<Long>( list1 ) );
        }

        return companyDto;
    }

    @Override
    public List<CompanyDto> CompanyListToCompanyDtoList(List<Company> companyList) {
        if ( companyList == null ) {
            return null;
        }

        List<CompanyDto> list = new ArrayList<CompanyDto>( companyList.size() );
        for ( Company company : companyList ) {
            list.add( CompanyToCompanyDto( company ) );
        }

        return list;
    }

    @Override
    public CompanyDto CompanyToSummaryCompanyDto(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyDto companyDto = new CompanyDto();

        companyDto.setId( company.getId() );
        companyDto.setName( company.getName() );
        companyDto.setDescription( company.getDescription() );

        return companyDto;
    }

    @Override
    public List<CompanyDto> CompanyListToSummaryCompanyDtoList(List<Company> companyList) {
        if ( companyList == null ) {
            return null;
        }

        List<CompanyDto> list = new ArrayList<CompanyDto>( companyList.size() );
        for ( Company company : companyList ) {
            list.add( CompanyToSummaryCompanyDto( company ) );
        }

        return list;
    }

    @Override
    public CompanyDto CompanywithReviewsToSummaryCompanyDto(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyDto companyDto = new CompanyDto();

        companyDto.setId( company.getId() );
        companyDto.setName( company.getName() );
        companyDto.setDescription( company.getDescription() );
        List<Long> list = company.getReviewIdList();
        if ( list != null ) {
            companyDto.setReviewIdList( new ArrayList<Long>( list ) );
        }

        return companyDto;
    }

    @Override
    public List<CompanyDto> CompanyListWithReviewsToCompanyDtoList(List<Company> companyList) {
        if ( companyList == null ) {
            return null;
        }

        List<CompanyDto> list = new ArrayList<CompanyDto>( companyList.size() );
        for ( Company company : companyList ) {
            list.add( CompanywithReviewsToSummaryCompanyDto( company ) );
        }

        return list;
    }

    @Override
    public CompanyDto CompanywithJobsToSummaryCompanyDto(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyDto companyDto = new CompanyDto();

        companyDto.setId( company.getId() );
        companyDto.setName( company.getName() );
        companyDto.setDescription( company.getDescription() );
        List<Long> list = company.getJobIdList();
        if ( list != null ) {
            companyDto.setJobIdList( new ArrayList<Long>( list ) );
        }

        return companyDto;
    }

    @Override
    public List<CompanyDto> CompanyListWithJobsToCompanyDtoList(List<Company> companyList) {
        if ( companyList == null ) {
            return null;
        }

        List<CompanyDto> list = new ArrayList<CompanyDto>( companyList.size() );
        for ( Company company : companyList ) {
            list.add( CompanywithJobsToSummaryCompanyDto( company ) );
        }

        return list;
    }
}

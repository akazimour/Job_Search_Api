package com.akazimour.COMPANY_MS.company.dto;

import com.akazimour.COMPANY_MS.company.external.JobDto;
import com.akazimour.COMPANY_MS.company.external.ReviewDto;

import java.util.ArrayList;
import java.util.List;

public class CompanyResponseWithDetails {
    private long id;
    private String name;
    private String description;
    List<ReviewDto> reviewDtoList;
    List<JobDto> jobDtoList;

    public CompanyResponseWithDetails() {
    }

    public CompanyResponseWithDetails(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ReviewDto> getReviewDtoList() {
        return reviewDtoList;
    }

    public void setReviewDtoList(List<ReviewDto> reviewDtoList) {
        this.reviewDtoList = reviewDtoList;
    }

    public List<JobDto> getJobDtoList() {
        return jobDtoList;
    }

    public void setJobDtoList(List<JobDto> jobDtoList) {
        this.jobDtoList = jobDtoList;
    }

    public void addReviewDtoToRevList(ReviewDto reviewDto){
        if (reviewDtoList==null){
            reviewDtoList = new ArrayList<>();
        }
        reviewDtoList.add(reviewDto);
        reviewDto.setCompanyId(this.id);
    }
    public void addJobsDtoToJobList(JobDto jobDto){
        if (jobDtoList==null){
            jobDtoList = new ArrayList<>();
        }
        jobDtoList.add(jobDto);
        jobDto.setCompanyId(this.id);
    }

    @Override
    public String toString() {
        return "CompanyResponseWithDetails{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", reviewDtoList=" + reviewDtoList +
                ", jobDtoList=" + jobDtoList +
                '}';
    }
}

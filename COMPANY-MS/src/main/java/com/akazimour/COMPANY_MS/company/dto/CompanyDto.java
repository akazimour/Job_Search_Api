package com.akazimour.COMPANY_MS.company.dto;


import java.util.List;

public class CompanyDto {

    private long id;
    private String name;
    private String description;
    private List<Long> jobIdList;
    private List<Long> reviewIdList;

    public CompanyDto() {
    }

    public CompanyDto(long id, String name, String description, List<Long> jobIdList, List<Long> reviewIdList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.jobIdList = jobIdList;
        this.reviewIdList = reviewIdList;
    }

    public CompanyDto(long id, String name, String description) {
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

    public List<Long> getJobIdList() {
        return jobIdList;
    }

    public void setJobIdList(List<Long> jobIdList) {
        this.jobIdList = jobIdList;
    }

    public List<Long> getReviewIdList() {
        return reviewIdList;
    }

    public void setReviewIdList(List<Long> reviewIdList) {
        this.reviewIdList = reviewIdList;
    }

    public static CompanyDto withReviews(long id, String name, String description, List<Long> reviewIdList) {
        CompanyDto dto = new CompanyDto();
        dto.id = id;
        dto.name = name;
        dto.description = description;
        dto.reviewIdList = reviewIdList;
        return dto;
    }


    @Override
    public String toString() {
        return "CompanyDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", jobIdList=" + jobIdList +
                ", reviewIdList=" + reviewIdList +
                '}';
    }
}

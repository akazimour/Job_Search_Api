package com.akazimour.COMPANY_MS.company.model;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Company {
@Id
@GeneratedValue
    private long id;
    private String name;
    private String description;
    @ElementCollection
    List<Long> jobIdList;
    @ElementCollection
    List<Long> reviewIdList;

    public Company() {
    }

    public Company(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public Company(long id, String name, String description, List<Long> reviewIdList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.reviewIdList = reviewIdList;
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

    public void addJobToCompany(long jobId){
        if(this.jobIdList == null){
            this.jobIdList = new ArrayList<>();
        }
        this.jobIdList.add(jobId);
    }
    public void addReviewToCompany(long reviewId){
        if(this.reviewIdList == null){
            this.reviewIdList = new ArrayList<>();
        }
        this.reviewIdList.add(reviewId);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", jobIdList=" + jobIdList +
                ", reviewIdList=" + reviewIdList +
                '}';
    }
}

package com.akazimour.COMPANY_MS.company.external;
public class JobDto {

    private long id;
    private String title;
    private String location;
    private String description;
    private JobType jobType;
    private JobCategory jobCategory;
    private long companyId;

    public JobDto() {
    }

    public JobDto(long id, String title, String location, String description, JobType jobType, JobCategory jobCategory) {
        this.id =id;
        this.title = title;
        this.location = location;
        this.description = description;
        this.jobType = jobType;
        this.jobCategory = jobCategory;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public JobCategory getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(JobCategory jobCategory) {
        this.jobCategory = jobCategory;
    }

    public long getCompanyId() {
        return companyId;
    }
    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "JobDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", jobType=" + jobType +
                ", jobCategory=" + jobCategory +
                ", companyId=" + companyId +
                '}';
    }
}


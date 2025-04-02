package com.akazimour.COMPANY_MS.company.external;

public class ReviewDto {
    private long id;
    private String title;
    private String description;
    private long rating;
    private long companyId;

    public ReviewDto() {
    }

    public ReviewDto(long id, String title, String description, long rating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "ReviewDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", companyId=" + companyId +
                '}';
    }
}

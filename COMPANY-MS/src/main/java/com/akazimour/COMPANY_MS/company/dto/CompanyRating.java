package com.akazimour.COMPANY_MS.company.dto;

public class CompanyRating {
    private long id;
    private String name;
    private String description;
    private long rating;

    public CompanyRating() {
    }

    public CompanyRating(long id, String name, String description, long rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
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

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "CompanyRating{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                '}';
    }
}

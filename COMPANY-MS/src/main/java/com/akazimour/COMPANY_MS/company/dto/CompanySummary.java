package com.akazimour.COMPANY_MS.company.dto;

import java.util.List;

public interface CompanySummary {
    long getId();
    String getName();
    String getDescription();
    List<Long> getJobIdList();
    List<Long> getReviewIdList();
}

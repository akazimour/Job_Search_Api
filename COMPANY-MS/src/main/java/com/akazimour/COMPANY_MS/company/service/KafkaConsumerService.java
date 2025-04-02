package com.akazimour.COMPANY_MS.company.service;

import com.akazimour.COMPANY_MS.company.dto.ReviewDto;
import com.akazimour.COMPANY_MS.company.external.JobDto;
import com.akazimour.COMPANY_MS.company.model.Company;
import com.akazimour.COMPANY_MS.company.repository.CompanyRepository;
import com.akazimour.COMPANY_MS.exception.NoSuchModelException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumerService {

    private final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerService.class);
    @Autowired
    CompanyRepository companyRepository;

    @KafkaListener(topics = "review-topic", groupId = "akazimour")
    public void consume(@Payload String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ReviewDto reviewDto = objectMapper.readValue(message, ReviewDto.class);
            if (companyRepository.findCompanyByReviewId(reviewDto.getId()).isPresent()) {
                System.out.println("The review has been received: " + reviewDto);
            } else {
                Company company = companyRepository.findById(reviewDto.getCompanyId()).orElseThrow(() -> new NoSuchModelException("There is no such Company with id: " + reviewDto.getCompanyId()));
                if (company.getReviewIdList()==null){
                    List<Long> revList= new ArrayList<>();
                    revList.add(reviewDto.getId());
                    company.setReviewIdList(revList);
                    companyRepository.save(company);
                }else
                    company.getReviewIdList().add(reviewDto.getId());
                companyRepository.save(company);
                LOGGER.info("The delayed review has been added from the broker!"+reviewDto);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "job-topic", groupId = "akazimour")
    public void consumeJob(@Payload String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JobDto jobDto = objectMapper.readValue(message, JobDto.class);
            if (companyRepository.findCompanyByJobId(jobDto.getId()).isPresent()) {
                System.out.println("The Job has been received: " + jobDto);
            } else {
                Company company = companyRepository.getCompanyById(jobDto.getCompanyId()).orElseThrow(() -> new NoSuchModelException("There is no such Company with id: " + jobDto.getCompanyId()));
                if (company.getJobIdList()==null){
                    List<Long> jobList= new ArrayList<>();
                    jobList.add(jobDto.getId());
                    company.setReviewIdList(jobList);
                    companyRepository.save(company);
                }else
                    company.getJobIdList().add(jobDto.getId());
                companyRepository.save(company);
                LOGGER.info("The delayed job has been added from the broker!"+jobDto);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

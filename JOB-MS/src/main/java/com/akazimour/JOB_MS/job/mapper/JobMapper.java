package com.akazimour.JOB_MS.job.mapper;

import com.akazimour.JOB_MS.job.dto.JobDto;
import com.akazimour.JOB_MS.job.model.Job;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobMapper {
    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    @Named(value = "companySummary")
    JobDto JobToJobDto(Job job);

    @IterableMapping(qualifiedByName = "companySummary")
    List<JobDto> JobListToJobDtoList(List<Job> jobList);

}

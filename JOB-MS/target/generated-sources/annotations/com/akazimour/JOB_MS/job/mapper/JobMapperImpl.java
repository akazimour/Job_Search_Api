package com.akazimour.JOB_MS.job.mapper;

import com.akazimour.JOB_MS.job.dto.JobDto;
import com.akazimour.JOB_MS.job.model.Job;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-02T21:31:25+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class JobMapperImpl implements JobMapper {

    @Override
    public JobDto JobToJobDto(Job job) {
        if ( job == null ) {
            return null;
        }

        JobDto jobDto = new JobDto();

        jobDto.setId( job.getId() );
        jobDto.setTitle( job.getTitle() );
        jobDto.setLocation( job.getLocation() );
        jobDto.setDescription( job.getDescription() );
        jobDto.setJobType( job.getJobType() );
        jobDto.setJobCategory( job.getJobCategory() );
        jobDto.setCompanyId( job.getCompanyId() );

        return jobDto;
    }

    @Override
    public List<JobDto> JobListToJobDtoList(List<Job> jobList) {
        if ( jobList == null ) {
            return null;
        }

        List<JobDto> list = new ArrayList<JobDto>( jobList.size() );
        for ( Job job : jobList ) {
            list.add( JobToJobDto( job ) );
        }

        return list;
    }
}

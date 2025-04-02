package com.akazimour.JOB_MS.job.repository;


import com.akazimour.JOB_MS.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job,Long> {
    Optional<List<Job>> findAllJobByCompanyId(@Param("id") Long id);

}

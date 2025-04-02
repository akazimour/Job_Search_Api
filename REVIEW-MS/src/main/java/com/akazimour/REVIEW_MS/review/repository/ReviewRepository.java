package com.akazimour.REVIEW_MS.review.repository;

import com.akazimour.REVIEW_MS.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    Optional<List<Review>> findAllReviewByCompanyId(@Param("id") Long id);
}

package com.akazimour.REVIEW_MS.review.service;
import com.akazimour.REVIEW_MS.review.model.Review;

import java.util.List;

public interface ReviewService {

    public Review updateReview(Review review, long id);
    public String deleteReview(long id);
    public Review getReviewById(long id);
    public Review createReview(Review review);
    public List<Review> getReviewsByCompId(long id);

}

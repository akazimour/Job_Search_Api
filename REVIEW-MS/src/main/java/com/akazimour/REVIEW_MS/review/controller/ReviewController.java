package com.akazimour.REVIEW_MS.review.controller;
import com.akazimour.REVIEW_MS.review.dto.ReviewDto;
import com.akazimour.REVIEW_MS.review.mapper.ReviewMapper;
import com.akazimour.REVIEW_MS.review.model.Review;
import com.akazimour.REVIEW_MS.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    public ReviewController(ReviewService reviewService, ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
    }
    @PostMapping
    @Operation(summary = "Create new review", description = "Create new review")
    public ReviewDto createNewReview(@RequestBody Review review){
        return reviewMapper.ReviewToReviewDto(reviewService.createReview(review));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update review", description = "Update review")
    public ReviewDto RefreshReview (@RequestBody Review review, @PathVariable long id){
     return reviewMapper.ReviewToReviewDto(reviewService.updateReview(review,id));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find review by id", description = "Find review by id")
    public ReviewDto GetReviewById (@PathVariable long id){
        return reviewMapper.ReviewToReviewDto(reviewService.getReviewById(id));
    }
    @GetMapping("/byCompanyId/{id}")
    @Operation(summary = "List of reviews by company id", description = "List of reviews by company id")
    List<ReviewDto> GetAllReviewBasedOnCompanyId(@PathVariable long id){
        return reviewMapper.ReviewListToReviewDto(reviewService.getReviewsByCompId(id));
    }

    @DeleteMapping("/{revId}")
    @Operation(summary = "Delete review", description = "Delete review")
    public String deleteReview(@PathVariable long revId){
        return reviewService.deleteReview(revId);
    }
}

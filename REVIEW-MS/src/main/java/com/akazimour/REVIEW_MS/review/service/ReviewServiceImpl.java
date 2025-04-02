package com.akazimour.REVIEW_MS.review.service;

import com.akazimour.REVIEW_MS.exception.IdCanNotBeNullException;
import com.akazimour.REVIEW_MS.exception.NoSuchModelException;
import com.akazimour.REVIEW_MS.review.clients.CompanyClient;
import com.akazimour.REVIEW_MS.review.dto.ReviewDto;
import com.akazimour.REVIEW_MS.review.dto.ReviewMessage;
import com.akazimour.REVIEW_MS.review.model.Review;
import com.akazimour.REVIEW_MS.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    //    RestTemplate restTemplate = new RestTemplate();
    @Autowired
    CompanyClient companyClient;
    @Autowired
    KafkaProducerService kafkaProducerService;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review updateReview(Review review, long id) {
        checkIfIdIsNull(id);
        Review searchedReview = getReview(id);
        searchedReview.setRating(review.getRating());
        searchedReview.setDescription(review.getDescription());
        searchedReview.setTitle(review.getTitle());
        return reviewRepository.save(searchedReview);
    }

    @Override
    public String deleteReview(long id) {
        checkIfIdIsNull(id);
        if (getReviewById(id) != null)
            reviewRepository.deleteById(id);
        companyClient.deleteReviewItem(id);
//        restTemplate.delete("http://localhost:8082/api/companies/remove/review/" + id, String.class);
        return "Review successfully deleted with id: " + id;
    }

    @Override
    public Review getReviewById(long id) {
        checkIfIdIsNull(id);
        return getReview(id);
    }

    @Override
    public Review createReview(Review review) {
        if (review == null) {
            throw new IdCanNotBeNullException("Review is null!");
        }
        Review savedReview = reviewRepository.save(review);
        ReviewDto messageDto = new ReviewDto();
        messageDto.setCompanyId(savedReview.getCompanyId());
        messageDto.setId(savedReview.getId());
        messageDto.setTitle(savedReview.getTitle());
        messageDto.setDescription(savedReview.getDescription());
        messageDto.setRating(savedReview.getRating());
        kafkaProducerService.sendMessage("review-topic", UUID.randomUUID(), messageDto);
        return savedReview;
    }

    public List<Review> getReviewsByCompId(long id) {
        return reviewRepository.findAllReviewByCompanyId(id).orElseGet(ArrayList::new);
    }

    private Review getReview(long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new NoSuchModelException("No such Review with id: " + id));
    }

    private static void checkIfIdIsNull(long id) {
        if (id == 0) {
            throw new IdCanNotBeNullException("Id can not be 0!");
        }
    }
}

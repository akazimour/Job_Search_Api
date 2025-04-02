package com.akazimour.REVIEW_MS.review.mapper;

import com.akazimour.REVIEW_MS.review.dto.ReviewDto;
import com.akazimour.REVIEW_MS.review.model.Review;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-02T13:47:30+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public ReviewDto ReviewToReviewDto(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId( review.getId() );
        reviewDto.setTitle( review.getTitle() );
        reviewDto.setDescription( review.getDescription() );
        reviewDto.setRating( review.getRating() );
        reviewDto.setCompanyId( review.getCompanyId() );

        return reviewDto;
    }

    @Override
    public List<ReviewDto> ReviewListToReviewDto(List<Review> reviews) {
        if ( reviews == null ) {
            return null;
        }

        List<ReviewDto> list = new ArrayList<ReviewDto>( reviews.size() );
        for ( Review review : reviews ) {
            list.add( ReviewToReviewDto( review ) );
        }

        return list;
    }
}

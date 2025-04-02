package com.akazimour.REVIEW_MS.review.mapper;
import com.akazimour.REVIEW_MS.review.dto.ReviewDto;
import com.akazimour.REVIEW_MS.review.model.Review;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    ReviewDto ReviewToReviewDto(Review review);

    List<ReviewDto> ReviewListToReviewDto(List<Review>reviews);
}

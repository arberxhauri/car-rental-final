package com.example.carrental.mapper;

import com.example.carrental.dto.ReviewDto;
import com.example.carrental.entity.Review;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReviewMapper {

    private CarMapper carMapper;
    private CostumerMapper costumerMapper;

    public Review mapToEntity(ReviewDto reviewDto){
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setDescription(reviewDto.getDescription());
        review.setStars(reviewDto.getStars());

        return review;
    }

    public ReviewDto mapToDto(Review review){
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setDescription(review.getDescription());
        reviewDto.setStars(review.getStars());
        reviewDto.setCarId(review.getCar().getId());
        reviewDto.setCarDto(carMapper.mapToDto(review.getCar()));
        reviewDto.setCostumerId(review.getCostumer().getId());
        reviewDto.setCostumerDto(costumerMapper.mapToDto(review.getCostumer()));

        return reviewDto;
    }
}

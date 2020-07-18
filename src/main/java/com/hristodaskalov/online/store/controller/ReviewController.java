package com.hristodaskalov.online.store.controller;

import com.hristodaskalov.online.store.dto.ReviewDto;
import com.hristodaskalov.online.store.model.Review;
import com.hristodaskalov.online.store.service.ReviewService;
import com.hristodaskalov.online.store.utils.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items/{itemId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@PathVariable("itemId") Long itemId, @RequestBody ReviewDto reviewDto) {
        Review review = ObjectConverter.convertObject(reviewDto, Review.class);
        review = reviewService.createReview(itemId, review);
        return new ResponseEntity<>(ObjectConverter.convertObject(review, ReviewDto.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable("itemId") Long itemId,
                                                  @PathVariable("id") Long id,
                                                  @RequestBody ReviewDto reviewDto) {

        Review review = ObjectConverter.convertObject(reviewDto, Review.class);
        review = reviewService.updateReview(itemId, id, review);
        return new ResponseEntity<>(ObjectConverter.convertObject(review, ReviewDto.class), HttpStatus.OK);
    }

    @GetMapping
    public List<ReviewDto> getAllItemReviews(@PathVariable("itemId") Long itemId) {
        List<Review> itemReviews = reviewService.getItemReviewsByItemId(itemId);
        return ObjectConverter.convertList(itemReviews, ReviewDto.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteReview(@PathVariable("itemId") Long itemId, @PathVariable("id")Long id) {
        reviewService.deleteReview(itemId, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

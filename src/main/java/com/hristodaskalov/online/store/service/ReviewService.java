package com.hristodaskalov.online.store.service;

import com.hristodaskalov.online.store.model.Review;

import java.util.List;

public interface ReviewService {

    Review createReview(Long itemId, Review review);

    Review updateReview(Long itemId, Long id, Review newReview);

    Review getReviewByIdAndItemId(Long itemId, Long id);

    List<Review> getItemReviewsByItemId(Long itemId);

    void deleteReview(Long itemId, Long id);
}

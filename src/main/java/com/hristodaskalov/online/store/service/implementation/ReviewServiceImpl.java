package com.hristodaskalov.online.store.service.implementation;

import com.hristodaskalov.online.store.entity.ReviewEntity;
import com.hristodaskalov.online.store.exception.InvalidInputException;
import com.hristodaskalov.online.store.model.Review;
import com.hristodaskalov.online.store.repository.ReviewRepository;
import com.hristodaskalov.online.store.service.ItemService;
import com.hristodaskalov.online.store.service.ReviewService;
import com.hristodaskalov.online.store.utils.Constants;
import com.hristodaskalov.online.store.utils.ObjectConverter;
import com.hristodaskalov.online.store.utils.Validation;

import javax.transaction.Transactional;
import java.util.List;

public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ItemService itemService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ItemService itemService) {
        this.reviewRepository = reviewRepository;
        this.itemService = itemService;
    }

    @Override
    @Transactional
    public Review createReview(Long itemId, Review review) {

        //TODO see what happens if you pass null
        validateFields(review);
        review.setItem(
                itemService.getItem(itemId)
        );

        ReviewEntity reviewEntity = ObjectConverter.convertObject(review, ReviewEntity.class);
        reviewEntity = reviewRepository.save(reviewEntity);

        return ObjectConverter.convertObject(reviewEntity, Review.class);
    }

    @Override
    @Transactional
    public Review updateReview(Long itemId, Long id, Review newReview) {

        Review existingReview = getReviewByIdAndItemId(itemId, id);

        validateFields(newReview);
        updateFieldsIfValid(existingReview, newReview);

        ReviewEntity reviewEntity = reviewRepository.save(
                ObjectConverter.convertObject(existingReview, ReviewEntity.class)
        );

        return ObjectConverter.convertObject(reviewEntity, Review.class);
    }

    @Override
    public Review getReviewByIdAndItemId(Long itemId, Long id) {
        ReviewEntity reviewEntity = reviewRepository.findByIdAndItemId(id, itemId).orElseThrow(
                ()-> new InvalidInputException(String.format("Review with id: %d does not exist.", id))
        );

        return ObjectConverter.convertObject(reviewEntity, Review.class);
    }

    @Override
    public List<Review> getItemReviewsByItemId(Long itemId) {
        List<ReviewEntity> itemReviewList = reviewRepository.findAllByItemId(itemId);
        return ObjectConverter.convertList(itemReviewList, Review.class);
    }

    @Override
    @Transactional
    public void deleteReview(Long itemId, Long id) {
        Review review = getReviewByIdAndItemId(itemId, id);
        reviewRepository.delete(
                ObjectConverter.convertObject(review, ReviewEntity.class)
        );
    }

    private void validateFields(Review review) {
        validateRating(review.getRating());
        validateContent(review.getContent());
    }

    private void validateRating(Float rating) {

        Validation.isValidPositiveNumber(rating.toString());
        if (rating < Constants.RATING_MIN_VALUE || rating > Constants.RATING_MAX_VALUE) {
            throw new InvalidInputException(
                    String.format("Rating must be between %.2f and %.2f",
                            Constants.RATING_MIN_VALUE,
                            Constants.RATING_MAX_VALUE
                    )
            );
        }
    }

    private void validateContent(String content) {
        if (content != null) {
            Validation.fieldHasValidLength(content, Constants.CONTENT_FIELD_MAX_LENGTH, "content");
        }
    }

    private void updateFieldsIfValid(Review existingReview, Review newReview) {

        if (!existingReview.getRating().equals(newReview.getRating())) {
            existingReview.setRating(newReview.getRating());
        }
        if (Validation.isSameAsExisting(existingReview.getContent(), newReview.getContent())) {
            existingReview.setContent(newReview.getContent());
        }
    }
}
package com.api.emotibook.service;

import com.api.emotibook.model.Rating;

import java.util.List;

public interface RatingService {

    Rating saveRating(Rating rating);

    List<Rating> findByUserId(Long userId);

    List<Rating> findByPostId(Long postId);
}

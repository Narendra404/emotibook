package com.api.emotibook.serviceImpl;

import com.api.emotibook.model.Post;
import com.api.emotibook.model.Rating;
import com.api.emotibook.repository.PostRepository;
import com.api.emotibook.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecommendationService {

    private final RatingRepository ratingRepository;
    private final PostRepository postRepository;

    @Autowired
    public RecommendationService(RatingRepository ratingRepository, PostRepository postRepository) {
        this.ratingRepository = ratingRepository;
        this.postRepository = postRepository;
    }

    public List<Post> recommendPosts(Long userId) {
        // Get all ratings given by the user
        List<Rating> userRatings = ratingRepository.findByUserId(userId);

        // Calculate recommendations using Slope One algorithm
        Map<Long, Double> recommendations = calculateRecommendations(userRatings);

        // Get top recommended posts
        List<Long> recommendedPostIds = getTopRecommendedPostIds(recommendations);

        // Retrieve recommended posts from the database
        return postRepository.findAllById(recommendedPostIds);
    }

    private Map<Long, Double> calculateRecommendations(List<Rating> userRatings) {
        Map<Long, Map<Long, Integer>> frequencyMatrix = new HashMap<>();
        Map<Long, Map<Long, Double>> deviationMatrix = new HashMap<>();

        // Step 1: Calculate deviations and frequencies
        for (Rating rating : userRatings) {
            Long userId = rating.getId();
            Long postId = rating.getPost().getId();
            int score = rating.getSentimentScore();

            for (Rating otherRating : ratingRepository.findByPostId(postId)) {
                Long otherUserId = otherRating.getUser().getId();
                Long otherPostId = otherRating.getPost().getId();
                int otherScore = otherRating.getSentimentScore();

                if (!userId.equals(otherUserId)) {
                    frequencyMatrix.putIfAbsent(userId, new HashMap<>());
                    frequencyMatrix.get(userId).putIfAbsent(otherUserId, 0);
                    frequencyMatrix.get(userId).put(otherUserId, frequencyMatrix.get(userId).get(otherUserId) + 1);

                    deviationMatrix.putIfAbsent(userId, new HashMap<>());
                    deviationMatrix.get(userId).putIfAbsent(otherUserId, 0.0);
                    deviationMatrix.get(userId).put(otherUserId,
                            deviationMatrix.get(userId).get(otherUserId) + (score - otherScore));
                }
            }
        }

        // Step 2: Calculate recommendations
        Map<Long, Double> recommendations = new HashMap<>();
        for (Map.Entry<Long, Map<Long, Double>> entry : deviationMatrix.entrySet()) {
            Long userId = entry.getKey();
            Map<Long, Double> userDeviations = entry.getValue();

            for (Map.Entry<Long, Double> devEntry : userDeviations.entrySet()) {
                Long otherUserId = devEntry.getKey();
                Double deviationSum = devEntry.getValue();
                int frequency = frequencyMatrix.get(userId).get(otherUserId);

                double recommendation = deviationSum / frequency;
                recommendations.put(otherUserId, recommendations.getOrDefault(otherUserId, 0.0) + recommendation);
            }
        }

        return recommendations;
    }

    private List<Long> getTopRecommendedPostIds(Map<Long, Double> recommendations) {
        // Sort recommendations and get top recommended post IDs
        // This step depends on how you want to sort and filter recommendations
        // For example, you could select top N recommendations or apply a threshold
        // Here, we'll just return all recommended user IDs
        return new ArrayList<>(recommendations.keySet());
    }
}

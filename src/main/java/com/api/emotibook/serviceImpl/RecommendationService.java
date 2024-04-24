package com.api.emotibook.serviceImpl;

import com.api.emotibook.model.Post;
import com.api.emotibook.model.User;
import com.api.emotibook.repository.PostRepository;
import com.api.emotibook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

    @Service
    public class RecommendationService {

        private final UserRepository userRepository;
        private final PostRepository postRepository;

        @Autowired
        public RecommendationService(UserRepository userRepository, PostRepository postRepository) {
            this.userRepository = userRepository;
            this.postRepository = postRepository;
        }

        public Map<String, Double> getRecommendations(Long userId) {
            Map<String, Double> recommendations = new HashMap<>();

            // Get the user's posts and sentiment scores
            User user = userRepository.findById(userId).orElse(null);
            if (user != null) {
                for (Post post : user.getPosts()) {
                    // Use sentiment score of each post to calculate recommendations
                    Map<String, Double> postRecommendations = calculatePostRecommendations(post);
                    recommendations.putAll(postRecommendations);
                }
            }

            return recommendations;
        }

        private Map<String, Double> calculatePostRecommendations(Post post) {
            // Implement Slope One algorithm or any other recommendation algorithm here
            // For demonstration purposes, let's return hardcoded recommendations
            Map<String, Double> recommendations = new HashMap<>();
            recommendations.put("recommended_item1", 4.5);
            recommendations.put("recommended_item2", 4.0);
            recommendations.put("recommended_item3", 3.5);
            return recommendations;
        }
    }
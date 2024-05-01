package com.api.emotibook.serviceImpl;

import com.api.emotibook.model.Post;
import com.api.emotibook.repository.PostRepository;
import com.api.emotibook.repository.UserRepository;
import com.api.emotibook.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final SentimentService sentimentService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, SentimentService sentimentService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.sentimentService = sentimentService;
    }

    @Override
    public Post createPost(Post post) {
        String sentiment = sentimentService.tokenizeText(post.getContent());
        post.setSentiment(sentiment);
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public Post updatePost(Long id, Post newPost) {
        return postRepository.findById(id).map(post -> {
            post.setLikes(newPost.getLikes());
            return postRepository.save(post);
        }).orElse(null);
    }

    @Override
    public String deletePost(Long id) {
        postRepository.deleteById(id);
        return "Post with id : " + id + " is deleted successfully";
    }

    public void deletePostByUserId(Long userId) {

        List<Post> posts = getPostsByUserId(userId);
        for (Post post : posts) {
            deletePost(post.getId());
        }
    }

    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findPostsByUser(userRepository.findById(userId).orElse(null));
    }
}

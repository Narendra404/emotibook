package com.api.emotibook.service;

import com.api.emotibook.model.Post;

import java.util.List;

public interface PostService {

    Post createPost(Post post);
    List<Post> getAllPosts();
    Post getPostById(Long id);
    Post updatePost(Long id, Post post);
    String deletePost(Long id);
}

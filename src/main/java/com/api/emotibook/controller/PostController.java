package com.api.emotibook.controller;

import com.api.emotibook.model.Post;
import com.api.emotibook.serviceImpl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class PostController {

    private final PostServiceImpl postServiceImpl;

    @Autowired
    public PostController(PostServiceImpl postServiceImpl) {
        this.postServiceImpl = postServiceImpl;
    }

    @PostMapping("/post")
    Post newPost(@RequestBody Post post) {
        return postServiceImpl.createPost(post);
    }

    @GetMapping("/posts/{userId}")
    List<Post> getAllPostsByUserId(@PathVariable Long userId) {
        return postServiceImpl.getPostsByUserId(userId);
    }

    @GetMapping("/posts")
    List<Post> getAllPosts() {
        return postServiceImpl.getAllPosts();
    }
    @GetMapping("/post/{id}")
    Post getPostById(@PathVariable Long id) {
        return postServiceImpl.getPostById(id);
    }

    @PutMapping("/post/{id}")
    Post updatePost(@RequestBody Post newPost, @PathVariable Long id) {
        return postServiceImpl.updatePost(id, newPost);
    }

    @DeleteMapping("/post/{id}")
    String deletePost(@PathVariable Long id) {
        return postServiceImpl.deletePost(id);
    }

    @DeleteMapping("/postdelete/{userId}")
    void deletePostByUserId(@PathVariable Long userId) {
        postServiceImpl.deletePostByUserId(userId);
    }
}

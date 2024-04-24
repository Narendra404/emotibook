package com.api.emotibook.repository;

import com.api.emotibook.model.Post;
import com.api.emotibook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostsByUser(User user);
}

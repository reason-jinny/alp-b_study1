package com.kt.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt.blog.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
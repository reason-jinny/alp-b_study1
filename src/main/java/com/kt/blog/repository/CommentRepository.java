package com.kt.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt.blog.model.Comment;
import com.kt.blog.model.Post;

public interface CommentRepository extends JpaRepository<Comment, Long> { // JpaRepository<Comment, Long>: Comment 엔티티의 기본 CRUD 기능을 제공

    List<Comment> findByPost(Post post); // 특정 게시글(Post)에 속한 모든 댓글 조회
}

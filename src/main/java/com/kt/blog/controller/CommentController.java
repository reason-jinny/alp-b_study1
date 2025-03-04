package com.kt.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.blog.model.Comment;
import com.kt.blog.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성 API
    @PostMapping
    public ResponseEntity<Comment> addComment(
        @PathVariable Long postId, 
        @RequestBody Comment comment) {
        Comment savedComment = commentService.addComment(postId, comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }

    // 특정 게시글의 모든 댓글 조회 API
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments(@PathVariable Long postId) {
        List<Comment> comments = commentService.getAllComments(postId);
        return ResponseEntity.ok(comments);
    }

    // 특정 게시글의 특정 댓글 조회 API
    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getCommentById(
            @PathVariable Long postId,
            @PathVariable Long commentId) {
        Comment comment = commentService.getCommentById(postId, commentId);
        return ResponseEntity.ok(comment);
    }

    // 댓글 삭제 API
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("댓글 삭제 완료");
    }
}
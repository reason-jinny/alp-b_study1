package com.kt.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kt.blog.dto.CommentDTO;
import com.kt.blog.model.Comment;
import com.kt.blog.model.Post;
import com.kt.blog.repository.CommentRepository;
import com.kt.blog.repository.PostRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    // 댓글 작성
    public Comment addComment(Long postId, Comment comment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID의 게시글을 찾을 수 없습니다: " + postId));

            comment.setPost(post); // 댓글과 게시글 연관관계 설정
            return commentRepository.save(comment);
    };

    // 특정 게시글의 모든 댓글 조회
    public List<Comment> getAllComments(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID의 게시글을 찾을 수 없습니다: " + postId));
        
        return commentRepository.findByPost(post);
    }

    // 특정 게시글의 특정 댓글 조회
    public Comment getCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID의 게시글을 찾을 수 없습니다: " + postId));

        return commentRepository.findByCommentIdAndPost(commentId, post)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID의 댓글을 찾을 수 없습니다: " + commentId));
    }

    // 댓글 삭제
    public void deleteComment(Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID의 댓글을 찾을 수 없습니다: " + commentId));
    
        commentRepository.delete(comment);
    }

    // 변환 메서드 추가
    public CommentDTO convertToDTO(Comment comment) {
        return new CommentDTO(
            comment.getCommentId(), 
            comment.getCommentContent(), 
            comment.getCommentAuthor()
        );
    }
}
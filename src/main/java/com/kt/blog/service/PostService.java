package com.kt.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kt.blog.exception.EntityNotFoundException;
import com.kt.blog.model.Post;
import com.kt.blog.repository.PostRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor // 모든 필드를 포함하는 생성자 자동 생성
public class PostService {

    public final PostRepository postRepository; // Lombok이 생성자를 자동 생성해줌

    // 글 작성(추가)
    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    // 모든 글 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 특정 ID의 글 조회
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID의 글을 찾을 수 없습니다: " + id));
    }   

    // 특정 ID의 글 수정
    public Post updatePost(Long id, Post updatedPost) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID의 글을 찾을 수 없습니다: " + id));

        post.setTitle(updatedPost.getTitle());
        post.setContent(updatedPost.getContent());
        post.setAuthor(updatedPost.getAuthor());

        return postRepository.save(post);
    }

    // 특정 ID의 글 삭제
    public boolean deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new EntityNotFoundException("해당 ID의 글을 찾을 수 없습니다: " + id);
        }
        postRepository.deleteById(id);
        return true;    
    }
}
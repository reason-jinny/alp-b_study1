package com.kt.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kt.blog.exception.EntityNotFoundException;
import com.kt.blog.model.Post;
import com.kt.blog.repository.PostRepository;

@Service
public class PostService {

    // private List<Post> posts = new ArrayList<>(); // JPA 사용 시 필요 없음
    // private int nextId = 1; // ID 자동 증가 필요 없음 (JPA가 자동으로 관리)

    public final PostRepository postRepository; // 이제 JPA를 사용하므로, DB에서 데이터 관리

    // 생성자에서 PostRepository를 받아서 초기화
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 글 작성(추가)
    public Post addPost(Post post) {
        return postRepository.save(post); // JPA가 자동으로 ID를 생성하고 DB에 저장
    }

    // 모든 글 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();   // DB에서 모든 데이터 가져오기
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
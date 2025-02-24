package com.kt.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
        return postRepository.findById(id).orElse(null); // JPA가 자동으로 특정 ID 검색
    }   

    // 특정 ID의 글 수정
    public Post updatePost(Long id, Post updatedPost) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get(); // Optional에서 Post 객체 가져오기
            post.setTitle(updatedPost.getTitle());
            post.setContent(updatedPost.getContent());
            post.setAuthor(updatedPost.getAuthor());
            return postRepository.save(post); // 수정된 내용 DB에 반영
        }
        return null;
    }

    // 특정 ID의 글 삭제
    public boolean deletePost(Long id) {
        if (postRepository.existsById(id)) { // 해당 ID가 존재하는지 확인
            postRepository.deleteById(id); // DB에서 삭제
            return true;
        }
        return false;    
    }
}

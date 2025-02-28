package com.kt.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.blog.exception.EntityNotFoundException;
import com.kt.blog.model.Post;
import com.kt.blog.service.PostService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor // 모든 필드를 생성하는 생성자 자동 생성
public class PostController {

    private final PostService postService; // Lombok이 생성자를 자동 생성해줌

    @PostMapping // 글 작성(추가) API
    public ResponseEntity<Post> addPost(@RequestBody Post post) { // @RequestBody Post post: 사용자가 보낸 JSON 데이터를 Post 객체로 변환
        Post savedPost = postService.addPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost); // `201 Created` 응답 반환
    }

    @GetMapping // 모든 글 조회 API
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}") // 특정 ID의 글 조회 API
    public ResponseEntity<Post> getPostById(@PathVariable Long id) { // @PathVariable int id: URL에서 {id} 값을 가져와서 id 변수에 저장
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(post); // 글이 없으면 null 반환
    }

    @PutMapping("/{id}") // 특정 ID의 글 수정 API
    public ResponseEntity<String> updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        postService.updatePost(id, updatedPost);
        return ResponseEntity.ok("수정 완료");
    }

    @DeleteMapping("/{id}") // 특정 ID의 글 삭제 API
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok("삭제 완료");
    }

    @ExceptionHandler(EntityNotFoundException.class) // 예외 핸들러
    public ResponseEntity<String> handleNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}